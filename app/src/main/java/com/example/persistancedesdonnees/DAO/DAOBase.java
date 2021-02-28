package com.example.persistancedesdonnees.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import com.example.persistancedesdonnees.AccountDBHelper;

public abstract class DAOBase {
    protected final static int VERSION = 1;
    protected final static String BASE_NOM = "user.db";

    protected SQLiteDatabase mDb = null;
    protected AccountDBHelper mHandler = null;

    public DAOBase (Context pContext){
        this.mHandler = new AccountDBHelper(pContext, BASE_NOM, null, VERSION);
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb(EditText username) {
        return mDb;
    }

}
