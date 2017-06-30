package com.zoptal.eauction.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.format.Formatter;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText ed_username,ed_password,ed_cpassword,ed_phnno;
    private TextView tv_login,tv_terms;
    private Button btn_register;
    private String ip;
    ProgressDialog loading;
    public final String MyPREFERENCES = "MyPrefs1" ;
    SharedPreferences sharedpreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

     //   setLocale("ar");

        setContentView(R.layout.activity_register);

//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setDisplayShowCustomEnabled(true);
//        getSupportActionBar().setCustomView(R.layout.actionbar_custom_view_home);
//        View view =getSupportActionBar().getCustomView();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        sharedpreferences1 =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

          initview();
    }

    public void initview() {

        ed_username=(EditText)findViewById(R.id.ed_username);
        ed_password=(EditText)findViewById(R.id.ed_password);
        ed_cpassword=(EditText)findViewById(R.id.ed_cpassword);
        ed_phnno=(EditText)findViewById(R.id.ed_phnno);

        tv_login=(TextView)findViewById(R.id.tv_login);
        tv_terms=(TextView)findViewById(R.id.tv_terms);
     //   tv_privacy=(TextView)findViewById(R.id.tv_privacy);

        btn_register=(Button)findViewById(R.id.btn_register);

        SpannableString login = new SpannableString("LOGIN");
        login.setSpan(new UnderlineSpan(), 0, login.length(), 0);
        tv_login.setText(login);

        SpannableString terms = new SpannableString("Terms of Use & Privacy Policy");
        terms.setSpan(new UnderlineSpan(), 0, terms.length(), 0);
        tv_terms.setText(terms);


        btn_register.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_terms.setOnClickListener(this);

        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
        ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        String locale = getResources().getConfiguration().locale.getCountry();
        Log.e("ip locale",""+locale);

        getLocalIpAddress();
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.mymenu, menu);
//        return true;
//    }

    public String getLocalIpAddress(){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {

                        Log.e("ip address----",""+inetAddress.getHostAddress().toString());

                        return inetAddress.getHostAddress().toString();
                    }
                }
            }

        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_register:

            //    Intent intent = new Intent(RegisterActivity.this,OTPActivity.class);
              //  startActivity(intent);

                if (NetworkConnection.isConnectedToInternet(RegisterActivity.this)) {

                    registerUser();

                }
                else {
                    Toast.makeText(RegisterActivity.this,R.string.networkconnection, Toast.LENGTH_SHORT).show();
                    return;

                }

                break;

            case R.id.tv_login:

                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);

                break;
            case R.id.tv_terms:

                Intent terms=new Intent(RegisterActivity.this,TermsActivity.class);
                startActivity(terms);

                break;
        }

    }

    private void registerUser(){

        final String username=ed_username.getText().toString().trim();
        final String password=ed_password.getText().toString().trim();
        String cpassword=ed_cpassword.getText().toString().trim();
        final String phonenum="+91"+ed_phnno.getText().toString().trim();


        if(username.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(RegisterActivity.this, "Please enter username.");

            alertmsg.dialog.show();

            return;
        }

        if(password.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(RegisterActivity.this, "Please enter your password.");

            alertmsg.dialog.show();

            return;
        }

        if (!isValidPassword(password)) {

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(RegisterActivity.this, "Password must contain minimum 8 characters.");

            alertmsg.dialog.show();


            return;
        }


        if(cpassword.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(RegisterActivity.this, "Please enter confirm password.");

            alertmsg.dialog.show();

            return;
        }

        if(!cpassword.equals(password)){

            ed_cpassword.setError("Password and confirm password should be same");
            return;
        }

        if(phonenum.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(RegisterActivity.this, "Please enter your phone number.");

            alertmsg.dialog.show();

            return;
        }

        else {

            loading = new ProgressDialog(RegisterActivity.this,R.style.AppCompatAlertDialogStyle);
            loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.register,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();

                            Log.e("respose===",""+response);

                            try {
                                JSONObject main_obj = new JSONObject(response);
                                String msg =main_obj.getString("message");
                                String smsstatus =main_obj.getString("smsstatus");


                                JSONObject  obj=main_obj.getJSONObject("data");

                                String name=obj.getString("name");
                                String access_token =obj.getString("access_token");

                                SharedPreferences.Editor editor1 = sharedpreferences1.edit();
                                editor1.putString("name",name);
                                editor1.putString("access_token",access_token);
                                editor1.commit();

//                                AlertDialogMsg alertmsg = new
//                                        AlertDialogMsg(RegisterActivity.this,smsstatus);
//
//                                alertmsg.dialog.show();

                                Toast.makeText(RegisterActivity.this,smsstatus,Toast.LENGTH_LONG).show();

                                Intent i = new Intent(RegisterActivity.this,OTPActivity.class);
                                 startActivity(i);

                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(RegisterActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("username",username);
                    params.put("password", password);
                    params.put("phone", phonenum);
                    params.put("ip_address","103.239.234.222");
                    params.put("device_type", "android");
                    params.put("device_token", "12345678");

                    Log.e("reg param---",""+params);
                 //   params.put("firebase_registration_id",MainActivity.regId);
                    return params;
                }

            };


            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }

    // validating password with retype password
    private boolean isValidPassword(String password) {
        if (password != null && password.length() > 7) {
            return true;
        }
        return false;
    }

    public void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

    }


}

