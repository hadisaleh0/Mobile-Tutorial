package com.example.mobiletutorial;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Children {
    private String firstName;
    private String lastName;
    private String motherName;
    private Calendar DateOfBirth;
    private String gender;
    private String bloodgroup;
    private String PlaceOfBirth;
    private String[] CompletedVaccines;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Calendar getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Calendar dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gneder) {
        this.gender = gneder;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getPlaceOfBirth() {
        return PlaceOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        PlaceOfBirth = placeOfBirth;
    }

    public String[] getCompletedVaccines() {
        return CompletedVaccines;
    }

    public void setCompletedVaccines(String[] completedVaccines) {
        CompletedVaccines = completedVaccines;
    }
}
