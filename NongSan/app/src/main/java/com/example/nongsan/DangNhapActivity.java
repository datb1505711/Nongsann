package com.example.nongsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

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
    private CheckBox chkRememberLogin;
    private ProgressBar progress;
    private EditText edtUsername, edtPassword;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControls();
        LoadData();
        login();
        progress.setVisibility(ProgressBar.INVISIBLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }


    private void LoadData() {
        if(chkRememberLogin.isChecked()) {
            edtUsername.setText(SharedPreference.read("username",""));
            edtPassword.setText(SharedPreference.read("password",""));
        }
    }

    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {

            List<User> users = new ArrayList<User>();


            @Override
            public void onClick(View view) {
                progress.setIndeterminate(true);
                progress.setVisibility(ProgressBar.VISIBLE);
                firebaseFirestore.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> documentSnapshots = task.getResult().getDocuments();

                            for (DocumentSnapshot documentSnapshot : documentSnapshots) {
                                User user = documentSnapshot.toObject(User.class);
                                users.add(user);
                            }

                            for (User user : users) {
                                if (edtUsername.getText().toString().equals(user.getUsername())
                                        && edtPassword.getText().toString().equals(user.getPassword())) {
                                    Intent intent = new Intent(DangNhapActivity.this, TrangChuActivity.class);
                                    DangNhapActivity.this.startActivity(intent);
                                    if (chkRememberLogin.isChecked()) {
                                        SharedPreference.write("isRememberLogin", true);
                                        SharedPreference.write("username", user.getUsername());
                                        SharedPreference.write("password", user.getPassword());
                                        SharedPreference.write("hoten", user.getHoTen());
                                        SharedPreference.write("sdt", user.getSdt());
                                        SharedPreference.write("diachi", user.getDiaChi());
                                        SharedPreference.write("account_type", user.getAccountType());
                                    }

                                } else {
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
        progress = findViewById(R.id.progress);
        SharedPreference.init(getApplicationContext());
        chkRememberLogin = findViewById(R.id.chkRememberLogin);
        chkRememberLogin.setChecked(SharedPreference.read("isRememberLogin", false));
        btnLogin = findViewById(R.id.btnAcceptLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
    }
}
