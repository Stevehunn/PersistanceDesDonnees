package com.example.persistancedesdonnees;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.LogPrinter;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.persistancedesdonnees.BEANS.User;
import com.example.persistancedesdonnees.DAO.UserDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public EditText username;
    private EditText email;
    public EditText phone;
    private EditText password;

    protected Bundle savedInstanceState;
    private String dialogToDisplay;

    private UserDAO userDAO;

    private final String DIALOG_TITLE = "Account List";

    /*
    private final String FILENAME = "Register.txt";
    private final String DIRECTORY_FILES_PATH = "/data/user/0/com.example.persistancedesdonnees/files";
    */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button register = findViewById(R.id.buttonRegister);
        register.setOnClickListener(this);

        Button showAccounts = findViewById(R.id.buttonShowAccount);
        showAccounts.setOnClickListener(this);

        this.username= findViewById(R.id.username);
        this.email= findViewById(R.id.email);
        this.phone= findViewById(R.id.phone);
        this.password= findViewById(R.id.password);

        userDAO = new UserDAO(this);
        userDAO.open();

        /*
        // List déroulante
        final Spinner spin =(Spinner) findViewById(R.id.spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);

        final Button active = (Button) findViewById(R.id.selection);
        active.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Option();
            }
        });

        //Bouton pour appeler
        final Button btAppel = (Button) findViewById(R.id.appel);
        String number = "0123456789";
        btAppel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = number; //.getText().toString();
                if (phone.isEmpty()){
                    Toast.makeText(getApplicationContext(),"L'utilisateur n'a pas de numéro de téléphone enregistré", Toast.LENGTH_SHORT).show();
                }
                else{
                    String s = "tel:" + phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });

         */

    }

    @Override
    public void onClick(View v) {

        // Gestion du click sur le bouton REGISTER
        if(v.getId() == R.id.buttonRegister){
            String usernameText = username.getText().toString();
            String emailText = email.getText().toString();
            String phoneText = phone.getText().toString();
            String passwordText = password.getText().toString();
            boolean test = usernameText.isEmpty() || emailText.isEmpty() || phoneText.isEmpty() || passwordText.isEmpty();

            if(!test){
                try {

                    // Création d'un objet User permettant de sauvegarder les saisies du formulaire
                    User user = new User(usernameText,emailText,phoneText,passwordText);

                    // Ajout en BDD des saisies du formulaire via le BEANS User créer précédemment
                    userDAO.ajouter(user);

                    // Remise à zéro des champs du formulaire
                    username.setText(null);
                    email.setText(null);
                    phone.setText(null);
                    password.setText(null);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        // Gestion du click sur le bouton SHOW ACCOUNTS
        if(v.getId() == R.id.buttonShowAccount){
            Log.e("Clique SHOW ACCOUNT :", "nous sommes dans le block de selection des utilisateurs");
            onCreateDialog(savedInstanceState).show();
        }

    }

    protected Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_account);


        // List déroulante
        final Spinner spin = (Spinner) findViewById(R.id.spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);

        final Button active = (Button) findViewById(R.id.selection);
        active.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Option();
            }
        });

        final Button btAppel = (Button) findViewById(R.id.appel);
        String number = "0123456789";
        btAppel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = number; //.getText().toString();
                if (phone.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "L'utilisateur n'a pas de numéro de téléphone enregistré", Toast.LENGTH_SHORT).show();
                } else {
                    String s = "tel:" + phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(s));
                    startActivity(intent);
                }
            }
        });
        return null;
    }

    //List des utlisateurs
    static final String[] Users = new String[]{"user 1", "user 2"};

    //Select Account
    public void Option(){
        final Button active = (Button) findViewById(R.id.selection);
        if (active!= null){
            final Spinner spin = (Spinner) findViewById(R.id.spin);
            final TextView text = (TextView)findViewById(R.id.information);
            final Button appel = (Button)findViewById(R.id.appel);
            if ((spin!= null ) && spin.isEnabled()){
                appel.setText("Appeler l'utilisateur");
                text.setText("Vous pouvez appler l'utilisateur selectionné en appayant sur le button en dessus");
                spin.setEnabled(false);
                active.setText("Selectionner un autre utilisateur");
            }
            else{
                spin.setEnabled(true);
                active.setText("Selectionner un utilisateur");
                text.setText("Aucune sélection");
            }
        }
    }
/*
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        try {
            ArrayList<User> userArray = userDAO.getAllData();
            User user = null;
            String display = null;

            for (int i = 0; i<userArray.size(); i++){
                user = userArray.get(i);
                Log.e("Lecture de Bdd :", user.getUsername());
                display = "ID enregistrement : " + user.getIdEnregistrement() + "\n"
                        + "Username : " + user.getUsername() + "\n"
                        + "Email : " + user.getEmail() + "\n"
                        + "Phone : " + user.getPhone() + "\n"
                        + "Password : " + user.getPassword() + "\n";
                arrayAdapter.add(display);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(DIALOG_TITLE)
                .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // NOTHING TO DO HERE
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

 */




    /*
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonRegister){
            String usernameText = username.getText().toString();
            String emailText = email.getText().toString();
            String phoneText = phone.getText().toString();
            String passwordText = password.getText().toString();
            boolean test = usernameText.isEmpty() || emailText.isEmpty() || phoneText.isEmpty() || passwordText.isEmpty();

            String fileContent = usernameText+";"+emailText+";"+phoneText+";"+passwordText+"\n";

            FileOutputStream outputStream;

            if(!test){
                try {
                    outputStream = openFileOutput(FILENAME, Context.MODE_APPEND);
                    outputStream.write(fileContent.getBytes());
                    outputStream.close();

                    username.setText(null);
                    email.setText(null);
                    phone.setText(null);
                    password.setText(null);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        if(v.getId() == R.id.buttonShowAccount){
            onCreateDialog(savedInstanceState).show();
        }
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // accéder au fichier et parcourir son contenu pour créer
        FileInputStream inputStream;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        try {
            File file = new File(DIRECTORY_FILES_PATH + "/" + FILENAME);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String display;
            int cpt = 0;
            while ((line = br.readLine()) != null) {
                line = (cpt+1) + ";" + line;
                String[] array = line.split(";");
                display = "ID enregistrement : " + array[0] + "\n"
                        + "Username : " + array[1] + "\n"
                        + "Email : " + array[2] + "\n"
                        + "Phone : " + array[3] + "\n"
                        + "Password : " + array[4] + "\n";
                arrayAdapter.add(display);
                cpt += 1;
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(DIALOG_TITLE)
                .setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // NOTHING TO DO HERE
                    }
                    });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    */
}