package com.example.dps924_assignment2_jbrown124;

import androidx.room.Database;
import androidx.room.RoomDatabase;

  @Database(entities = {CarsEntity.class}, version = 1)
  public abstract class AppDatabase extends RoomDatabase {
        public abstract CarDAO carDAO();
  }

