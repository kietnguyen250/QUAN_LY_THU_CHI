package com.kietnt.mob202_asm.TabFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kietnt.mob202_asm.Dialog.Bottom_sheet_insert_chi;

import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.adapter.KhoanChiAdapter;
import com.kietnt.mob202_asm.dao.GiaoDich_DAO;
import com.kietnt.mob202_asm.model.GiaoDich;

import java.util.ArrayList;

public class Tab_Khoanchi extends Fragment {
    FloatingActionButton fl_khoanchi;
    public static RecyclerView rv_thu;
    public static KhoanChiAdapter khoanChiAdapter;
    public static ArrayList<GiaoDich> ds_khoanthu;
    GiaoDich_DAO giaoDich_dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_khoan_chi, container,false);
        rv_thu = view.findViewById(R.id.rcvKhoanChi);
        rv_thu.setLayoutManager(new LinearLayoutManager(getContext()));
        fl_khoanchi = view.findViewById(R.id.fl_khoan_chi);
        fl_khoanchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("trangthai", "Chi");

                Bottom_sheet_insert_chi bottom_sheet_gd = new Bottom_sheet_insert_chi();
                bottom_sheet_gd.setArguments(args);
                bottom_sheet_gd.show(getFragmentManager(),bottom_sheet_gd.getTag());
            }
        });
        ds_khoanthu = new ArrayList<>();
        giaoDich_dao = new GiaoDich_DAO(getContext());

        ds_khoanthu = giaoDich_dao.readAll("Chi");
        khoanChiAdapter = new KhoanChiAdapter(ds_khoanthu, getContext());
        rv_thu.setAdapter(khoanChiAdapter);
        return view;
    }
}
