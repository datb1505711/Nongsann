package com.example.nongsan;

import java.util.Map;

public class User {
    private String hoTen;
    private String username;
    private String password;
    private String sdt;
    private String diaChi;
    private String accountType;
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public User(String hoTen, String username, String password, String sdt, String diaChi, String accountType, String imageUrl) {
        this.hoTen = hoTen;
        this.username = username;
        this.password = password;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.accountType = accountType;
        this.imageUrl = imageUrl;
    }

    public User() {}


}
