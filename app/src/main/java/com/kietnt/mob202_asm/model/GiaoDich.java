package com.kietnt.mob202_asm.model;

import java.util.Date;

public class GiaoDich {
    private int MaGD;
    private String TieuDe;
    private String Ngay;
    private double Tien;
    private String MoTa;
    private int MaLoai;

    public GiaoDich() {
    }

    public GiaoDich(String tieuDe, String ngay, double tien, String moTa, int maLoai) {
        TieuDe = tieuDe;
        Ngay = ngay;
        Tien = tien;
        MoTa = moTa;
        MaLoai = maLoai;
    }

    public GiaoDich(int maGD, String tieuDe, String ngay, double tien, String moTa, int maLoai) {
        MaGD = maGD;
        TieuDe = tieuDe;
        Ngay = ngay;
        Tien = tien;
        MoTa = moTa;
        MaLoai = maLoai;
    }

    public int getMaGD() {
        return MaGD;
    }

    public void setMaGD(int maGD) {
        MaGD = maGD;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String tieuDe) {
        TieuDe = tieuDe;
    }

    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public double getTien() {
        return Tien;
    }

    public void setTien(double tien) {
        Tien = tien;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }
}
