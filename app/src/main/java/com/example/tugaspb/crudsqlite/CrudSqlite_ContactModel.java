package com.example.tugaspb.crudsqlite;

public class CrudSqlite_ContactModel {

    String name;
    int id;

    public CrudSqlite_ContactModel(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
