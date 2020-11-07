package com.kietnt.mob202_asm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kietnt.mob202_asm.R;

public class Fragment_thongke extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container, false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_nav);
        if (savedInstanceState == null) {
                loadFragment(new Fragment_thongke_thu());
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.tk_thu:
                        fragment = new Fragment_thongke_thu();
                        loadFragment(fragment);
                        return true;
                    case R.id.tk_chi:
                        fragment = new Fragment_thongke_chi();
                        loadFragment(fragment);
                        return true;
                }
                return false;
            }
        });
        return view;
    }
    private void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_tk, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }
}
