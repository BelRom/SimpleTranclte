package com.belrs.simpletranclte.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.belrs.simpletranclte.Data.WordDbSchema.WordTable;

public class WordBaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "WordBaseHelper";
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "wordBase.db";

    public WordBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + WordTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                WordTable.Cols.UUID + ", " +
                WordTable.Cols.FERSTWORD + ", " +
                WordTable.Cols.SECONDWORD + ", " +
                WordTable.Cols.SOLVED +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
