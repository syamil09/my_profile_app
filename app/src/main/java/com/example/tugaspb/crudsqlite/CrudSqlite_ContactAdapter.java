package com.example.tugaspb.crudsqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CrudSqlite_ContactAdapter extends BaseAdapter {

    List<CrudSqlite_ContactModel> list;
    Context ctx;

    public CrudSqlite_ContactAdapter(List<CrudSqlite_ContactModel> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final CrudSqlite_ContactModel model = list.get(i);

        if(view == null)
        {
            LayoutInflater inflater = LayoutInflater.from(ctx);
            view = inflater.inflate(android.R.layout.activity_list_item,viewGroup,false);
        }

        TextView txtName = view.findViewById(android.R.id.text1);
        txtName.setText(model.getName());
        txtName.setTextSize(20);
        view.setPadding(20,20,20,20);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id_To_Search = model.getId();

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(ctx, CrudSqlite_DisplayContact.class);

                intent.putExtras(dataBundle);
                ctx.startActivity(intent);
            }
        });

        return view;
    }
}
