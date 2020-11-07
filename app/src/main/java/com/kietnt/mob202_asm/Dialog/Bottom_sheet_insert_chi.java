package com.kietnt.mob202_asm.Dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.adapter.Adapter_sp_thu;
import com.kietnt.mob202_asm.adapter.KhoanChiAdapter;
import com.kietnt.mob202_asm.dao.GiaoDich_DAO;
import com.kietnt.mob202_asm.model.GiaoDich;
import com.kietnt.mob202_asm.model.PhanLoai;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.kietnt.mob202_asm.TabFragment.Tab_Khoanchi.rv_thu;


public class Bottom_sheet_insert_chi extends BottomSheetDialogFragment {
    EditText edt_tieuDe, edt_tien, edt_moTa;
    Spinner sp_giaoDich;
    TextView tv_trangthai, edt_ngay;
    Button btn_them_GD;
    GiaoDich_DAO giaoDich_dao;
    ArrayList<GiaoDich> ds_giaodich;
    ArrayList<PhanLoai> ds_loai_thu;
    Adapter_sp_thu adapter_sp_thu;
    KhoanChiAdapter khoanChiAdapter;

    public Bottom_sheet_insert_chi(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_gd_chi, container, false);
        edt_tieuDe = view.findViewById(R.id.edt_tieuDe_chi);
        edt_ngay = view.findViewById(R.id.edt_ngay_chi);
        edt_tien = view.findViewById(R.id.edt_tien_chi);
        edt_moTa = view.findViewById(R.id.edt_moTa_chi);
        sp_giaoDich = view.findViewById(R.id.sp_chi);
        tv_trangthai = view.findViewById(R.id.tv_trangthai_chi);
        btn_them_GD = view.findViewById(R.id.btn_Add_chi);

         Bundle getdata =getArguments();
         final String trangthai_ = getdata.getString("trangthai");
        tv_trangthai.setText(trangthai_);

        giaoDich_dao = new GiaoDich_DAO(getContext());
        ds_loai_thu = new ArrayList<>();
            ds_loai_thu = giaoDich_dao.get_Thu_chi("Chi");

        adapter_sp_thu = new Adapter_sp_thu(ds_loai_thu,getContext());
        sp_giaoDich.setAdapter(adapter_sp_thu);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);

        edt_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(i,i1,i2);
                        edt_ngay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },years,months,dayOfWeek);
                datePickerDialog.show();
            }
        });

        btn_them_GD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieu_de = edt_tieuDe.getText().toString();
                String ngay = edt_ngay.getText().toString();
                double tien = Double.parseDouble(edt_tien.getText().toString());
                String mo_ta = edt_moTa.getText().toString();
                int index = sp_giaoDich.getSelectedItemPosition();
                int MaLoai = ds_loai_thu.get(index).getMaLoai();

                GiaoDich gd = new GiaoDich(tieu_de, ngay, tien, mo_ta, MaLoai);
                giaoDich_dao = new GiaoDich_DAO(getContext());
                giaoDich_dao.insert(gd);
                capnhatChi();
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
        return view;
    }

    public void capnhatChi(){
            ds_giaodich = giaoDich_dao.readAll("Chi");
            khoanChiAdapter = new KhoanChiAdapter(ds_giaodich, getContext());
            rv_thu.setAdapter(khoanChiAdapter);
    }
}
