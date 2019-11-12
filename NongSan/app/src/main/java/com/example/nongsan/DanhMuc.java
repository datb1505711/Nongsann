package com.example.nongsan;

public class DanhMuc {
    int id;
    String description;

    public DanhMuc() {
    }

    public DanhMuc(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
