package com.zoptal.eauction.main;

import android.app.Dialog;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.zoptal.eauction.R;
import com.zoptal.eauction.common.NetworkConnection;
import com.zoptal.eauction.model.Model_MyAuctions;
import com.zoptal.eauction.url.RegisterUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyAuctionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private  ImageView img_backarrow,img_arrowdetail,img_uparrowdetail;
    private RelativeLayout rel_productdesc,rel_timer,rel_productdesc1;
    private LinearLayout linr_bidprice;
    private ImageView img_user,img_ques,img_chat;
    private Dialog dialog,dialog1;
   // private ListView listview;

    private ImageView img_usermain;
    private TextView tv_days,tv_hours,tv_minuts,tv_seconds;

    private ImageView img_user1;
    private TextView tv_days1,tv_hours1,tv_minuts1,tv_seconds1;
    private TextView tv_productname,tv_category,tv_desc,tv_price;
    private ImageView img1,img2,img3;

    public final String MyPREFERENCES = "MyPrefs1" ;
    SharedPreferences sharedpreferences1;
    String access_tokn;
    ProgressDialog loading;
    String pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myauctiondetail);

        sharedpreferences1 =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        access_tokn = sharedpreferences1.getString("access_token", "");

        initview();
    }

    public void initview(){

        Intent intent = getIntent();
        pid=intent.getStringExtra("pid");

        img_backarrow=(ImageView)findViewById(R.id.img_backarrow);
        img_arrowdetail=(ImageView)findViewById(R.id.img_arrowdetail);
        img_uparrowdetail=(ImageView)findViewById(R.id.img_uparrowdetail);
        img_user=(ImageView)findViewById(R.id.img_user);
        img_ques=(ImageView)findViewById(R.id.img_ques);
        img_chat=(ImageView)findViewById(R.id.img_chat);

        img_usermain=(ImageView)findViewById(R.id.img_usermain);
        tv_days=(TextView)findViewById(R.id.tv_days);
        tv_hours=(TextView)findViewById(R.id.tv_hours);
        tv_minuts=(TextView)findViewById(R.id.tv_minuts);
        tv_seconds=(TextView)findViewById(R.id.tv_seconds);


        img_user1=(ImageView)findViewById(R.id.img_user1);
        tv_days1=(TextView)findViewById(R.id.tv_days1);
        tv_hours1=(TextView)findViewById(R.id.tv_hours1);
        tv_minuts1=(TextView)findViewById(R.id.tv_minuts1);
        tv_seconds1=(TextView)findViewById(R.id.tv_seconds1);

        tv_productname=(TextView)findViewById(R.id.tv_productname);
        tv_category=(TextView)findViewById(R.id.tv_category);
        tv_desc=(TextView)findViewById(R.id.tv_desc);
        tv_price=(TextView)findViewById(R.id.tv_price);

        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);


      //  listview=(ListView)findViewById(R.id.listview);
        //listview.setAdapter(new CustomListAdapter_bidlist());


        rel_productdesc=(RelativeLayout)findViewById(R.id.rel_productdesc);
        rel_timer=(RelativeLayout)findViewById(R.id.rel_timer);
        rel_productdesc1=(RelativeLayout)findViewById(R.id.rel_productdesc1);
       // linr_main=(LinearLayout) findViewById(R.id.linr_main);
        linr_bidprice=(LinearLayout) findViewById(R.id.linr_bidprice);



        img_backarrow.setOnClickListener(this);
        img_arrowdetail.setOnClickListener(this);
        img_uparrowdetail.setOnClickListener(this);
        img_ques.setOnClickListener(this);
        img_chat.setOnClickListener(this);


//         if (NetworkConnection.isConnectedToInternet(MyAuctionDetailActivity.this)) {
//
//                 myauctionsdetail();
//
//                }
//                else {
//                    Toast.makeText(MyAuctionDetailActivity.this,R.string.networkconnection, Toast.LENGTH_SHORT).show();
//                    return;
//
//                }


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_backarrow:

                Intent i = new Intent(MyAuctionDetailActivity.this, UserAccountActivity.class);
                startActivity(i);

                break;

            case R.id.img_arrowdetail:

                rel_productdesc1.setVisibility(View.VISIBLE);
                rel_productdesc.setVisibility(View.GONE);
                rel_timer.setVisibility(View.GONE);
             //   listview.setVisibility(View.GONE);
               img_usermain.setVisibility(View.GONE);

//                Intent intent = new Intent(AuctionActivity.this,AuctionDetailActivity.class);
//                startActivity(intent);


//                FragmentManager fragmentManager1 = getFragmentManager();
//                Fragment_AlbumDetailAll fragment_privacy= new Fragment_AlbumDetailAll();
//                fragmentManager1.beginTransaction().replace(R.id.activity_main_content_fragment, fragment_privacy).commit();

                break;

            case R.id.img_uparrowdetail:

                rel_productdesc1.setVisibility(View.GONE);
                rel_productdesc.setVisibility(View.VISIBLE);
              //  rel_timer.setVisibility(View.VISIBLE);
              //  listview.setVisibility(View.VISIBLE);
                img_usermain.setVisibility(View.VISIBLE);

                break;

            case R.id.img_ques:
                //   linr_bidprice.setVisibility(View.VISIBLE);

                dialog_quesans();

                break;

            case R.id.img_chat:
                //   linr_bidprice.setVisibility(View.VISIBLE);

                dialog_chat();

                break;
        }

        }


    public void dialog_quesans() {

        dialog1 = new Dialog(MyAuctionDetailActivity.this, android.R.style.Theme_Translucent) {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                // Tap anywhere to close dialog.
                dialog1.dismiss();
                return true;
            }
        };


        dialog1.setCanceledOnTouchOutside(true);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(true);
        dialog1.setContentView(R.layout.alertdialog_ansseller);

        ImageView img_cross=(ImageView)dialog1.findViewById(R.id.img_cross);
        ListView listview=(ListView)dialog1.findViewById(R.id.listview);

        listview.setAdapter(new CustomListAdapter_quesans());

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        dialog1.show();

    }

    public void dialog_chat() {

        dialog1 = new Dialog(MyAuctionDetailActivity.this, android.R.style.Theme_Translucent) {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                // Tap anywhere to close dialog.
                dialog1.dismiss();
                return true;
            }
        };


        dialog1.setCanceledOnTouchOutside(true);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(true);
        dialog1.setContentView(R.layout.alertdialog_chat);

        ImageView img_cross=(ImageView)dialog1.findViewById(R.id.img_cross);
        ListView listview=(ListView)dialog1.findViewById(R.id.listview);

        listview.setAdapter(new CustomListAdapter_chat());

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        dialog1.show();

    }

    class CustomListAdapter_quesans extends BaseAdapter {

        LayoutInflater inflater;
        Context context;

        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
        };
        public CustomListAdapter_quesans() {

            this.context = context;
            inflater = LayoutInflater.from(getApplicationContext());

            // TODO Auto-generated constructor stub
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return 2;

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

            final Holdlerstore holder = new Holdlerstore();

            convertView = inflater.inflate(R.layout.customview_ansseller, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img_buyer);
            holder.rel_reply = (RelativeLayout) convertView.findViewById(R.id.rel_reply);
            holder.btn_Reply = (Button) convertView.findViewById(R.id.btn_Reply);
            holder.btn_Ans = (Button) convertView.findViewById(R.id.btn_Ans);
            holder.ed_ans = (EditText) convertView.findViewById(R.id.ed_ans);

            holder.ed_ans.setClickable(true);
            holder.ed_ans.setFocusable(true);

            holder.btn_Reply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.rel_reply.setVisibility(View.VISIBLE);
                }
            });

            holder.btn_Ans.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.rel_reply.setVisibility(View.GONE);
                }
            });



            return convertView;


        }
        class Holdlerstore
        {
            ImageView img;
            RelativeLayout rel_price;
            TextView tv_name;
            Button btn_Reply;
            Button btn_Ans;
            EditText ed_ans;
            RelativeLayout rel_reply;

        }

    }

    class CustomListAdapter_chat extends BaseAdapter {

        LayoutInflater inflater;
        Context context;

        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
        };
        public CustomListAdapter_chat() {

            this.context = context;
            inflater = LayoutInflater.from(getApplicationContext());

            // TODO Auto-generated constructor stub
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return 2;
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

            convertView = inflater.inflate(R.layout.customview_chat, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img_buyer);

            if(position==1){

                convertView = inflater.inflate(R.layout.customview_ans, null);
            }

            return convertView;


        }
        class Holdlerstore
        {
            ImageView img;
            RelativeLayout rel_price;
            TextView tv_name;
            RelativeLayout rel_namephn;

        }

    }

    class CustomListAdapter_bidlist extends BaseAdapter {

        LayoutInflater inflater;
        Context context;


        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
        };
        public CustomListAdapter_bidlist() {

            this.context = context;
            inflater = LayoutInflater.from(getApplicationContext());

            // TODO Auto-generated constructor stub
        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return 5;

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

            convertView = inflater.inflate(R.layout.customview_biduserlist, null);

            holder.tv_timr = (TextView) convertView.findViewById(R.id.tv_timr);




            return convertView;


        }
        class Holdlerstore
        {
            ImageView img;
            RelativeLayout rel_price;
            TextView tv_timr;
            RelativeLayout rel_namephn;

        }

    }

    private void myauctionsdetail() {

        loading = new ProgressDialog(MyAuctionDetailActivity.this, R.style.AppCompatAlertDialogStyle);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loading.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.auction_detail,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();


                        Log.e("respose my auction detail===", "" + response);
                        try {
                            JSONObject main_obj = new JSONObject(response);
                            JSONObject  obj=main_obj.getJSONObject("data");
                            String id=obj.getString("id");
                            String userid=obj.getString("user_id");
                            String catid=obj.getString("cat_id");
                            String productname=obj.getString("product_name");
                            String productcondition=obj.getString("product_condition");
                            String openprice=obj.getString("opening_price");
                            String catname=obj.getString("cat_name");
                            String remaintime=obj.getString("remaining_time");
                            String userimg=obj.getString("user_image");


                            tv_productname.setText(productname);
                            tv_category.setText(catname);
                            tv_desc.setText(productcondition);
                            tv_price.setText(openprice);

                            if(userimg.isEmpty()){

                            }
                            else {
                                Glide.with(MyAuctionDetailActivity.this)
                                        .load(userimg)
                                        .into(img_usermain);
                                Glide.with(MyAuctionDetailActivity.this)
                                        .load(userimg)
                                        .into(img_user1);
                            }

                            int seconds= Integer.parseInt(remaintime);
                           long milisecnds=seconds*1000;

                            Log.e("miliseconds----",""+milisecnds);

                            new CountDownTimer(milisecnds, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    long secondsInMilli = 1000;
                                    long minutesInMilli = secondsInMilli * 60;
                                    long hoursInMilli = minutesInMilli * 60;
                                    long day = hoursInMilli * 24;

                                    long elapsedHours = millisUntilFinished / hoursInMilli;
                                    millisUntilFinished = millisUntilFinished % hoursInMilli;

                                    long elapsedMinutes = millisUntilFinished / minutesInMilli;
                                    millisUntilFinished = millisUntilFinished % minutesInMilli;

                                    long elapsedSeconds = millisUntilFinished / secondsInMilli;

                                    String yy = String.format("%02d:%02d:%02d", elapsedHours, elapsedMinutes,elapsedSeconds);
                                    //  Log.e("yyy--",""+yy);
                                    tv_hours.setText(String.valueOf(elapsedHours));
                                    tv_minuts.setText(String.valueOf(elapsedMinutes));
                                    tv_seconds.setText(String.valueOf(elapsedSeconds));

                                    tv_hours1.setText(String.valueOf(elapsedHours));
                                    tv_minuts1.setText(String.valueOf(elapsedMinutes));
                                    tv_seconds1.setText(String.valueOf(elapsedSeconds));
                                    //  holder.tv_time.setText(yy);
                                }

                                public void onFinish() {

                                    //   holder.tv_time.setText("00:00:00");

                                }
                            }.start();










                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //    Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();

                map.put("access_token",access_tokn);
                map.put("product_id",pid);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MyAuctionDetailActivity.this);
        requestQueue.add(stringRequest);
    }


}
