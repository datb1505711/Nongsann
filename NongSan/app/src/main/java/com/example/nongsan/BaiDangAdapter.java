package com.example.nongsan;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

public class BaiDangAdapter extends BaseAdapter {

    public BaiDangAdapter(Context context, List<BaiDang> listBaiDang) {
        this.context = context;
        this.listBaiDang = listBaiDang;
    }

    private Context context;
    private List<BaiDang> listBaiDang;


    class ViewHolder {
        public ImageView anhBaiDang;
        public TextView tenBaiDang;
        public TextView noiDung;
        public TextView ngayDang;
        public TextView diaChi;
    }

    @Override
    public int getCount() {
        return listBaiDang.size();
    }

    public BaiDang getItem(int position) {
        return listBaiDang.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.dong_baiban, parent, false);
            holder = new ViewHolder();

            holder.anhBaiDang = convertView.findViewById(R.id.imageBaiBan);
            holder.tenBaiDang = convertView.findViewById(R.id.txtTenBaiBan);
            holder.noiDung = convertView.findViewById(R.id.txtNoiDung);
            holder.ngayDang = convertView.findViewById(R.id.txtNgayDang);
            holder.diaChi = convertView.findViewById(R.id.txtDiaChi);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        BaiDang baiDang = getItem(position);

        holder.anhBaiDang.setImageURI(Uri.parse(baiDang.getAnh()));
        holder.tenBaiDang.setText(baiDang.getTenBaiDang());
        holder.noiDung.setText(baiDang.getNoiDung());
        holder.ngayDang.setText(new Date().toString());
        holder.diaChi.setText(baiDang.getDiachi());

        return convertView;
    }
}
