package com.nucleussoftware.easy2learn;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustLstViewAssement extends ArrayAdapter<String> {

    private String[] SrNo;
    private String[] Answer;

    private Activity context;


    public CustLstViewAssement(Activity context,Cursor res1)  {
        super(context, R.layout.listview_assement,new String[res1.getCount()]);

        this.context=context;

        DatabaseHelper myDb = new DatabaseHelper(context);
        Cursor res = myDb.GetSubjectData(SubjectActivity.subject);

        //total = res.getCount();

        SrNo = new String[res.getCount()];
        Answer = new String[res.getCount()];

        int quesNumber =0;

        for(int j=0;j<res.getCount();j++)
        {
            res.moveToPosition(j);
            quesNumber++;
            SrNo[j] = Integer.toString(quesNumber);
            Answer[j] = res.getString(7);

        //    System.out.println("SrNo["+Integer.toString(j)+"] ==> " + SrNo[j] + " and Answer[j] ==> "+Answer[j] + " and UserAnswer[j] ==> "+ QuizActivity.UserAnswer[j]);

        }

    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

       View view = convertView;

        ViewHolder viewHolder = null;
        if(view==null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view=layoutInflater.inflate(R.layout.listview_assement,null,true);

            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);

        }
        else
        {
            viewHolder = (ViewHolder)view.getTag();
        }


        viewHolder.txtQuestionNumber.setText(SrNo[position]);
        viewHolder.txtAnswer.setText(Answer[position]);
        viewHolder.txtUserAnswer.setText(QuizActivity.UserAnswer[position]);


        if(QuizActivity.UserAnswer[position].equals(Answer[position]))
        {
            viewHolder.txtUserAnswer.setTextColor(Color.BLUE);
        }
        else
        {
            viewHolder.txtUserAnswer.setTextColor(Color.RED);
        }


        return view;
    }

    class ViewHolder{
        TextView txtQuestionNumber;
        TextView txtAnswer;
        TextView txtUserAnswer;

        ViewHolder( View v)
        {
            txtQuestionNumber=(TextView)v.findViewById(R.id.txtQuestionNumber);
            txtAnswer=(TextView)v.findViewById(R.id.txtAnswer);
            txtUserAnswer=(TextView)v.findViewById(R.id.txtUserAnswer);
        }
    }
}
