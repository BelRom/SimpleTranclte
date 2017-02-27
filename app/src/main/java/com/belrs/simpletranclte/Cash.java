package com.belrs.simpletranclte;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;


class Cash {

    private static Cash ourInstance = new Cash();
    private HashMap<String, String> mCashWords;
    private final String mFileName= "cash";


    static Cash getCash() {
        if (ourInstance == null) {
            synchronized(Cash.class) {
                if(ourInstance == null) {
                    ourInstance = new Cash();
                }
            }
        }
        return ourInstance;
    }

    private Cash() {
    }

    boolean hasWord (Context context, String language, String word){
        String key = language+word.trim();
        if (mCashWords == null){
            loadListCashWord(context);
            if (mCashWords == null){
                mCashWords = new HashMap<String, String>();
            }
        }
        return mCashWords.containsKey(key);
    }



    private void loadListCashWord(Context context)  {
        FileInputStream fis;
        ObjectInputStream in;
        try {
            fis = new FileInputStream(context.getFilesDir() + "/" + mFileName);
            in = new ObjectInputStream(fis);
            mCashWords = (HashMap<String, String>) in.readObject();
            in.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void save (Context context, String language, String firstWord, String secondWord)  {

        String key = language+firstWord.trim();

        if (mCashWords == null){
            loadListCashWord(context);
            if (mCashWords == null){
                mCashWords = new HashMap<String, String>();
            }
        }

        mCashWords.put(key, secondWord);

        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(
                    new File(context.getFilesDir() + "/" + mFileName)));
            oos.writeObject(mCashWords);
            oos.flush();
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    String loadTranslateWord(Context context, String language, String firstWord){
        String key = language+firstWord.trim();
        if(mCashWords== null){
            loadListCashWord (context);
        }
        return mCashWords.get(key);
    }
}
