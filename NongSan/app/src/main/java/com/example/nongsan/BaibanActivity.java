package com.example.nongsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BaibanActivity extends AppCompatActivity {
    private TextView txtTenBaiBan, txtNoiDung, txtNgayDang, txtDiaChi;
    private ListView lvBaiBan;
    private List<BaiDang> listBaiBan;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baiban);

        addControls();
        loadData();

    }

    private void loadData() {

        firebaseFirestore.collection("BaiDang").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listBaiBan = new ArrayList<>();
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();

                    for (DocumentSnapshot snapshot : documentSnapshotList) {
                        BaiDang baiDang = snapshot.toObject(BaiDang.class);
                        if(baiDang.getLoaiBaiDang().equals("Nguoi Ban")) {
                            listBaiBan.add(baiDang);

                        }

                    }

                    BaiDangAdapter adapter = new BaiDangAdapter(getApplicationContext(),listBaiBan);
                    lvBaiBan.setAdapter(adapter);
                }
            }
        });
    }

    private void addControls() {
        lvBaiBan = findViewById(R.id.listBaiBan2);
    }
}
