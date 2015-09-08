package com.example.LbFolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by libing on 2015/9/7.
 */
public class FListViewAdapter extends BaseAdapter {

    private List<String> items;
    private LayoutInflater inflater;

    public FListViewAdapter(Context context, List<String> items) {
        this.items = items;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.f_list_item, null);
        }
        TextView text = (TextView) convertView.findViewById(R.id.fName);
        text.setText(items.get(position));
        return convertView;
    }

    /**
     * 添加列表项
     * @param item
     */
    public void addItem(String item) {
        items.add(item);
    }

    public void addItemList(List<String> itemList){
        items.addAll(itemList);
    }
}