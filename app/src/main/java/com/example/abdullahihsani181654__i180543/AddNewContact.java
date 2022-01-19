package com.example.abdullahihsani181654__i180543;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AddNewContact extends AppCompatActivity {

    ImageButton add_contact;
    ImageButton add_contact_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);
        add_contact=findViewById(R.id.add_contact);
        add_contact_group=findViewById(R.id.add_contact_group);


        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddNewContact.this,AddContact.class);
                startActivity(intent);
            }
        });

        add_contact_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddNewContact.this,AddContactGroup.class);
                startActivity(intent);
            }
        });




    }
}