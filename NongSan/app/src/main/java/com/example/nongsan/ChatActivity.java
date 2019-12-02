package com.example.nongsan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.collect.Lists;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    private ListView listViewChat;
    private List<Messages> messagesList;
    private EditText edtChat;
    private String chatWith;
    private ImageButton btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        addControls();
        addData();
        sendMessages();
    }

    private void sendMessages() {


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Messages messages = new Messages(
                        null, SharedPreference.read("username", null), new Date(), edtChat.getText().toString(), chatWith);

                FirebaseFirestore.getInstance().collection("Messages").document().set(messages);

                edtChat.setText("");addData();

            }
        });
    }

    private void addData() {
        chatWith = getIntent().getStringExtra("chatWith");

        FirebaseFirestore.getInstance().collection("Messages").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    messagesList = new ArrayList<>();

                    List<DocumentSnapshot> snapshots = task.getResult().getDocuments();

                    for (DocumentSnapshot snapshot : snapshots) {
                        Messages messages = snapshot.toObject(Messages.class);
                        if (messages.getUsername().equals(SharedPreference.read("username", null))
                                || messages.getChatWith().equals(SharedPreference.read("username", null))) {
                            messagesList.add(messages);
                        }
                    }

                    sort(messagesList);
                    ChatAdapter adapter = new ChatAdapter(getApplicationContext(), messagesList);

                    listViewChat.setAdapter(adapter);
                    listViewChat.setSelection(listViewChat.getAdapter().getCount()-1);


                }
            }
        });

    }

    private List<Messages> sort(List<Messages> messages) {
        for(int i = 0;i<messages.size();i++) {
            for(int j =i+1;j<messages.size();j++) {
                if(messages.get(i).getCreateAt().compareTo(messages.get(j).getCreateAt()) >0) {
                    Messages temp = messages.get(i);
                    messages.set(i,messages.get(j));
                    messages.set(j,temp);
                }
            }
        }
        return messages;
    }

    private void addControls() {
        listViewChat = findViewById(R.id.lvChat);
        edtChat = findViewById(R.id.edtChat);
        btnSend = findViewById(R.id.btnSend);
    }
}
