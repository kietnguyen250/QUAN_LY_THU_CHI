package com.kietnt.mob202_asm.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.TabFragment.Tab_Khoanthu;
import com.kietnt.mob202_asm.TabFragment.Tab_Khoanchi;
import com.kietnt.mob202_asm.adapter.TabAdapter;

public class Fragment_thu_chi extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu_chi, container, false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);


        adapter = new TabAdapter(getActivity().getSupportFragmentManager());

        adapter.addFragment(new Tab_Khoanthu(), "");
        adapter.addFragment(new Tab_Khoanchi(), "");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.thu2);
        tabLayout.getTabAt(1).setIcon(R.drawable.thu1);


        return view;
    }
}
