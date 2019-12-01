package com.example.nongsan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class chat_user_adapter extends BaseAdapter {
    public chat_user_adapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    public chat_user_adapter() {
    }

    private Context context;
    private List<User> users;

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    class ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        TextView txtHoTenUser;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.user_item, viewGroup, false);
            viewHolder = new ViewHolder();

            viewHolder.linearLayout =view.findViewById(R.id.iddddddd);
            viewHolder.imageView= view.findViewById(R.id.imgAvatarrrrr);
            viewHolder.txtHoTenUser= view.findViewById(R.id.txtHoTenUser);

            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        final User user = users.get(i);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ChatActivity.class);
                intent.putExtra("chatWith",user.getUsername());
                context.startActivity(intent);
            }
        });
        Picasso.get().load(user.getImageUrl()).into(viewHolder.imageView);
        viewHolder.txtHoTenUser.setText(user.getHoTen());
        return view;
    }



}
