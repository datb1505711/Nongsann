package com.example.nongsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class TrangChuActivity extends AppCompatActivity {
    private Toolbar toolbartc;
    private List<Fragment> fragmentList;

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
                    fragmentList.get(0)).commit();
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);

    }

    private void Contract() {
        toolbartc = (Toolbar) findViewById(R.id.toolbartc);
        fragmentList = new ArrayList<>();
        fragmentList.add(new TrangchuFragment());
        fragmentList.add(new VietbaiFragment());
        fragmentList.add(new MessageFragment());
        fragmentList.add(new TuyChonFragment());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.bttc:
                            toolbartc.setTitle("Trang Chủ");
                            selectedFragment = fragmentList.get(0);
                            break;
                        case R.id.btvb:
                            toolbartc.setTitle("Viết Bài");
                            selectedFragment = fragmentList.get(1);
                            break;
                        case R.id.btmg:
                            toolbartc.setTitle("Chat");
                            selectedFragment = fragmentList.get(2);
                            break;
                        case R.id.btlg:
                            toolbartc.setTitle("Tùy chọn");
                            selectedFragment = fragmentList.get(3);
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}