package com.example.dps924_assignment2_jbrown124;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Network  {


    Context ctxt;
    JSON_Car temp;
    List<JSON_Car> carList;


    BufferedReader reader = null;
    String forecastJsonStr = null;



    public Network(Context context) {
        this.ctxt = context;
        carList = new ArrayList<JSON_Car>();

    }


    List<JSON_Car> getData(String url)  {

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            int status = httpURLConnection.getResponseCode();
            Log.d("GET RX", " status=> " + status);
            int lengthOfFile = httpURLConnection.getContentLength();
            // Read the input stream into a String
            InputStream inputStream = httpURLConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                Log.d("No Input", "Something went wrong...Fetch");
                return null;

            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                Log.d("No Input", "Something went wrong...Buffer");
                return null;
            }
            forecastJsonStr = buffer.toString();
            Log.e("Json1", forecastJsonStr);

            JSONArray jsonArray = new JSONArray(forecastJsonStr);
            int i = 0;
            do {
                temp = new JSON_Car();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                temp.setId(jsonObject.getInt("id"));
                temp.setCarMake(jsonObject.getString("CarModel1"));
                temp.setCarModel(jsonObject.getString("CarModel2"));
                temp.setYear(jsonObject.getInt("Year"));
                this.carList.add(i,temp);
                Log.e("Temp", carList.get(i).toString());
                temp = null;
                i++;

            }while( i != jsonArray.length());
        }
        catch (IOException e){
            Log.e("PlaceholderFragment", "Error ", e);

        }
        catch (JSONException e) { e.printStackTrace();}
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }

        return this.carList;
    }




}

