package com.example.nongsan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrangchuFragment extends Fragment {
    private ImageButton imgBtnRefresh;
    private ListView lvBaiBan;
    private EditText edtSeach;
    private ImageButton imgBtn;
    private List<BaiDang> listBaiBan;
    private List<BaiDang> listBaiBanData;

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_trangchu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        loadData(view);
        search();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

    private void search() {
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBaiBan = new ArrayList<>();

                for(BaiDang baiDang: listBaiBanData) {
                    if(baiDang.getTenBaiDang().toLowerCase().contains(edtSeach.getText().toString().toLowerCase()) ||
                        baiDang.getLoaiBaiDang().toLowerCase().contains(edtSeach.getText().toString().toLowerCase()) ||
                            baiDang.getDiachi().toLowerCase().contains(edtSeach.getText().toString().toLowerCase()) ||
                            baiDang.getGia().toLowerCase().contains(edtSeach.getText().toString().toLowerCase()) ||
                            baiDang.getNoiDung().toLowerCase().contains(edtSeach.getText().toString().toLowerCase()) ||
                            baiDang.getSdt().toLowerCase().contains(edtSeach.getText().toString().toLowerCase())
                    ) {
                        listBaiBan.add(baiDang);
                    }

                }
                BaiDangAdapter adapter = new BaiDangAdapter(getContext(),listBaiBan);
                lvBaiBan.setAdapter(adapter);
            }
        });

    }

    private void addControls(final View view) {
        lvBaiBan = view.findViewById(R.id.listBaiBan);
        edtSeach = view.findViewById(R.id.edtsearchhhhhhhhh);
        imgBtn = view.findViewById(R.id.btnSearchhhhhhh);
        lvBaiBan = view.findViewById(R.id.listBaiBan);
        imgBtnRefresh = view.findViewById(R.id.btnRefresh);


        imgBtnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                Log.d("saddsadsa","click");
                loadData(view);
            }
        });
    }
    private void loadData(View view) {

        firebaseFirestore.collection("BaiDang").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listBaiBan = new ArrayList<>();
                listBaiBanData = new ArrayList<>();
                if (task.isSuccessful()) {
                    List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();

                    for (DocumentSnapshot snapshot : documentSnapshotList) {
                        BaiDang baiDang = snapshot.toObject(BaiDang.class);
                        if(baiDang.getLoaiBaiDang().equals(SharedPreference.read("account_type","Nguoi Ban"))) {
                            listBaiBan.add(baiDang);
                        }

                    }

                    listBaiBanData = listBaiBan;
                    BaiDangAdapter adapter = new BaiDangAdapter(getContext(),listBaiBan);
                    lvBaiBan.setAdapter(adapter);
                }
            }
        });
    }
}
