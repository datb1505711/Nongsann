package com.example.nongsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

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
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {
        ImageView avatar;
        TextView txtMessages, txtNgayDang;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dong_chat, viewGroup, false);

            viewHolder = new ViewHolder();
            viewHolder.avatar = view.findViewById(R.id.imageAvatarChat);
            viewHolder.txtMessages = view.findViewById(R.id.txtBinhLuan);
            viewHolder.txtNgayDang = view.findViewById(R.id.txtNgayTaoBinhLuan);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final Messages message = listdata.get(i);
        FirebaseFirestore.getInstance().collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();
                    List<User> users = new ArrayList<>();
                    for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                        User user = documentSnapshot.toObject(User.class);
                        users.add(user);
                    }

                    for (User user : users) {
                        if (user.getUsername() == message.getChatWith()) {
                            message.setImageURL(user.getImageUrl());
                        }
                    }

                    viewHolder.txtNgayDang.setText(message.getCreateAt().getDate() + "");
                    viewHolder.txtMessages.setText(message.getMessages());
                    Picasso.get().load(message.getImageURL()).into(viewHolder.avatar);
                }
            }
        });

        return view;
    }
}
