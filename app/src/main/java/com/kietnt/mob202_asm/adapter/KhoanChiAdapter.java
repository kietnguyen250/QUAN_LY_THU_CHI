package com.kietnt.mob202_asm.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.kietnt.mob202_asm.Dialog.Bottom_sheet_edit_GD_chi;
import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.dao.GiaoDich_DAO;
import com.kietnt.mob202_asm.model.GiaoDich;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.kietnt.mob202_asm.TabFragment.Tab_Khoanchi.rv_thu;


public class KhoanChiAdapter extends RecyclerView.Adapter<KhoanChiAdapter.MyViewHolder> {
    private ArrayList<GiaoDich> ds_thu;
    private Context context;
    GiaoDich_DAO giaoDich_dao;
    private KhoanChiAdapter khoanChiAdapter;


    public KhoanChiAdapter(ArrayList<GiaoDich> ds_thu, Context context) {
        this.ds_thu = ds_thu;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_tieuDe;
        public TextView tv_ngay;
        public TextView tv_tien;
        public TextView tv_moTa;
        public TextView tv_maLoai;
        private Button iv_xoa_thu;
        private Button iv_sua_thu;

        public MyViewHolder(View v) {
            super(v);
            tv_tieuDe = v.findViewById(R.id.tv_tieuDe);
            tv_ngay = v.findViewById(R.id.tv_ngay);
            tv_tien = v.findViewById(R.id.tv_tien);
            tv_moTa = v.findViewById(R.id.tv_moTa);
            tv_maLoai = v.findViewById(R.id.tv_maLoai);
            iv_sua_thu = v.findViewById(R.id.iv_sua_thu);
            iv_xoa_thu = v.findViewById(R.id.iv_xoa_thu);
        }
    }

    @NonNull
    @Override
    public KhoanChiAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_thu, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_tieuDe.setText(ds_thu.get(position).getTieuDe());
        holder.tv_ngay.setText(ds_thu.get(position).getNgay()+"");
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(ds_thu.get(position).getTien());
        holder.tv_tien.setText(s);
        holder.tv_moTa.setText(ds_thu.get(position).getMoTa());
        holder.tv_maLoai.setText(ds_thu.get(position).getMaLoai()+"");

        holder.iv_xoa_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giaoDich_dao = new GiaoDich_DAO(context);
//                final String trangthaii = giaoDich_dao.getTrangthai(ds_thu.get(position).getMaLoai(), ds_thu.get(position).getMaGD());
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn muốn xóa "+ds_thu.get(position).getTieuDe()+ "?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Có",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                giaoDich_dao.delete(ds_thu.get(position).getMaGD());
                                Toast.makeText(context, "Xóa thành công "+ds_thu.get(position).getTieuDe(), Toast.LENGTH_SHORT).show();
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

        holder.iv_sua_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putInt("MaGD", ds_thu.get(position).getMaGD());
                args.putString("TieuDe", ds_thu.get(position).getTieuDe()+"");
                args.putString("Ngay", ds_thu.get(position).getNgay());
                args.putDouble("Tien", ds_thu.get(position).getTien());
                args.putString("MoTa", ds_thu.get(position).getMoTa());
                args.putInt("MaLoai", ds_thu.get(position).getMaLoai());

                Bottom_sheet_edit_GD_chi bottom_sheet_edit_gd = new Bottom_sheet_edit_GD_chi();
                bottom_sheet_edit_gd.setArguments(args);
                bottom_sheet_edit_gd.show(((AppCompatActivity)context).getSupportFragmentManager(), bottom_sheet_edit_gd.getTag());
            }
        });

    }

    @Override
    public int getItemCount() {
        return ds_thu.size();
    }

    public void capnhat(){
            ds_thu = giaoDich_dao.readAll("Chi");
        khoanChiAdapter = new KhoanChiAdapter(ds_thu, context);
        rv_thu.setAdapter(khoanChiAdapter);
    }
}