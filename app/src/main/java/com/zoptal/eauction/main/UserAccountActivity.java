package com.zoptal.eauction.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zoptal.eauction.R;
import com.zoptal.eauction.common.ExpandableHeightGridView;

public class UserAccountActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_setting,img_filter,img_logo,img_chat;
    private ExpandableHeightGridView gridView;
    private ListView listview;
    private TextView tv_mybids,tv_myauction,tv_totalauction,tv_totalbids,tv_totalchat,tv_more;
    private RelativeLayout rel_mybids,rel_mychat,rel_myauction,rel_chat;
    private View view1,view2,view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        initview();
    }

    public void initview() {

        img_setting = (ImageView) findViewById(R.id.img_setting);
        img_filter = (ImageView) findViewById(R.id.img_filter);
        img_logo = (ImageView) findViewById(R.id.img_logo);
        img_chat = (ImageView) findViewById(R.id.img_chat);
        tv_more=(TextView)findViewById(R.id.tv_more);

        gridView=(ExpandableHeightGridView)findViewById(R.id.gridView);
        gridView.setExpanded(true);
        gridView.setAdapter(new CustomListAdapter_userauction());

        SpannableString more = new SpannableString("CLICK HERE");
        more.setSpan(new UnderlineSpan(), 0, more.length(), 0);
        tv_more.setText(more);

        listview=(ListView) findViewById(R.id.listview);

        rel_mybids=(RelativeLayout)findViewById(R.id.rel_mybids);
        rel_mychat=(RelativeLayout)findViewById(R.id.rel_mychat);
        rel_myauction=(RelativeLayout)findViewById(R.id.rel_myauction);
        rel_chat=(RelativeLayout)findViewById(R.id.rel_chat);

        tv_mybids=(TextView)findViewById(R.id.tv_mybids);
        tv_myauction=(TextView)findViewById(R.id.tv_myauction);

        tv_totalauction=(TextView)findViewById(R.id.tv_totalauction);
        tv_totalbids=(TextView)findViewById(R.id.tv_totalbids);
        tv_totalchat=(TextView)findViewById(R.id.tv_totalchat);


        view1=(View)findViewById(R.id.view1);
        view2=(View)findViewById(R.id.view2);
        view3=(View)findViewById(R.id.view3);

        img_setting.setOnClickListener(this);
        img_filter.setOnClickListener(this);
        img_logo.setOnClickListener(this);

        rel_mybids.setOnClickListener(this);
        rel_myauction.setOnClickListener(this);
        rel_mychat.setOnClickListener(this);
        tv_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_setting:

                Intent i = new Intent(UserAccountActivity.this, SettingActivity.class);
                startActivity(i);

                break;

            case R.id.img_filter:

                Intent filterintent = new Intent(UserAccountActivity.this, FilterActivity.class);
                startActivity(filterintent);

                break;

            case R.id.img_logo:

                Intent intnt = new Intent(UserAccountActivity.this, MainActivity.class);
                startActivity(intnt);

                break;
            case R.id.tv_more:

                dialog_buycredits();

                break;
            case R.id.rel_mybids:

                rel_chat.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);

                tv_mybids.setTextColor(Color.parseColor("#458CCC"));
                tv_myauction.setTextColor(Color.parseColor("#D3D3D3"));
                img_chat.setBackgroundResource(R.mipmap.chat_icon);

                tv_totalauction.setBackgroundResource(R.mipmap.no_cricle);
                tv_totalbids.setBackgroundResource(R.mipmap.red_cricle);
                tv_totalchat.setBackgroundResource(R.mipmap.no_cricle);

                view1.setBackgroundColor(Color.parseColor("#458CCC"));
                view3.setBackgroundColor(Color.parseColor("#D3D3D3"));
                view2.setBackgroundColor(Color.parseColor("#D3D3D3"));

                gridView=(ExpandableHeightGridView)findViewById(R.id.gridView);
                gridView.setExpanded(true);
                gridView.setAdapter(new CustomListAdapter_mybids());
                break;

            case R.id.rel_myauction:

                rel_chat.setVisibility(View.GONE);
                gridView.setVisibility(View.VISIBLE);

                tv_myauction.setTextColor(Color.parseColor("#458CCC"));
                tv_mybids.setTextColor(Color.parseColor("#D3D3D3"));
                img_chat.setBackgroundResource(R.mipmap.chat_icon);

                tv_totalauction.setBackgroundResource(R.mipmap.red_cricle);
                tv_totalbids.setBackgroundResource(R.mipmap.no_cricle);
                tv_totalchat.setBackgroundResource(R.mipmap.no_cricle);

                view3.setBackgroundColor(Color.parseColor("#458CCC"));
                view1.setBackgroundColor(Color.parseColor("#D3D3D3"));
                view2.setBackgroundColor(Color.parseColor("#D3D3D3"));

                gridView=(ExpandableHeightGridView)findViewById(R.id.gridView);
                gridView.setExpanded(true);
                gridView.setAdapter(new CustomListAdapter_userauction());
                break;

            case R.id.rel_mychat:

                rel_chat.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.GONE);
                listview.setAdapter(new CustomListAdapter_chatuserlist());


                tv_myauction.setTextColor(Color.parseColor("#D3D3D3"));
                tv_mybids.setTextColor(Color.parseColor("#D3D3D3"));
                img_chat.setBackgroundResource(R.mipmap.chat_iconblue);

                tv_totalchat.setBackgroundResource(R.mipmap.red_cricle);
                tv_totalauction.setBackgroundResource(R.mipmap.no_cricle);
                tv_totalbids.setBackgroundResource(R.mipmap.no_cricle);


                view2.setBackgroundColor(Color.parseColor("#458CCC"));
                view1.setBackgroundColor(Color.parseColor("#D3D3D3"));
                view3.setBackgroundColor(Color.parseColor("#D3D3D3"));


                break;
        }

    }

    class CustomListAdapter_userauction extends BaseAdapter {

        LayoutInflater inflater;
        Context context;

        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.mipmap.plus_icon,
        };
        public CustomListAdapter_userauction() {

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

            convertView = inflater.inflate(R.layout.customview_userauction, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.rel_price=(RelativeLayout) convertView.findViewById(R.id.rel_price);
          //  holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
            holder.rel_namephn=(RelativeLayout) convertView.findViewById(R.id.rel_namephn);
            holder.linr_timr=(LinearLayout) convertView.findViewById(R.id.linr_timr);
            holder.rel_back=(RelativeLayout) convertView.findViewById(R.id.rel_back);

            holder.img.setBackgroundResource(mResources[position]);


            if(position==3){

                holder.rel_price.setVisibility(View.GONE);
                holder.rel_namephn.setVisibility(View.GONE);
                holder.linr_timr.setVisibility(View.GONE);
                holder.rel_back.setBackgroundResource(R.color.colortransparent);

                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(UserAccountActivity.this,AddNewAuctionActivity.class);
                        startActivity(i);

                    }
                });


            }
            else{

                holder.rel_price.setVisibility(View.VISIBLE);
                holder.rel_namephn.setVisibility(View.VISIBLE);
                holder.linr_timr.setVisibility(View.VISIBLE);
                holder.rel_back.setBackgroundResource(R.mipmap.background);
                holder.img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i=new Intent(UserAccountActivity.this,MyAuctionDetailActivity.class);
                        startActivity(i);

                    }
                });


            }
            return convertView;
        }
        class Holdlerstore
        {
            ImageView img;
            RelativeLayout rel_price;
            TextView tv_name;
            RelativeLayout rel_namephn;
            RelativeLayout rel_back;
            LinearLayout linr_timr;

        }
    }

    class CustomListAdapter_mybids extends BaseAdapter {

        LayoutInflater inflater;
        Context context;

        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
        };
        public CustomListAdapter_mybids() {

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

            convertView = inflater.inflate(R.layout.customview_userauction, null);
            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.rel_price=(RelativeLayout) convertView.findViewById(R.id.rel_price);
            //  holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
            holder.rel_namephn=(RelativeLayout) convertView.findViewById(R.id.rel_namephn);

            holder.img.setBackgroundResource(mResources[position]);



            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(UserAccountActivity.this,MyBidsDetailActivity.class);
                    startActivity(i);

                }
            });



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

    class CustomListAdapter_chatuser extends BaseAdapter {

        LayoutInflater inflater;
        Context context;

        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.mipmap.plus_icon,
        };
        public CustomListAdapter_chatuser() {

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

            convertView = inflater.inflate(R.layout.customview_chatuser, null);
//            holder.img = (ImageView) convertView.findViewById(R.id.img);
//            holder.rel_price=(RelativeLayout) convertView.findViewById(R.id.rel_price);
//            //  holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
//            holder.rel_namephn=(RelativeLayout) convertView.findViewById(R.id.rel_namephn);
//
//
//            holder.img.setBackgroundResource(mResources[position]);


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

    class CustomListAdapter_chatuserlist extends BaseAdapter {

        LayoutInflater inflater;
        Context context;

        int[] mResources = {
                R.drawable.dummyimg1,
                R.drawable.dummyimg2,
                R.drawable.dummyimg1,
                R.mipmap.plus_icon,
        };
        public CustomListAdapter_chatuserlist() {

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

            convertView = inflater.inflate(R.layout.customview_chatuserlist, null);
//            holder.img = (ImageView) convertView.findViewById(R.id.img);
            holder.rel_chatuser=(RelativeLayout) convertView.findViewById(R.id.rel_chatuser);
             holder.tv_msg=(TextView) convertView.findViewById(R.id.tv_msg);
//            holder.rel_namephn=(RelativeLayout) convertView.findViewById(R.id.rel_namephn);
//
//
//            holder.img.setBackgroundResource(mResources[position]);

            holder.rel_chatuser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(UserAccountActivity.this,MyBidsDetailActivity.class);
                    startActivity(i);
                }
            });


            return convertView;


        }
        class Holdlerstore
        {
            ImageView img;
            RelativeLayout rel_chatuser;
            TextView tv_msg;
            RelativeLayout rel_namephn;

        }

    }

    public void dialog_buycredits() {

        final Dialog dialog = new Dialog(UserAccountActivity.this, android.R.style.Theme_Translucent);
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.alertdialog_buycredits);

        ImageView img_cross = (ImageView) dialog.findViewById(R.id.img_cross);


        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
