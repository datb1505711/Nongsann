package com.example.nongsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TrangChuActivity extends AppCompatActivity  {
    Toolbar toolbartc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        Contract();
        toolbartc.setTitle("Trang Chủ");
        BottomNavigationView bottomNav = findViewById(R.id.bottomnavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new TrangchuFragment()).commit();
        }
    }

    private void Contract() {
        toolbartc = (Toolbar) findViewById(R.id.toolbartc);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.bttc:
                            toolbartc.setTitle("Trang Chủ");
                            selectedFragment = new TrangchuFragment();
                            break;
                        case R.id.btvb:
                            toolbartc.setTitle("Viết Bài");
                            selectedFragment = new VietbaiFragment();
                            break;
                        case R.id.bttb:
                            toolbartc.setTitle("Thông Báo");
                            selectedFragment = new ThongbaoFragment();
                            break;
                        case R.id.btmg:
                            toolbartc.setTitle("Chat");
                            selectedFragment = new MessageFragment();
                            break;
                        case R.id.btlg:
                            Toast.makeText(TrangChuActivity.this, "Logout", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}