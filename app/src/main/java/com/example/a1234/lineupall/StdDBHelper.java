package com.example.a1234.lineupall;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mikumiku999 on 2017/12/30.
 */

public class StdDBHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Class";
    private static final int DATABASE_VERSION = 1;
    public StdDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE students (" +
                "_id integer primary key, " +
                "name text not null, grade real not null)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        onCreate(db);
    }
}