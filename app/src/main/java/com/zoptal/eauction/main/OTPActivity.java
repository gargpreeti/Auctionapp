package com.zoptal.eauction.main;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
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
import com.zoptal.eauction.url.RegisterUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OTPActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_otp;
    private TextView tv_resend, tv_terms;
    private Button btn_submit;
    ProgressDialog loading;
    public final String MyPREFERENCES = "MyPrefs1";
    SharedPreferences sharedpreferences1;
    String access_tokn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        sharedpreferences1 = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        access_tokn = sharedpreferences1.getString("access_token", "");
        initview();
    }

    public void initview() {

        ed_otp = (EditText) findViewById(R.id.ed_otp);
        tv_resend = (TextView) findViewById(R.id.tv_resend);
        tv_terms = (TextView) findViewById(R.id.tv_terms);


        SpannableString more = new SpannableString("Resend");
        more.setSpan(new UnderlineSpan(), 0, more.length(), 0);
        tv_resend.setText(more);

        btn_submit = (Button) findViewById(R.id.btn_submit);

        btn_submit.setOnClickListener(this);
        tv_resend.setOnClickListener(this);
        tv_terms.setOnClickListener(this);
        tv_terms.setOnClickListener(this);

        SpannableString terms = new SpannableString("Terms of Use & Privacy Policy");
        terms.setSpan(new UnderlineSpan(), 0, terms.length(), 0);
        tv_terms.setText(terms);
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.btn_submit:

                Intent i=new Intent(OTPActivity.this,MainActivity.class);
                startActivity(i);

//                if (NetworkConnection.isConnectedToInternet(OTPActivity.this)) {
//
//                    otpreg();
//
//                } else {
//                    Toast.makeText(OTPActivity.this, R.string.networkconnection, Toast.LENGTH_SHORT).show();
//                    return;
//
//                }
                break;

            case R.id.tv_terms:

                Intent terms=new Intent(OTPActivity.this,TermsActivity.class);
                startActivity(terms);
                break;
        }
    }

    private void otpreg() {

        final String otp = ed_otp.getText().toString().trim();

        if (otp.equals("")) {

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(OTPActivity.this, "Please enter OTP.");

            alertmsg.dialog.show();

            return;
        }

        else {

            loading = new ProgressDialog(OTPActivity.this, R.style.AppCompatAlertDialogStyle);
            loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.verifyotpcode,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();

                            Log.e("respose===", "" + response);

                            try {
                                JSONObject main_obj = new JSONObject(response);
                                String msg =main_obj.getString("message");
                                String status =main_obj.getString("status");

                               if(status.equals("Success"))
                               {
                                   Toast.makeText(OTPActivity.this, msg, Toast.LENGTH_LONG).show();
                                   Intent i=new Intent(OTPActivity.this,MainActivity.class);
                                   startActivity(i);
                               }
                              else {

                                   Toast.makeText(OTPActivity.this, msg, Toast.LENGTH_LONG).show();
                               }
                            }

                            catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(OTPActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("access_token", access_tokn);
                    params.put("otp_code", otp);


                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}