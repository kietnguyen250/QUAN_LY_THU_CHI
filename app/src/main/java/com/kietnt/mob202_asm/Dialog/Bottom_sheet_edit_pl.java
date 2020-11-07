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

public class Bottom_sheet_edit_pl extends BottomSheetDialogFragment {
    EditText edt_tenLoai_edit;
    Spinner sp_phanLoai_edit;
    Button btn_sua;
    PhanLoai_DAO phanLoai_dao;
    ArrayList<PhanLoai> ds_phanloai;
    PhanLoaiAdapter phanLoaiAdapters;
    int MaLoai;
    public Bottom_sheet_edit_pl(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_phanloai_edit, container, false);
        edt_tenLoai_edit = view.findViewById(R.id.edt_tenLoai_edit);
        sp_phanLoai_edit = view.findViewById(R.id.sp_phanLoai_edit);
        btn_sua = view.findViewById(R.id.btn_Edit);
        phanLoai_dao = new PhanLoai_DAO(getContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_phanLoai_edit.setAdapter(adapter);

        Bundle mArgs = getArguments();
        MaLoai = Integer.parseInt(mArgs.getString("MaLoai"));
        String TenLoai = mArgs.getString("TenLoai");
        String TrangThai = mArgs.getString("TrangThai");
        selectSpinerValue(sp_phanLoai_edit, TrangThai);
        edt_tenLoai_edit.setText(TenLoai);

        btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten_loai = edt_tenLoai_edit.getText().toString();
                String phanLoai = sp_phanLoai_edit.getSelectedItem().toString();
                PhanLoai pl = new PhanLoai(MaLoai, ten_loai, phanLoai);
                phanLoai_dao.update(pl);
                capnhat();
                Toast.makeText(getContext(), "Update thành công", Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });

        return view;
    }

    private void selectSpinerValue(Spinner spinner, String myString){
        int index = 0;
        for (int i = 0; i< spinner.getCount(); i++){
            if (spinner.getItemAtPosition(i).toString().equals(myString)){
                spinner.setSelection(i);
                break;
            }
        }

    }

    public void capnhat(){
        ds_phanloai = new ArrayList<>();
        ds_phanloai = phanLoai_dao.getAll();
        phanLoaiAdapters = new PhanLoaiAdapter(ds_phanloai, getContext());
        rv_phanloai.setAdapter(phanLoaiAdapters);
    }
}
