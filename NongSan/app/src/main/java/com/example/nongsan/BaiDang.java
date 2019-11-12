package com.example.nongsan;

import java.util.Date;

public class BaiDang {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenBaiDang() {
        return tenBaiDang;
    }

    public void setTenBaiDang(String tenBaiDang) {
        this.tenBaiDang = tenBaiDang;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getLoaisp() {
        return loaisp;
    }

    public void setLoaisp(String loaisp) {
        this.loaisp = loaisp;
    }

    public String getLoaiGia() {
        return loaiGia;
    }

    public void setLoaiGia(String loaiGia) {
        this.loaiGia = loaiGia;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public BaiDang() {
    }

    public BaiDang(int id, String tenBaiDang, String noiDung, Date ngayLap, String anh, String idUser, String loaisp, String loaiGia, String gia) {
        this.id = id;
        this.tenBaiDang = tenBaiDang;
        this.noiDung = noiDung;
        this.ngayLap = ngayLap;
        this.anh = anh;
        this.idUser = idUser;
        this.loaisp = loaisp;
        this.loaiGia = loaiGia;
        this.gia = gia;
    }

    private int id;
    private String tenBaiDang;
    private String noiDung;
    private Date ngayLap;
    private String anh;
    private String idUser;
    private String loaisp;
    private String loaiGia;
    private String gia;
}
