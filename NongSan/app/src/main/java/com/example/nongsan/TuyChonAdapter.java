package com.example.nongsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TuyChonAdapter extends BaseAdapter {
    private Context context;
    private List<String> listData;

    public TuyChonAdapter(Context context, List<String> listData) {
        this.context = context;
        this.listData = listData;
    }

    @Override
    public int getCount() {
         return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listData.size();
    }

    class ViewHolder {
        TextView textView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if(view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.dong_tuychon,viewGroup,false);

            viewHolder.textView = view.findViewById(R.id.txtTuyChon);

            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.textView.setText(listData.get(i));

        return view;
    }
}
