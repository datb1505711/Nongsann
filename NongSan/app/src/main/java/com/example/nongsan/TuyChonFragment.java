package com.example.nongsan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class TuyChonFragment extends Fragment {

    private ListView listTuyChon;
    private List<String> listString;

    public static TuyChonFragment newInstance(String param1, String param2) {
        TuyChonFragment fragment = new TuyChonFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_tuy_chon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        addData();
    }

    private void addData() {
        listString.add("Quản lý tài khoản");
        listString.add("Liên kết");
        listString.add("Đăng xuất");

        TuyChonAdapter adapter = new TuyChonAdapter(getContext(), listString);

    }

    public void addControls(View view) {
        listString = new ArrayList<>();
        listTuyChon = view.findViewById(R.id.listTuyChon);


    }


}
