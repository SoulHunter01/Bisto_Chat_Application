package com.example.abdullahihsani181654__i180543;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddContact extends AppCompatActivity {
    ImageButton image_upload;
    ImageView circular_image;
    EditText FirstName;
    EditText LastName;
    EditText PhoneNumber;
    Button add_contact_button;
    int pic_id=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        image_upload=findViewById(R.id.image_upload_button);
        circular_image=findViewById(R.id.circular_image);
        FirstName=findViewById(R.id.FirstName);
        LastName=findViewById(R.id.LastName);
        PhoneNumber=findViewById(R.id.PhoneNumber);
        add_contact_button=findViewById(R.id.add_contact_button);


        add_contact_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddContact.this,Chats.class);

                circular_image.buildDrawingCache();
                Bitmap bitmap = circular_image.getDrawingCache();
                intent.putExtra("BitmapImage", bitmap);
                intent.putExtra("FirstName",FirstName.getText().toString());
                intent.putExtra("LastName",LastName.getText().toString());
                intent.putExtra("PhoneNumber",PhoneNumber.getText().toString());
                startActivity(intent);
            }
        });



        image_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(camera_intent, pic_id);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            circular_image.setImageBitmap(photo);
        }
    }
}
