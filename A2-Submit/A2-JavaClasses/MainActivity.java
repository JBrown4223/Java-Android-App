package com.example.dps924_assignment2_jbrown124;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.LineNumberInputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    DatabaseClient dbAccess;

    //Methods for Adapter
    List<JSON_Car> carList;
    Network network;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.CarList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Connect(getApplicationContext());

    }

    //Get Cars from Network convert to JSON_Car object add to List then check if any of them are in the BD
    public void Connect(Context context){
        class FetchData extends AsyncTask<Void,Void,List<JSON_Car> >{

            @Override
            protected List<JSON_Car> doInBackground(Void... voids) {
                String url = "https://raw.githubusercontent.com/RaniaArbash/Assignment2_SkeletonProject/master/cars.json";
                network = new Network(context);
               return carList = network.getData(url);
            }

            @Override
            protected void onPostExecute(List<JSON_Car> carList) {
                CarAdapter adapter=new CarAdapter(getApplicationContext(),carList);
                recyclerView.setAdapter(adapter);
                super.onPostExecute(carList);
            }
        }
        FetchData fd =new FetchData();
        fd.execute();
    }


}