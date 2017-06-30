package com.zoptal.eauction.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zoptal.eauction.R;
import com.zoptal.eauction.common.AlertDialogMsg;
import com.zoptal.eauction.common.NetworkConnection;
import com.zoptal.eauction.url.RegisterUrl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactusActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_setting,img_filter,img_logo;
    private EditText ed_username,ed_email,ed_subject,ed_msg;
    private Button btn_send;
    ProgressDialog loading;
    public final String MyPREFERENCES = "MyPrefs1" ;
    SharedPreferences sharedpreferences1;
    String access_tokn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);
        sharedpreferences1 =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        access_tokn = sharedpreferences1.getString("access_token", "");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initview();

    }


    public void initview(){

        img_setting=(ImageView)findViewById(R.id.img_setting);
        img_filter=(ImageView)findViewById(R.id.img_filter);
        img_logo=(ImageView)findViewById(R.id.img_logo);


        ed_username=(EditText)findViewById(R.id.ed_username);
        ed_email=(EditText)findViewById(R.id.ed_email);
        ed_subject=(EditText)findViewById(R.id.ed_subject);
        ed_msg=(EditText)findViewById(R.id.ed_msg);
        btn_send=(Button)findViewById(R.id.btn_send);


        img_filter.setOnClickListener(this);
        img_setting.setOnClickListener(this);
        img_logo.setOnClickListener(this);
        btn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_setting:

                Intent intent1 = new Intent(ContactusActivity.this, SettingActivity.class);
                startActivity(intent1);
                break;

            case R.id.img_filter:

                Intent intent = new Intent(ContactusActivity.this, FilterActivity.class);
                startActivity(intent);
                break;

            case R.id.img_logo:

                Intent accountintent = new Intent(ContactusActivity.this, UserAccountActivity.class);
                startActivity(accountintent);

                break;

            case R.id.btn_send:

                if (NetworkConnection.isConnectedToInternet(ContactusActivity.this)) {

                    contactdetailsend();
                }
                else {
                    Toast.makeText(ContactusActivity.this,R.string.networkconnection, Toast.LENGTH_SHORT).show();
                    return;

                }


                // contactdetailsend();

                break;

        }
    }


    private void contactdetailsend() {

        final String username=ed_username.getText().toString().trim();
        final String email=ed_email.getText().toString().trim();
        final String subjct=ed_subject.getText().toString().trim();
        final String msg=ed_msg.getText().toString().trim();


        if(username.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(ContactusActivity.this, "Please enter your name.");

            alertmsg.dialog.show();

            return;
        }


        if(email.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(ContactusActivity.this, "Please enter your email-id.");

            alertmsg.dialog.show();

            return;
        }


        if (!isValidEmail(email)) {
            ed_email.setError("Invalid Email");
            return;
        }

        if(subjct.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(ContactusActivity.this, "Please enter subject");

            alertmsg.dialog.show();

            return;
        }
        if(msg.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(ContactusActivity.this, "Please enter Message");

            alertmsg.dialog.show();

            return;
        }

        else {

            loading = new ProgressDialog(ContactusActivity.this,R.style.AppCompatAlertDialogStyle);
            loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.contact_us,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            Toast.makeText(ContactusActivity.this,"Your message submitted successfully.",Toast.LENGTH_SHORT).show();

                            Log.e("respose contactus===",""+response);
                            Intent intent1 = new Intent(ContactusActivity.this, SettingActivity.class);
                            startActivity(intent1);

                        }


                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(ContactusActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<String,String>();

                    map.put("access_token",access_tokn);
                    map.put("name",username);
                    map.put("email",email);
                    map.put("subject",subjct);
                    map.put("message",msg);
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            requestQueue.add(stringRequest);

//
//         Intent intent1 = new Intent(ContactusActivity.this, SettingActivity.class);
//         startActivity(intent1);

        }

    }

    // validating email id
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
