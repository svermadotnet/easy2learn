package com.nucleussoftware.easy2learn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;

public class AssessmentActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView txtAssessment,toolbar_title;
    TextView txtCorrect,txtIncorrect,txtPercentage,txtStatus;
    //float Correct,Incorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);

        // Set back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar_title  = findViewById(R.id.toolbar_title);
        toolbar_title.setText(toolbar_title.getText().toString() + "    ::   "+SubjectActivity.subject);


        myDb = new DatabaseHelper(this);
        txtAssessment = findViewById(R.id.txtAssessment);

        txtCorrect = findViewById(R.id.txtCorrect);
        txtIncorrect = findViewById(R.id.txtIncorrect);
        txtPercentage = findViewById(R.id.txtPercentage);
        txtStatus = findViewById(R.id.txtStatus);


        Cursor res = myDb.GetSubjectData(SubjectActivity.subject);

        // Binf Listview Data
        ListView listView = findViewById(R.id.listView);
        CustLstViewAssement custLstViewAssement = null;
        custLstViewAssement = new CustLstViewAssement(this,res);
        listView.setAdapter(custLstViewAssement);


        // Calculate Percentage
        float correct=0,incorrect=0,total=res.getCount();

        for (int i =0;i<total;i++)
        {
            res.moveToPosition(i);
            if(res.getString(7).equals(QuizActivity.UserAnswer[i]))
                correct++;
            else
                incorrect++;
        }

        double perc =(float) ((correct / total) * 100);

        txtCorrect.setText( "Correct : "+Integer.toString((int)correct));
        txtIncorrect.setText("Incorrect : "+Integer.toString((int)incorrect));

        txtPercentage.setText("Percentage : "+Integer.toString((int)perc));

        // Change Color
        if(perc <60)
        {
            txtStatus.setText("Staus : FAIL");
            txtStatus.setTextColor(Color.RED);
        }
        else
        {
            txtStatus.setText("Staus : PASS");
            txtStatus.setTextColor(Color.GREEN);
        }

        /*
        System.out.println("correct     : "+correct);
        System.out.println("incorrect   : "+incorrect);
        System.out.println("total : "+res.getCount());
        */
    }


    // Set ChooseActivity activity on click of back button
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), SubjectActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


    public void ShowMessage(String Title, String message)
    {
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage((message));

        builder.show();
    }
}
