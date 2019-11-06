package com.example.nongsan;

public class DonViTinh {
    private int id;
    private String DonVi;

    public DonViTinh(int id, String donVi) {
        this.id = id;
        DonVi = donVi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDonVi() {
        return DonVi;
    }

    public void setDonVi(String donVi) {
        DonVi = donVi;
    }
}
