package com.example.tugaspb.crudsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class CrudSqlite_DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mydb";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    public static final String CONTACTS_COLUMN_IMAGE = "image";
    private HashMap hp;

    public CrudSqlite_DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table "+CONTACTS_TABLE_NAME+
                        "(id integer primary key, " +
                        "name text not null," +
                        "phone text," +
                        "email text, " +
                        "street text," +
                        "place text," +
                        "image blob )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact (String name, String phone, String email, String street,String place, byte[] img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
//        if (!img.isEmpty()) {
//            try {
//                FileInputStream fs = new FileInputStream(img);
//                byte[] imgbyte = new byte[fs.available()];
//                fs.read(imgbyte);
//                contentValues.put("image", imgbyte);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        contentValues.put("image", img);
        Log.d("insert", name);
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);

        return true;
    }
    public void clearDatabase(String TABLE_NAME) {
        SQLiteDatabase db = this.getReadableDatabase();
        String clearDBQuery = "DELETE FROM "+TABLE_NAME;
        db.execSQL(clearDBQuery);
    }
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        Log.d("data getData",res.toString());
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place,byte[] img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        contentValues.put("image", img);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<com.example.tugaspb.crudsqlite.CrudSqlite_ContactModel> getAllContacts() {
        ArrayList<com.example.tugaspb.crudsqlite.CrudSqlite_ContactModel> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            com.example.tugaspb.crudsqlite.CrudSqlite_ContactModel model = new com.example.tugaspb.crudsqlite.CrudSqlite_ContactModel(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)),res.getInt(res.getColumnIndex(CONTACTS_COLUMN_ID)));
            array_list.add(model);
            res.moveToNext();
        }
        return array_list;
    }
}