package com.kietnt.mob202_asm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.kietnt.mob202_asm.Fragment.Fragment_phanloai;
import com.kietnt.mob202_asm.Fragment.Fragment_gioithieu;
import com.kietnt.mob202_asm.Fragment.Fragment_thoat;
import com.kietnt.mob202_asm.Fragment.Fragment_thongke;
import com.kietnt.mob202_asm.Fragment.Fragment_thu_chi;


public class MainActivity extends AppCompatActivity {
    DrawerLayout dr_ly;
    Toolbar tb;
    NavigationView nv;
    ActionBarDrawerToggle drawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.colorAccent));

        dr_ly = findViewById(R.id.dr_ly);
        tb = findViewById(R.id.tg_bar);
        nv = findViewById(R.id.nv_view);
        dr_ly.addDrawerListener(drawerToggle);
        drawerToggle = setupDrawableToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null){
            nv.setCheckedItem(R.id.khoanthu);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_thu_chi()).commit();
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment = null;
//                Class fragmentClass = null;
                switch (item.getItemId()){
                    case R.id.khoanthu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_thu_chi()).commit();
                        break;
                    case R.id.khoanchi:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_phanloai()).commit();
                        break;
                    case R.id.thongke:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_thongke()).commit();
                        break;
                    case R.id.gioithieu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_gioithieu()).commit();
                        Toast.makeText(MainActivity.this, "Welcome to Giới thiệu", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.thoat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_thoat()).commit();
                        Toast.makeText(MainActivity.this, "Hẹn gặp lại!", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly, new Fragment_thu_chi()).commit();
                }
//                try {
//                    fragment = (Fragment) fragmentClass.newInstance();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                fragmentManager.beginTransaction().replace(R.id.fr_ly, fragment).commit();

                item.setChecked(true);
                setTitle(item.getTitle());
                dr_ly.closeDrawers();
                return true;
            }
        });
    }

    private ActionBarDrawerToggle setupDrawableToggle(){
        return new ActionBarDrawerToggle(MainActivity.this, dr_ly, tb,R.string.Open, R.string.Close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}