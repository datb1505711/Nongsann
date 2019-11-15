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
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class TrangchuFragment extends Fragment {

    private ListView lvBaiBan;
    private List<BaiDang> listBaiBan;
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


    }

    private void addControls(View view) {
        lvBaiBan = view.findViewById(R.id.listBaiBan);
    }
    private void loadData(View view) {
        lvBaiBan = view.findViewById(R.id.listBaiBan);

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
                    BaiDangAdapter adapter = new BaiDangAdapter(getContext(),listBaiBan);
                    lvBaiBan.setAdapter(adapter);
                }
            }
        });
    }
}