package com.example.yigitcan.EventManager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Activityaddvenue extends AppCompatActivity {
    private static final int IMAGE_GALLERY_REQUEST = 20;
    private EditText namee;
    private EditText detailss;
    private EditText address;
    private EditText phonee;
    private ImageView iview;
    private RadioButton checkvenue;

    FirebaseFirestore db =  FirebaseFirestore.getInstance();
    Map<String,String> venueinstance = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvenue);
        iview=(ImageView)findViewById(R.id.imgview);
        namee=(EditText)findViewById(R.id.namee);
        detailss=(EditText)findViewById(R.id.detailss);
        address=(EditText)findViewById(R.id.addresss);
        phonee=(EditText)findViewById(R.id.phonee);
        checkvenue=(RadioButton)findViewById(R.id.isvenue);
    }

    public void butttonOnClick(View v)
    {
        if (v.getId()==R.id.addv)
        {
            AddVenue();
        }
        if (v.getId()==R.id.selectimage)
        {
            ChooseFromGallery();
        }
    }
    public void ChooseFromGallery()
    {
        Intent it= new Intent(Intent.ACTION_PICK);

        File choose= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String path= choose.getPath();

        Uri imagee= Uri.parse(path);

        it.setDataAndType(imagee,"image/*");
        startActivityForResult(it, IMAGE_GALLERY_REQUEST);

    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        if (resultCode == RESULT_OK) {
            Uri imguri = data.getData();
            InputStream inst;
            try {
                inst = getContentResolver().openInputStream(imguri);
                Bitmap image= BitmapFactory.decodeStream(inst);
                iview.setImageBitmap(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void AddVenue()
    {
        iview.buildDrawingCache();
        Bitmap bmap= iview.getDrawingCache();
        ByteArrayOutputStream st= new ByteArrayOutputStream();
        bmap.compress(Bitmap.CompressFormat.JPEG,100,st);
        byte[] byteformat = st.toByteArray();
        String bytestring= Base64.encodeToString(byteformat,Base64.NO_WRAP);
        if (checkvenue.isChecked())
        {
            venueinstance.put("name","VENUE: "+namee.getText().toString());
        }
        else
        {
            venueinstance.put("name","ADVERT: "+namee.getText().toString());
        }
        venueinstance.put("address",address.getText().toString());
        venueinstance.put("details",detailss.getText().toString());
        venueinstance.put("phone",phonee.getText().toString());
        venueinstance.put("picture",bytestring);
        db.collection("Venues").add(venueinstance)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("asd", "onSuccess:"+documentReference.getId());
                        startActivity(new Intent(Activityaddvenue.this,Activitymainpage.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("asd","Error occured",e);
                Toast.makeText(Activityaddvenue.this, "Error occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
