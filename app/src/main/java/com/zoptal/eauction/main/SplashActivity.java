package com.zoptal.eauction.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.zoptal.eauction.R;


public class SplashActivity extends AppCompatActivity {

  //  GifImageView gifView;
    //byte[] bytes;
   @Override
   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_splash);


//       try {
//           InputStream is = getAssets().open("ring_img.gif");
//            bytes = new byte[is.available()];
//           is.read(bytes);
//           is.close();
//
//           gifView = (GifImageView) findViewById(R.id.gifImageView);
//           gifView = new GifImageView(this);
//           gifView.setBytes(bytes);
//           gifView.startAnimation();
//
//
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
       Thread t1=new Thread()
       {
           public void run()
           {
               try{

                Thread.sleep(2000);

                       Intent i=new Intent(SplashActivity.this,RegisterActivity.class);
                       startActivity(i);
                       finish();

               }
               catch(Exception e)
               {

               }

           }


       };
       t1.start();

   }
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(gifView != null) gifView.startAnimation();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if(gifView != null) gifView.startAnimation();
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        //  getMenuInflater().inflate(R., menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//       if (id == R.id.action_settings) {
//           return true;
//       }

        return super.onOptionsItemSelected(item);
    }

}

// gmail:- zoptalnotification@gmail.com
//Ahmad webkey:-  AIzaSyCfit0DgQA8fD9pVWo-PXCy0DELH-3hwPE
//legacy key-  AIzaSyC8dODhxaowcM58aBCWvBbQWaNJtHtEkrs
//server key:- AAAAu-HME4s:APA91bGUMbIQ7FFfSwsi8vYJjorEC1ySV9DMH2Ip60YBuumJGTi4UTsMUfyInZ2eNNvJzic_xAR0eDi7myrNQs4uTl3efje5TQiWo2hZSdS-JoLbAj54PiGeidXq52J-zEOxJbKIpNCe