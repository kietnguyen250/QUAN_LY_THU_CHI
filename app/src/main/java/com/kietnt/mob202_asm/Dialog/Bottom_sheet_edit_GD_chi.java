package com.kietnt.mob202_asm.Dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.kietnt.mob202_asm.TabFragment.Tab_Khoanchi.khoanChiAdapter;
import static com.kietnt.mob202_asm.TabFragment.Tab_Khoanchi.rv_thu;

public class Bottom_sheet_edit_GD_chi extends BottomSheetDialogFragment {
    EditText edt_tieuDe_edit, edt_tien_edit, edt_moTa_edit;
    TextView tv_trangthai, edt_ngay_edit;
    Spinner sp_thu_edit;
    Button btn_Edit_thu;
    GiaoDich_DAO giaoDich_dao;
    ArrayList<GiaoDich> ds_giaodich;
    ArrayList<PhanLoai> ds_loai_thu;
    Adapter_sp_thu adapter_sp_thu;
    //int MaGD;
    public Bottom_sheet_edit_GD_chi(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.botom_sheet_giaodich_edit_chi, container, false);
        edt_tieuDe_edit = view.findViewById(R.id.edt_tieuDe_edit_chi);
        edt_ngay_edit = view.findViewById(R.id.edt_ngay_edit_chi);
        edt_tien_edit = view.findViewById(R.id.edt_tien_edit_chi);
        edt_moTa_edit = view.findViewById(R.id.edt_moTa_edit_chi);
        tv_trangthai = view.findViewById(R.id.tv_trangthai_chi_edit);
        sp_thu_edit = view.findViewById(R.id.sp_chi_edit);
        btn_Edit_thu = view.findViewById(R.id.btn_Edit_chi);

        Bundle mArgs = getArguments();
        final String trangthai_ = mArgs.getString("trangthai");
        tv_trangthai.setText(trangthai_);
        final int MaGD = mArgs.getInt("MaGD");
        String TieuDe = mArgs.getString("TieuDe");
        String Ngay = mArgs.getString("Ngay");
        double Tien = mArgs.getDouble("Tien");
        String MoTa = mArgs.getString("MoTa");
        String MaLoai = mArgs.getString("MaLoai");

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###,###,###");
        String formattedString = formatter.format(Tien);
        edt_tien_edit.setText(formattedString);
        edt_tien_edit.addTextChangedListener(onTextChangedListener());

        edt_tieuDe_edit.setText(TieuDe);
        edt_ngay_edit.setText(Ngay);
        edt_tien_edit.setText(formattedString);
        edt_moTa_edit.setText(MoTa);

        giaoDich_dao = new GiaoDich_DAO(getContext());
        ds_loai_thu = new ArrayList<>();
        ds_loai_thu = giaoDich_dao.get_Thu_chi("Chi");
        String ten_ =giaoDich_dao.getTen(MaLoai);

        adapter_sp_thu = new Adapter_sp_thu(ds_loai_thu,getContext());
        sp_thu_edit.setAdapter(adapter_sp_thu);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);

        edt_ngay_edit.setOnClickListener(new View.OnClickListener() {
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
                        edt_ngay_edit.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },years,months,dayOfWeek);
                datePickerDialog.show();
            }
        });

        for(int i = 0; i < ds_loai_thu.size(); i++){
            if(ds_loai_thu.get(i).getTenLoai().equals(ten_)){
                sp_thu_edit.setSelection(i);
                break;
            }
        }

        btn_Edit_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TieuDe = edt_tieuDe_edit.getText().toString();
                String Ngay = edt_ngay_edit.getText().toString();
                String string = edt_tien_edit.getText().toString();

                DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                format.setParseBigDecimal(true);
                BigDecimal number = null;
                try {
                    number = (BigDecimal) format.parse(string);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                double so = Double.parseDouble(number+"");

                String MoTa = edt_moTa_edit.getText().toString();

                int index = sp_thu_edit.getSelectedItemPosition();
                int ma_loai = ds_loai_thu.get(index).getMaLoai();

                GiaoDich gd = new GiaoDich(MaGD, TieuDe, Ngay, so, MoTa, ma_loai);
                giaoDich_dao = new GiaoDich_DAO(getContext());
                giaoDich_dao.capnhat(gd);

                capnhatChi();

                Toast.makeText(getContext(), "Update thành công "+MaGD, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return view;
    }

//    private void selectSpinerValue(Spinner spinner, String myString){
//        int index = 0;
//        for (int i = 0; i< spinner.getCount(); i++){
//            if (spinner.getItemAtPosition(i).toString().equals(myString)){
//                spinner.setSelection(i);
//                break;
//            }
//        }
//
//    }

    public void capnhatChi(){
        ds_giaodich = giaoDich_dao.readAll("Chi");
        khoanChiAdapter = new KhoanChiAdapter(ds_giaodich, getContext());
        rv_thu.setAdapter(khoanChiAdapter);
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edt_tien_edit.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    edt_tien_edit.setText(formattedString);
                    edt_tien_edit.setSelection(edt_tien_edit.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                edt_tien_edit.addTextChangedListener(this);
            }
        };
    }
}
