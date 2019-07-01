package com.tourists.tourists.model;

import java.util.Date;
import java.util.List;

public class Flight {
    private int id;
    private String name;
    private String departure;
    private String arrive;
    private int seats;
    private int price;
    private List<Tourist> tourists;




    public Flight(int id, String name, String departure, String arrive, int seats, int price) {
        this.id = id;
        this.name = name;
        this.departure = departure;
        this.arrive = arrive;
        this.seats = seats;
        this.tourists = tourists;
        this.price = price;
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

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<Tourist> getTourists() {
        return tourists;
    }

    public void setTourists(List<Tourist> tourists) {
        this.tourists = tourists;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
