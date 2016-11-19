package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Transaction;

/**
 * Created by Randil Fernando on 11/19/2016.
 */

public class PersistanceTransactionDAO implements TransactionDAO,Serializable {
    private SQLiteOpenHelper helper;

    public PersistanceTransactionDAO(Context context) {
        helper = new DBHelper(context);
    }

    @Override
    public void logTransaction(Date date, String accountNo, ExpenseType expenseType, double amount) {

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql_transaction = String.format("INSERT OR IGNORE INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
                DBSchema.TransactionTable.TABLE_NAME,
                DBSchema.TransactionTable.TRANSACTION_DATE,
                DBSchema.TransactionTable.ACCOUNT_NUMBER,
                DBSchema.TransactionTable.EXPENSE_TYPE,
                DBSchema.TransactionTable.AMOUNT);

        db.execSQL(sql_transaction, new Object[]{
                date.getTime(),
                accountNo,
                expenseType,
                amount});
    }

    @Override
    public List<Transaction> getAllTransactionLogs() {

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql_transaction = String.format("SELECT %s, %s, %s, %s FROM %s)",
                DBSchema.TransactionTable.TRANSACTION_DATE,
                DBSchema.TransactionTable.ACCOUNT_NUMBER,
                DBSchema.TransactionTable.EXPENSE_TYPE,
                DBSchema.TransactionTable.AMOUNT,
                DBSchema.TransactionTable.TABLE_NAME);

        List<Transaction> transactions = new ArrayList<>();
        final Cursor cursor = db.rawQuery(sql_transaction, null);

        if (cursor.moveToFirst()) {
            do {
                transactions.add(new Transaction(
                        new Date(cursor.getLong(0)),
                        cursor.getString(1),
                        Enum.valueOf(ExpenseType.class, cursor.getString(2)),
                        cursor.getDouble(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return transactions;
    }

    @Override
    public List<Transaction> getPaginatedTransactionLogs(int limit) {

        SQLiteDatabase db = helper.getWritableDatabase();

        String sql_transaction = String.format("SELECT %s, %s, %s, %s FROM %s LIMIT %s",
                DBSchema.TransactionTable.TRANSACTION_DATE,
                DBSchema.TransactionTable.ACCOUNT_NUMBER,
                DBSchema.TransactionTable.EXPENSE_TYPE,
                DBSchema.TransactionTable.AMOUNT,
                DBSchema.TransactionTable.TABLE_NAME,
                limit);

        List<Transaction> results = new ArrayList<>();
        final Cursor cursor = db.rawQuery(sql_transaction, null);

        if (cursor.moveToFirst()) {
            do {
                results.add(new Transaction(
                        new Date(cursor.getLong(0)),
                        cursor.getString(1),
                        Enum.valueOf(ExpenseType.class, cursor.getString(2)),
                        cursor.getDouble(3)));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return results;
    }
}
