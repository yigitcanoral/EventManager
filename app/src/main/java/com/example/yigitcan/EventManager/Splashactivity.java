package com.example.yigitcan.EventManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.flaviofaria.kenburnsview.KenBurnsView;

import java.util.Random;

//Splash Ekranını yazdım #GuneyhanKaan
    public class Splashactivity extends AppCompatActivity {
        KenBurnsView splashimage;
        ImageView splashtext;
        Context context = this;
        //Splash icin bir array yarattım her açılışta rastgele backgroun #GuneyhanKaan
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.splash_layout);
            splashimage= findViewById(R.id.splashImage);
            splashtext = findViewById(R.id.splashtxt);
            String splashBackground [] = {"splash_image2","splash_image3"};
            int randomId = new Random().nextInt(splashBackground.length);
            String randomCount = splashBackground[randomId];
            int resourceId = getResources().getIdentifier(randomCount,"drawable",getPackageName());
            splashimage.setImageResource(resourceId);
            splashimage = findViewById(R.id.splashImage);
            //Splashpassing çalıştırma
            new splashPassing().start();

        }

        //Thread ile Ekranı belirli bir süre çalıştırdıktan sonra AppLaunchActivity'e geçiş #GuneyhanKaan
        private class splashPassing extends Thread{
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(context,Activitysignup.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }




