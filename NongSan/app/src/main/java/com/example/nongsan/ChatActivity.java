package com.example.nongsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
        Messages messages = new Messages(new Date(), edtChat.getText().toString(), chatWith);
        final Map<String, Object> map = new HashMap<>();
        map.put(chatWith, messages);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore.getInstance().collection("Messages").document(SharedPreference.read("username", null)).set(map);
            }
        });
    }

    private void addData() {
        chatWith = getIntent().getStringExtra("chatWith");

        FirebaseFirestore.getInstance().collection("Messages").document(SharedPreference.read("username", null)).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
//                    DocumentSnapshot snapshot = task.getResult();
//
//                    Messages message = snapshot.toObject(Messages.class);
//                    messagesList.add(message);
//
//                    ChatAdapter adapter = new ChatAdapter(getApplicationContext(), messagesList);
//                    listViewChat.setAdapter(adapter);
                }
            }
        });

    }

    private void addControls() {
        listViewChat = findViewById(R.id.lvChat);
        edtChat = findViewById(R.id.edtChat);
        btnSend = findViewById(R.id.btnSend);
    }
}
