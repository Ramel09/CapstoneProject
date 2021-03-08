package com.rajendra.onlinedailygroceries;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class ProductList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Productseller> list;
    ProductListAdapter adapter=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foodlist);

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

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                CharSequence[] items = {"Update","Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(ProductList.this);

                dialog.setTitle("Choose an Action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {

                        if(item==0){
                            //Update

                            Cursor c = AddSellerProd.dbHelperAddSellerProd.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(ProductList.this,arrID.get(position));
                                  }
                        else{
                            //Delete
                            Cursor c = AddSellerProd.dbHelperAddSellerProd.getData("SELECT id FROM FOOD");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while(c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                           showDialogDelete(arrID.get(position));        }
                    }
                });
                dialog.show();
                return ;
            }
        });
    }

    ImageView imageViewFood;
    private void showDialogUpdate(Activity activity, final int position ){

        final Dialog dialog= new Dialog(activity);
        dialog.setContentView(R.layout.updatefood);
        dialog.setTitle("Update");

        imageViewFood = (ImageView) dialog.findViewById(R.id.imageViewFood);
        final     EditText et_name=(EditText) dialog.findViewById(R.id.editTextText_name);
        final     EditText et_description=(EditText) dialog.findViewById(R.id.editTextText_description);
        final     EditText et_Price=(EditText) dialog.findViewById(R.id.editTextText_Price);
        Button btn_Update= (Button) dialog.findViewById(R.id.button_update);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width,height);
        dialog.show();

        imageViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //request photo library
                ActivityCompat.requestPermissions(
                        ProductList.this,
                        new  String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        888
                );
            }
        });
        btn_Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    AddSellerProd.dbHelperAddSellerProd.UpdateData(
                            et_name.getText().toString().trim(),
                            Double.parseDouble(et_Price.toString().trim()),
                            AddSellerProd.imageViewToByte(imageViewFood),
                            et_description.getText().toString().trim(),
                            position


                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update Successfully", Toast.LENGTH_SHORT).show();

                }catch (Exception error){
                    Log.d("Update error" , error.getMessage());
                }
                updateProductList();
            }
        });
    }

    private void showDialogDelete(final int idProduct){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ProductList.this);
        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    AddSellerProd.dbHelperAddSellerProd.deleteData(idProduct);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateProductList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }
    private void updateProductList(){
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==888){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,888);

            }
            else {
                Toast.makeText(this, "You don't have permission to access file location", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == 888 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewFood.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
