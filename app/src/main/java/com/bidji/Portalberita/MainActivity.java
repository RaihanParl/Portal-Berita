package com.bidji.Portalberita;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends Baseapp {
AQuery aq;
    ArrayList<mkonten> data;
     adapterkonten adapterkonten;
    ListView lvdata;
    SwipeRefreshLayout swLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data=new ArrayList<>();
        lvdata = (ListView)findViewById(R.id.lvkonten);
        aq = new AQuery(getApplicationContext());
        swLayout = (SwipeRefreshLayout)findViewById(R.id.refresh);
        swLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swLayout.setRefreshing(false);
                getkonten();
            }
        });
        getkonten();
        lvdata.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent , View view, int position, long id) {
                mkonten b = data.get(position);
                Intent a =new Intent(getApplicationContext(),DetailKonten.class);
                a.putExtra("id_konten",b.getId_konten());
                a.putExtra("judul",b.getJudul_konten());
                a.putExtra("isi",b.getIsi());
                a.putExtra("gambar_konten",b.getIconketerangan());
                startActivity(a);
            }
        });
    }
    private void getkonten() {
        try {


            data.clear();
            String url = server.BASE_URL + "getkonten.php";
            Map<String, String> parampa = new HashMap<>();
            ProgressDialog progressDialog = new ProgressDialog(getApplicationContext());
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setInverseBackgroundForced(false);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.setTitle("Please wait...");
            progressDialog.setMessage("Loading...");
            try {
                aq.progress(progressDialog).ajax(url, parampa, String.class, new AjaxCallback<String>() {
                    @Override
                    public void callback(String url, String hasil, AjaxStatus status) {

                        if (hasil != null) {
                            try {
                                JSONObject json = new JSONObject(hasil);
                                String result = json.getString("result");
                                String pesan = json.getString("msg");
                                if (result.equalsIgnoreCase("1")) {
                                    JSONArray jsonArray = json.getJSONArray("jurnal");
                                    for (int a = 0; a < jsonArray.length(); a++) {
                                        JSONObject object = jsonArray.getJSONObject(a);
                                        mkonten d = new mkonten();
                                        d.setId_konten(object.getString("id_konten"));
                                        d.setJudul_konten(object.getString("judul_konten"));
                                        d.setIsi(object.getString("isi_konten"));
                                        d.setIconketerangan(object.getString("gambar_konten"));
                                        data.add(d);
                                        adapterkonten = new adapterkonten(getApplicationContext(), data);
                                        lvdata.setAdapter(adapterkonten);
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), pesan, Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "error parsing data", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "error get data ", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "gagal nyambung ", Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }
    }

}
