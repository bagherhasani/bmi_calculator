package com.example.instagramvideodownloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.hcr2bot.instagramvideosdownloader.InstaVideo;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Grantpermission();
        Initialize();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InstaVideo.downloadVideo(getApplicationContext(),editText.getText().toString());
            }
        });


            load1();


    }

    private void Initialize() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        editText=findViewById(R.id.edtx);
        button=findViewById(R.id.btn);
        adView=findViewById(R.id.adView);

    }

    private void Grantpermission() {

        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
        else { //you dont need to worry about these stuff below api level 23

        }
    }


    void load1(){


        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId("ca-app-pub-5113000083669228/6118782339");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener(){

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {

                Toast.makeText(MainActivity.this, loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
//                super.onAdFailedToLoad(loadAdError);
            }
        });
}

    }