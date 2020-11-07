package com.kietnt.mob202_asm.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "PS13478_ASM", null, 1);
}

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng Loại thu chi
        String sql = "CREATE TABLE LOAI_TC(MaLoai integer primary key autoincrement, TenLoai text, TrangThai text)";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES('Lãi ngân hàng', 'Thu')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES('Lương', 'Thu')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES('Bán hàng', 'Thu')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES('Thu nợ', 'Thu')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES('Sinh hoạt hằng ngày', 'Chi')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES('Đóng tiền học', 'Chi')";
        db.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai, TrangThai) VALUES('Du lịch', 'Chi')";
        db.execSQL(sql);

        //Tạo bảng Khoản thu chi
        sql = "CREATE TABLE GIAO_DICH(MaGD integer primary key autoincrement, TieuDe Text, Ngay Date, Tien double, MoTa Text, MaLoai integer references LOAI_TC(MaLoai))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS LOAI_TC");
        db.execSQL("DROP TABLE IF EXISTS GIAO_DICH");
    }
}
