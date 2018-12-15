package com.example.yigitcan.EventManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Activityshowvenues extends AppCompatActivity {
    private RecyclerView cycler;
    private List<Venue> venues;
    FirebaseFirestore db =  FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvenues);
        cycler=(RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm= new LinearLayoutManager(this);
        cycler.setLayoutManager(llm);
        cycler.setHasFixedSize(true);

        SeeVenue();
    }

    public void SeeVenue()
    {
        venues = new ArrayList<>();
        db.collection("Venues")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d("ad", document.getId() + " => " + document.getData()+document.getData().size());

                        byte[] d= Base64.decode(document.getString("picture"),Base64.DEFAULT);
                        Bitmap decodedbyte = BitmapFactory.decodeByteArray(d,0,d.length);
                     Venue vinstance = new Venue(document.getString("name")
                             ,document.getString("address")
                             ,document.getString("details")
                             ,document.getString("phone")
                             ,decodedbyte);
                        if (Activitymainpage.bl())
                        {
                            venues.add(vinstance);
                        }
                        else
                        {
                             String venuestring
                                     = document.getString("name")+
                                    document.getString("address")+
                                    document.getString("details")+
                                    document.getString("phone");
                            if (venuestring.contains(Activitymainpage.searchvalue))
                            {
                                venues.add(vinstance);
                            }
                        }
                    }
                    RVAdapter adp= new RVAdapter(venues);
                    cycler.setAdapter(adp);


                } else {
                    Log.w("awd", "Error getting documents.", task.getException());
                }
            }
        });
    }

}
