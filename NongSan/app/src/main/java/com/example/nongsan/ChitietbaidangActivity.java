package com.example.nongsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ChitietbaidangActivity extends AppCompatActivity {
    private TextView txtTen, txtMoTa, txtNgayDang, txtTinhThanh, txtSDT, txtLoaiSanPham, txtKhoiLuong, txtChat;
    private ImageView imageBaiDang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietbaidang);
        addControls();
        addData();
    }

    private void addData() {
        BaiDang baiDang = (BaiDang) getIntent().getSerializableExtra("baiDang");

        txtTen.setText(baiDang.getTenBaiDang());
        txtMoTa.setText(baiDang.getNoiDung());
        txtNgayDang.setText(baiDang.getNgayLap().toString());
        txtTinhThanh.setText(baiDang.getDiachi());
        txtKhoiLuong.setText(baiDang.getGia());
        txtLoaiSanPham.setText(baiDang.getLoaisp());
        txtSDT.setText(baiDang.getSdt());
        Picasso.get().load(baiDang.getAnh()).into(imageBaiDang);

        txtChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }

    private void addControls() {
        txtTen = findViewById(R.id.txtChiTietTenBaiDang);
        txtMoTa = findViewById(R.id.txtMoTaChiTiet);
        txtNgayDang = findViewById(R.id.txtChiTietNgayDang);
        txtTinhThanh = findViewById(R.id.txtChiTietTinhThanh);
        txtSDT = findViewById(R.id.txtChiTietSdt);
        txtLoaiSanPham = findViewById(R.id.txtLoaiSanPhamChiTiet);
        txtKhoiLuong = findViewById(R.id.txtKhoiLuongChiTiet);
        txtChat = findViewById(R.id.txtChat);
        imageBaiDang  =findViewById(R.id.imageChiTietBaiDang);
    }
}
