package com.zoptal.eauction.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zoptal.eauction.R;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_setting, img_filter,img_logo;
    private RelativeLayout rel_electfind,rel_cmputrfind,rel_mobilephnfind,rel_videogamefind,rel_jewelleryfind,rel_seafind,rel_collectfind;
    private RelativeLayout rel_electonic,rel_computr,rel_mob,rel_videogame,rel_jewellwery,rel_birds,rel_land,rel_automotive,rel_collect,rel_miscell;

    private TextView tv_elect,tv_mob,tv_jwery,tv_sea,tv_collect;
    private Button btn_find2,btn_find1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        initview();
    }

    public void initview() {

        img_setting = (ImageView) findViewById(R.id.img_setting);
        img_filter = (ImageView) findViewById(R.id.img_filter);
        img_logo=(ImageView)findViewById(R.id.img_logo);

        rel_electfind=(RelativeLayout)findViewById(R.id.rel_electfind);
        rel_computr=(RelativeLayout)findViewById(R.id.rel_computr);
        rel_electonic=(RelativeLayout)findViewById(R.id.rel_electonic);

        tv_elect=(TextView)findViewById(R.id.tv_elect);
        tv_mob=(TextView)findViewById(R.id.tv_mob);
        tv_jwery=(TextView)findViewById(R.id.tv_jwery);
        tv_sea=(TextView)findViewById(R.id.tv_sea);
        tv_collect=(TextView)findViewById(R.id.tv_collect);

        rel_mob=(RelativeLayout)findViewById(R.id.rel_mob);
        rel_videogame=(RelativeLayout)findViewById(R.id.rel_videogame);
        rel_mobilephnfind=(RelativeLayout)findViewById(R.id.rel_mobilephnfind);


        rel_jewelleryfind=(RelativeLayout)findViewById(R.id.rel_jewelleryfind);
        rel_jewellwery=(RelativeLayout)findViewById(R.id.rel_jewellwery);
        rel_birds=(RelativeLayout)findViewById(R.id.rel_birds);

        rel_land=(RelativeLayout)findViewById(R.id.rel_land);
        rel_automotive=(RelativeLayout)findViewById(R.id.rel_automotive);
        rel_seafind=(RelativeLayout)findViewById(R.id.rel_seafind);

        rel_collect=(RelativeLayout)findViewById(R.id.rel_collect);
        rel_miscell=(RelativeLayout)findViewById(R.id.rel_miscell);
        rel_collectfind=(RelativeLayout)findViewById(R.id.rel_collectfind);

        btn_find1=(Button)findViewById(R.id.btn_find1);
        btn_find2=(Button)findViewById(R.id.btn_find2);

        img_filter.setOnClickListener(this);
        img_setting.setOnClickListener(this);
        img_logo.setOnClickListener(this);
        rel_mob.setOnClickListener(this);
        rel_electonic.setOnClickListener(this);
        rel_computr.setOnClickListener(this);
        rel_videogame.setOnClickListener(this);
        rel_jewellwery.setOnClickListener(this);
        rel_birds.setOnClickListener(this);
        rel_land.setOnClickListener(this);
        rel_automotive.setOnClickListener(this);
        rel_collect.setOnClickListener(this);
        rel_miscell.setOnClickListener(this);
        btn_find1.setOnClickListener(this);
        btn_find2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_setting:

                Intent intent1 = new Intent(FilterActivity.this, SettingActivity.class);
                startActivity(intent1);
                break;

            case R.id.img_logo:

                Intent accountintent = new Intent(FilterActivity.this, UserAccountActivity.class);
                startActivity(accountintent);

                break;

            case R.id.rel_mob:

                tv_mob.setText("M O B I L E  P H O N E S");
                rel_mobilephnfind.setVisibility(View.VISIBLE);
                rel_electfind.setVisibility(View.GONE);
                rel_jewelleryfind.setVisibility(View.GONE);
                rel_seafind.setVisibility(View.GONE);
                rel_collectfind.setVisibility(View.GONE);
                rel_mobilephnfind.setBackgroundResource(R.drawable.tooltip_left);
                break;

            case R.id.rel_videogame:

                tv_mob.setText("V I D E O  G A M E S");
                rel_mobilephnfind.setVisibility(View.VISIBLE);
                rel_electfind.setVisibility(View.GONE);
                rel_jewelleryfind.setVisibility(View.GONE);
                rel_seafind.setVisibility(View.GONE);
                rel_collectfind.setVisibility(View.GONE);
                rel_mobilephnfind.setBackgroundResource(R.drawable.tooltipright);
                break;

            case R.id.rel_electonic:

                tv_elect.setText("Electricals & Electronics");
                rel_electfind.setVisibility(View.VISIBLE);
                rel_mobilephnfind.setVisibility(View.GONE);
                rel_jewelleryfind.setVisibility(View.GONE);
                rel_seafind.setVisibility(View.GONE);
                rel_collectfind.setVisibility(View.GONE);
                rel_electfind.setBackgroundResource(R.drawable.tooltip_left);


                break;
            case R.id.rel_computr:

                tv_elect.setText("C O M P U T E R");
                rel_electfind.setVisibility(View.VISIBLE);
                rel_mobilephnfind.setVisibility(View.GONE);
                rel_jewelleryfind.setVisibility(View.GONE);
                rel_seafind.setVisibility(View.GONE);
                rel_collectfind.setVisibility(View.GONE);
                rel_electfind.setBackgroundResource(R.drawable.tooltipright);
                break;

            case R.id.rel_jewellwery:

                tv_jwery.setText("Jewellery & Accessories");
                rel_jewelleryfind.setVisibility(View.VISIBLE);
                rel_mobilephnfind.setVisibility(View.GONE);
                rel_electfind.setVisibility(View.GONE);
                rel_seafind.setVisibility(View.GONE);
                rel_collectfind.setVisibility(View.GONE);
                rel_jewelleryfind.setBackgroundResource(R.drawable.tooltip_left);
                break;

            case R.id.rel_birds:

                tv_jwery.setText("Birds & Animals");
                rel_jewelleryfind.setVisibility(View.VISIBLE);
                rel_mobilephnfind.setVisibility(View.GONE);
                rel_electfind.setVisibility(View.GONE);
                rel_seafind.setVisibility(View.GONE);
                rel_collectfind.setVisibility(View.GONE);
                rel_jewelleryfind.setBackgroundResource(R.drawable.tooltipright);

                break;

            case R.id.rel_land:
                tv_sea.setText("Land & Sea supplies");
                rel_seafind.setVisibility(View.VISIBLE);
                rel_jewelleryfind.setVisibility(View.GONE);
                rel_mobilephnfind.setVisibility(View.GONE);
                rel_electfind.setVisibility(View.GONE);
                rel_collectfind.setVisibility(View.GONE);
                rel_seafind.setBackgroundResource(R.drawable.tooltip_left);

                break;

            case R.id.rel_automotive:

                tv_sea.setText("Automotive");
                rel_seafind.setVisibility(View.VISIBLE);
                rel_jewelleryfind.setVisibility(View.GONE);
                rel_mobilephnfind.setVisibility(View.GONE);
                rel_electfind.setVisibility(View.GONE);
                rel_collectfind.setVisibility(View.GONE);
                rel_seafind.setBackgroundResource(R.drawable.tooltipright);
                break;

            case R.id.rel_collect:

                tv_collect.setText("Collectables & Rarities");
                rel_collectfind.setVisibility(View.VISIBLE);
                rel_seafind.setVisibility(View.GONE);
                rel_jewelleryfind.setVisibility(View.GONE);
                rel_mobilephnfind.setVisibility(View.GONE);
                rel_electfind.setVisibility(View.GONE);
                rel_collectfind.setBackgroundResource(R.drawable.tooltip_left);

                break;

            case R.id.rel_miscell:
                tv_collect.setText("Miscellaneous");
                rel_collectfind.setVisibility(View.VISIBLE);
                rel_seafind.setVisibility(View.GONE);
                rel_jewelleryfind.setVisibility(View.GONE);
                rel_mobilephnfind.setVisibility(View.GONE);
                rel_electfind.setVisibility(View.GONE);
                rel_collectfind.setBackgroundResource(R.drawable.tooltipright);
                break;

            case R.id.btn_find1:

                Intent i=new Intent(FilterActivity.this,MainActivity.class);
                startActivity(i);
                break;

            case R.id.btn_find2:

                Intent intent=new Intent(FilterActivity.this,MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}