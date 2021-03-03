package com.example.tugaspb.crudsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tugaspb.R;

import java.util.ArrayList;
import java.util.List;

public class CrudSqlite_MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    private ListView obj;
    CrudSqlite_DBHelper mydb;
    List<CrudSqlite_ContactModel> array_list;
    com.example.tugaspb.crudsqlite.CrudSqlite_ContactAdapter arrayAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crud_sqlite__activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        array_list = new ArrayList();
        arrayAdapter = new com.example.tugaspb.crudsqlite.CrudSqlite_ContactAdapter(array_list,this);

        obj = (ListView)findViewById(R.id.listView1);
        mydb = new CrudSqlite_DBHelper(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        array_list = mydb.getAllContacts();
        arrayAdapter=new com.example.tugaspb.crudsqlite.CrudSqlite_ContactAdapter(array_list, this);
        obj.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        switch(item.getItemId()) {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id",0);

                Intent intent = new Intent(this, CrudSqlite_DisplayContact.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }
}