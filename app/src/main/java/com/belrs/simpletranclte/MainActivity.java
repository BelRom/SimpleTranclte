package com.belrs.simpletranclte;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<String> {

    private static final int TRANSLATE_LOADER_ID = 1;
    public static final String TAG = MainActivity.class.getName();
    private EditText text;
    private TextView translated;
    private Button translateBtn;


    private final String URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private final String KEY = "trnsl.1.1.20161107T134745Z.9649398a0eff1f45.276bfd87a5df1dd10e7c1ef2ed94a5486397df45";
    private static final String USGS_REQUEST_URL =
            "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20161107T134745Z.9649398a0eff1f45.276bfd87a5df1dd10e7c1ef2ed94a5486397df45&text=how%20when&lang=en-ru";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.textTranslate);
        translated = (TextView) findViewById(R.id.textAnswer);

        translateBtn = (Button) findViewById(R.id.buttonTranslate);
        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(TRANSLATE_LOADER_ID, null, this);



    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader");

        return new TranslateLoader(this, USGS_REQUEST_URL);
    }


    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.i(TAG, "onLoadFinished");
        translated.setText(data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

}
