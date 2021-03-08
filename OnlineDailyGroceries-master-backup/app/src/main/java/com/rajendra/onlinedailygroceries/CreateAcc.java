package com.rajendra.onlinedailygroceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CreateAcc extends AppCompatActivity {

    EditText et_repassword,et_emailadd,et_password,et_fullname,et_Cnumber,et_CAddress;
    Button btn_Registration,btn_allreadregister;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acc);


        et_emailadd=findViewById(R.id.edittext_emailaddress);
        et_password =findViewById(R.id.edittext_password);
        et_repassword=findViewById(R.id.edittext_repassword);
        et_fullname=findViewById(R.id.edittext_selleremail);
        et_Cnumber=findViewById(R.id.edittext_cnumber);
        et_CAddress=findViewById(R.id.edittext_cAddress);
        DB = new DBHelper(this);

        btn_allreadregister=findViewById(R.id.btn_allreadyreg);
        btn_allreadregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),login.class);
                startActivity(i);
            }
        });
        btn_Registration=findViewById(R.id.btnsellerlogin);
        btn_Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String emailadd=et_emailadd.getText().toString();
                String password=et_password.getText().toString();
                String repassword=et_repassword.getText().toString();
                String fullname=et_fullname.getText().toString();
                String Cnumber=et_Cnumber.getText().toString();
                String CAddress=et_CAddress.getText().toString();

                
                if (emailadd.equals("")||password.equals("")||repassword.equals("")||fullname.equals("")||Cnumber.equals("")||CAddress.equals(""))
                    Toast.makeText(CreateAcc.this, "Please Enter All The Fields", Toast.LENGTH_SHORT).show();
                else
                {
                    if(password.equals(repassword)){
                        Boolean checkuser=DB.checkusername(emailadd);
                        if (checkuser==false){
                            Boolean insert =DB.insertData(emailadd,password,fullname,Cnumber,CAddress);
                            if (insert==true){
                                Toast.makeText(CreateAcc.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(CreateAcc.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(CreateAcc.this, "User Already exists! Please Login ", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else {
                        Toast.makeText(CreateAcc.this, "Password not Matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

}