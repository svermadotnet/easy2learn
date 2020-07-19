package com.nucleussoftware.easy2learn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper myDb;
    EditText question, op1, op2, op3, op4;
    Button btnPreview,getData;
    Cursor res;
    int temCount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent=new Intent(MainActivity.this,SubjectActivity.class);
        startActivity(intent);

        /*
       myDb = new DatabaseHelper(this);

       question = findViewById(R.id.question);
        op1 = findViewById(R.id.option1);
        op2 = findViewById(R.id.option2);
        op3 = findViewById(R.id.option3);
        op4 = findViewById(R.id.option4);
        //answer = findViewById(R.id.answer);

        btnPreview  = findViewById(R.id.btnPreview);
        getData  = findViewById(R.id.getData);


        res = myDb.GetAllData();
        temCount =res.getCount();

        btnPreview.setOnClickListener(this);
        getData.setOnClickListener(this);

         */
    }


    public void ShowMessage(String Title, String message)
    {
        AlertDialog.Builder builder =  new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage((message));

        builder.show();
    }

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


    boolean validations()
    {
        if(question.getText().toString().trim().length() ==0 || op1.getText().toString().trim().length() ==0  ||
                op2.getText().toString().trim().length() ==0  || op3.getText().toString().trim().length() ==0  ||
                op4.getText().toString().trim().length() ==0 )

            return false;
        else
            return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnPreview:

                if(question.getText().toString().trim().length() ==0 || op1.getText().toString().trim().length() ==0  ||
                        op2.getText().toString().trim().length() ==0  || op3.getText().toString().trim().length() ==0  ||
                        op4.getText().toString().trim().length() ==0 )
                {
                    ShowMessage("Ooops ...","Please enter question and all the options");
                    return;
                }
                Intent intentCheckout=new Intent(MainActivity.this,QuestionPreview.class);
                // Pass value to MyCart activity
                intentCheckout.putExtra("question",question.getText().toString());
                intentCheckout.putExtra("option1",op1.getText().toString());
                intentCheckout.putExtra("option2",op2.getText().toString());
                intentCheckout.putExtra("option3",op3.getText().toString());
                intentCheckout.putExtra("option4",op4.getText().toString());

                startActivity(intentCheckout);
                /*
                boolean isInserted = myDb.insertData(question.getText().toString(),op1.getText().toString(),
                        op2.getText().toString(),op3.getText().toString(),op4.getText().toString(),answer.getText().toString());

                if(isInserted)
                    //Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG);
                    System.out.println("Data Inserted");

                else
                    //Toast.makeText(MainActivity.this,"Data Jnsertions failed",Toast.LENGTH_LONG);
                    System.out.println("Data Jnsertions failed");

                */
                break;
            case R.id.getData:

                Intent intentQuiz=new Intent(MainActivity.this,QuizActivity.class);

                startActivity(intentQuiz);

                /*
                 if(temCount>0) {
                     getDataSequentialy(res.getCount() - temCount);
                     temCount--;
                 }
                 else
                 {
                     ShowMessage("Quize","Quize finished");
                 }
                 */

                /*
                //System.out.println("Get Data button clicked");
                Cursor res = myDb.GetAllData();
                if(res.getCount() ==0)
                {
                    System.out.println("No data found");
                    ShowMessage("Error","No Data Found");
                    return;
                }

                StringBuffer stringBuffer = new StringBuffer();
                while (res.moveToNext()){
                    stringBuffer.append(("ID : "+ res.getString(0))+"\n");
                    stringBuffer.append(("question : "+ res.getString(2))+"\n");
                    stringBuffer.append(("option1 : "+ res.getString(3))+"\n");
                    stringBuffer.append(("option2 : "+ res.getString(4))+"\n");
                    stringBuffer.append(("option3 : "+ res.getString(5))+"\n");
                    stringBuffer.append(("option4 : "+ res.getString(6))+"\n");
                    stringBuffer.append(("Answer: "+ res.getString(7))+"\n");

                }

                ShowMessage("Data",stringBuffer.toString());

                */
                break;
        }
    }
}
