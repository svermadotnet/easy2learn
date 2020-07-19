package com.nucleussoftware.easy2learn;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class PrepareMaterialActivity extends AppCompatActivity implements View.OnClickListener{

    //DatabaseHelper myDb;
    EditText question, op1, op2, op3, op4;
    Button btnPreview,getData;
    TextView toolbar_title;
    ImageView btnSpeech;
    String evalue,speechLangu="E";
    RadioButton rdEnglish,rdHindi;
    //Cursor res;
    //int temCount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_material);

        // Set back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        toolbar_title  = findViewById(R.id.toolbar_title);
        toolbar_title.setText(toolbar_title.getText().toString() + "    ::   "+SubjectActivity.subject);


        rdEnglish  = findViewById(R.id.rdEnglish);
        rdHindi  = findViewById(R.id.rdHindi);

        btnSpeech = findViewById(R.id.btnSpeech);
        //myDb = new DatabaseHelper(this);

        question = findViewById(R.id.question);
        op1 = findViewById(R.id.option1);
        op2 = findViewById(R.id.option2);
        op3 = findViewById(R.id.option3);
        op4 = findViewById(R.id.option4);
        //answer = findViewById(R.id.answer);

        btnPreview  = findViewById(R.id.btnPreview);
        getData  = findViewById(R.id.getData);


        //res = myDb.GetAllData();
        //temCount =res.getCount();

        btnPreview.setOnClickListener(this);
        btnSpeech.setOnClickListener(this);
        rdEnglish.setOnClickListener(this);
        rdHindi.setOnClickListener(this);

        SetTouchListner();

    }


    // Set ChooseActivity activity on click of back button
    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), ChooseActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    void setSpeechLanguage(String _language)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

        if(_language =="E") // English
        {
            //Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        }
        if(_language =="H") // Hindi
        {
            String languagePref = "hi";
           // Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languagePref);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, languagePref);
            intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, languagePref);
        }

        if (intent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(intent,10);
        }
        else
        {
            ShowMessage("Show","Your device don't support speech input");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.rdEnglish:
                speechLangu="E"; // English
                break;
            case R.id.rdHindi:
                speechLangu="H"; // Hindi
                break;
            case R.id.btnPreview:

                if(question.getText().toString().trim().length() ==0 || op1.getText().toString().trim().length() ==0  ||
                        op2.getText().toString().trim().length() ==0  || op3.getText().toString().trim().length() ==0  ||
                        op4.getText().toString().trim().length() ==0 )
                {
                    ShowMessage("Ooops ...","Please enter question and all the options");
                    return;
                }
                Intent intentCheckout=new Intent(PrepareMaterialActivity.this,QuestionPreview.class);
                // Pass value to MyCart activity
                intentCheckout.putExtra("question",question.getText().toString());
                intentCheckout.putExtra("option1",op1.getText().toString());
                intentCheckout.putExtra("option2",op2.getText().toString());
                intentCheckout.putExtra("option3",op3.getText().toString());
                intentCheckout.putExtra("option4",op4.getText().toString());

                startActivity(intentCheckout);

                break;

            case R.id.btnSpeech:
                setSpeechLanguage(speechLangu);
/*
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
*/

/*
                String languagePref = "hi";
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, languagePref);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, languagePref);
                intent.putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, languagePref);
*/
/*
                if (intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivityForResult(intent,10);
                }
                else
                {
                    ShowMessage("Show","Your device don't support speech input");
                }
*/
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode)
        {
            case 10:
                if(resultCode == RESULT_OK && data != null)
                {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //question.setText(result.get(0));

                    if(evalue=="1")
                    {
                        op1.setText(result.get(0));
                    }
                    if(evalue=="2")
                    {
                        op2.setText(result.get(0));
                    }
                    if(evalue=="3")
                    {
                        op3.setText(result.get(0));
                    }
                    if(evalue=="4")
                    {
                        op4.setText(result.get(0));
                    }
                    if(evalue=="5")
                    {
                        question.setText(result.get(0));
                    }

                }
                break;
        }
    }

    public void ShowMessage(String Title, String message)
    {
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage((message));

        builder.show();
    }

    /*
    public void getDataSequentialy(int questionNumber)
    {
        if(res.getCount() ==0)
        {
            System.out.println("No data found");
            ShowMessage("Error","No Data Found");
            return;
        }

        StringBuffer stringBuffer = new StringBuffer();

        res.moveToPosition(questionNumber);

        stringBuffer.append(("ID : "+ res.getString(0))+"\n");
        stringBuffer.append(("question : "+ res.getString(2))+"\n");
        stringBuffer.append(("option1 : "+ res.getString(3))+"\n");
        stringBuffer.append(("option2 : "+ res.getString(4))+"\n");
        stringBuffer.append(("option3 : "+ res.getString(5))+"\n");
        stringBuffer.append(("option4 : "+ res.getString(6))+"\n");
        stringBuffer.append(("Answer: "+ res.getString(7))+"\n");

        ShowMessage("Data",stringBuffer.toString());
    }
*/

    boolean validations()
    {
        if(question.getText().toString().trim().length() ==0 || op1.getText().toString().trim().length() ==0  ||
                op2.getText().toString().trim().length() ==0  || op3.getText().toString().trim().length() ==0  ||
                op4.getText().toString().trim().length() ==0 )

            return false;
        else
            return true;
    }

    void SetTouchListner()
    {
        op1.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                evalue="1";
                return false;
            }
        });

        op2.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                evalue="2";
                return false;
            }
        });

        op3.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                evalue="3";
                return false;
            }
        });

        op4.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                evalue="4";
                return false;
            }
        });

        question.setOnTouchListener(new View.OnTouchListener()
        {
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                evalue="5";
                return false;
            }
        });
    }

}
