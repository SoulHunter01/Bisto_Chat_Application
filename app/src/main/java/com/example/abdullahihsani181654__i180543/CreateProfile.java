package com.example.abdullahihsani181654__i180543;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfile extends AppCompatActivity {
    EditText ProfilePic;
    EditText Firstname;
    EditText Lastname;
    EditText Gender;
    EditText Bio;
    Button CreateButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        Firstname=findViewById(R.id.firstname);
        Lastname=findViewById(R.id.lastname);
        Gender=findViewById(R.id.lastname);
        Bio=findViewById(R.id.Bio);
        CreateButton=findViewById(R.id.createbutton);
        mAuth=FirebaseAuth.getInstance();

        CreateButton.setOnClickListener(v -> {
            Intent intent=getIntent();
            String get_email=intent.getStringExtra("Email");
            String get_password=intent.getStringExtra("Password");
            String get_confirmpassword=intent.getStringExtra("ConfirmPassword");
            String get_ProfilePic=ProfilePic.getText().toString();
            String get_FirstName=Firstname.getText().toString();
            String get_LastName=Lastname.getText().toString();
            String get_Gender=Gender.getText().toString();
            String get_Bio=Bio.getText().toString();


            mAuth.createUserWithEmailAndPassword(get_email,get_password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Toast.makeText(CreateProfile.this,"User Successfully Registered",Toast.LENGTH_LONG).show();
                    // Write a message to the database

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("message");
                    myRef.setValue("Hello, World!");



                    Intent newIntent=new Intent(CreateProfile.this, LoginPage.class);
                    startActivity(newIntent);
                }
                else{
                    FirebaseAuthException e = (FirebaseAuthException )task.getException();
                    Toast.makeText(CreateProfile.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });



    }
}