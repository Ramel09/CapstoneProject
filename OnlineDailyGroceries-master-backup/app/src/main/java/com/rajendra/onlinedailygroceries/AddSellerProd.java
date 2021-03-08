package com.rajendra.onlinedailygroceries;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddSellerProd extends AppCompatActivity {
    EditText et_name,et_price,et_desc;
    Button btn_choose,btn_add,btn_list;
    ImageView imageView;

    final int REQUEST_CODE_GALLERY=999;

    public static DBHelperAddSellerProd dbHelperAddSellerProd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seller_prod);

        init();

        dbHelperAddSellerProd=new DBHelperAddSellerProd(this,"Food1DB.sqlite",null,1);

        dbHelperAddSellerProd.queryData("CREATE TABLE IF NOT EXISTS FOOD (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR,description VARCHAR, price DOUBLE, image BLOG)");

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        AddSellerProd.this,
                        new  String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    dbHelperAddSellerProd.insertData(
                            et_name.getText().toString().trim(),
                            Double.parseDouble(et_price.getText().toString()),
                            imageViewToByte(imageView),
                            et_desc.getText().toString().trim()
                    );
                    Toast.makeText(AddSellerProd.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                    et_name.setText("");
                    et_price.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    et_desc.setText("");
                } 
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSellerProd.this,ProductList.class);
                startActivity(intent);
            }
        });
    }

    public static byte[] imageViewToByte(ImageView image) {
    Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray= stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode==REQUEST_CODE_GALLERY){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);

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

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){
        et_name=findViewById(R.id.editTextText_name);
        et_price=findViewById(R.id.editTextText_Price);
        et_desc=findViewById(R.id.editTextText_description);
        btn_add=findViewById(R.id.button_update);
        btn_choose=findViewById(R.id.button_chooseimage);
        btn_list=findViewById(R.id.button_list);
        imageView=findViewById(R.id.imageViewFood);
    }
}