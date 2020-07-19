package com.nucleussoftware.easy2learn;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class CustLstViewQuestions extends ArrayAdapter<String> {

    private String[] option1,option2,option3,option4,question,Seq,ID;

    private Activity context;

    customButtonListener customListner;

    public interface customButtonListener {
        public void onDeleteButtonClickListner(int position, String ID, String question, String op1, String op2, String op3, String op4);
    }

    public void setCustomButtonListner(customButtonListener listener) {
        this.customListner = listener;
    }


    public CustLstViewQuestions(Activity context, Cursor res1)  {
        super(context, R.layout.listview_questions,new String[res1.getCount()]);

        this.context=context;

        DatabaseHelper myDb = new DatabaseHelper(context);
        Cursor res = myDb._GetAllData();

        option1 = new String[res.getCount()];
        option2 = new String[res.getCount()];
        option3 = new String[res.getCount()];
        option4 = new String[res.getCount()];
        question = new String[res.getCount()];
        Seq = new String[res.getCount()];
        ID = new String[res.getCount()];

        int quesNumber =0;

        for(int j=0;j<res.getCount();j++)
        {
            res.moveToPosition(j);
            quesNumber++;
            Seq[j] = Integer.toString(quesNumber);
            ID[j] = res.getString(0);
            question[j] = res.getString(2);
            option1[j] =  res.getString(3);
            option2[j] = res.getString(4);
            option3[j] = res.getString(5);
            option4[j] = res.getString(6);


        }

    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

       View view = convertView;

        ViewHolder viewHolder = null;
        if(view==null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view=layoutInflater.inflate(R.layout.listview_questions,null,true);

            viewHolder = new ViewHolder(view);

            viewHolder.btnDelete= (ImageButton) view.findViewById(R.id.btnDelete);

            view.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }


        viewHolder.option1.setText("a. " +option1[position]);
        viewHolder.option2.setText("b. " +option2[position]);
        viewHolder.option3.setText("c. " +option3[position]);
        viewHolder.option4.setText("d. " +option4[position]);
        viewHolder.txtQuestion.setText(Seq[position]+". "+question[position]);


        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customListner != null) {
                    System.out.println("Delete button clicked:");
                    customListner.onDeleteButtonClickListner(position, ID[position], question[position], option1[position], option2[position], option3[position], option4[position]);
                }
            }
        });


        return view;
    }

    class ViewHolder{
        TextView option1,option2,option3,option4,txtQuestion;
        TextView txtAnswer;
        TextView txtUserAnswer;
        ImageButton btnDelete;

        ViewHolder( View v)
        {
            option1=(TextView)v.findViewById(R.id.option1);
            option2=(TextView)v.findViewById(R.id.option2);
            option3=(TextView)v.findViewById(R.id.option3);
            option4=(TextView)v.findViewById(R.id.option4);
            txtQuestion=(TextView)v.findViewById(R.id.txtQuestion);
            btnDelete=(ImageButton)v.findViewById(R.id.btnDelete);
        }
    }
}
