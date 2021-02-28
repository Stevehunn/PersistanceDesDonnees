package com.example.persistancedesdonnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountDBHelper extends SQLiteOpenHelper {
    /**
     * Les champs nécessaires à la création de la base de données
     */
    private static final String TABLE_USER = "table_user";

    public static final String COLONNE_ID_ENREGISTREMENT = "id_enregistrement";
    public static final int COLONNE_ID_ENREGISTREMENT_ID = 0;
    public static final String COLONNE_USERNAME = "username";
    public static final int COLONNE_USERNAME_ID = 1;
    public static final String COLONNE_EMAIL = "email";
    public static final int COLONNE_EMAIL_ID = 2;
    public static final String COLONNE_PHONE = "phone";
    public static final int COLONNE_PHONE_ID = 3;
    public static final String COLONNE_PASSWORD = "password";
    public static final int COLONNE_PASSWORD_ID = 4;

    /**
     * La requête de création de la structure de la base de données.
     */
    private static final String REQUETE_CREATION_BD = "create table " + TABLE_USER + " ("
            + COLONNE_ID_ENREGISTREMENT + " integer primary key autoincrement, "
            + COLONNE_USERNAME + " text not null, "
            + COLONNE_EMAIL + " text not null, "
            + COLONNE_PHONE + " text not null, "
            + COLONNE_PASSWORD + " text not null " + ");";

    /**
     * La requête de suppression de la structure de la base de données.
     */
    public static final String REQUETE_DROP_BD = "DROP TABLE IF EXISTS " + TABLE_USER + ";";


    public AccountDBHelper(Context context, String nom, SQLiteDatabase.CursorFactory cursorfactory, int version) {
        super(context, nom, cursorfactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE_CREATION_BD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(REQUETE_DROP_BD);
        onCreate(db);
    }
}
