package com.example.abdullahihsani181654__i180543;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.RemoteMessage;
import com.onesignal.OneSignal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText username;
    EditText password;
    Button login;
    TextView register;
    TextView forgot;
    private String email_get,password_get;
    private String URL="http://192.168.100.17/SMD-A/Login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginbutton);
        register = findViewById(R.id.registerbutton);
        forgot = findViewById(R.id.forgotpassword);
        mAuth = FirebaseAuth.getInstance();
        email_get=password_get="";
      login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              email_get=username.getText().toString();
              password_get=password.getText().toString();
              if(!email_get.equals("") && !password_get.equals("")){
                  String url="http://192.168.100.17/SMD-A/Login.php?email="+username.getText().toString()+"&password="+password.getText().toString();
                  StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                      @Override
                      public void onResponse(String response) {
                          if(response.equals("Success")){


                              String URL_ONLINE_STATUS="http://192.168.100.17/SMD-A/onlinestatus.php?email="+username.getText().toString()+"&onlinestatus=online";
                              StringRequest stringRequest1 = new StringRequest(Request.Method.GET, URL_ONLINE_STATUS ,
                                      new Response.Listener<String>() {
                                          @Override
                                          public void onResponse(String response) {
                                              if(response.startsWith("S")){
                                                    Toast.makeText(LoginPage.this,"User Status Changed To Online",Toast.LENGTH_LONG).show();

                                                }
                                                else if(response.startsWith("F")){
                                                    Toast.makeText(LoginPage.this,"Failed To Change Online Status",Toast.LENGTH_LONG).show();
                                                }
                                          }
                                      },
                                      new Response.ErrorListener() {
                                          @Override
                                          public void onErrorResponse(VolleyError error) {
                                              Log.e("error" , error.toString());
                                          }
                                      });


                              RequestQueue queue= Volley.newRequestQueue(LoginPage.this);
                              queue.add(stringRequest1);

                             Intent intent=new Intent(LoginPage.this,Chats.class);
                             intent.putExtra("Email_GET",email_get);
                             startActivity(intent);
                          }
                          else if(response.equals("Failure")){
                            //  Toast.makeText(LoginPage.this,response,Toast.LENGTH_LONG).show();
                          }

                      }
                  }, new Response.ErrorListener() {
                      @Override
                      public void onErrorResponse(VolleyError error) {
                          Toast.makeText(LoginPage.this,"Error in volley",Toast.LENGTH_LONG).show();
                      }
                  });
                  RequestQueue queue= Volley.newRequestQueue(LoginPage.this);
                  queue.add(stringRequest);
              }
              else{
                  Toast.makeText(LoginPage.this,"Error occurred",Toast.LENGTH_SHORT).show();
              }
          }
      });


      register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(LoginPage.this,CreateAccount.class);
              startActivity(intent);
          }
      });


        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);

        // OneSignal Initialization
        OneSignal.initWithContext(this);
        OneSignal.setAppId("0f73f22e-76f3-4022-9136-f01ea8068121");


    }










}