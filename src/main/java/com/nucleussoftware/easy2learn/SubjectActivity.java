package com.nucleussoftware.easy2learn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubjectActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnHindi,btnEnglish,btnMaths,btnScience,btnEVS,btnComputer;
    public static String subject,action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        btnHindi  = findViewById(R.id.btnHindi);
        btnEnglish  = findViewById(R.id.btnEnglish);
        btnMaths  = findViewById(R.id.btnMaths);
        btnScience  = findViewById(R.id.btnScience);
        btnEVS  = findViewById(R.id.btnEVS);
        btnComputer  = findViewById(R.id.btnComputer);


        btnHindi.setOnClickListener(this);
        btnEnglish.setOnClickListener(this);
        btnMaths.setOnClickListener(this);
        btnScience.setOnClickListener(this);
        btnEVS.setOnClickListener(this);
        btnComputer.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnHindi:
                Intent intentChoosHindi=new Intent(SubjectActivity.this,ChooseActivity.class);
                //intentChoosHindi.putExtra("subject","HINDI");
                subject = "HINDI";
                startActivity(intentChoosHindi);
                break;

            case R.id.btnEnglish:
                Intent intentChoosEng=new Intent(SubjectActivity.this,ChooseActivity.class);
                //intentChoosEng.putExtra("subject","ENGLISH");
                subject = "ENGLISH";
                startActivity(intentChoosEng);
                break;

            case R.id.btnMaths:
                Intent intentChoosMath=new Intent(SubjectActivity.this,ChooseActivity.class);
                //intentChoosMath.putExtra("subject","MATH");
                subject = "MATH";
                startActivity(intentChoosMath);
                break;

            case R.id.btnScience:
                Intent intentChoosSci=new Intent(SubjectActivity.this,ChooseActivity.class);
                //intentChoosSci.putExtra("subject","SCIENCE");
                subject = "SCIENCE";
                startActivity(intentChoosSci);
                break;

            case R.id.btnEVS:
                Intent intentChoosEvs=new Intent(SubjectActivity.this,ChooseActivity.class);
                //intentChoosEvs.putExtra("subject","EVS/SST");
                subject = "EVS/SST";
                startActivity(intentChoosEvs);
                break;

            case R.id.btnComputer:
                Intent intentChoosComuter=new Intent(SubjectActivity.this,ChooseActivity.class);
                //intentChoosComuter.putExtra("subject","COMPUTER");
                subject = "COMPUTER";
                startActivity(intentChoosComuter);
                break;
        }
    }
}
