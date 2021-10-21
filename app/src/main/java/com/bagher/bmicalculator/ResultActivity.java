package com.bagher.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ResultActivity extends AppCompatActivity {


    AdView adView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Initialize();

        adView2=findViewById(R.id.adView);


        Intent intent = getIntent();

        float BMI  = Math.round((intent.getFloatExtra("BMI", 0) * 100) / 100);
        String age_value = intent.getStringExtra("age");

        TextView your_bmi = findViewById(R.id.your_bmi);
        your_bmi.setText(String.valueOf(BMI));

        TextView age = findViewById(R.id.age);
        age.setText(age_value);

        TextView category = findViewById(R.id.category);
        Category category1 = new Category();
        category.setText(category1.getCategory(BMI));

        TextView condition = findViewById(R.id.condition);
        Condition condition1 = new Condition();
        condition.setText(condition1.getCategory(BMI));

        Button recalculate = findViewById(R.id.recalculate);
        recalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUI();
            }
        });


        Load2();

    }

    private void updateUI() {
        Intent intent1 = new Intent(ResultActivity.this,MainActivity.class);
        startActivity(intent1);
        fileList();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateUI();
    }

    private void Initialize() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

    void Load2(){


        adView2 = new AdView(this);
        adView2.setAdSize(AdSize.SMART_BANNER);
        adView2.setAdUnitId("ca-app-pub-5113000083669228/5468556398");
        AdRequest adRequest = new AdRequest.Builder().build();
        adView2.loadAd(adRequest);

        adView2.setAdListener(new AdListener(){

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {

                Toast.makeText(ResultActivity.this, loadAdError.getMessage(), Toast.LENGTH_SHORT).show();
//                super.onAdFailedToLoad(loadAdError);
            }
        });
    }


}