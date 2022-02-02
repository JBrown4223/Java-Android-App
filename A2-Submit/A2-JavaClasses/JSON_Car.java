package com.example.dps924_assignment2_jbrown124;

// This Class turns our fetch API data into an object that the App can work with
// Specifically it turns the data into an object we can control

public class JSON_Car {
    private String carMake, carModel;
    private int id, year;


    //Setter
    void setId(int _id){
        this.id = _id;
    }

    void setCarMake(String _carMake){
        this.carMake = _carMake;
    }

    void setCarModel(String _carModel){
        this.carModel = _carModel;
    }

    void setYear(int _year){
        this.year = _year;
    }

    //Getters

    public int getId() { return id; }
    public String getMake(){ return this.carMake;}
    public String getModel(){ return this.carModel;}


    public int getYear() { return  this.year; }
}
