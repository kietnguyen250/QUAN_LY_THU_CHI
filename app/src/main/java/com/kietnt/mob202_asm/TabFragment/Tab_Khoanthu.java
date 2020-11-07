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
import com.kietnt.mob202_asm.Dialog.Bottom_sheet_insert_thu;
import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.adapter.KhoanThuAdapter;
import com.kietnt.mob202_asm.dao.GiaoDich_DAO;
import com.kietnt.mob202_asm.model.GiaoDich;

import java.util.ArrayList;

public class Tab_Khoanthu extends Fragment {
    FloatingActionButton fl_khoanthu;
    public static RecyclerView rv_thu;
    public static KhoanThuAdapter khoanThuAdapter;
    public static ArrayList<GiaoDich> ds_khoanthu;
    GiaoDich_DAO giaoDich_dao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_khoan_thu, container,false);

        fl_khoanthu = view.findViewById(R.id.fl_khoan_thu);
        rv_thu = view.findViewById(R.id.rcvKhoanThu);
        rv_thu.setLayoutManager(new LinearLayoutManager(getContext()));
        fl_khoanthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Bundle args = new Bundle();
                    args.putString("trangthai", "Thu");

                    Bottom_sheet_insert_thu bottom_sheet_gd = new Bottom_sheet_insert_thu();
                    bottom_sheet_gd.setArguments(args);
                    bottom_sheet_gd.show(getFragmentManager(),bottom_sheet_gd.getTag());
            }
        });

        ds_khoanthu = new ArrayList<>();
        giaoDich_dao = new GiaoDich_DAO(getContext());

        ds_khoanthu = giaoDich_dao.readAll("Thu");
        khoanThuAdapter = new KhoanThuAdapter(ds_khoanthu, getContext());
        rv_thu.setAdapter(khoanThuAdapter);
        return view;
    }
}
