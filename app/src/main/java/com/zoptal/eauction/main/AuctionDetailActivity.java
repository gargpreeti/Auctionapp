package com.zoptal.eauction.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zoptal.eauction.R;

public class AuctionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_backarrow,img_arrowdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auction_detail);

        initview();

    }

    public void initview(){

        img_backarrow=(ImageView)findViewById(R.id.img_backarrow);
        img_arrowdetail=(ImageView)findViewById(R.id.img_arrowdetail);



        img_backarrow.setOnClickListener(this);
        img_arrowdetail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_backarrow:

                Intent i = new Intent(AuctionDetailActivity.this, AuctionActivity.class);
                startActivity(i);

                break;


            case R.id.img_arrowdetail:

                Intent intent = new Intent(AuctionDetailActivity.this, AuctionActivity.class);
                startActivity(intent);

                break;

        }


    }
}
