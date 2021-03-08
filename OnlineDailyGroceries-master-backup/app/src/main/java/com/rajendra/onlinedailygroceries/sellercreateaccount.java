package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class sellercreateaccount extends AppCompatActivity {
    EditText et_sellerrepassword,et_selleremailadd,et_sellerpassword,et_sellerfullname,et_sellerCnumber,et_sellerCAddress,et_shopname;
    Button btn_allreadregister,btn_registered;
    DBHelperSellerLog  DBseller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellercreateaccount);

        et_sellerfullname=findViewById(R.id.edittext_selleremail);
        et_sellerCnumber=findViewById(R.id.edittext_cnumber);
        et_sellerCAddress=findViewById(R.id.edittext_cAddress);
        et_selleremailadd=findViewById(R.id.edittext_emailaddress);
        et_sellerpassword=findViewById(R.id.edittext_password);
        et_sellerrepassword=findViewById(R.id.edittext_repassword);
        et_shopname=findViewById(R.id.edittext_shopname);

        DBseller = new DBHelperSellerLog(this);

        btn_allreadregister=findViewById(R.id.btn_allreadyreg);
        btn_allreadregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),sellerslogin.class);
                startActivity(i);
            }
        });



        btn_registered=findViewById(R.id.btnsellerlogin);
        btn_registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailadd=et_selleremailadd.getText().toString();
                String password=et_sellerpassword.getText().toString();
                String repassword=et_sellerrepassword.getText().toString();
                String fullname=et_sellerfullname.getText().toString();
                String Cnumber=et_sellerCnumber.getText().toString();
                String CAddress=et_sellerCAddress.getText().toString();
                String ShopName=et_shopname.getText().toString();

                if (emailadd.equals("")||password.equals("")||repassword.equals("")||fullname.equals("")||Cnumber.equals("")||CAddress.equals("")||ShopName.equals(""))
                    Toast.makeText(sellercreateaccount.this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();
                else
                {
                    if(password.equals(repassword)){
                        Boolean checkuser=DBseller.checksellerusername(emailadd);
                        if (checkuser==false){
                            Boolean insert =DBseller.insertData(emailadd,password,fullname,Cnumber,CAddress,ShopName);
                            if (insert==true){
                                Toast.makeText(sellercreateaccount.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),AddSellerProd.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(sellercreateaccount.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(sellercreateaccount.this, "User Already exists! Please Login ", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(sellercreateaccount.this, "Password not Matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
    }
}