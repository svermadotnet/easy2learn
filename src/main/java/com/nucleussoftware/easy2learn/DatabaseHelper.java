package com.nucleussoftware.easy2learn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="Learn.db";
    public static final String TABLE_NAME="quiz_table";
    public static final String COL_1 = "SNO";
    public static final String COL_2 = "QUESTION";
    public static final String COL_3 = "OPTION_1";
    public static final String COL_4 = "OPTION_2";
    public static final String COL_5 = "OPTION_3";
    public static final String COL_6 = "OPTION_4";
    public static final String COL_7 = "ANSWER";
    public static final String COL_8 = "OPTION_5";// Subject
    public static final String COL_9 = "OPTION_6";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);

        //SQLiteDatabase db =this.getWritableDatabase();
    }

    public Cursor _GetAllData()
    {
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return result;
    }

    public Cursor GetSubjectData(String subjectName)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_NAME + " WHERE OPTION_5='"+subjectName + "'",null);
        return result;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, SNO INTEGER, " +
                "QUESTION TEXT, OPTION_1 TEXT, OPTION_2 TEXT, OPTION_3 TEXT, OPTION_4 TEXT, ANSWER TEXT, OPTION_5 TEXT, OPTION_6 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public  boolean deleteData(String ID)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"ID ="+ID,null);
        if (result ==-1)
            return false;
        else
            return true;
    }

    public boolean insertData(String question, String op1, String op2, String op3, String op4, String answer)
    {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,question);
        contentValues.put(COL_3,op1);
        contentValues.put(COL_4,op2);
        contentValues.put(COL_5,op3);
        contentValues.put(COL_6,op4);
        contentValues.put(COL_7,answer);
        contentValues.put(COL_8,SubjectActivity.subject);
        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result ==-1)
            return false;
        else
            return true;
    }
}
