package com.example.nongsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class DangNhapActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText edtUsername,edtPassword;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControls();
        login();


    }

    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            List<User> users = new ArrayList<User>();


            @Override
            public void onClick(View view) {
                firebaseFirestore.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();

                            for(DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                User user =  documentSnapshot.toObject(User.class);
                                users.add(user);
                            }

                            for(User user: users) {
                                if(edtUsername.getText().toString().equals(user.getUsername())
                                        && edtPassword.getText().toString().equals(user.getPassword())) {
                                    Intent intent = new Intent(DangNhapActivity.this, BaiDangActivity.class);
                                    DangNhapActivity.this.startActivity(intent);
                                }
                                else
                                {
                                    Log.d("login", "login failed");

                                }
                            }
                        }
                    }
                });
            }
        });
    }

    private void addControls() {
        btnLogin = findViewById(R.id.btnAcceptLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
    }
}
