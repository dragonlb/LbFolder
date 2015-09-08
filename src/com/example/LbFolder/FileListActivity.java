package com.example.LbFolder;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout.LayoutParams;
import com.ts.ui.ListViewActivity;

import java.io.File;

/**
 * Created by libing on 2015/8/16.
 */
public class FileListActivity extends Activity
//        ListActivity implements AbsListView.OnScrollListener
        {

    private static final String TAG = FileListActivity.class.getName();

    private LinearLayout mLoadLayout;
    private ListView mListView;
    private ListViewAdapter mListViewAdapter = new ListViewAdapter();
    private int mLastItem = 0;
    private int mCount = 41;
    private final Handler mHandler = new Handler();
    private final LayoutParams mProgressBarLayoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    private final LayoutParams mTipContentLayoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_list);
//        /**
//         * "加载项"布局，此布局被添加到ListView的Footer中。
//         */
//        mLoadLayout = new LinearLayout(this);
//        mLoadLayout.setMinimumHeight(60);
//        mLoadLayout.setGravity(Gravity.CENTER);
//        mLoadLayout.setOrientation(LinearLayout.HORIZONTAL);
//
//        /**
//         * 向"加载项"布局中添加一个圆型进度条。
//         */
//        ProgressBar mProgressBar = new ProgressBar(this);
//        mProgressBar.setPadding(0, 0, 15, 0);
//        mLoadLayout.addView(mProgressBar, mProgressBarLayoutParams);
//
//        /**
//         * 向"加载项"布局中添加提示信息。
//         */
//        TextView mTipContent = new TextView(this);
//        mTipContent.setText("加载中...");
//        mLoadLayout.addView(mTipContent, mTipContentLayoutParams);
//        /**
//         * 获取ListView组件，并将"加载项"布局添加到ListView组件的Footer中。
//         */
//        mListView = getListView();
//        mListView.addFooterView(mLoadLayout);
//        /**
//         * 组ListView组件设置Adapter,并设置滑动监听事件。
//         */
//        setListAdapter(mListViewAdapter);
//        mListView.setOnScrollListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "AutoComplete");
        menu.add(0, 1, 1, "ts_ListView");
        menu.add(0, 2, 2, "CheckBox");
        menu.add(0, 3, 3, "EditText");
        menu.add(0, 4, 4, "RadioGroup");
        menu.add(0, 5, 5, "Spinner");
        menu.add(0, 6, 6, "LbFile");
        menu.add(0, 7, 7, "LbFileList");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
//        item.getGroupId()
        switch (item.getItemId()){
            case 0:
                Log.d(TAG, "case 0 doing..");
                return true;
            case 1:
                Intent intent = new Intent();
                intent.setClass(FileListActivity.this, ListViewActivity.class);
                FileListActivity.this.startActivity(intent);
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
            case 7:
                toFileList();
                return true;
        }
        return true;
    }

    private void scanDir(){
        File rootDir = new File(File.separator);
        if(rootDir.isDirectory()){
            int i = 0;
            for( File oneFile: rootDir.listFiles() ){
                Log.d(TAG, "F "+(++i)+" :\t\t"+oneFile.getAbsoluteFile());
            }
        }
    }

    private void toFileList(){
        Intent intent = new Intent();
        intent.putExtra("p1", "");
        intent.setClass(FileListActivity.this, FListMainActivity.class);
        FileListActivity.this.startActivity(intent);
    }

    public void onScrollStateChanged(AbsListView view, int mScrollState) {
        /**
         * 当ListView滑动到最后一条记录时这时，我们会看到已经被添加到ListView的"加载项"布局， 这时应该加载剩余数据。
         */
        if (mLastItem == mListViewAdapter.count
                && mScrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            if (mListViewAdapter.count <= mCount) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListViewAdapter.count += 10;
                        mListViewAdapter.notifyDataSetChanged();
                        mListView.setSelection(mLastItem);
                    }
                }, 1000);
            }
        }
    }

    public void onScroll(AbsListView view, int mFirstVisibleItem,
                         int mVisibleItemCount, int mTotalItemCount) {
        mLastItem = mFirstVisibleItem + mVisibleItemCount - 1;
        if (mListViewAdapter.count > mCount) {
            mListView.removeFooterView(mLoadLayout);
        }
    }

    class ListViewAdapter extends BaseAdapter {
        int count = 10;
        public int getCount() {
            return count;
        }
        public Object getItem(int position) {
            return position;
        }
        public long getItemId(int position) {
            return position;
        }
        public View getView(int position, View view, ViewGroup parent) {
            TextView mTextView;
            if (view == null) {
                mTextView = new TextView(FileListActivity.this);
            } else {
                mTextView = (TextView) view;
            }
            mTextView.setText("Item " + position);
            mTextView.setTextSize(20f);
            mTextView.setGravity(Gravity.CENTER);
            mTextView.setHeight(60);
            return mTextView;
        }
    }
}