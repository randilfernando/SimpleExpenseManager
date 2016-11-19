package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistanceAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistanceTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;

/**
 * Created by Randil Fernando on 11/19/2016.
 */

public class PersistanceDemoExpenseManager extends ExpenseManager{

    public PersistanceDemoExpenseManager() {
        setup();
    }

    @Override
    public void setup() {
        /*** Begin generating dummy data for In-Memory implementation ***/

        TransactionDAO PersistanceTransactionDAO = new PersistanceTransactionDAO();
        setTransactionsDAO(PersistanceTransactionDAO);

        AccountDAO PersistanceAccountDAO = new PersistanceAccountDAO();
        setAccountsDAO(PersistanceAccountDAO);
    }
}
