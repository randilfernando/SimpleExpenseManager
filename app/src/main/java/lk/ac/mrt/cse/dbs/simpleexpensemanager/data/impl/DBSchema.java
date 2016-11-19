/*
 * Copyright 2015 Department of Computer Science and Engineering, University of Moratuwa.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *                  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.provider.BaseColumns;

import java.io.Serializable;

/**
 * Created by Randil Fernando on 11/19/2016.
 */

final class DBSchema implements Serializable {

    static final String DATABASE_NAME = "140161F";

    private DBSchema() {}

    static class AccountTable implements BaseColumns {
        static final String ACCOUNT_NUMBER = "acc_no";
        static final String BANK_NAME = "bank";
        static final String ACCOUNT_HOLDER_NAME = "holder";
        static final String BALANCE = "balance";
        static final String TABLE_NAME = "account_table";
    }

    static class TransactionTable implements BaseColumns {
        static final String TRANSACTION_DATE = "date";
        static final String AMOUNT = "amount";
        static final String EXPENSE_TYPE = "type";
        static final String ACCOUNT_NUMBER = "acc_no";
        static final String TABLE_NAME = "transaction_table";
    }
}
