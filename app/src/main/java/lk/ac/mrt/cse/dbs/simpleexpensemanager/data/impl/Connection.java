package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.nio.channels.FileChannel;

/**
 * Created by Randil Fernando on 11/19/2016.
 */

final class Connection {
    private static SQLiteDatabase mydatabase;

    private Connection(){}

    static SQLiteDatabase getDatabase() {
        if (mydatabase == null){
            mydatabase = SQLiteDatabase.openOrCreateDatabase("140161F.db",null);
        }
        return mydatabase;
    }

}
