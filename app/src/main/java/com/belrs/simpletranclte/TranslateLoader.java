package com.belrs.simpletranclte;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Роман on 16.11.2016.
 */

public class TranslateLoader extends AsyncTaskLoader<Translate> {

    private String mUrl;

     public TranslateLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public Translate loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        Translate translate = QueryUtils.fetchEarthquakeData(mUrl);
        return translate;
    }
}
