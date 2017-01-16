package com.bidji.Portalberita;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailKonten extends Baseapp {
    WebView wbkonten;
    TextView txtisi;
    ImageView imgkonten;
    String img, judul, wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_konten);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //txtisi = (TextView) findViewById(R.id.txtisi);

        wb = getIntent().getStringExtra("isi");
        img = getIntent().getStringExtra("gambar_konten");
        judul = getIntent().getStringExtra("judul");
        imgkonten = (ImageView) findViewById(R.id.imgber);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(judul);
        wbkonten = (WebView) findViewById(R.id.web);
        Glide.with(c).load(server.BASE_imgkat + img).into(imgkonten);
        String htmlText = "<html><body style=\"text-align:justify\"> %s </body></Html>";
        wbkonten.loadData(String.format(htmlText, wb), "text/html", "utf-8");
        txtisi = (TextView) findViewById(R.id.txtisi);
//                    txtisi.setText(Html.fromHtml(Html.fromHtml(wb).toString()));
        txtisi.setText(Html.fromHtml(Html.fromHtml(wb).toString()));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");

                share.putExtra(Intent.EXTRA_SUBJECT, judul);
                share.putExtra(Intent.EXTRA_TEXT, judul + "\n" + txtisi.getText().toString());

                startActivity(Intent.createChooser(share, "Bagikan dengan"));
            }
        });
    }
}
