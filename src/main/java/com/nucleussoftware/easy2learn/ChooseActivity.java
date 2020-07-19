package com.nucleussoftware.easy2learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ChooseActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnQuiz,btnPrepare,btnDelete;
    //public static String subject,action;
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        // Set back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Get Value which were passed from previous activity Subject Activity
        //subject = getIntent().getExtras().getString("subject");


        btnQuiz  = findViewById(R.id.btnQuiz);
        btnPrepare  = findViewById(R.id.btnPrepare);
        btnDelete = findViewById(R.id.btnDelete);

        toolbar_title  = findViewById(R.id.toolbar_title);
        toolbar_title.setText(toolbar_title.getText().toString() + "    ::   "+SubjectActivity.subject);



        btnQuiz.setOnClickListener(this);
        btnPrepare.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnPrepare:
                //action = "prepare";
                Intent intentPrepare=new Intent(ChooseActivity.this,PrepareMaterialActivity.class);
                startActivity(intentPrepare);

                break;

            case R.id.btnQuiz:
                //action = "quiz";
                Intent intentQuiz=new Intent(ChooseActivity.this,QuizActivity.class);
                startActivity(intentQuiz);

                break;

            case R.id.btnDelete:
                //action = "quiz";
                Intent intentDelete=new Intent(ChooseActivity.this,QuestionsListActivity.class);
                startActivity(intentDelete);

                break;
        }
    }

    // Set ChooseActivity activity on click of back button
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), SubjectActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}
