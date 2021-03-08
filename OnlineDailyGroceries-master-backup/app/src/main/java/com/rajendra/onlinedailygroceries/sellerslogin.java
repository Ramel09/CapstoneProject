package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sellerslogin extends AppCompatActivity {
    Button btn_sellerlogin,btnsellercreateacc,btncustomer;
    EditText et_selleremail,et_sellerpassword;
    DBHelperSellerLog  DBseller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellerslogin);

        et_selleremail=findViewById(R.id.edittext_selleremail);
        et_sellerpassword=findViewById(R.id.edittext_sellerpass);
        btn_sellerlogin=findViewById(R.id.btnsellerlogin);
        btncustomer=findViewById(R.id.btncustomer);
        btnsellercreateacc=findViewById(R.id.btn_sellercreateacc);
        btnsellercreateacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),sellercreateaccount.class);
                startActivity(i);
            }
        });

        DBseller=new DBHelperSellerLog(this);
        btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });
        btn_sellerlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selleremail=et_selleremail.getText().toString();
                String sellerpass=et_sellerpassword.getText().toString();

                if(selleremail.equals("")||sellerpass.equals(""))
                    Toast.makeText(sellerslogin.this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass=DBseller.checkselleremailpassword(selleremail,sellerpass);
                    if(checkuserpass==true){
                        Toast.makeText(sellerslogin.this, "Login Successfull", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(getApplicationContext(),AddSellerProd.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(sellerslogin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}