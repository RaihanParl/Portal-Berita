package com.bidji.Portalberita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by You on 10/11/2016.
 */
public class adapterkonten extends BaseAdapter {      private Context c;
    private LayoutInflater inflater = null;
    private ArrayList<mkonten> data;
    public adapterkonten(Context c, ArrayList<mkonten> data){
        this.c=c;
        this.data=data;
    }


    @Override
    public int getCount() {
        return  data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    private class viewHolder{
        TextView txtjudul;
        ImageView ickat;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = convertView;
        viewHolder holder=null;
        if (v==null){
            inflater = (LayoutInflater)c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
            v=inflater.inflate(R.layout.list_konten,null);
            holder = new viewHolder();
            holder.txtjudul = (TextView)v.findViewById(R.id.txtjudul);
            holder.ickat = (ImageView) v.findViewById(R.id.imgkonten);
            v.setTag(holder);
        }else{
            holder = (viewHolder)v.getTag();
        }
        mkonten b = data.get(i);
        holder.txtjudul.setText(b.getJudul_konten());
        //    holder.ickat.setImageResource(Integer.parseInt(b.getnmjurusan()));
        Glide.with(c).load(server.BASE_imgkat+b.getIconketerangan()).placeholder(R.drawable.x).into(holder.ickat);
        return v;
    }
}
