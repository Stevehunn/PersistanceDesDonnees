package com.example.persistancedesdonnees.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import androidx.cursoradapter.widget.SimpleCursorAdapter;

import com.example.persistancedesdonnees.BEANS.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserDAO extends DAOBase {
    public static final String TABLE_USER = "table_user";
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

    private static final String REQUETE_CREATION_BD = "create table " + TABLE_USER + " ("
            + COLONNE_ID_ENREGISTREMENT + " integer primary key autoincrement, "
            + COLONNE_USERNAME + " text not null, "
            + COLONNE_EMAIL + " text not null, "
            + COLONNE_PHONE + " text not null, "
            + COLONNE_PASSWORD + " text not null " + ");";

    public static final String REQUETE_DROP_BD = "DROP TABLE IF EXISTS " + TABLE_USER + ";";

    public UserDAO(Context pContext) {
        super(pContext);
    }


    public void ajouter (User user){
        ContentValues value = new ContentValues();
        value.put(UserDAO.COLONNE_USERNAME, user.getUsername());
        value.put(UserDAO.COLONNE_EMAIL, user.getEmail());
        value.put(UserDAO.COLONNE_PHONE, user.getPhone());
        value.put(UserDAO.COLONNE_PASSWORD, user.getPassword());
        mDb.insert(UserDAO.TABLE_USER, null, value);

    }

    public void supprimer (User user){
        mDb.delete(TABLE_USER, COLONNE_ID_ENREGISTREMENT + " = ?", new String[] {String.valueOf(user.getIdEnregistrement())});
    }

    public void modifier (User user){
        ContentValues value = new ContentValues();
        value.put(COLONNE_USERNAME, user.getUsername());
        value.put(UserDAO.COLONNE_EMAIL, user.getEmail());
        value.put(UserDAO.COLONNE_PHONE, user.getPhone());
        value.put(UserDAO.COLONNE_PASSWORD, user.getPassword());
        mDb.update(TABLE_USER, value, COLONNE_ID_ENREGISTREMENT  + " = ?", new String[] {String.valueOf(user.getIdEnregistrement())});
    }

    public ArrayList<User> getAllData (){
        Cursor c = mDb.query(TABLE_USER, new String[]{
                COLONNE_ID_ENREGISTREMENT,COLONNE_USERNAME,COLONNE_EMAIL,COLONNE_PHONE,COLONNE_PASSWORD },
               null , null, null, null, null);

        ArrayList<User> userArray = new ArrayList<User>();

        while (c.moveToNext()) {
            int id_enregistrement = c.getInt(0);
            String username = c.getString(1);
            String email = c.getString(2);
            String phone = c.getString(3);
            String password = c.getString(4);

            User user = new User(id_enregistrement, username, email, phone, password);
            userArray.add(user);
        }
        return userArray;
    }

}
