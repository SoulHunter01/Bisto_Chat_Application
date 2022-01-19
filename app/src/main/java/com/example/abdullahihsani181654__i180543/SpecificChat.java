package com.example.abdullahihsani181654__i180543;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.screenshotdetection.ScreenshotDetectionDelegate;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SpecificChat extends AppCompatActivity implements ScreenshotDetectionDelegate.ScreenshotDetectionListener {

    private static final String TAG = null;
    ArrayList<SpecificChatClass> ls;
    private static final int pic_id=123;
    TextView name;
    ImageView status;
    ImageButton camera;
    EditText message_text;
    ImageButton send;
    ImageButton call;
    ImageButton backbutton;
    RecyclerView rv;
    RecyclerViewAdapterSpecificChat adapter;
    ImageView click_image_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_chat);




        name = findViewById(R.id.textnameview);
        status = findViewById(R.id.textstatusview);
        camera=(ImageButton) findViewById(R.id.camera);
        message_text=(EditText) findViewById(R.id.message_text);
        send=findViewById(R.id.Send);
        call=findViewById(R.id.call);
        backbutton=findViewById(R.id.backbutton);
        rv=findViewById(R.id.rv);

        String get_name=getIntent().getStringExtra("NAME");
        String get_status=getIntent().getStringExtra("STATUS");
        name.setText(get_name);
        status.setImageDrawable(Drawable.createFromPath(getIntent().getStringExtra("IMAGE")));




        ls = new ArrayList<>();

        camera.setOnClickListener(v -> {
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, pic_id);
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences mySharedPreferences = SpecificChat.this.getSharedPreferences("MYPREFERENCENAME", Context.MODE_PRIVATE);
                String username = mySharedPreferences.getString("USERNAME", "");

                String get_text_message=message_text.getText().toString();
                name.setText(getIntent().getStringExtra("NAME"));
                status.setImageDrawable(Drawable.createFromPath(getIntent().getStringExtra("STATUS")));
                Date currentTime = Calendar.getInstance().getTime();
                String url="http://192.168.100.17/SMD-A/SaveMessage.php?name="+name.getText().toString()+"&message="+get_text_message+
                        "&time="+currentTime.toString()+"&to_username="+name.getText().toString()+"&from_username="+username;
                StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.startsWith("S")){
                            Log.e("XAXA","Message Saved");
                            ls.add(new SpecificChatClass(get_text_message,currentTime.toString()));
                            message_text.setText("");
                            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(SpecificChat.this, 1);
                            rv.setLayoutManager(mLayoutManager);
                            adapter = new RecyclerViewAdapterSpecificChat(ls,SpecificChat.this);
                            rv.setAdapter(adapter);
                            adapter.notifyDataSetChanged();

                        }
                        else if(response.startsWith("F")){
                            Toast.makeText(SpecificChat.this,response,Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SpecificChat.this,"Error in volley",Toast.LENGTH_LONG).show();
                    }
                });
                RequestQueue queue= Volley.newRequestQueue(SpecificChat.this);
                queue.add(stringRequest);





            }
        });





        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SpecificChat.this, calling.class);
                intent.putExtra("NAME_S",get_name);
                intent.putExtra("STATUS_S",get_status);
                startActivity(intent);
            }
        });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SpecificChat.this,Chats.class);
                startActivity(intent);
            }
        });

        

    }


    protected void onActivityResult(int requestCode,int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        click_image_id=findViewById(R.id.click_image_view);
        if (requestCode == pic_id) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            click_image_id.setImageBitmap(photo);
        }
    }



    @Override
    public void onScreenCaptured(@NonNull String s) {
        Toast.makeText(SpecificChat.this,"ScreenShot Is Taken",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onScreenCapturedWithDeniedPermission() {
        Toast.makeText(SpecificChat.this,"ScreenShot Permissions Is Denied",Toast.LENGTH_LONG).show();

    }
}