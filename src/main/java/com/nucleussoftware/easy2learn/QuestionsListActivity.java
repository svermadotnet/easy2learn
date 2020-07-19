package com.nucleussoftware.easy2learn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class QuestionsListActivity extends AppCompatActivity implements CustLstViewQuestions.customButtonListener {

    DatabaseHelper myDb;
    ListView listView;
    TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_list);

        // Set back button on toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar_title  = findViewById(R.id.toolbar_title);
        toolbar_title.setText(toolbar_title.getText().toString() + "    ::   "+SubjectActivity.subject);

        myDb = new DatabaseHelper(this);
        Cursor res = myDb.GetSubjectData(SubjectActivity.subject);
        //Cursor res = myDb._GetAllData();

        // Binf Listview Data
        listView = findViewById(R.id.listView);
        CustLstViewQuestions custLstViewQuestions = new CustLstViewQuestions(this,res);
        custLstViewQuestions.setCustomButtonListner(QuestionsListActivity.this); // Added click even for delete button
        listView.setAdapter(custLstViewQuestions);
    }

    CustLstViewQuestions custLstViewQuestions;
    @Override
    public void onDeleteButtonClickListner(int position, final String ID, String question, String op1, String op2, String op3, String op4) {
        //System.out.println("Delete button clicked for - ID :");
        //ShowMessage("Show","Button clicked"+"\nID : "+ID+"\nquestion : "+question+"\nop1 : "+op1+"\nop2 : "+op2+"\nop3 : "+op3+"\nop4 : "+op4);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Alert !!!");
        builder.setMessage("Are you sure want to delete this question?");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //ShowMessage("Show","Positive Called");
                        //Delete the record from database
                        myDb.deleteData(ID);

                        //custLstViewQuestions.notifyDataSetChanged();


                        // Redirect to
                        Intent intentChoosMath=new Intent(QuestionsListActivity.this,ChooseActivity.class);
                        startActivity(intentChoosMath);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //ShowMessage("Show","Negative Called");
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

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
        Intent myIntent = new Intent(getApplicationContext(), ChooseActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }


}
