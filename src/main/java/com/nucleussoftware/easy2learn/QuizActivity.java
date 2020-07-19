package com.nucleussoftware.easy2learn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{

    String quesion,option1,option2,option3,option4,answer ="",actualAns;
    TextView txtQuestion, txtOption1,txtOption2,txtOption3,txtOption4,txtAnswer,toolbar_title;
    Button btnNext;
    CheckBox chkOption1,chkOption2,chkOption3,chkOption4;
    DatabaseHelper myDb;

    ImageView img1,img2,img3,img4;

    Cursor res;
    int temCount,index ;
    public static String[] UserAnswer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_quiz);

        myDb = new DatabaseHelper(this);


        toolbar_title  = findViewById(R.id.toolbar_title);
        toolbar_title.setText(toolbar_title.getText().toString() + "    ::   "+SubjectActivity.subject);


        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        //VisibleFalseImage();

        txtQuestion = findViewById(R.id.txtQuestion);
        txtOption1 = findViewById(R.id.txtOption1);
        txtOption2 = findViewById(R.id.txtOption2);
        txtOption3 = findViewById(R.id.txtOption3);
        txtOption4 = findViewById(R.id.txtOption4);
        txtAnswer =  findViewById(R.id.txtAnswer);

        txtAnswer.setText(""); // Set blank answer at load time

        btnNext = findViewById(R.id.btnNext);

        chkOption1 = findViewById(R.id.chkOption1);
        chkOption2 = findViewById(R.id.chkOption2);
        chkOption3 = findViewById(R.id.chkOption3);
        chkOption4 = findViewById(R.id.chkOption4);

        myDb = new DatabaseHelper(this);

        res = myDb.GetSubjectData(SubjectActivity.subject);
        temCount =res.getCount();

        UserAnswer = new String[res.getCount()];
        //actualAns = new String[res.getCount()];

        if(temCount==0) {
            ShowMessage("No Data Found", "Questions are not availabe for subject : " + SubjectActivity.subject);
            disableCheckBox();
        }
        //getDataSequentialy(0); // Set first question detail
        index =0;

        btnNext.setOnClickListener(this);
        chkOption1.setOnClickListener(this);
        chkOption2.setOnClickListener(this);
        chkOption3.setOnClickListener(this);
        chkOption4.setOnClickListener(this);

        // Set back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    void disableCheckBox()
    {
        chkOption1.setEnabled(false);
        chkOption2.setEnabled(false);
        chkOption3.setEnabled(false);
        chkOption4.setEnabled(false);
    }

    void enableCheckBox()
    {
        chkOption1.setEnabled(true);
        chkOption2.setEnabled(true);
        chkOption3.setEnabled(true);
        chkOption4.setEnabled(true);
    }

    void VisibleFalseImage()
    {
        img1.setVisibility(View.GONE);
        img2.setVisibility(View.GONE);
        img3.setVisibility(View.GONE);
        img4.setVisibility(View.GONE);
    }


    void setIcon(ImageView img, String userAnswer, String actualAnswer)
    {
        img.setVisibility(View.VISIBLE);
        System.out.println("userAnswer     : "+userAnswer);
        System.out.println("actualAnswer     : "+actualAnswer);

        if(userAnswer.equals(actualAnswer))
            img.setBackgroundResource(R.drawable.ic_done_black_24dp);
        else
            img.setBackgroundResource(R.drawable.ic_clear_black_24dp);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case  R.id.chkOption1:
                if(chkOption1.isChecked())
                    txtAnswer.setVisibility(View.VISIBLE);
                    UserAnswer[index] = txtOption1.getText().toString();
                    setIcon(img1,UserAnswer[index],actualAns);
                    disableCheckBox();


                    //CheckAnswer(txtOption1.getText().toString());
                    //ShowMessage("Quize", "Clicked on option 1");
                break;
            case  R.id.chkOption2:
                if(chkOption2.isChecked())
                    txtAnswer.setVisibility(View.VISIBLE);
                    UserAnswer[index] = txtOption2.getText().toString();
                    setIcon(img2,UserAnswer[index],actualAns);
                    disableCheckBox();

                    //CheckAnswer(txtOption2.getText().toString());
                    //ShowMessage("Quize", "Clicked on option 2");
                break;
            case  R.id.chkOption3:
                if(chkOption3.isChecked())
                    txtAnswer.setVisibility(View.VISIBLE);
                    UserAnswer[index] = txtOption3.getText().toString();
                    setIcon(img3,UserAnswer[index],actualAns);
                    disableCheckBox();
                    //CheckAnswer(txtOption3.getText().toString());
                    //ShowMessage("Quize", "Clicked on option 3");
                break;
            case  R.id.chkOption4:
                if(chkOption4.isChecked())
                    txtAnswer.setVisibility(View.VISIBLE);
                    UserAnswer[index] = txtOption4.getText().toString();
                    setIcon(img4,UserAnswer[index],actualAns);
                    disableCheckBox();
                    //CheckAnswer(txtOption4.getText().toString());
                    //ShowMessage("Quize", "Clicked on option 4");

                break;

            case R.id.btnNext:

                if(!Validations())
                    return;

                if (temCount > 0) {
                    enableCheckBox();
                    VisibleFalseImage();
                    getDataSequentialy(res.getCount() - temCount);
                  /*
                    if(!CheckAnswer() && temCount <res.getCount())
                    {
                        ShowMessage("Quize", "Your answer is incorrect");
                        return;
                    }
                    */
                    temCount--;
                    clearCheckBox();
                } else {
                    ShowMessage("Quiz", "Quiz finished");

                    Intent intentAssess=new Intent(QuizActivity.this,AssessmentActivity.class);
                    startActivity(intentAssess);
                }
        }
    }

    // Set ChooseActivity activity on click of back button
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ChooseActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void getDataSequentialy(int questionNumber)
    {
        if(res.getCount() ==0)
        {
            System.out.println("No data found");
            ShowMessage("Error","No Data Found");
            return;
        }

        res.moveToPosition(questionNumber);

        // Bind Data in current page
        txtQuestion.setText( Integer.toString(questionNumber+1)+". "+ res.getString(2));
        txtOption1.setText(res.getString(3));
        txtOption2.setText(res.getString(4));
        txtOption3.setText(res.getString(5));
        txtOption4.setText(res.getString(6));
        answer = res.getString(7);
        txtAnswer.setText("ANSWER :  " + res.getString(7));
        txtAnswer.setVisibility(View.INVISIBLE);

        actualAns=res.getString(7);

        System.out.println("answer ==> "+answer);
        index=questionNumber;
    }

    boolean CheckAnswer(String userAnswer)
    {
        System.out.println("answer ==> "+answer + " and userAnswer ==> "+userAnswer);

        if(userAnswer.toLowerCase().trim() == answer.toLowerCase().trim())
        {
            ShowMessage("Sucess","Your answer is correct");
            return true;
        }
        else
        {
            ShowMessage("Ooops...","Incorrect answer...");
            return true;
        }
    }

    /*
    boolean CheckAnswer()
    {
        String userAnswer="";
        if(chkOption1.isChecked())
            userAnswer = txtOption1.getText().toString();
        else if(chkOption1.isChecked())
            userAnswer = txtOption2.getText().toString();
        else if(chkOption1.isChecked())
            userAnswer = txtOption3.getText().toString();
        else if(chkOption1.isChecked())
            userAnswer = txtOption4.getText().toString();


        System.out.println("answer ==> "+answer +" userAnswer ==> "+userAnswer);
        System.out.println("answer ==> "+answer.length() +" userAnswer ==> "+userAnswer.length());

        if(userAnswer == answer)
            return true;
        else
            return false;
    }
    */

    void clearCheckBox()
    {
        chkOption1.setChecked(false);
        chkOption2.setChecked(false);
        chkOption3.setChecked(false);
        chkOption4.setChecked(false);
    }

    boolean Validations()
    {
        if (!chkOption1.isChecked() && !chkOption2.isChecked() && !chkOption3.isChecked() && !chkOption4.isChecked()) {
            ShowMessage("Error !!!","Please select at atleast one checkbox for answer!!");
            System.out.println("Please select at atleast one checkbox for answer!!");
            return false;
        }


        int checkCount =0;
        if (chkOption1.isChecked() ) {
            checkCount++;
        }
        if (chkOption2.isChecked() ) {
            checkCount++;
        }
        if (chkOption3.isChecked() ) {
            checkCount++;
        }
        if (chkOption4.isChecked() ) {
            checkCount++;
        }

        if (checkCount>1) {
            ShowMessage("Error !!!","Please select only one checkbox for answer!!");
            System.out.println("Please select only one checkbox for answer!!");
            return false;
        }

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
