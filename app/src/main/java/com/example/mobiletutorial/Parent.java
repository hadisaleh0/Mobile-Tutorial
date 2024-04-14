package com.example.mobiletutorial;

public class Parent {

    private String userName;
    private String email;
    private String password;
    private int NbOfChildren;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNbOfChildren() {
        return NbOfChildren;
    }

    public void setNbOfChildren(int nbOfChildren) {
        NbOfChildren = nbOfChildren;
    }
}
