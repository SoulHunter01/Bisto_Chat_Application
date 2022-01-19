package com.example.abdullahihsani181654__i180543;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText confirmpassword;
    Button register;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirmpassword=findViewById(R.id.confirmpassword);
        register=findViewById(R.id.registerbutton);
        mAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String get_email=email.getText().toString();
                String get_password=password.getText().toString();
                String get_confirmpassword=confirmpassword.getText().toString();
                if (!get_password.equals(get_confirmpassword)){
                    Toast.makeText(CreateAccount.this,"Passwords Do Not Match",Toast.LENGTH_LONG).show();
                }
                else{
                    String url="http://192.168.100.17/SMD-A/Register.php?email="+get_email+"&password="+get_password;
                    StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.startsWith("S")){
                                Toast.makeText(CreateAccount.this,"Account Successfully Created",Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(CreateAccount.this,LoginPage.class);
                                startActivity(intent);
                            }
                            else if(response.startsWith("F")){
                                Toast.makeText(CreateAccount.this,response,Toast.LENGTH_LONG).show();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(CreateAccount.this,"Error in volley",Toast.LENGTH_LONG).show();
                        }
                    });
                    RequestQueue queue= Volley.newRequestQueue(CreateAccount.this);
                    queue.add(stringRequest);
                }

            }
        });







    }
}