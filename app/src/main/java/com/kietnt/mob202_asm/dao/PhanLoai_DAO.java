package com.kietnt.mob202_asm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kietnt.mob202_asm.database.DbHelper;
import com.kietnt.mob202_asm.model.PhanLoai;

import java.util.ArrayList;

public class PhanLoai_DAO {

    DbHelper helper;
    public PhanLoai_DAO(Context context) {helper = new DbHelper(context);}
    public ArrayList<PhanLoai> getAll(){
        ArrayList<PhanLoai> ds_pl = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.query("LOAI_TC", null, null, null, null, null, "TrangThai DESC   ");
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int maLoai = cs.getInt(0);
            String tenLoai = cs.getString(1);
            String trangThai = cs.getString(2);

            ds_pl.add(new PhanLoai(maLoai, tenLoai, trangThai));
            cs.moveToNext();
        }
        cs.close();
        return ds_pl;
    }
    public boolean insert(PhanLoai loaiThuChi){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai", loaiThuChi.getTenLoai());
        values.put("TrangThai", loaiThuChi.getTrangThai());

        long row = db.insert("LOAI_TC",null, values);
        return row > 0;
    }

    public boolean update(PhanLoai phanloai){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai", phanloai.getTenLoai());
        values.put("TrangThai", phanloai.getTrangThai());
        int row = db.update("LOAI_TC", values, "MaLoai=?",new String[]{phanloai.getMaLoai()+""});
        return row > 0;

    }

    public boolean delete(int maLoai){
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("LOAI_TC", "MaLoai=?",new String[]{maLoai+""});
        return row > 0;
    }
}
