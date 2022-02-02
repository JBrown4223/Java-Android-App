package com.example.dps924_assignment2_jbrown124;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

 public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {


        private Context mCtx;
        List<JSON_Car> listOfCars;


        public CarAdapter(Context mCtx, List<JSON_Car> listOfCars) {
            this.mCtx = mCtx;
            this.listOfCars = listOfCars;


        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_cars, parent, false);
            return new ViewHolder(view);
        }

       @Override
       public void onBindViewHolder(ViewHolder holder, int position) {

            CarsEntity tempCar = new CarsEntity();
            JSON_Car t = listOfCars.get(position);
           tempCar = checkForFavorite(mCtx,t.getMake(),t.getModel());
           if(tempCar != null){
             holder.buttonViewLike.setBackgroundResource(R.drawable.like);


          }else{
               holder.buttonViewLike.setBackgroundResource(R.drawable.unlike);
           }




         holder.buttonViewLike.setTag(t.getId());
         holder.buttonViewLike.setVisibility(View.VISIBLE);
         holder.textViewMake.setText(t.getMake());
         holder.textViewModel.setText(t.getModel());

         holder.buttonViewLike.setOnClickListener(new View.OnClickListener() {
             public void onClick(View v) {

                 AlertDialog.Builder builder = new AlertDialog.Builder(v.getRootView().getContext() );
                 builder.setMessage("Add to Favorites?")
                         .setCancelable(false)
                         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 CarsEntity carListing = new CarsEntity();
                                 carListing.setCarMake(t.getMake());
                                 carListing.setCarModel(t.getModel());
                                 carListing.setCarYear(t.getYear());


                                 DatabaseClient.databaseWriteExecutor.execute(()->{
                                     DatabaseClient.getInstance(mCtx)
                                             .getDb()
                                             .carDAO()
                                             .insert(carListing);
                                 });
                             }
                         })
                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                                 //  Action for 'NO' Button
                                 holder.buttonViewLike.setBackgroundResource(R.drawable.unlike);

                                 dialog.cancel();


                             }
                         });

                 AlertDialog alert = builder.create();
                 alert.setTitle("AlertDialogExample");
                 alert.show();
                 holder.buttonViewLike.setBackgroundResource(R.drawable.like);

             }
         });


       }



        @Override
        public int getItemCount() {
            return listOfCars.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder  {

            TextView textViewMake, textViewModel;
            Button buttonViewLike;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                buttonViewLike = itemView.findViewById(R.id.likeButton);
                textViewMake = itemView.findViewById(R.id.make_row_id);
                textViewModel = itemView.findViewById(R.id.model_row_id);


            }
        }

        //Check if any of the cars coming from the API are on the favorite list
         CarsEntity checkForFavorite(Context context, String testQuery1, String testQuery2){

            return getCar(context,testQuery1,testQuery2);


        }

        //Query to find a favorite car in the Room DB
        public CarsEntity getCar(Context context, String testQuery1, String testQuery2) {
            CarsEntity db = DatabaseClient.getInstance(context)
                 .getDb()
                 .carDAO()
                 .check(testQuery1, testQuery2);
              return db;
        }

}
