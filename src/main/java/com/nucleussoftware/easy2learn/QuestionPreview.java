package com.nucleussoftware.easy2learn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class QuestionPreview extends AppCompatActivity  implements View.OnClickListener{

    String quesion,option1,option2,option3,option4,answer;
    TextView txtQuestion, txtOption1,txtOption2,txtOption3,txtOption4,toolbar_title;
    Button btnSave;
    CheckBox chkOption1,chkOption2,chkOption3,chkOption4;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_preview);

        // Set back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar_title  = findViewById(R.id.toolbar_title);
        toolbar_title.setText(toolbar_title.getText().toString() + "    ::   "+SubjectActivity.subject);


        txtQuestion = findViewById(R.id.txtQuestion);
        txtOption1 = findViewById(R.id.txtOption1);
        txtOption2 = findViewById(R.id.txtOption2);
        txtOption3 = findViewById(R.id.txtOption3);
        txtOption4 = findViewById(R.id.txtOption4);

        btnSave = findViewById(R.id.btnSave);

        chkOption1 = findViewById(R.id.chkOption1);
        chkOption2 = findViewById(R.id.chkOption2);
        chkOption3 = findViewById(R.id.chkOption3);
        chkOption4 = findViewById(R.id.chkOption4);


        // Get Value which were passed from previous activity RedeemPoints
        quesion = getIntent().getExtras().getString("question");
        option1 = getIntent().getExtras().getString("option1");
        option2 = getIntent().getExtras().getString("option2");
        option3 = getIntent().getExtras().getString("option3");
        option4 = getIntent().getExtras().getString("option4");


        // Bind Data in current page


        txtQuestion.setText(quesion);
        txtOption1.setText(option1);
        txtOption2.setText(option2);
        txtOption3.setText(option3);
        txtOption4.setText(option4);


        myDb = new DatabaseHelper(this);

        btnSave.setOnClickListener(this);


    }

    public void ShowMessage(String Title, String message)
    {
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage((message));

        builder.show();
    }

    // Set ChooseActivity activity on click of back button
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), PrepareMaterialActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.btnSave:

                if (!chkOption1.isChecked() && !chkOption2.isChecked() && !chkOption3.isChecked() && !chkOption4.isChecked()) {
                    ShowMessage("Error !!!","Please select at atleast one checkbox for answer!!");
                    System.out.println("Please select at atleast one checkbox for answer!!");
                    return;
                }


                int checkCount =0;
                if (chkOption1.isChecked() ) {
                    answer = option1;
                    checkCount++;
                }
                if (chkOption2.isChecked() ) {
                    answer = option2;
                    checkCount++;
                }
                if (chkOption3.isChecked() ) {
                    answer = option3;
                    checkCount++;
                }
                if (chkOption4.isChecked() ) {
                    answer = option4;
                    checkCount++;
                }

                if (checkCount>1) {
                    ShowMessage("Error !!!","Please select only one checkbox for answer!!");
                    System.out.println("Please select only one checkbox for answer!!");
                    return;
                }

               boolean isInserted = myDb.insertData(quesion,option1, option2,option3,option4,answer);
                //boolean isInserted = myDb.deleteData();
                if(isInserted) {
                    ShowMessage("Success !!!", "Data Saved successfully!!");

                    // Move back to choose activity
                    Intent intentChoosMath=new Intent(QuestionPreview.this,ChooseActivity.class);
                    startActivity(intentChoosMath);
                }
                else
                    ShowMessage("Error !!!","There is some problem,Please contact to adminstrator!!");

                break;
        }
    }
}
