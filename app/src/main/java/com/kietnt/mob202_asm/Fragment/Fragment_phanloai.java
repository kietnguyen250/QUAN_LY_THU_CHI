package com.kietnt.mob202_asm.Fragment;

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
import com.kietnt.mob202_asm.Dialog.Bottom_sheet;
import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.adapter.PhanLoaiAdapter;
import com.kietnt.mob202_asm.dao.PhanLoai_DAO;
import com.kietnt.mob202_asm.model.PhanLoai;

import java.util.ArrayList;

public class Fragment_phanloai extends Fragment {
    FloatingActionButton fl_phanloai;
    public static PhanLoaiAdapter phanLoaiAdapters;
    PhanLoai_DAO phanLoai_dao;
    public static RecyclerView rv_phanloai;
    ArrayList<PhanLoai> ds_phanloai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phanloai, container, false);
        fl_phanloai = view.findViewById(R.id.fl_pl);
        rv_phanloai = view.findViewById(R.id.rv_pl);

        rv_phanloai.setLayoutManager(new LinearLayoutManager(getContext()));

        ds_phanloai = new ArrayList<>();
        phanLoai_dao = new PhanLoai_DAO(getContext());

        fl_phanloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottom_sheet bottom_sheet = new Bottom_sheet();
                bottom_sheet.show(getFragmentManager(), "TAG");


            }
        });
        ds_phanloai = phanLoai_dao.getAll();
        phanLoaiAdapters = new PhanLoaiAdapter(ds_phanloai, getContext());
        rv_phanloai.setAdapter(phanLoaiAdapters);

        return view;
    }
}
