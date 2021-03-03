package com.example.tugaspb;

public class CrudApi_Biodata {
    private int id;
    private String nama, nohp, email, komentar;

    public CrudApi_Biodata(int id, String nama, String email, String nohp, String komentar) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.nohp = nohp;
        this.komentar = komentar;
    }

    public int getId()
    {
        return id;
    }

    public String getNama()
    {
        return nama;
    }

    public String getEmail()
    {
        return email;
    }

    public String getKeterangan()
    {
        return komentar;
    }

    public String getNohp()
    {
        return nohp;
    }




}
