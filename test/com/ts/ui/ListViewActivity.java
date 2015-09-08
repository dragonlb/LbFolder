package com.ts.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.LbFolder.R;

/**
 * Created by libing on 2015/9/8.
 */
public class ListViewActivity extends Activity {

    private static final String[] strs = new String[] {
        "first", "second", "third", "fourth", "fifth"
    };
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ts_list_view);
        listView = (ListView)findViewById(R.id.tsListViewId);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.f_list_item, strs));
    }
}