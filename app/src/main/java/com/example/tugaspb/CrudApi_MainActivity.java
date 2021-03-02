package com.example.tugaspb;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;

public class CrudApi_MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    EditText etId, etNama, etNohp, etEmail;
    Spinner spKomentar;
    Button btnSave;
    ListView listView;
    ActionBar actionBar;

    int CODE_GET_REQUEST  = 1;
    int CODE_POST_REQUEST = 2;
    boolean isUpdating = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setContentView(R.layout.crud__api_activity_main);


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        etId = (EditText)findViewById(R.id.etId);
        etNama = findViewById(R.id.etNama);
        etNohp = (EditText)findViewById(R.id.etNohp);
        etEmail = (EditText)findViewById(R.id.etEmail);
        spKomentar = (Spinner)findViewById(R.id.spKomentar);
        btnSave = (Button)findViewById(R.id.btnSave);
        listView = findViewById(R.id.listView);
//        btnSave.setOnClickListener(this::onClick);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if it is updating
                if (isUpdating) {
                    updateData();
                } else {
                    //if it is not updating
                    //that means it is creating
                    buatData();
                }
            }
        });
        readDataku();
        closeKeyboard();
    }

    private void readDataku() {
        PerformNetworkRequest request = new PerformNetworkRequest(CrudApi_Api.READ_URL, null, CODE_GET_REQUEST);
        request.execute();
    }

    @Override
    public void onClick(View view) {
        //if it is updating
        if (isUpdating) {
            updateData();
        } else {
            //if it is not updating
            //that means it is creating
            buatData();
        }
    }

    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }
    private void buatData() {
        String nama = etNama.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String nohp = etNohp.getText().toString().trim();
        String Keterangan = spKomentar.getSelectedItem().toString();


        //validating the inputs
        if (TextUtils.isEmpty(nama)) {
            etNama.setError("Masukkan Nama");
            etNama.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Masukkan email");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(nohp)) {
            etNohp.setError("Masukkan Nomor HP");
            etNohp.requestFocus();
            return;
        }

        //if validation passes

        HashMap<String, String> params = new HashMap<>();
        params.put("nama", nama);
        params.put("email", email);
        params.put("nohp", nohp);
        params.put("komentar", Keterangan);

        //Calling the create API
        PerformNetworkRequest request = new PerformNetworkRequest(CrudApi_Api.ADD_URL, params, CODE_POST_REQUEST);
        request.execute();
        closeKeyboard();
    }

    private void updateData() {
        String id = etId.getText().toString();
        String nama = etNama.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String nohp = etNohp.getText().toString().trim();
        String komentar = spKomentar.getSelectedItem().toString();


        if (TextUtils.isEmpty(nama)) {
            etNama.setError("Please enter nama");
            etNama.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Please enter e-mail");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(nohp)) {
            etNohp.setError("Please enter Nomor HP");
            etNohp.requestFocus();
            return;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("nama", nama);
        params.put("email", email);
        params.put("nohp", nohp);
        params.put("komentar", komentar);


        PerformNetworkRequest request = new PerformNetworkRequest(CrudApi_Api.UPDATE_URL, params, CODE_POST_REQUEST);
        request.execute();

        btnSave.setText("Add");

        etNama.setText("");
        etEmail.setText("");
        etNohp.setText("");
        spKomentar.setSelection(0);

        isUpdating = false;
        closeKeyboard();
    }

    private void deleteData(int id) {

        PerformNetworkRequest request = new PerformNetworkRequest(CrudApi_Api.DELETE_URL + "&id=" + id, null, CODE_GET_REQUEST);
        request.execute();
    }

    public class PerformNetworkRequest extends AsyncTask<Void, Void, String> {
        //the url where we need to send the request
        String url;

        List<CrudApi_Biodata> dataList = new ArrayList<CrudApi_Biodata>();

        //the parameters
        HashMap<String, String> params;

        //the request code to define whether it is a GET or POST
        int requestCode;

        //constructor to initialize values
        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        //when the task started displaying a progressbar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        //this method will give the response from the request
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    //refreshing the list after every operation
                    //so we get an updated list
                    //we will create this method right now it is commented
                    //because we haven't created it yet
//                    Toast.makeText(CrudApi_MainActivity.this, "Sukses", Toast.LENGTH_SHORT).show();
                    refreshDataku(object.getJSONArray("data"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private void refreshDataku(JSONArray dataarrayku) throws JSONException {
            //clearing previous data
            dataList.clear();

            //traversing through all the items in the json array
            //the json we got from the response
            for (int i = 0; i < dataarrayku.length(); i++) {
                //getting each hero object
                JSONObject obj = dataarrayku.getJSONObject(i);

                //adding to the list
                dataList.add(new CrudApi_Biodata(
                        obj.getInt("id"),
                        obj.getString("nama"),
                        obj.getString("email"),
                        obj.getString("nohp"),
                        obj.getString("komentar")
                ));
            }

            //creating the adapter and setting it to the listview
            DatakuAdapter adapter = new DatakuAdapter(dataList);
            listView.setAdapter(adapter);
        }

        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            CrudApi_RequestHandler crudApiRequestHandler = new CrudApi_RequestHandler();
            if (requestCode == CODE_POST_REQUEST)
                return crudApiRequestHandler.sendPostRequest(url, params);
            else if (requestCode == CODE_GET_REQUEST)
                return crudApiRequestHandler.sendGetRequest(url);
            return null;
        }



    }

    class DatakuAdapter extends ArrayAdapter<CrudApi_Biodata> {

        //our list
        List<CrudApi_Biodata> dataList;

        //constructor to get the list
        public DatakuAdapter(List<CrudApi_Biodata> dataList) {
            super(com.example.tugaspb.CrudApi_MainActivity.this, R.layout.crud__api_layout_data_list, dataList );
            this.dataList = dataList;
        }


        //method returning list item
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.crud__api_layout_data_list, null, true);

            //getting the textview for displaying name
            TextView textViewName = listViewItem.findViewById(R.id.txtName);
            //the update and delete textview
            TextView textViewUpdate = listViewItem.findViewById(R.id.txtUpdate);
            TextView textViewDelete = listViewItem.findViewById(R.id.txtDelete);
            ImageView img = listViewItem.findViewById(R.id.imageView);


            final CrudApi_Biodata dataku = dataList.get(position);

            textViewName.setText(dataku.getNama());
            if (dataku.getKeterangan().equals("Teman")) {
                img.setImageResource(R.drawable.crud__api_laugh);
            } else if(dataku.getKeterangan().equals("Biasa Saja")) {
                img.setImageResource(R.drawable.crud__api_celtic);
            } else if (dataku.getKeterangan().equals("Bagaimana Ya?")) {
                img.setImageResource(R.drawable.crud__api_confusion);
            } else {
                img.setImageResource(R.drawable.crud__api_confused);
            }
            //attaching click listener to update
            textViewUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //so when it is updating we will
                    //make the isUpdating as true
                    isUpdating = true;

                    //we will set the selected hero to the UI elements
                    etId.setText(String.valueOf(dataku.getId()));
                    etNama.setText(dataku.getNama());
                    etEmail.setText(dataku.getEmail());
                    etNohp.setText(dataku.getNohp());
                    spKomentar.setSelection(((ArrayAdapter<String>) spKomentar.getAdapter()).getPosition(dataku.getKeterangan()));

                    //we will also make the button text to Update
                    btnSave.setText("Update");
                }
            });

            //when the user selected delete
            textViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // we will display a confirmation dialog before deleting
                    AlertDialog.Builder builder = new AlertDialog.Builder(com.example.tugaspb.CrudApi_MainActivity.this);

                    builder.setTitle("Delete " + dataku.getNama())
                            .setMessage("Are you sure you want to delete it?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //if the choice is yes we will delete the hero
                                    //method is commented because it is not yet created
                                    deleteData(dataku.getId());
                                }
                            })
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
            });

            return listViewItem;
        }
    }
}


