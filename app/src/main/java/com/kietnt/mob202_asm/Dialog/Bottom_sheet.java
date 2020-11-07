package com.kietnt.mob202_asm.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.adapter.PhanLoaiAdapter;
import com.kietnt.mob202_asm.dao.PhanLoai_DAO;
import com.kietnt.mob202_asm.model.PhanLoai;

import java.util.ArrayList;

import static com.kietnt.mob202_asm.Fragment.Fragment_phanloai.rv_phanloai;

public class Bottom_sheet extends BottomSheetDialogFragment {
    EditText edt_tenLoai;
    Spinner sp_loai;
    Button btn_them;
    PhanLoai_DAO phanLoai_dao;
    ArrayList<PhanLoai> ds_phanloai;
    PhanLoaiAdapter phanLoaiAdapters;
    public Bottom_sheet(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_phanloai, container, false);
        edt_tenLoai = view.findViewById(R.id.edt_tenLoai);
        sp_loai = view.findViewById(R.id.sp_phanLoai);
        btn_them = view.findViewById(R.id.btn_Add);
        phanLoai_dao = new PhanLoai_DAO(getContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_loai.setAdapter(adapter);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_loai = edt_tenLoai.getText().toString();
                String phanLoai = sp_loai.getSelectedItem().toString();
                PhanLoai pl = new PhanLoai(ten_loai, phanLoai);
                phanLoai_dao.insert(pl);
                capnhat();
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });

        return view;
    }

    public void capnhat(){
        ds_phanloai = new ArrayList<>();
        ds_phanloai = phanLoai_dao.getAll();
        phanLoaiAdapters = new PhanLoaiAdapter(ds_phanloai, getContext());
        rv_phanloai.setAdapter(phanLoaiAdapters);
    }
}
