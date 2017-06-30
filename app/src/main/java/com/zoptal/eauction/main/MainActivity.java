package com.zoptal.eauction.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.zoptal.eauction.R;
import com.zoptal.eauction.common.ExpandableHeightGridView;
import com.zoptal.eauction.model.Model_MyAuctions;
import com.zoptal.eauction.model.Model_ProductDetail;
import com.zoptal.eauction.url.RegisterUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView img_setting,img_filter,img_logo;

    private ExpandableHeightGridView gridView_endsoon;
    private ExpandableHeightGridView gridView_active;
    private ExpandableHeightGridView gridView_expired;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
    }

    public void initview(){

        img_setting=(ImageView)findViewById(R.id.img_setting);
        img_filter=(ImageView)findViewById(R.id.img_filter);
        img_logo=(ImageView)findViewById(R.id.img_logo);


        gridView_endsoon=(ExpandableHeightGridView)findViewById(R.id.gridView_endsoon);
        gridView_endsoon.setExpanded(true);
        gridView_endsoon.setAdapter(new CustomListAdapter_Endsoon());

        gridView_active=(ExpandableHeightGridView)findViewById(R.id.gridView_active);
        gridView_active.setExpanded(true);
        gridView_active.setAdapter(new CustomListAdapter_active());


        gridView_expired=(ExpandableHeightGridView)findViewById(R.id.gridView_expired);
        gridView_expired.setExpanded(true);
        gridView_expired.setAdapter(new CustomListAdapter_expired());

        img_setting.setOnClickListener(this);
        img_filter.setOnClickListener(this);
        img_logo.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {

        //  super.onBackPressed();
        this.moveTaskToBack(true);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_setting:

                Intent i=new Intent(MainActivity.this,SettingActivity.class);
                startActivity(i);

                break;

            case R.id.img_filter:

                Intent filterintent=new Intent(MainActivity.this,FilterActivity.class);
                startActivity(filterintent);

                break;

            case R.id.img_logo:

                Intent accountintent=new Intent(MainActivity.this,UserAccountActivity.class);
                startActivity(accountintent);


                break;

        }
    }

    class CustomListAdapter_Endsoon extends BaseAdapter {

        LayoutInflater inflater;
        Context context;
        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
        };
        public CustomListAdapter_Endsoon() {

            this.context = context;
            inflater = LayoutInflater.from(getApplicationContext());

            // TODO Auto-generated constructor stub
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return 4;

        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }



        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            Holdlerstore holder = new Holdlerstore();
            convertView = inflater.inflate(R.layout.customview_endsoon, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);

            holder.img.setBackgroundResource(mResources[position]);

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(MainActivity.this,AuctionActivity.class);
                    startActivity(i);

                }
            });




            return convertView;


        }
        class Holdlerstore
        {
            ImageView img;

        }

    }

    class CustomListAdapter_active extends BaseAdapter {

        LayoutInflater inflater;
        Context context;
        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
        };
        public CustomListAdapter_active() {

            this.context = context;
            inflater = LayoutInflater.from(getApplicationContext());

            // TODO Auto-generated constructor stub
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return 4;

        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }



        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            Holdleractive holder = new Holdleractive();
            convertView = inflater.inflate(R.layout.customview_active, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);

            holder.img.setBackgroundResource(mResources[position]);

            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(MainActivity.this,AuctionActivity.class);
                    startActivity(i);

                }
            });


            return convertView;


        }
        class Holdleractive
        {
            ImageView img;

        }

    }



    class CustomListAdapter_expired extends BaseAdapter {

        LayoutInflater inflater;
        Context context;
        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
        };
        public CustomListAdapter_expired() {

            this.context = context;
            inflater = LayoutInflater.from(getApplicationContext());

            // TODO Auto-generated constructor stub
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return 4;

        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }



        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            Holdleractive holder = new Holdleractive();
            convertView = inflater.inflate(R.layout.customview_expired, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);

            holder.img.setBackgroundResource(mResources[position]);


            return convertView;


        }
        class Holdleractive
        {
            ImageView img;

        }

    }

}


