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
import com.bumptech.glide.Glide;
import com.zoptal.eauction.R;
import com.zoptal.eauction.common.NetworkConnection;
import com.zoptal.eauction.url.RegisterUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AboutusActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_setting,img_filter,img_logo;
    private TextView tv_about;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        initview();
    }


    public void initview(){

        img_setting=(ImageView)findViewById(R.id.img_setting);
        img_filter=(ImageView)findViewById(R.id.img_filter);
        img_logo=(ImageView)findViewById(R.id.img_logo);

        tv_about=(TextView)findViewById(R.id.tv_about);

        img_filter.setOnClickListener(this);
        img_setting.setOnClickListener(this);
        img_logo.setOnClickListener(this);


        if (NetworkConnection.isConnectedToInternet(AboutusActivity.this)) {

           about_us();
        }
        else {
            Toast.makeText(AboutusActivity.this,R.string.networkconnection, Toast.LENGTH_SHORT).show();
            return;

        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_setting:

                Intent intent1 = new Intent(AboutusActivity.this, SettingActivity.class);
                startActivity(intent1);
                break;

            case R.id.img_filter:

                Intent intent = new Intent(AboutusActivity.this, FilterActivity.class);
                startActivity(intent);
                break;

            case R.id.img_logo:

                Intent accountintent = new Intent(AboutusActivity.this, UserAccountActivity.class);
                startActivity(accountintent);

                break;
        }
    }



    private void about_us(){

        loading = new ProgressDialog(AboutusActivity.this,R.style.AppCompatAlertDialogStyle);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loading.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.about_us,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();

                        Log.e("respose abutos===",""+response);

                        try {
                            JSONObject main_obj = new JSONObject(response);
                            String msg =main_obj.getString("message");
                            //   String smsstatus =main_obj.getString("smsstatus");


                            JSONObject  obj=main_obj.getJSONObject("data");

                            String content=obj.getString("content");

                            tv_about.setText(content);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AboutusActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
