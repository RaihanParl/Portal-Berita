package com.bidji.Portalberita;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidquery.AQuery;

/**
 * Created by May on 11/1/2016.
 */
public class Baseapp extends AppCompatActivity {
    public static  Context c;
    public static AQuery aq;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = this;
        aq = new AQuery(this);


    }
}
