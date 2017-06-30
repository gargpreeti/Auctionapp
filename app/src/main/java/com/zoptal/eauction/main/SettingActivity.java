package com.zoptal.eauction.main;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.soundcloud.android.crop.Crop;
import com.squareup.picasso.Picasso;
import com.zoptal.eauction.R;
import com.zoptal.eauction.common.AlertDialogMsg;
import com.zoptal.eauction.common.NetworkConnection;
import com.zoptal.eauction.url.RegisterUrl;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView img_setting,img_filter,img_logo;
    private TextView tv_more,tv_credit2,tv_chngepw;
    private ImageView user_img,img_camera;
    private EditText ed_username;
    private Button btn_save;
    private RelativeLayout rel_buycredits,rel_about,rel_contactus,rel_logout;

    private Bitmap bitmap=null;
    String userimg;
    private Dialog dialoglogout;
    ProgressDialog loading;
    public final String MyPREFERENCES = "MyPrefs1" ;
    SharedPreferences sharedpreferences1;
    String access_tokn;
    private final static int REQUEST_PERMISSION_REQ_CODE = 34;
    private static final int CAMERA_CODE = 101, GALLERY_CODE = 201, CROPING_CODE = 301;
    private Uri mImageCaptureUri;
    private File outPutFile = null;
    private String oldpw,newpw,cnfrmpw;


     @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        sharedpreferences1 =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        access_tokn = sharedpreferences1.getString("access_token", "");

        outPutFile = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");

        initview();

    }

    public void initview(){

        tv_more=(TextView)findViewById(R.id.tv_more);
        tv_credit2=(TextView)findViewById(R.id.tv_credit2);
        user_img=(ImageView)findViewById(R.id.user_img);
        img_camera=(ImageView)findViewById(R.id.img_camera);
        ed_username=(EditText)findViewById(R.id.ed_username);
      //  ed_phnnum=(EditText)findViewById(R.id.ed_phnnum);
        btn_save=(Button)findViewById(R.id.btn_save);

        rel_buycredits=(RelativeLayout)findViewById(R.id.rel_buycredits);
        rel_about=(RelativeLayout)findViewById(R.id.rel_about);
        rel_contactus=(RelativeLayout)findViewById(R.id.rel_contactus);
        rel_logout=(RelativeLayout)findViewById(R.id.rel_logout);

        img_setting=(ImageView)findViewById(R.id.img_setting);
        img_filter=(ImageView)findViewById(R.id.img_filter);
        img_logo=(ImageView)findViewById(R.id.img_logo);

        tv_chngepw=(TextView)findViewById(R.id.tv_chngepw);

        SpannableString more = new SpannableString("CLICK HERE");
        more.setSpan(new UnderlineSpan(), 0, more.length(), 0);
        tv_more.setText(more);


        SpannableString chngepw = new SpannableString("Change Password");
        chngepw.setSpan(new UnderlineSpan(), 0, chngepw.length(), 0);
        tv_chngepw.setText(chngepw);


        img_camera.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        rel_buycredits.setOnClickListener(this);
        rel_about.setOnClickListener(this);
        rel_contactus.setOnClickListener(this);
        img_filter.setOnClickListener(this);
        img_logo.setOnClickListener(this);
        img_setting.setOnClickListener(this);
        tv_more.setOnClickListener(this);
        rel_logout.setOnClickListener(this);
        tv_chngepw.setOnClickListener(this);


        if (NetworkConnection.isConnectedToInternet(SettingActivity.this)) {

            profile_data();
        }
        else {
            Toast.makeText(SettingActivity.this,R.string.networkconnection, Toast.LENGTH_SHORT).show();
            return;

        }

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.img_camera:

               selectImageOption();

                //Crop.pickImage(this);

                break;

            case R.id.btn_save:

                   update_profile();
                break;

            case R.id.rel_buycredits:

                dialog_buycredits();

                break;

            case R.id.rel_about:

                Intent intent = new Intent(SettingActivity.this,AboutusActivity.class);
                startActivity(intent);

                break;

            case R.id.rel_contactus:

                Intent intentcontact = new Intent(SettingActivity.this,ContactusActivity.class);
                startActivity(intentcontact);
                break;


            case R.id.img_filter:

                Intent intent1 = new Intent(SettingActivity.this, FilterActivity.class);
                startActivity(intent1);
                break;

            case R.id.img_logo:

                Intent accountintent = new Intent(SettingActivity.this, UserAccountActivity.class);
                startActivity(accountintent);

                break;

            case R.id.tv_more:

                dialog_buycredits();

                break;

            case R.id.tv_chngepw:

                dialog_chngepw();

                break;

            case R.id.img_setting:

                Intent intnt = new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intnt);

                break;

            case R.id.rel_logout:

                dialoglogout();

                break;

        }

        }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == RESULT_OK) {
            user_img.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    private void selectImageOption() {
        final CharSequence[] items = { "Capture Photo", "Choose from Gallery", "Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (items[item].equals("Capture Photo")) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp1.jpg");
                    mImageCaptureUri = Uri.fromFile(f);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
                    startActivityForResult(intent, CAMERA_CODE);

                } else if (items[item].equals("Choose from Gallery")) {

                    Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i, GALLERY_CODE);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(SettingActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_REQ_CODE);
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, final @NonNull String[] permissions, final @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_REQ_CODE: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission denied.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK && null != data) {

            mImageCaptureUri = data.getData();
            System.out.println("Gallery Image URI : "+mImageCaptureUri);
            CropingIMG();

        } else if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK) {

            System.out.println("Camera Image URI : "+mImageCaptureUri);
            CropingIMG();
        } else if (requestCode == CROPING_CODE) {

            try {
                if(outPutFile.exists()){
                    Bitmap photo = decodeFile(outPutFile);
                    user_img.setImageBitmap(photo);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Error while save image", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK) {
//            beginCrop(result.getData());
//        } else if (requestCode == Crop.REQUEST_CROP) {
//            handleCrop(resultCode, result);
//        }

    }

    private void CropingIMG() {

        final ArrayList<CropingOption> cropOptions = new ArrayList<CropingOption>();

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setType("image/*");

        List<ResolveInfo> list = getPackageManager().queryIntentActivities( intent, 0 );
        int size = list.size();
        if (size == 0) {
            Toast.makeText(this, "Cann't find image croping app", Toast.LENGTH_SHORT).show();
            return;
        } else {
            intent.setData(mImageCaptureUri);
            intent.putExtra("outputX", 512);
            intent.putExtra("outputY", 512);
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            intent.putExtra("scale", true);

            //TODO: don't use return-data tag because it's not return large image data and crash not given any message
            //intent.putExtra("return-data", true);

            //Create output file here
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outPutFile));

            if (size == 1) {
                Intent i   = new Intent(intent);
                ResolveInfo res = (ResolveInfo) list.get(0);

                i.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));

                startActivityForResult(i, CROPING_CODE);
            } else {
                for (ResolveInfo res : list) {
                    final CropingOption co = new CropingOption();

                    co.title  = getPackageManager().getApplicationLabel(res.activityInfo.applicationInfo);
                    co.icon  = getPackageManager().getApplicationIcon(res.activityInfo.applicationInfo);
                    co.appIntent= new Intent(intent);
                    co.appIntent.setComponent( new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                    cropOptions.add(co);
                }

                CropingOptionAdapter adapter = new CropingOptionAdapter(getApplicationContext(), cropOptions);

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Choose Croping App");
                builder.setCancelable(false);
                builder.setAdapter( adapter, new DialogInterface.OnClickListener() {
                    public void onClick( DialogInterface dialog, int item ) {
                        startActivityForResult( cropOptions.get(item).appIntent, CROPING_CODE);
                    }
                });

                builder.setOnCancelListener( new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel( DialogInterface dialog ) {

                        if (mImageCaptureUri != null ) {
                            getContentResolver().delete(mImageCaptureUri, null, null );
                            mImageCaptureUri = null;
                        }
                    }
                } );

                AlertDialog alert = builder.create();
                alert.show();
            }
        }
    }

    private Bitmap decodeFile(File f) {
        try {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f), null, o);

            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 512;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {
        }
        return null;
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,20, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    public void dialog_chngepw() {

        final Dialog dialog = new Dialog(SettingActivity.this, android.R.style.Theme_Translucent);
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.alertdialog_chngepw);

        ImageView img_cross = (ImageView) dialog.findViewById(R.id.img_cross);
         final EditText ed_oldpw=(EditText)dialog.findViewById(R.id.ed_oldpw);
        final EditText ed_newpw=(EditText)dialog.findViewById(R.id.ed_newpw);
        final EditText ed_confrmpw=(EditText)dialog.findViewById(R.id.ed_confrmpw);
        Button btn_save=(Button)dialog.findViewById(R.id.btn_save);

        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                oldpw=ed_oldpw.getText().toString().trim();
                newpw=ed_newpw.getText().toString().trim();
                cnfrmpw=ed_confrmpw.getText().toString().trim();


                if(oldpw.equals("")){

                    AlertDialogMsg alertmsg = new
                            AlertDialogMsg(SettingActivity.this, "Please enter Old Password.");

                    alertmsg.dialog.show();

                    return;
                }

                if(newpw.equals("")){

                    AlertDialogMsg alertmsg = new
                            AlertDialogMsg(SettingActivity.this, "Please enter New Password.");

                    alertmsg.dialog.show();

                    return;
                }

                if(cnfrmpw.equals("")){

                    AlertDialogMsg alertmsg = new
                            AlertDialogMsg(SettingActivity.this, "Please enter Confirm Password.");

                    alertmsg.dialog.show();

                    return;
                }
                if(!cnfrmpw.equals(newpw)){

                    AlertDialogMsg alertmsg = new
                            AlertDialogMsg(SettingActivity.this, "New password and Confrim Password should be same.");

                    alertmsg.dialog.show();
                }
            else{

                    if (NetworkConnection.isConnectedToInternet(SettingActivity.this)) {

                       chngepw();
                    }
                    else {
                        Toast.makeText(SettingActivity.this,R.string.networkconnection, Toast.LENGTH_SHORT).show();
                        return;

                    }

                }

            }
        });

        dialog.show();

    }


    public void dialog_buycredits() {

        final Dialog dialog = new Dialog(SettingActivity.this, android.R.style.Theme_Translucent);
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


    public void dialoglogout() {

        dialoglogout = new Dialog(SettingActivity.this, android.R.style.Theme_Translucent) {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                // Tap anywhere to close dialog.
                dialoglogout.dismiss();
                return true;
            }
        };
        dialoglogout = new Dialog(SettingActivity.this, android.R.style.Theme_Translucent);
        dialoglogout.setCanceledOnTouchOutside(true);
        dialoglogout.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialoglogout.setCancelable(true);
        dialoglogout.setContentView(R.layout.alertdialog_logout);

        ImageView img_cross = (ImageView) dialoglogout.findViewById(R.id.img_cross);
        TextView tv_yes=(TextView)dialoglogout.findViewById(R.id.tv_ok);
        TextView tv_no=(TextView)dialoglogout.findViewById(R.id.tv_cncl);
        TextView tv_msg=(TextView)dialoglogout.findViewById(R.id.tv_msg);



        img_cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoglogout.dismiss();
            }
        });
        tv_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialoglogout.dismiss();
                userLogout();
//                Intent i=new Intent(SettingActivity.this,RegisterActivity.class);
//                startActivity(i);


            }
        });
        tv_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialoglogout.dismiss();
            }
        });
        dialoglogout.show();

    }
    private void userLogout() {

        loading = new ProgressDialog(SettingActivity.this,R.style.AppCompatAlertDialogStyle);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loading.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.logout,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(SettingActivity.this," Logout Successfully",Toast.LENGTH_SHORT).show();

                        Log.e("respose logout===",""+response);


                        SharedPreferences.Editor editor1 = sharedpreferences1.edit();
                        editor1.putString("id","");
                        editor1.putString("access_token","");
                        editor1.putString("name","");
                        editor1.commit();

                        Intent i = new Intent(SettingActivity.this, RegisterActivity.class);
                        startActivity(i);
                        finish();
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SettingActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();

                map.put("access_token",access_tokn);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }



    private void chngepw() {

        loading = new ProgressDialog(SettingActivity.this,R.style.AppCompatAlertDialogStyle);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loading.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.chnge_pw,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                    Toast.makeText(SettingActivity.this,"Password has been changed Successfully",Toast.LENGTH_SHORT).show();

                        Log.e("respose chngepw===",""+response);

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SettingActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();

                map.put("access_token",access_tokn);
                map.put("old_password",oldpw);
                map.put("password",newpw);
                map.put("cpassword",cnfrmpw);

                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }
    private void profile_data(){

            loading = new ProgressDialog(SettingActivity.this,R.style.AppCompatAlertDialogStyle);
            loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.profile_data,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();

                            Log.e("respose proflie===",""+response);

                            try {
                                JSONObject main_obj = new JSONObject(response);
                                String msg =main_obj.getString("message");
                                //   String smsstatus =main_obj.getString("smsstatus");


                                JSONObject  obj=main_obj.getJSONObject("data");

                                String name=obj.getString("name");
                                String access_token =obj.getString("access_token");
                                String phone =obj.getString("phone");
                                String profile_pic =obj.getString("profile_pic");

                                ed_username.setText(name);
                           //     ed_phnnum.setText(phone);

                                if(profile_pic.isEmpty()){

                                }
                                else {
//                                    Glide.with(SettingActivity.this)
//                                          .load(profile_pic)
//                                            .into(user_img);

                                    Picasso.with(SettingActivity.this).load(profile_pic).into(user_img);

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
                            Toast.makeText(SettingActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        }
                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("access_token",access_tokn);

                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


    }

    private void update_profile(){

        final String username=ed_username.getText().toString().trim();
       // final String phnnum=ed_phnnum.getText().toString().trim();

        BitmapDrawable drawable = (BitmapDrawable) user_img.getDrawable();
        bitmap = drawable.getBitmap();
        userimg = getStringImage(bitmap);

        if(username.equals("")){

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(SettingActivity.this, "Username should not be empty.");

            alertmsg.dialog.show();

            return;
        }



//        if(phnnum.equals("")){
//
//            AlertDialogMsg alertmsg = new
//                    AlertDialogMsg(SettingActivity.this, "Phonenum should not be empty.");
//
//            alertmsg.dialog.show();
//
//            return;
//        }





        else {

            loading = new ProgressDialog(SettingActivity.this, R.style.AppCompatAlertDialogStyle);
            loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.update_profile,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();

                            Log.e("respose===", "" + response);

                            try {
                                JSONObject main_obj = new JSONObject(response);
                                String msg = main_obj.getString("message");
                                //   String smsstatus =main_obj.getString("smsstatus");


                                JSONObject obj = main_obj.getJSONObject("data");

                                String name = obj.getString("name");
                                String access_token = obj.getString("access_token");

                                String profile_pic = obj.getString("profile_pic");

                                ed_username.setText(name);
                                   if (profile_pic.isEmpty()) {

                                } else {
//                                    Glide.with(SettingActivity.this)
//                                            .load(profile_pic)
//                                         .into(user_img);

                                       Picasso.with(SettingActivity.this).load(profile_pic).into(user_img);

                                   }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SettingActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("access_token", access_tokn);
                    params.put("username",username);
                    params.put("profile_pic",userimg);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
    }
}
