package com.kietnt.mob202_asm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.model.GiaoDich;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Tk_Thu_Adapter extends RecyclerView.Adapter<Tk_Thu_Adapter.MyViewHolder> {
    private ArrayList<GiaoDich> ds_thu;
    private Context context;

    public Tk_Thu_Adapter(ArrayList<GiaoDich> ds_thu, Context context) {
        this.ds_thu = ds_thu;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tieuDe;
        public TextView tv_ngay;
        private TextView tv_tien;
        public MyViewHolder(View view) {
            super(view);
            tv_tieuDe = view.findViewById(R.id.tv_tieuDe);
            tv_ngay = view.findViewById(R.id.tv_ngay);
            tv_tien = view.findViewById(R.id.tv_tien);


        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongke, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_tieuDe.setText(ds_thu.get(position).getTieuDe());
        holder.tv_ngay.setText(ds_thu.get(position).getNgay()+"");
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(ds_thu.get(position).getTien());
        holder.tv_tien.setText(s);
    }

    @Override
    public int getItemCount() {
        return ds_thu.size();
    }
}
