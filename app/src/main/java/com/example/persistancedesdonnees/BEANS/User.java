package com.example.persistancedesdonnees.BEANS;

public class User {
    // Notez que l'identifiant est un long
    private int id_enregistrement;
    private String username;
    private String email;
    private String phone;
    private String password;

    public User(int id_enregistrement, String username, String email, String phone, String password)  {
        super();
        this.id_enregistrement = id_enregistrement;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User(String username, String email, String phone, String password)  {
        super();
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public int getIdEnregistrement() {
        return id_enregistrement;
    }

    public void setIdEnregistrement(int id) {
        this.id_enregistrement = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
