package com.example.dps924_assignment2_jbrown124;

import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseClient {

    private Context mCtx;
    private static DatabaseClient dbClient;

    AppDatabase db;
    private static final int NUMBER_OF_THREADS=4;
    public static final ExecutorService databaseWriteExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private DatabaseClient(Context mCtx){
        this.mCtx = mCtx;

        db = Room.databaseBuilder(mCtx, AppDatabase.class, "CarsEntity").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseClient getInstance(Context mCtx){
        if(dbClient == null){
            dbClient = new DatabaseClient(mCtx);
        }
        return  dbClient;
    }

    public AppDatabase getDb() {
        return db;
    }

    public static void insert(Context context, CarsEntity car){
        DatabaseClient.databaseWriteExecutor.execute(()->{
            DatabaseClient.getInstance(context)
                    .getDb()
                    .carDAO()
                    .insert(car);
        });

    }
    public static void remove(Context context, CarsEntity car){
        DatabaseClient.databaseWriteExecutor.execute(()->{
            DatabaseClient.getInstance(context)
                    .getDb()
                    .carDAO()
                    .delete(car);
        });

    }


}

