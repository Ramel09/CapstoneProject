package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetails extends AppCompatActivity {

    ImageView img, back;
    TextView proName, proPrice, proDesc, proQty, proUnit;
    Button btn_Cart,btn_BuyNow;
    String name, price, desc, qty, unit;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        btn_Cart=findViewById(R.id.btn_cart);
        btn_BuyNow=findViewById(R.id.btn_buynow);

        btn_BuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCheckout();
            }
        });

        btn_BuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ProductDetails.this,Checkout.class);
                startActivity(i);
            }
        });


        btn_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenCart();
            }
        });

        btn_Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProductDetails.this, Cart.class);
                startActivity(i);
            }
        });


        Intent i = getIntent();

         name = i.getStringExtra("name");
         image = i.getIntExtra("image", R.drawable.b1);
         price = i.getStringExtra("price");
         desc = i.getStringExtra("desc");

         unit = i.getStringExtra("unit");

         proName = findViewById(R.id.textView_name);
         proDesc = findViewById(R.id.textView_desc);
         proPrice = findViewById(R.id.textView_price);
         img = findViewById(R.id.imageProduct);
         back = findViewById(R.id.back2);



         proName.setText(name);
         proPrice.setText(price);
         proDesc.setText(desc);



        img.setImageResource(image);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });

    }

    public  void  OpenCart(){
        Intent intent= new Intent(this,Cart.class);
        startActivity(intent);
    }
    public void OpenCheckout(){
        Intent intent=new Intent(this,Checkout.class);
        startActivity(intent);
    }


   // this tutorial has been completed
    // see you in the next.
}
