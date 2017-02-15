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
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.belrs.simpletranclte.Data.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<String> {

    final String LOG_TAG = "myLogs";
    private  int translate_loader_id = 1;
    public static final String TAG = MainActivity.class.getName();
    private TextView mTranslatedTextView;
    private EditText mEditText;
    private Button mTranslateButton, mSaveButton, mRefreshButton, mUpdateButtton, mDeleteButton;
    private Spinner mFerstLanguageSpinner, mSecondLanguageSpinner;
    private List <String> Convert1 = new ArrayList<>();
    private String mFerstLangugeCode, mSecondLangugeCode;
    RecyclerView recyclerView;
    static Map<String, String> mAllLanguage = new HashMap<>();
    private WordLab mWordLab;
    private RecyclerWordAdapter mRecyclerWordAdapter;






    private final String BASE_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate";
    private final String KEY = "trnsl.1.1.20161107T134745Z.9649398a0eff1f45.276bfd87a5df1dd10e7c1ef2ed94a5486397df45";
    private String mTranslateWord = "";




    @Override
    public void onCreate(Bundle savedInstanceState) {


        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setdata();


        mUpdateButtton  = (Button) findViewById(R.id.buttonUpdate);
        mDeleteButton  = (Button) findViewById(R.id.buttonDelete);
        mEditText = (EditText) findViewById(R.id.textTranslate);
        mTranslatedTextView = (TextView) findViewById(R.id.textAnswer);
        mTranslateButton = (Button) findViewById(R.id.buttonTranslate);
        mSaveButton = (Button) findViewById(R.id.buttonSave);
        mRefreshButton = (Button) findViewById(R.id.buttonRefresh);


        mWordLab = WordLab.get(this);
        mRecyclerWordAdapter = new RecyclerWordAdapter(mWordLab.getWord(),this);

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mWordLab.deleteWord(mEditText.getText().toString());
                Log.d(LOG_TAG, "delete");
            }
        });

        mUpdateButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "--- Update mytable: ---");
                String ferstWord = mEditText.getText().toString();
                String lastWord = mTranslatedTextView.getText().toString();
                Word mWord = new Word(ferstWord, lastWord);
                mWordLab.updateWord(mWord);

            }
        });


        mTranslateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTranslateWord = mEditText.getText().toString();
                startloader();
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ferstWord = mEditText.getText().toString();
                String lastWord = mTranslatedTextView.getText().toString();
                Word mWord = new Word(ferstWord, lastWord);
                mWordLab.addWord(mWord);
                mRecyclerWordAdapter.swap(mWordLab.getWord());
            }
        });

        mRefreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(LOG_TAG, "log");
                List<Word> listWords = mWordLab.getWord();
                for (Word word : listWords) {
                    Log.d(LOG_TAG, word.getFerstWord() +" "+ word.getSecondWord());
                }
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mRecyclerWordAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        mFerstLanguageSpinner = (Spinner) findViewById(R.id.ferstLanguge);
        mSecondLanguageSpinner = (Spinner) findViewById(R.id.secondLanguge);
        Convert1.addAll(mAllLanguage.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Convert1);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFerstLanguageSpinner.setAdapter(adapter);
        mSecondLanguageSpinner.setAdapter(adapter);

        mFerstLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mFerstLangugeCode = mAllLanguage.get(adapterView.getItemAtPosition(i).toString());
                Toast.makeText(getBaseContext(), mFerstLangugeCode,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mSecondLanguageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mSecondLangugeCode = mAllLanguage.get(adapterView.getItemAtPosition(i).toString());
                Toast.makeText(getBaseContext(), mSecondLangugeCode,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }



    @Override
    protected void onPause() {
        super.onPause();

    }

    private void startloader (){

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

       if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(translate_loader_id++, null, this);
        } else {
          mTranslatedTextView.setText(R.string.no_internet_connection);
        }



    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        Log.i(TAG, "onCreateLoader");
        Uri baseUri = Uri.parse(BASE_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("key", KEY);
        uriBuilder.appendQueryParameter("text", mTranslateWord);
        uriBuilder.appendQueryParameter("lang", mFerstLangugeCode +"-"+ mSecondLangugeCode);

        return new TranslateLoader(this, uriBuilder.toString());
    }


    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.i(TAG, "onLoadFinished");
        mTranslatedTextView.setText(data);

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }



    public void setdata(){
        mAllLanguage.put("Azerbaijani","az");
        mAllLanguage.put("Albanian","sq");
        mAllLanguage.put("Русский","ru");
        mAllLanguage.put("English","en");
    }




}
