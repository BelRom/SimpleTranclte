package com.belrs.simpletranclte.Data;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.belrs.simpletranclte.Data.WordDbSchema.WordTable;

import java.util.UUID;

public class WordCursorWrapper extends CursorWrapper {

    public WordCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Word getWord() {
        String uuidString = getString(getColumnIndex(WordTable.Cols.UUID));
        String ferstWord = getString(getColumnIndex(WordTable.Cols.FERSTWORD));
        String secondWord = getString(getColumnIndex(WordTable.Cols.SECONDWORD));
        int isSolved = getInt(getColumnIndex(WordTable.Cols.SOLVED));


        Word word = new Word(UUID.fromString(uuidString));
        word.setFerstWord(ferstWord);
        word.setSecondWord(secondWord);
        word.setCoountRight(isSolved);

        return word;
    }
}
