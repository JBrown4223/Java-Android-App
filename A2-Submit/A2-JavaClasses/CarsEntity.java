package com.example.dps924_assignment2_jbrown124;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CarsEntity implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo
    public String carMake;

    @ColumnInfo
    public String carModel;

    @ColumnInfo
    public int carYear;



    public void setCarMake(String carMake) {this.carMake = carMake;}
    public void setCarModel(String carModel){this.carModel = carModel;}
    public void setCarYear(int carYear){this.carYear = carYear;}

 //Future Functions
    public String getCarMake(){ return this.carMake;}
    public String getCarModel(){ return this.carModel;}
    public int getCarYear(){ return this.carYear;}



}
