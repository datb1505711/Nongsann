package com.example.nongsan;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends Fragment {
    private ListView listView;
    private List<Messages> messages;
    private List<String> usernames;
    private List<User> users;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_write, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addControls(view);
        loadData(view);


    }

    private void addControls(View view) {
        listView = view.findViewById(R.id.lvChatttttt);
    }

    private void loadData(View view) {
        listView = view.findViewById(R.id.lvChatttttt);

        FirebaseFirestore.getInstance().collection("Messages").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                messages = new ArrayList<>();
                users = new ArrayList<>();
                usernames = new ArrayList<>();

                if (task.isSuccessful()) {
                    List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();

                    for (DocumentSnapshot snapshot : documentSnapshotList) {
                        Messages messages = snapshot.toObject(Messages.class);
                        if (messages.getUsername().equals(SharedPreference.read("username", null))
                                || messages.getChatWith().equals(SharedPreference.read("username", null))) {
                            if (!usernames.contains(messages.getUsername())) {
                                usernames.add(messages.getUsername());
                            }
                        }
                    }

                    FirebaseFirestore.getInstance().collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                List<DocumentSnapshot> documentSnapshotList = task.getResult().getDocuments();

                                for (DocumentSnapshot snapshot : documentSnapshotList) {
                                    User user = snapshot.toObject(User.class);
                                    if(usernames.contains(user.getUsername())){
                                        users.add(user);
                                    }
                                }
                                chat_user_adapter adapter = new chat_user_adapter(getContext(),users);
                                listView.setAdapter(adapter);
                            }
                        }
                    });

                }


            }
        });
    }
}
