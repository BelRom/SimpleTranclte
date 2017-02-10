package com.belrs.simpletranclte.Data;

import java.util.UUID;

/**
 * Created by Роман on 11.01.2017.
 */

public class Word {
    private UUID mId;
    private String mFerstWord;
    private String mSecondWord;
    private int mCoountRight;

    public Word(UUID mId) {
        this.mId=mId;
    }


    public Word(String ferstWord, String secondWord) {
        mId = UUID.randomUUID();
        mFerstWord = ferstWord;
        mSecondWord = secondWord;
        mCoountRight = 0;
    }




    public UUID getId() {
        return mId;
    }

    public void setId(int id) {
        mId = UUID.fromString(id+"");
    }

    public String getFerstWord() {
        return mFerstWord;
    }

    public void setFerstWord(String ferstWord) {
        mFerstWord = ferstWord;
    }

    public String getSecondWord() {
        return mSecondWord;
    }

    public void setSecondWord(String secondWord) {
        mSecondWord = secondWord;
    }

    public int getSolved() {
        return mCoountRight;
    }

    public void setCoountRight(int coountRight) {
        mCoountRight = coountRight;
    }
}
