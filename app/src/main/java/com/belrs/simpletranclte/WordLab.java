package com.belrs.simpletranclte;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.belrs.simpletranclte.Data.Word;
import com.belrs.simpletranclte.Data.WordBaseHelper;
import com.belrs.simpletranclte.Data.WordCursorWrapper;
import com.belrs.simpletranclte.Data.WordDbSchema.WordTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Роман on 11.01.2017.
 */

public class WordLab {

    private static WordLab sWordLab;
    private SQLiteDatabase mWordBaseHelper;
    private Context mContext;

    public static WordLab get(Context context) {
        if (sWordLab == null) {
            sWordLab = new WordLab(context);
        }
        return sWordLab;
    }


    private WordLab(Context context) {
        mContext = context.getApplicationContext();
        mWordBaseHelper = new WordBaseHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(Word word) {
        ContentValues values = new ContentValues();
        values.put(WordTable.Cols.UUID, word.getId().toString());
        values.put(WordTable.Cols.FERSTWORD, word.getFerstWord());
        values.put(WordTable.Cols.SECONDWORD, word.getSecondWord());
        values.put(WordTable.Cols.SOLVED, word.getSolved());

        return values;
    }


    public void addWord(Word w){
        ContentValues values = getContentValues(w);
        mWordBaseHelper.insert(WordTable.NAME, null, values);
    }



    public List<Word> getWord(){
        List<Word> mAllWord = new ArrayList<>();
        WordCursorWrapper cursor = queryWord(null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            mAllWord.add(cursor.getWord());
            cursor.moveToNext();
        }
        cursor.close();

        return mAllWord;
    }

    public void updateWord(Word word) {
        ContentValues values = getContentValues(word);

        mWordBaseHelper.update(WordTable.NAME, values,
                WordTable.Cols.FERSTWORD + " = ?",  new String[] { word.getFerstWord()});

    }

    public void deleteWord (String word){
         mWordBaseHelper.delete(WordTable.NAME, WordTable.Cols.FERSTWORD + " = ?" , new String[] { word});

    }

    private WordCursorWrapper queryWord(String whereClause, String[] whereArgs) {
        Cursor cursor = mWordBaseHelper.query(
                WordTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );

        return new WordCursorWrapper(cursor);
    }

}
