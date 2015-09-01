package com.example.LbFolder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private static final String TAG = MyActivity.class.getName();

    private EditText domName;
    private String cacheName = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        domName = (EditText)findViewById(R.id.zName);
        Log.d(TAG, "Doing create....");
        Intent listActivity = new Intent(this, FileListActivity.class);
        startActivity(listActivity);
    }

    public void onStart(){
        super.onStart();
        Log.d(TAG, "Doing start....");
    }

    public void onRestart(){
        super.onRestart();
        if(cacheName!=null){
            domName.setText(cacheName);
        }
        Log.d(TAG, "Doing restart....");
    }

    public void onResume(){
        super.onResume();
        Log.d(TAG, "Doing resume....");
    }

    public void onPause(){
        super.onPause();
        cacheName = domName.getText().toString();
        Log.d(TAG, "Doing pause....");
    }

    public void onStop(){
        super.onStop();
        Log.d(TAG, "Doing stop....");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Doing destroy....");
    }

}
