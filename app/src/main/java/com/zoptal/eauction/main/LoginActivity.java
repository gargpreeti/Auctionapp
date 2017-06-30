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
import com.zoptal.eauction.common.NetworkConnection;
import com.zoptal.eauction.url.RegisterUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText ed_username,ed_password;
    private TextView tv_forgotpw,tv_register,tv_terms;
    private Button btn_login;
    ProgressDialog loading;
    public final String MyPREFERENCES = "MyPrefs1" ;
    SharedPreferences sharedpreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        sharedpreferences1 =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        initview();

    }

    public void initview() {

        ed_username=(EditText)findViewById(R.id.ed_username);
        ed_password=(EditText)findViewById(R.id.ed_password);

        tv_forgotpw=(TextView)findViewById(R.id.tv_forgotpw);
        tv_register=(TextView)findViewById(R.id.tv_register);
        tv_terms=(TextView)findViewById(R.id.tv_terms);

        btn_login=(Button)findViewById(R.id.btn_login);


        SpannableString terms = new SpannableString("Terms of Use & Privacy Policy");
        terms.setSpan(new UnderlineSpan(), 0, terms.length(), 0);
        tv_terms.setText(terms);


        SpannableString forgotpw = new SpannableString("Forgot Password ?");
        forgotpw.setSpan(new UnderlineSpan(), 0, forgotpw.length(), 0);
        tv_forgotpw.setText(forgotpw);

        SpannableString reg = new SpannableString("REGISTER");
        reg.setSpan(new UnderlineSpan(), 0, reg.length(), 0);
        tv_register.setText(reg);

        btn_login.setOnClickListener(this);
        tv_forgotpw.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        tv_terms.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:

//                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
//                  startActivity(intent);

                if (NetworkConnection.isConnectedToInternet(LoginActivity.this)) {

                    login();

//                    Intent i=new Intent(LoginActivity.this,MainActivity.class);
//                    startActivity(i);

                }
                else {
                    Toast.makeText(LoginActivity.this,R.string.networkconnection, Toast.LENGTH_SHORT).show();
                    return;

                }

                break;

            case R.id.tv_register:

                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);

                break;

            case R.id.tv_forgotpw:

                Intent intnt=new Intent(LoginActivity.this,ForgotPwActivity.class);
                startActivity(intnt);

                break;
            case R.id.tv_terms:

                Intent terms=new Intent(LoginActivity.this,TermsActivity.class);
                startActivity(terms);

                break;
        }
    }

    private void login(){

        final String username=ed_username.getText().toString().trim();
        final String password=ed_password.getText().toString().trim();


        if(username.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(LoginActivity.this, "Please enter username.");

            alertmsg.dialog.show();

            return;
        }


        if(password.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(LoginActivity.this, "Please enter your password.");

            alertmsg.dialog.show();

            return;
        }

        else {

            loading = new ProgressDialog(LoginActivity.this,R.style.AppCompatAlertDialogStyle);
            loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.login,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();

                            Log.e("respose===",""+response);

                            try {
                                JSONObject main_obj = new JSONObject(response);
                                String code=main_obj.getString("code");

                                String msg =main_obj.getString("message");
                             //   String smsstatus =main_obj.getString("smsstatus");

                                if(code.equals("201")){
                                    Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();
                                    return;
                                }

                                JSONObject  obj=main_obj.getJSONObject("data");

                                String name=obj.getString("name");
                                String access_token =obj.getString("access_token");

                                SharedPreferences.Editor editor1 = sharedpreferences1.edit();
                                editor1.putString("name",name);
                                editor1.putString("access_token",access_token);
                                editor1.commit();


                             //   Toast.makeText(LoginActivity.this,smsstatus,Toast.LENGTH_LONG).show();

                                Intent i=new Intent(LoginActivity.this,MainActivity.class);
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
                            Toast.makeText(LoginActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("username",username);
                    params.put("password", password);
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
}
