package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;

/**
 * Created by Randil Fernando on 11/19/2016.
 */

public class DBHelper extends SQLiteOpenHelper implements Serializable {

    public static final int database_version = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String REAL_TYPE = " REAL";
    private static final String INT_TYPE = " INT";
    private static final String COMMA_SEP = ",";

    /* AccountTable SQL queries */
    private static final String CREATE_ACCOUNT_ENTRIES =
            "CREATE TABLE " + DBSchema.AccountTable.TABLE_NAME + " (" +
                    DBSchema.AccountTable.ACCOUNT_NUMBER + TEXT_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    DBSchema.AccountTable.BANK_NAME + TEXT_TYPE + COMMA_SEP +
                    DBSchema.AccountTable.ACCOUNT_HOLDER_NAME + TEXT_TYPE + COMMA_SEP +
                    DBSchema.AccountTable.BALANCE + REAL_TYPE + ")";

    private static final String DELETE_ACCOUNT_ENTRIES =
            "DROP TABLE IF EXISTS " + DBSchema.AccountTable.TABLE_NAME;

    /* TransactionTable SQL queries */
    private static final String CREATE_TRANSACTION_ENTRIES =
            "CREATE TABLE " + DBSchema.TransactionTable.TABLE_NAME + " (" +
                    DBSchema.TransactionTable.TRANSACTION_DATE + TEXT_TYPE + " PRIMARY KEY" + COMMA_SEP +
                    DBSchema.TransactionTable.AMOUNT + REAL_TYPE + COMMA_SEP +
                    DBSchema.TransactionTable.EXPENSE_TYPE + TEXT_TYPE + COMMA_SEP +
                    DBSchema.TransactionTable.ACCOUNT_NUMBER + TEXT_TYPE + COMMA_SEP +
                    "FOREIGN KEY (" + DBSchema.AccountTable.ACCOUNT_NUMBER + ") REFERENCES " +
                    DBSchema.AccountTable.TABLE_NAME + "(" + DBSchema.AccountTable.ACCOUNT_NUMBER + "))";

    private static final String DELETE_TRANSACTION_ENTRIES =
            "DROP TABLE IF EXISTS " + DBSchema.TransactionTable.TABLE_NAME;

    public DBHelper(Context context) {
        super(context, DBSchema.DATABASE_NAME, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ACCOUNT_ENTRIES);
        db.execSQL(CREATE_TRANSACTION_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_ACCOUNT_ENTRIES);
        db.execSQL(DELETE_TRANSACTION_ENTRIES);
        onCreate(db);
    }
}