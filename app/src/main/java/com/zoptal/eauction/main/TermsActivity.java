package com.zoptal.eauction.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zoptal.eauction.R;
import com.zoptal.eauction.url.RegisterUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TermsActivity extends AppCompatActivity implements View.OnClickListener {

//    private ImageView img_setting,img_filter,img_logo;
//    private TextView tv_about;
//    ProgressDialog loading;
    private ImageView img_bck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        initview();
    }


    public void initview(){

        img_bck=(ImageView)findViewById(R.id.img_bck);

//        img_setting=(ImageView)findViewById(R.id.img_setting);
//        img_filter=(ImageView)findViewById(R.id.img_filter);
//        img_logo=(ImageView)findViewById(R.id.img_logo);
//
//        tv_about=(TextView)findViewById(R.id.tv_about);
//
        img_bck.setOnClickListener(this);
//        img_setting.setOnClickListener(this);
//        img_logo.setOnClickListener(this);


//        if (NetworkConnection.isConnectedToInternet(AboutusActivity.this)) {
//
//           about_us();
//        }
//        else {
//            Toast.makeText(AboutusActivity.this,R.string.networkconnection, Toast.LENGTH_SHORT).show();
//            return;
//
//        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_bck:

                finish();

                break;

        }
    }



}
