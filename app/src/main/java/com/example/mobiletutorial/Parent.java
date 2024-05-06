package com.example.mobiletutorial;

import android.graphics.Bitmap;

public class Parent {

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    private int parentId;
    private String userName;
    private String email;
    private String password;
    private int NbOfChildren;
    private Bitmap parentPhoto;

    public Bitmap getParentPhoto() {
        return parentPhoto;
    }

    public void setParentPhoto(Bitmap parentPhoto) {
        this.parentPhoto = parentPhoto;
    }



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
