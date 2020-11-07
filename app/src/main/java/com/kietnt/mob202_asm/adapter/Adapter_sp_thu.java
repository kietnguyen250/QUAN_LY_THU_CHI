package com.kietnt.mob202_asm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kietnt.mob202_asm.R;
import com.kietnt.mob202_asm.model.PhanLoai;

import java.util.ArrayList;

public class Adapter_sp_thu extends BaseAdapter {

    ArrayList<PhanLoai> ds_pl_thu;
    Context context;

    public Adapter_sp_thu(ArrayList<PhanLoai> ds_pl_thu, Context context) {
        this.ds_pl_thu = ds_pl_thu;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ds_pl_thu.size();
    }

    @Override
    public Object getItem(int position) {
        return ds_pl_thu.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = ((Activity)context).getLayoutInflater().inflate(R.layout.item_sp_thu, parent, false);
        TextView tv_sp_pl = convertView.findViewById(R.id.tv_sp_thu);
        tv_sp_pl.setText(ds_pl_thu.get(position).getTenLoai());
        return convertView;
    }
}
