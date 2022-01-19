package com.example.abdullahihsani181654__i180543;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    TextView get_email;
    FirebaseAuth mAuth;
    Button Login_Button;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        get_email = findViewById(R.id.get_email_from_view);
        mAuth = FirebaseAuth.getInstance();
        Login_Button = findViewById(R.id.loginbutton);
        Login_Button.setOnClickListener(v -> mAuth.sendPasswordResetEmail(get_email.getText().toString())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPassword.this, "Reset Email Instructions Sent To " + get_email.getText().toString(), Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(ForgotPassword.this, get_email.getText().toString() + " Does Not Exist", Toast.LENGTH_LONG).show();
                        Log.e("TAG",get_email.getText().toString());
                    }
                }));
    }
}