package com.example.yigitcan.EventManager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class Activitymainpage extends AppCompatActivity implements View.OnClickListener {
    public static boolean showall;
    public static String searchvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        findViewById(R.id.imageView8).setOnClickListener((View.OnClickListener) this);

    }
    public static boolean bl()
    {
        return showall;
    }
    public void buttonOnClick(View v)
    {
       if (v.getId()==R.id.addvenue)
        {
           startActivity(new Intent(Activitymainpage.this,Activityaddvenue.class));
        }
        if (v.getId()==R.id.showvenues)
        { showall=true;
           startActivity(new Intent(Activitymainpage.this,Activityshowvenues.class));
        }
        if (v.getId()==R.id.searchbutton)
        {
            showall=false;
            searchvalue=((EditText) findViewById(R.id.searchfield)).getText().toString();

            startActivity(new Intent(Activitymainpage.this,Activityshowvenues.class));
        }

    }

    @Override
    public void onClick(View v) {
        FirebaseAuth.getInstance().signOut();
        Intent intentSignOut=new Intent(this,Activitysignup.class);
        intentSignOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentSignOut);

    }
}
