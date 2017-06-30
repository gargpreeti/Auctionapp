package com.zoptal.eauction.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zoptal.eauction.R;

public class MyBidsDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private  ImageView img_backarrow,img_arrowdetail,img_uparrowdetail;
    private RelativeLayout rel_productdesc,rel_timer,rel_productdesc1;
    private LinearLayout linr_bidprice;
    private ImageView img_user;
    private ImageView img_1;
    private Dialog dialog,dialog1;
    private ImageView img_ques,img_chat;

    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction);

        initview();

    }
    public void initview(){

        img_backarrow=(ImageView)findViewById(R.id.img_backarrow);
        img_arrowdetail=(ImageView)findViewById(R.id.img_arrowdetail);
        img_uparrowdetail=(ImageView)findViewById(R.id.img_uparrowdetail);
        img_user=(ImageView)findViewById(R.id.img_user);
        img_1=(ImageView)findViewById(R.id.img_1);

        rel_productdesc=(RelativeLayout)findViewById(R.id.rel_productdesc);
        rel_timer=(RelativeLayout)findViewById(R.id.rel_timer);
        rel_productdesc1=(RelativeLayout)findViewById(R.id.rel_productdesc1);
      //  linr_main=(LinearLayout) findViewById(R.id.linr_main);
        linr_bidprice=(LinearLayout) findViewById(R.id.linr_bidprice);

        img_ques=(ImageView)findViewById(R.id.img_ques);
        img_chat=(ImageView)findViewById(R.id.img_chat);
        listview=(ListView)findViewById(R.id.listview);
        listview.setAdapter(new CustomListAdapter_bidlist());

        img_backarrow.setOnClickListener(this);
        img_arrowdetail.setOnClickListener(this);
        img_uparrowdetail.setOnClickListener(this);
        img_1.setOnClickListener(this);
        img_ques.setOnClickListener(this);
        img_chat.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_backarrow:

                Intent i = new Intent(MyBidsDetailActivity.this, UserAccountActivity.class);
                startActivity(i);

                break;

            case R.id.img_arrowdetail:

                rel_productdesc1.setVisibility(View.VISIBLE);
                rel_productdesc.setVisibility(View.GONE);
                rel_timer.setVisibility(View.GONE);
                listview.setVisibility(View.GONE);
                img_user.setVisibility(View.GONE);

//                Intent intent = new Intent(AuctionActivity.this,AuctionDetailActivity.class);
//                startActivity(intent);


//                FragmentManager fragmentManager1 = getFragmentManager();
//                Fragment_AlbumDetailAll fragment_privacy= new Fragment_AlbumDetailAll();
//                fragmentManager1.beginTransaction().replace(R.id.activity_main_content_fragment, fragment_privacy).commit();

                break;

            case R.id.img_uparrowdetail:

                rel_productdesc1.setVisibility(View.GONE);
                rel_productdesc.setVisibility(View.VISIBLE);
                rel_timer.setVisibility(View.VISIBLE);
                listview.setVisibility(View.VISIBLE);
                img_user.setVisibility(View.VISIBLE);

                break;

            case R.id.img_1:
                //   linr_bidprice.setVisibility(View.VISIBLE);

                dialog_pricebids();

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



    public void dialog_pricebids() {

        dialog = new Dialog(MyBidsDetailActivity.this, android.R.style.Theme_Translucent) {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                // Tap anywhere to close dialog.
                dialog.dismiss();
                return true;
            }
        };


        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.alertdialog_pricebids);

        dialog.show();

    }

    public void dialog_quesans() {

        dialog1 = new Dialog(MyBidsDetailActivity.this, android.R.style.Theme_Translucent) {
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
        dialog1.setContentView(R.layout.alertdialog_quesans);

        ImageView img_cross=(ImageView)dialog1.findViewById(R.id.img_cross);
        Button btn_askques=(Button) dialog1.findViewById(R.id.btn_askques);
        Button btn_submit=(Button) dialog1.findViewById(R.id.btn_submit);

        final RelativeLayout rel_ques=(RelativeLayout)dialog1.findViewById(R.id.rel_ques);

        ListView listview=(ListView)dialog1.findViewById(R.id.listview);

        listview.setAdapter(new CustomListAdapter_quesans());

        btn_askques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_ques.setVisibility(View.VISIBLE);

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rel_ques.setVisibility(View.GONE);
            }
        });

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        dialog1.show();

    }

    public void dialog_chat() {

        dialog1 = new Dialog(MyBidsDetailActivity.this, android.R.style.Theme_Translucent) {
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

            Holdlerstore holder = new Holdlerstore();

            convertView = inflater.inflate(R.layout.customview_ques, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img_buyer);


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
}
