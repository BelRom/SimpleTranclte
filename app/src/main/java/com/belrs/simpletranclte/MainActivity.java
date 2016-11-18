package com.belrs.simpletranclte;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Translate> {

    private static final int EARTHQUAKE_LOADER_ID = 1;
    private EditText text;
    private TextView translated;
    private Button translateBtn;


    private final String URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private final String KEY = "trnsl.1.1.20161107T134745Z.9649398a0eff1f45.276bfd87a5df1dd10e7c1ef2ed94a5486397df45";
    private static final String USGS_REQUEST_URL =
            "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20161107T134745Z.9649398a0eff1f45.276bfd87a5df1dd10e7c1ef2ed94a5486397df45&text=how%20when&lang=en-ru";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.textTranslate);
        translated = (TextView) findViewById(R.id.textAnswer);
        Translate tr = new Translate();

        translateBtn = (Button) findViewById(R.id.buttonTranslate);
        translateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadnetwork();

                // Get a reference to the ConnectivityManager to check state of network connectivity


            }
        });
    }

    private void loadnetwork() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    }

    @Override
    public Loader<Translate> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new TranslateLoader(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<Translate> translate, Translate earthquakes) {
        translated.setText(translate.getTraslateText());
    }

    @Override
    public void onLoaderReset(Loader<Translate> loader) {

    }
}
