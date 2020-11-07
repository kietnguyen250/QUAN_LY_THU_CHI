package com.kietnt.mob202_asm.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.kietnt.mob202_asm.Dialog.Bottom_sheet_edit_pl;
import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.dao.PhanLoai_DAO;
import com.kietnt.mob202_asm.model.PhanLoai;

import java.util.ArrayList;

import static com.kietnt.mob202_asm.Fragment.Fragment_phanloai.rv_phanloai;

public class PhanLoaiAdapter extends RecyclerView.Adapter<PhanLoaiAdapter.MyViewHolder> {
    private ArrayList<PhanLoai> ds_phanloai;
    private Context context;
    PhanLoai_DAO phanLoai_dao;
    private PhanLoaiAdapter phanLoaiAdapters;


    public PhanLoaiAdapter(ArrayList<PhanLoai> ds_phanloai, Context context) {
        this.ds_phanloai = ds_phanloai;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tenloai;
        public TextView tv_trangthai;
        private ImageView iv_xoa_pl;
        private ImageView iv_edit_pl;

        public MyViewHolder(View v) {
            super(v);
            tv_tenloai = v.findViewById(R.id.tv_tenLoai);
            tv_trangthai = v.findViewById(R.id.tv_trangThai);
            iv_edit_pl = v.findViewById(R.id.iv_sua_pl);
            iv_xoa_pl = v.findViewById(R.id.iv_xoa_pl);
        }
    }

    @NonNull
    @Override
    public PhanLoaiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phanloai, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_tenloai.setText(ds_phanloai.get(position).getTenLoai());
        holder.tv_trangthai.setText(ds_phanloai.get(position).getTrangThai());
        holder.iv_xoa_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn muốn xóa "+ds_phanloai.get(position).getTenLoai()+ "?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Có",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                phanLoai_dao = new PhanLoai_DAO(context);
                                phanLoai_dao.delete(ds_phanloai.get(position).getMaLoai());
                                Toast.makeText(context, "Xóa thành công "+ds_phanloai.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                                capnhat();
                            }
                        });

                builder1.setNegativeButton(
                        "Không",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

        holder.iv_edit_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("MaLoai", ds_phanloai.get(position).getMaLoai()+"");
                args.putString("TenLoai", ds_phanloai.get(position).getTenLoai());
                args.putString("TrangThai", ds_phanloai.get(position).getTrangThai());
                Bottom_sheet_edit_pl bottom_sheet = new Bottom_sheet_edit_pl();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(), bottom_sheet.getTag());
            }
        });

    }

    @Override
    public int getItemCount() {
        return ds_phanloai.size();
    }

    public void capnhat(){
        ds_phanloai = phanLoai_dao.getAll();
        phanLoaiAdapters = new PhanLoaiAdapter(ds_phanloai, context);
        rv_phanloai.setAdapter(phanLoaiAdapters);
    }
}