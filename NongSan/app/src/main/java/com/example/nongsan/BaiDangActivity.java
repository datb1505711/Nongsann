package com.example.nongsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class BaiDangActivity extends AppCompatActivity {
    private String donvitinh,loai;
    private Button btndangbai;
    private ImageView ImageBaiDang;
    private EditText txtten,txtgia,txtnoidung;
    private List<String> listloai = new ArrayList<String>();
    private List<String> listdonvi = new ArrayList<String>();
    private Spinner sploai,spdonvitinh;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    StorageReference storageRef = firebaseStorage.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_dang);
        AddControl();
        Loaddata();
        Adddata();
        ButtonClick();
    }

    private void ButtonClick() {
        btndangbai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Product_Type>  data = new ArrayList<Product_Type>(20);
                data.add(new Product_Type(1,"Rau, Củ, Qủa"));
                data.add(new Product_Type(2,"Tiêu, Hành, Tỏi"));
                data.add(new Product_Type(3,"Trái Cây"));
                data.add(new Product_Type(4,"Lúa"));
                data.add(new Product_Type(5,"Tôm, Cá"));
                data.add(new Product_Type(6,"Khác"));
                firebaseFirestore.collection("Product_Type")
                        .document().set(data);
            }
        });
    }

    private void Adddata() {

    }

    private void Loaddata() {
        spdonvitinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                donvitinh = spdonvitinh.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sploai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loai = sploai.getSelectedItem().toString();
                //  Log.e("aaa",spintinhthanh.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void  AddControl() {
        sploai = (Spinner)findViewById(R.id.SpLoai);
        spdonvitinh = (Spinner)findViewById(R.id.SpDonvitinh);
        btndangbai = (Button) findViewById(R.id.btndangbai);
        ImageBaiDang = (ImageView) findViewById(R.id.imagebaidang);
        txtten = (EditText) findViewById(R.id.edtBaidang);
        txtgia = (EditText) findViewById(R.id.edtGia);
        txtnoidung = (EditText) findViewById(R.id.edtNoidung);
    }
}
