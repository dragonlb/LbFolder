package com.example.LbFolder;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

/**
 * Created by libing on 2015/8/16.
 */
public class FileListActivity extends Activity {

    private static final String TAG = FileListActivity.class.getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_list);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "AutoComplete");
        menu.add(0, 1, 1, "Button");
        menu.add(0, 2, 2, "CheckBox");
        menu.add(0, 3, 3, "EditText");
        menu.add(0, 4, 4, "RadioGroup");
        menu.add(0, 5, 5, "Spinner");
        menu.add(0, 6, 6, "LbFile");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
//        item.getGroupId()
        switch (item.getItemId()){
            case 0:
                Log.d(TAG, "case 0 doing..");
                return true;
            case 1:
                return true;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                return true;
            case 6:
                scanDir();
                return true;
        }
        return true;
    }

    private void scanDir(){
        File rootDir = new File("/");
//        rootDir.getf
        if(rootDir.isDirectory()){
            int i = 0;
            for( File oneFile: rootDir.listFiles() ){
                Log.d(TAG, "F "+(++i)+" :\t\t"+oneFile.getPath());
            }
        }
    }

}