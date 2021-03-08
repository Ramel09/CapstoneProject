package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button btnlog,btncreateacc,btn_customer,btncus;
    EditText et_email,et_pass;
    DBHelper DB;
    public static DBHelperAddSellerProd dbHelperAddSellerProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email=findViewById(R.id.edittext_selleremail);
        et_pass=findViewById(R.id.edittext_sellerpass);

        DB= new DBHelper(this);

        dbHelperAddSellerProd=new DBHelperAddSellerProd(this,"Food1DB.sqlite",null,1);

        dbHelperAddSellerProd.queryData("CREATE TABLE IF NOT EXISTS FOOD (Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR,description VARCHAR, price DOUBLE, image BLOG)");

        btnlog=findViewById(R.id.btnsellerlogin);
        btncreateacc=findViewById(R.id.btn_sellercreateacc);
        btn_customer=findViewById(R.id.btncustomer);
        btn_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),sellerslogin.class);
                startActivity(i);
            }
        });


         /////////////////create login///////////
        btncreateacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Openregister();
            }
        });
        btncreateacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,CreateAcc.class);
                startActivity(i);
            }
        });



          ////////////   login ////////////////////
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=et_email.getText().toString();
                String pass=et_pass.getText().toString();
                
                if(email.equals("")||pass.equals(""))
                    Toast.makeText(login.this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass=DB.checkemailpassword(email,pass);
                    if(checkuserpass==true){
                        Toast.makeText(login.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(),CustomerView.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }




    //////////////open register////////////


    public  void Openregister(){
        Intent intent=new Intent(this,CreateAcc.class);
        startActivity(intent);
    }
}