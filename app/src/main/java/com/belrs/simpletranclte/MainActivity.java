package com.belrs.simpletranclte;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<String> {

    private  int translate_loader_id = 1;
    public static final String TAG = MainActivity.class.getName();
    private EditText text;
    private TextView translated;
    private Button translateBtn;


    private final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private final String KEY = "trnsl.1.1.20161107T134745Z.9649398a0eff1f45.276bfd87a5df1dd10e7c1ef2ed94a5486397df45";
    private String mText = "";
    private final String LANGUAGE = "en-ru";


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
                mText = text.getText().toString();
                startloader ();
            }
        });

    }


    private void startloader (){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

       if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(translate_loader_id++, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

           translated.setText(R.string.no_internet_connection);
        }



    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader");
        Uri baseUri = Uri.parse(BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("key", KEY);
        uriBuilder.appendQueryParameter("text", mText);
        uriBuilder.appendQueryParameter("lang", LANGUAGE);

        return new TranslateLoader(this, uriBuilder.toString());
    }


    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.i(TAG, "onLoadFinished");
        translated.setText(data);
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

}
