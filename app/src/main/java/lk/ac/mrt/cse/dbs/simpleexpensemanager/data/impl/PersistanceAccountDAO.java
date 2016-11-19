package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;

/**
 * Created by Randil Fernando on 11/19/2016.
 */

public class PersistanceAccountDAO implements AccountDAO {
    private SQLiteDatabase database;

    public PersistanceAccountDAO() {
        this.database = Connection.getDatabase();
    }

    @Override
    public List<String> getAccountNumbersList() {
        List<String> numbers = new ArrayList<String>();
        String selectQuary = "SELECT acc_id FROM account;";
        Cursor cursor = database.rawQuery(selectQuary,null);

        if (cursor.moveToFirst()){
            do{
                String temp = cursor.getString(0);
                numbers.add(temp);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return numbers;
    }

    @Override
    public List<Account> getAccountsList() {
        List<Account> accounts = new ArrayList<Account>();
        String selectQuary = "SELECT * FROM account;";
        Cursor cursor = database.rawQuery(selectQuary,null);

        if (cursor.moveToFirst()){
            do{
                Account temp = new Account(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                        Double.parseDouble(cursor.getString(3)));
                accounts.add(temp);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return accounts;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        Account account = null;
        String selectQuary = "SELECT * from account WHERE acc_id = " + accountNo + ";";
        Cursor cursor = database.rawQuery(selectQuary,null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            Account temp = new Account(cursor.getString(0),cursor.getString(1),cursor.getString(2),
                    Double.parseDouble(cursor.getString(3)));
            return temp;
        }
        String msg = "Account " + accountNo + " is invalid.";
        throw new InvalidAccountException(msg);
    }

    @Override
    public void addAccount(Account account) {
        String addQuary = "INSERT INTO account VALUES(" +
                account.getAccountNo() + "," +
                account.getBankName() + "," +
                account.getAccountHolderName() + "," +
                account.getBalance() + ");";
        try {
            database.execSQL(addQuary);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {

    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {

    }
}
