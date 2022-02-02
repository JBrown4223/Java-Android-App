package com.example.dps924_assignment2_jbrown124;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CarDAO {

    //Check if Car is in DB
    @Query("SELECT uid,carMake,carMake,carYear FROM CarsEntity where carMake = :make AND carModel = :model")
    CarsEntity check(String make, String model);

    //Add Car to the db
    @Insert
    void insert(CarsEntity car);

    //Remove Car if unfavorited
    @Delete
    void delete(CarsEntity car);

}
