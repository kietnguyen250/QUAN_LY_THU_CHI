package com.kietnt.mob202_asm.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.adapter.Tk_Thu_Adapter;
import com.kietnt.mob202_asm.dao.GiaoDich_DAO;
import com.kietnt.mob202_asm.model.GiaoDich;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Fragment_thongke_thu extends Fragment {
    Button btn_date1, btn_date2;
    TextView tv_total;
    RecyclerView rv_tk_thu;
    public static Tk_Thu_Adapter tk_thu_adapter;
    public static ArrayList<GiaoDich> ds_tk_thu;
    public static ArrayList<GiaoDich> ds_tk_thus;
    GiaoDich_DAO giaoDich_dao;
    double total_1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke_thu, container, false);
        btn_date1 = view.findViewById(R.id.btn_date1);
        btn_date2 = view.findViewById(R.id.btn_date2);
        tv_total = view.findViewById(R.id.tv_total);
        rv_tk_thu = view.findViewById(R.id.rv_tk_thu);
        rv_tk_thu.setLayoutManager(new LinearLayoutManager(getContext()));
        giaoDich_dao = new GiaoDich_DAO(getContext());

        DecimalFormat formatter = new DecimalFormat("#,###");
        total_1 = giaoDich_dao.getTotalthu();
        String s = formatter.format(total_1);
        tv_total.setText("Tổng tiền: "+s+" VNĐ");
        btn_date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date1();
            }
        });
        btn_date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date2();
            }
        });

        ds_tk_thu = new ArrayList<>();
        giaoDich_dao = new GiaoDich_DAO(getContext());

        ds_tk_thu = giaoDich_dao.readAll("Thu");
        tk_thu_adapter = new Tk_Thu_Adapter(ds_tk_thu, getContext());
        rv_tk_thu.setAdapter(tk_thu_adapter);
        return view;
    }
    private void Date1(){
        Date today =new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        final int months = calendar.get(Calendar.MONTH);
        final int years = calendar.get(Calendar.YEAR);

        final Calendar calendar1 = Calendar.getInstance();
        int date = calendar1.get(Calendar.DAY_OF_MONTH);
        int month = calendar1.get(Calendar.MONTH);
        int year = calendar1.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar1.set(i,i1,i2);
                btn_date1.setText(simpleDateFormat.format(calendar1.getTime()));
            }
        },years, months,dayOfWeek);
        datePickerDialog.show();
    }
    private void Date2(){
        Date today =new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        final int months = calendar.get(Calendar.MONTH);
        final int years = calendar.get(Calendar.YEAR);

        final Calendar calendar1 = Calendar.getInstance();
        int date = calendar1.get(Calendar.DAY_OF_MONTH);
        int month = calendar1.get(Calendar.MONTH);
        int year = calendar1.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker view, int i, int i1, int i2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                calendar1.set(i,i1,i2);
                btn_date2.setText(simpleDateFormat.format(calendar1.getTime()));

                ds_tk_thus = giaoDich_dao.get_Tk_Thu(btn_date1.getText().toString(), btn_date2.getText().toString());
                tk_thu_adapter = new Tk_Thu_Adapter(ds_tk_thus, getContext());
                rv_tk_thu.setAdapter(tk_thu_adapter);

                String date1 = btn_date1.getText().toString();
                DecimalFormat formatter = new DecimalFormat("#,###");
                double total_2 = giaoDich_dao.getTotalthubyDate(date1, simpleDateFormat.format(calendar1.getTime()));
                String s = formatter.format(total_2);
                tv_total.setText("Tổng tiền: "+s+"VNĐ");
            }
        },years, months,dayOfWeek);
        datePickerDialog.show();
    }
}
