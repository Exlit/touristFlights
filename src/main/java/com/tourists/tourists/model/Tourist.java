package com.tourists.tourists.model;

import java.util.Date;
import java.util.List;

public class Tourist {
    private int id;
    private String name;
    private String surname;
    private Gender gender;
    private Country country;
    private String remarks;
    private String dateOfBirth;
   // private List<Flight> listOfFlights;
    public Tourist()
    {}

    public Tourist(String name) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.remarks = remarks;
        this.dateOfBirth = dateOfBirth;
      //  this.listOfFlights = listOfFlights;
    }

    public Tourist(int id, String name, String surname, Gender gender, Country country, String remarks, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.country = country;
        this.remarks = remarks;
        this.dateOfBirth = dateOfBirth;
     //   this.listOfFlights = listOfFlights;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Gender getGender() {
        return gender;
    }



    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

//    public List<Flight> getListOfFlights() {
//        return listOfFlights;
//    }
//
//    public void setListOfFlights(List<Flight> listOfFlights) {
//        this.listOfFlights = listOfFlights;
//    }

    @Override
    public String toString() {
        return super.toString();
    }
}
