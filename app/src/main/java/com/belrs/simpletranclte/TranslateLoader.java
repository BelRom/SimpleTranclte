package com.belrs.simpletranclte;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

/**
 * Created by Роман on 20.11.2016.
 */

public class TranslateLoader extends AsyncTaskLoader<String> {
    private static final String LOG_TAG = TranslateLoader.class.getName();
    private String mUrl;

    public TranslateLoader(Context context, String url) {
        super(context);
        Log.i(LOG_TAG, "TranslateLoader");

        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        Log.i(LOG_TAG, "loadInBackground");
        if (mUrl == null) {
            return null;
        }
        String result = new Fetch()
                .fetchTranslateData(mUrl);
        return result;


    }
}
