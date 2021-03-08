package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

public class CustomerView extends AppCompatActivity {

    GridView gridView;
    ArrayList<Productseller> list;
    ProductListAdapter adapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        gridView= findViewById(R.id.gridView);
        list= new ArrayList<>();
        adapter= new ProductListAdapter(this,R.layout.productitem,list);
        gridView.setAdapter(adapter);

        // get all data from sqlite///

        Cursor cursor= AddSellerProd.dbHelperAddSellerProd.getData("SELECT * FROM FOOD");

        list.clear();
        while (cursor.moveToNext()){
            int id =cursor.getInt(0);
            String name=cursor.getString(1);
            Double price = cursor.getDouble(2);
            byte[] image=cursor.getBlob(3);
            String description=cursor.getString(4);

            list.add(new Productseller(id,name,price,image,description));

        }
        adapter.notifyDataSetChanged();

    }

}