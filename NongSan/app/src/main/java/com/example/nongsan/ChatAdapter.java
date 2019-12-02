package com.example.nongsan;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseAdapter {

    private Context context;

    public ChatAdapter(Context context, List<Messages> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    private List<Messages> listdata;

    @Override
    public int getCount() {
        return listdata.size();
    }

    @Override
    public Object getItem(int i) {
        return listdata.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {
        TextView txtMessages, txtNgayDang,txtUsername;
        LinearLayout lnChat;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dong_chat, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.txtUsername = view.findViewById(R.id.txtusername);
            viewHolder.txtMessages = view.findViewById(R.id.txtBinhLuan);
            viewHolder.txtNgayDang = view.findViewById(R.id.txtNgayTaoBinhLuan);
            viewHolder.lnChat = view.findViewById(R.id.lnChat);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final Messages message = listdata.get(i);
        SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEE hh:MM dd-MM-yyyy"); // the day of the week spelled out completely
        viewHolder.txtNgayDang.setText(simpleDateformat.format(message.getCreateAt()));
        viewHolder.txtMessages.setText(message.getMessages());
        viewHolder.txtUsername.setText(message.getUsername());

        return view;
    }
}
