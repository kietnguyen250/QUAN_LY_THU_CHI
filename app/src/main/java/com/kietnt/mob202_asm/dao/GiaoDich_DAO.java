package com.kietnt.mob202_asm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kietnt.mob202_asm.database.DbHelper;
import com.kietnt.mob202_asm.model.GiaoDich;
import com.kietnt.mob202_asm.model.PhanLoai;

import java.util.ArrayList;

public class GiaoDich_DAO {
    DbHelper helper;
    public GiaoDich_DAO(Context context) {helper = new DbHelper(context);}

    public ArrayList<PhanLoai> get_Thu_chi(String trangthais){
        ArrayList<PhanLoai> ds_pl = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM LOAI_TC WHERE TrangThai LIKE '"+trangthais+"'", null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int maLoai = cs.getInt(0);
            String tenLoai = cs.getString(1);
            String trangThai = cs.getString(2);

            ds_pl.add(new PhanLoai(maLoai, tenLoai, trangThai));
            cs.moveToNext();
        }

        return ds_pl;
    }



    public String getTen(String id){
        String ten = "";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT TenLoai FROM LOAI_TC WHERE MaLoai = '"+id+"'",null);
        while (!cs.isAfterLast()){
            ten = cs.getString(0);
            cs.moveToNext();
        }

        return ten;

    }
    

    public ArrayList<GiaoDich> readAll(String trangthaiz){
        ArrayList<GiaoDich> ds_giaodich = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM GIAO_DICH INNER JOIN LOAI_TC ON GIAO_DICH.MaLoai = LOAI_TC.MaLoai" +
                " AND LOAI_TC.TrangThai LIKE '"+trangthaiz+"'",null);
        cs.moveToFirst();
        while (!cs.isAfterLast()){
            int MaGD = cs.getInt(0);
            String TieuDe = cs.getString(1);
            String Ngay = cs.getString(2);
            double Tien = cs.getDouble(3);
            String MoTa = cs.getString(4);
            int MaLoai = cs.getInt(5);

            ds_giaodich.add(new GiaoDich(MaGD, TieuDe, Ngay, Tien, MoTa, MaLoai));
            cs.moveToNext();
        }
        return ds_giaodich;
    }

    public ArrayList<GiaoDich> get_Tk_Thu(String date1, String date2){
        ArrayList<GiaoDich> ds_giaodich = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GIAO_DICH,LOAI_TC WHERE ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Thu'" ,null);
        if (cursor.moveToFirst()){
            do {
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                double Tien = Double.parseDouble(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                ds_giaodich.add(new GiaoDich(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public ArrayList<GiaoDich> get_Tk_Chi(String date1, String date2){
        ArrayList<GiaoDich> ds_giaodich = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GIAO_DICH,LOAI_TC WHERE ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GIAO_DICH.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE 'Chi'" ,null);
        if (cursor.moveToFirst()){
            do {
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                double Tien = Double.parseDouble(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                ds_giaodich.add(new GiaoDich(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public String getBetweenDay(String date1, String date2){
        String total= "";
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(Tien) FROM GIAO_DICH WHERE Ngay BETWEEN '2020-07-20' AND '2020-07-21'", null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getString(0);
            } while (cursor.moveToNext());
        }

        return total;
    }

    public double getTotalthubyDate(String date1, String date2){
        double total = 0.0;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(Tien) FROM GIAO_DICH,LOAI_TC WHERE Ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GIAO_DICH.MaLoai = LOAI_TC.MaLoai AND LOAI_TC.TrangThai LIKE 'Thu'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);

            }while (cursor.moveToNext());
        }
        return total;
    }

    public double getTotalchibyDate(String date1, String date2){
        double total = 0.0;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(Tien) FROM GIAO_DICH,LOAI_TC WHERE Ngay BETWEEN '"+date1+"' AND '"+date2+"' AND GIAO_DICH.MaLoai = LOAI_TC.MaLoai AND LOAI_TC.TrangThai LIKE 'Chi'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);

            }while (cursor.moveToNext());
        }
        return total;
    }
    public double getTotalthu(){
        double total = 0.0;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(Tien) FROM GIAO_DICH INNER JOIN LOAI_TC ON GIAO_DICH.MaLoai = LOAI_TC.MaLoai AND LOAI_TC.TrangThai LIKE 'Thu'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);

            }while (cursor.moveToNext());
        }
        return total;
    }

    public double getTotalchi(){
        double total = 0.0;
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(Tien) FROM GIAO_DICH INNER JOIN LOAI_TC ON GIAO_DICH.MaLoai = LOAI_TC.MaLoai AND LOAI_TC.TrangThai LIKE 'Chi'",null);
        if (cursor.moveToFirst()){
            do {
                total = cursor.getDouble(0);

            }while (cursor.moveToNext());
        }
        return total;
    }




    public boolean insert(GiaoDich gd){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TieuDe", gd.getTieuDe());
        values.put("Ngay", gd.getNgay());
        values.put("Tien", gd.getTien());
        values.put("MoTa", gd.getMoTa());
        values.put("MaLoai", gd.getMaLoai());
        long row = db.insert("GIAO_DICH",null, values);
        return row > 0;
    }

    public boolean capnhat(GiaoDich gd){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TieuDe", gd.getTieuDe());
        values.put("Ngay", gd.getNgay());
        values.put("Tien", gd.getTien());
        values.put("MoTa", gd.getMoTa());
        values.put("MaLoai", gd.getMaLoai());
        int row = db.update("GIAO_DICH", values, "MaGD=?",new String[]{gd.getMaGD()+""});
        return row > 0;

    }

    public boolean delete(int MaGD){
        SQLiteDatabase db = helper.getWritableDatabase();
        int row = db.delete("GIAO_DICH", "MaGD=?",new String[]{MaGD+""});
        return row > 0;
    }
}
