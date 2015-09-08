package com.example.LbFolder;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by libing on 2015/9/7.
 */
public class FListMainActivity extends ListActivity implements AbsListView.OnScrollListener {

    private ListView listView;
    private int visibleLastIndex = 0;   //最后的可视项索引
    private int visibleItemCount;       // 当前窗口可见项总数
    private FListViewAdapter adapter;
    private View loadMoreView;
    private TextView loadMoreTextView;
    private Button loadMoreButton;
    private Handler handler = new Handler();
    private static final int PAGE_SIZE = 15;
    private int viewStartPoint = 0;
    private String viewFilePath= null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_list_main);

        loadMoreView = getLayoutInflater().inflate(R.layout.f_list_loadmore, null);
        loadMoreButton = (Button) loadMoreView.findViewById(R.id.fListLoadMoreBtn);

        loadMoreTextView = buildFooter();


        listView = getListView();               //获取id是list的ListView
//        listView = (ListView)getLayoutInflater().inflate(R.layout.f_list_main, null).findViewById(R.id.fListMain);

//        listView.addFooterView(loadMoreView);   //设置列表底部视图
        listView.addFooterView(loadMoreTextView);   //设置列表底部视图

        initAdapter();
        setListAdapter(adapter);                //自动为id是list的ListView设置适配器

        listView.setOnScrollListener(this);     //添加滑动监听
//        listView.setOnItemClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast toast = Toast.makeText(FListMainActivity.this, listView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG);
//                Log.d("ListView", listView.getItemAtPosition(position).toString());
//                toast.show();
                Log.d("ListView", "click..."+listView.getItemAtPosition(position).toString());
            }
        });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        List<String> items = new ArrayList<String>();
        items.addAll(loadData(viewFilePath, viewStartPoint, PAGE_SIZE));
        adapter = new FListViewAdapter(this, items);
    }

    /**
     * 滑动时被调用
     */
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.visibleItemCount = visibleItemCount;
        visibleLastIndex = firstVisibleItem + visibleItemCount - 1;
    }

    /**
     * 滑动状态改变时被调用
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        int itemsLastIndex = adapter.getCount() - 1;    //数据集最后一项的索引
        int lastIndex = itemsLastIndex + 1;             //加上底部的loadMoreView项
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && visibleLastIndex == lastIndex) {
            loadMore();
        }
    }

    /**
     * 点击按钮事件
     * @param view
     */
    public void loadMore(View view) {
        loadMore();
    }

    private void loadMore(){
        if("No more".equals(loadMoreTextView.getText() )){
            Toast toast = Toast.makeText(this, "No more files.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        loadMoreTextView.setText("loading...");   //设置按钮文字loading
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> dataList = loadData(viewFilePath, viewStartPoint, PAGE_SIZE);
                for (String st : dataList) {
                    adapter.addItem(st);
                }
                adapter.notifyDataSetChanged(); //数据集变化后,通知adapter
//                listView.setSelection(visibleLastIndex - visibleItemCount + 1); //设置选中项
                if (dataList.size() > 0) loadMoreTextView.setText("Load more...");   //设置按钮文字loading
                if (dataList.size() <= 0) loadMoreTextView.setText("No more");         //设置按钮文字loading
            }
        }, 1000);
    }

    private List<String> loadData(String dirPath, int startPoint, int pageSize){
        List<String> retList = new ArrayList<String>();
        if(dirPath==null){
            dirPath = File.separator;
        }
        File rootDir = new File(dirPath);
        int findCount = 0;
        if(rootDir.isDirectory()){
            String itemVal = null;
            for( File oneFile: rootDir.listFiles() ){
                findCount++;
                itemVal = findCount+".\t"+oneFile.getName();
                if(findCount<=startPoint)    continue;
                if(findCount>startPoint+pageSize)   break;
                retList.add(itemVal);
            }
        }
        this.viewStartPoint += retList.size();
        this.viewFilePath = dirPath;
        return retList;
    }

    private TextView buildFooter() {
        TextView txt=new TextView(this);
        txt.setGravity(Gravity.CENTER);
        txt.setText("Load more...");
        return(txt);
    }

}