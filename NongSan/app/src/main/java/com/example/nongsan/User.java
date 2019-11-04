package com.example.nongsan;

public class User {
    private String hoTen;
    private String email;
    private String password;
    private String sdt;
    private String diaChi;
    private String accountType;

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public User(String hoTen, String email, String password, String sdt, String diaChi, String accountType) {
        this.hoTen = hoTen;
        this.email = email;
        this.password = password;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.accountType = accountType;
    }
}
