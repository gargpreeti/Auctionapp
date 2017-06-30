package com.zoptal.eauction.main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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
import com.soundcloud.android.crop.Crop;
import com.zoptal.eauction.R;
import com.zoptal.eauction.common.AlertDialogMsg;
import com.zoptal.eauction.model.Model_Categories;
import com.zoptal.eauction.url.RegisterUrl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class AddNewAuctionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView img_backarrow, img_filter, img_logo;
    private EditText ed_productname,ed_condition,ed_openingprice;
    private ImageView img_1,img_2,img_3;
    private TextView tv_buymore;
    private Button btn_submit;
    private Dialog dialog,dialog_category;
    private ListView listview;
    Spinner spinner1;
    public  static  ArrayList<Model_Categories> al_category= new ArrayList<Model_Categories>();
    private List<String> categoryList = new ArrayList<String>();
    CustomListAdaptercategorylist adapter;
    ProgressDialog loading;
    public final String MyPREFERENCES = "MyPrefs1" ;
    SharedPreferences sharedpreferences1;
    String access_tokn;
    String categoryid="";
    private Bitmap bitmap,bitmap1,bitmap2;
    String img1,img2,img3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_auction);

        sharedpreferences1 =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        access_tokn = sharedpreferences1.getString("access_token", "");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initview();

        getcategorylist();

    }
    public void initview() {

        img_backarrow=(ImageView)findViewById(R.id.img_backarrow);
        img_filter=(ImageView)findViewById(R.id.img_filter);
        img_logo=(ImageView)findViewById(R.id.img_logo);

        tv_buymore=(TextView)findViewById(R.id.tv_buymore);

        SpannableString more = new SpannableString("BUY MORE");
        more.setSpan(new UnderlineSpan(), 0, more.length(), 0);
        tv_buymore.setText(more);

        btn_submit=(Button)findViewById(R.id.btn_submit);
        ed_productname=(EditText)findViewById(R.id.ed_productname);
        ed_condition=(EditText)findViewById(R.id.ed_condition);
        ed_openingprice=(EditText)findViewById(R.id.ed_openingprice);
        img_1=(ImageView)findViewById(R.id.img_1);
        img_2=(ImageView)findViewById(R.id.img_2);
        img_3=(ImageView)findViewById(R.id.img_3);

        // rel_choosecategory=(RelativeLayout)findViewById(R.id.rel_choosecategory);
        spinner1=(Spinner)findViewById(R.id.spinner1);
//        categoryList.add("Computer");
//        categoryList.add("Mobile");
//        categoryList.add("Electricals & Electronics");
//        categoryList.add("Video Games");
//        categoryList.add("Jewellery & Accessories");


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String item = parent.getItemAtPosition(position).toString();

                //  ((TextView) view).setGravity(Gravity.RIGHT);
//                Log.e("drop down item---",""+item);
//                Log.e("drop down id---",""+al_category.get(spinner1.getSelectedItemPosition()).getId());
              categoryid=al_category.get(spinner1.getSelectedItemPosition()).getId();

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        img_backarrow.setOnClickListener(this);
        img_filter.setOnClickListener(this);
        img_logo.setOnClickListener(this);
        btn_submit.setOnClickListener(this);
        img_1.setOnClickListener(this);
        img_2.setOnClickListener(this);
        img_3.setOnClickListener(this);
       // rel_choosecategory.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.img_backarrow:

                Intent i = new Intent(AddNewAuctionActivity.this, UserAccountActivity.class);
                startActivity(i);

                break;

            case R.id.img_filter:

                Intent filterintent = new Intent(AddNewAuctionActivity.this, FilterActivity.class);
                startActivity(filterintent);

                break;

            case R.id.img_logo:

                Intent accountintent = new Intent(AddNewAuctionActivity.this, UserAccountActivity.class);
                startActivity(accountintent);

                break;

            case R.id.img_1:

                Crop.pickImage(AddNewAuctionActivity.this, 1);

                break;

            case R.id.img_2:

                Crop.pickImage(AddNewAuctionActivity.this, 2);

                break;

            case R.id.img_3:

                Crop.pickImage(AddNewAuctionActivity.this, 3);

                break;

            case R.id.btn_submit:

                dialog = new Dialog(AddNewAuctionActivity.this, android.R.style.Theme_Translucent) {
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
                dialog.setContentView(R.layout.alertdialog_confirm);

                TextView tv_cncl=(TextView)dialog.findViewById(R.id.tv_cncl);
                TextView tv_ok=(TextView)dialog.findViewById(R.id.tv_ok);
                TextView tv_msg=(TextView)dialog.findViewById(R.id.tv_msg);
                tv_msg.setText("Are you sure to want to save product?");
                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
//                        Intent intnt = new Intent(AddNewAuctionActivity.this, UserAccountActivity.class);
//                        startActivity(intnt);

                      addnewauction();

                    }
                });

                tv_cncl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
                dialog.show();


                break;
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(AddNewAuctionActivity.this.getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(AddNewAuctionActivity.this, 1);

    }

    private void handleCrop(int resultCode, Intent result) {

        if (resultCode == RESULT_OK) {

            img_1.setImageURI(null);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Crop.getOutput(result));
            } catch (IOException e) {
                e.printStackTrace();
            }

            img_1.setImageURI(Crop.getOutput(result));


        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(AddNewAuctionActivity.this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


    private void beginCrop1(Uri source) {
        Uri destination = Uri.fromFile(new File(AddNewAuctionActivity.this.getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(AddNewAuctionActivity.this, 2);

    }

    private void handleCrop1(int resultCode, Intent result) {

        if (resultCode == RESULT_OK) {

            img_2.setImageURI(null);
            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), Crop.getOutput(result));
            } catch (IOException e) {
                e.printStackTrace();
            }

            img_2.setImageURI(Crop.getOutput(result));

        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(AddNewAuctionActivity.this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void beginCrop2(Uri source) {
        Uri destination = Uri.fromFile(new File(AddNewAuctionActivity.this.getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(AddNewAuctionActivity.this, 3);

    }

    private void handleCrop2(int resultCode, Intent result) {

        if (resultCode == RESULT_OK) {

            img_3.setImageURI(null);
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(), Crop.getOutput(result));
            } catch (IOException e) {
                e.printStackTrace();
            }

            img_3.setImageURI(Crop.getOutput(result));

        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(AddNewAuctionActivity.this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {

            onActivityResult1(requestCode, resultCode, data);
        }
        if (requestCode == 2 && resultCode == RESULT_OK) {

            onActivityResult2(requestCode, resultCode, data);
        }
        if (requestCode == 3 && resultCode == RESULT_OK) {

            onActivityResult3(requestCode, resultCode, data);
        }
    }
    public void onActivityResult1(int requestCode, int resultCode, Intent data) {

        try {
            if (requestCode == 1) {
                beginCrop(data.getData());

            }
            if (requestCode == 1) {

                handleCrop(resultCode, data);
            }

        } catch (Exception e) {

        }

        try {
            if (requestCode == 2) {
                beginCrop1(data.getData());

            }
            if (requestCode ==2) {

                handleCrop1(resultCode, data);
            }

        } catch (Exception e) {

        }
    }
    public void onActivityResult2(int requestCode, int resultCode, Intent data) {

        try {
            if (requestCode == 2) {
                beginCrop1(data.getData());

            }
            if (requestCode == 2) {

                handleCrop1(resultCode, data);
            }

        } catch (Exception e) {

        }
    }

    public void onActivityResult3(int requestCode, int resultCode, Intent data) {

        try {
            if (requestCode == 3) {
                beginCrop2(data.getData());

            }
            if (requestCode == 3) {

                handleCrop2(resultCode, data);
            }

        } catch (Exception e) {

        }
    }
    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG,20, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void dialog_category() {

        dialog = new Dialog(AddNewAuctionActivity.this, android.R.style.Theme_Translucent) {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                // Tap anywhere to close dialog.
                dialog.dismiss();
                return true;
            }
        };
      //  arrayList_storeitems  = new ArrayList<String>();
        dialog_category.setCanceledOnTouchOutside(true);
        dialog_category.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog_category.setCancelable(true);
        dialog_category.setContentView(R.layout.dialog_categorylist);

        listview=(ListView)dialog_category.findViewById(R.id.listview);
        listview.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        Button btn_cncl = (Button) dialog_category.findViewById(R.id.btn_cncl);
        Button btn_confirm = (Button) dialog_category.findViewById(R.id.btn_confirm);

        categoryList.clear();
        adapter = new CustomListAdaptercategorylist(categoryList);
        listview.setAdapter(adapter);



        btn_cncl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dialog_category.dismiss();
            }
        });


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                for(int i=0;i<arrayList_storeitems.size();i++) {
//
//                    select_store.setText(arrayList_storeitems.get(i).toString());
//
//                }
//                commaSepValueBuilder_id = new StringBuilder();
//
//                for(int i=0;i<arrayList_storeitems_id.size();i++) {
//
//
//                    commaSepValueBuilder_id.append(arrayList_storeitems_id.get(i));
//
//                    if ( i != arrayList_storeitems_id.size()-1){
//                        commaSepValueBuilder_id.append(", ");
//                    }
//                }
//                Log.e("commaSepValueBuilder_id",""+commaSepValueBuilder_id.toString());
                dialog_category.dismiss();
            }
        });

        dialog_category.show();

    }

    class CustomListAdaptercategorylist extends BaseAdapter {

        LayoutInflater inflater;
        Context context;
        private List<String> categoryItems;

        public CustomListAdaptercategorylist(List<String> categoryItems) {

            this.context = context;
            this.categoryItems = categoryItems;
            inflater = LayoutInflater.from(getApplicationContext());

        }


        @Override
        public int getCount() {
            // TODO Auto-generated method stub

            return categoryItems.size();

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

            final Holdlercategorylist holder = new Holdlercategorylist();

            convertView = inflater.inflate(R.layout.customview_categorylist, null);

            holder.tv = (TextView) convertView.findViewById(R.id.tv_name);
            holder.img_check= (ImageView)convertView.findViewById(R.id.img_check);
            holder.rel_list=(RelativeLayout)convertView.findViewById(R.id.rel_list);
            holder.tv.setText(categoryItems.get(position));



//            if(singleselect==true) {
//
//                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        for(int i=0;i<storeItems.size();i++) {
//
//                            if(i==position){
//                                storeItems.get(i).setStatus(true);
//
//                            }
//                            else {
//
//                                storeItems.get(i).setStatus(false);
//                            }
//                        }
//
//                        selectedstore=storeItems.get(position).getStore_name();
//                        arrayList_storeitems.add(selectedstore);
//
//
//                        selectedstoreid=storeItems.get(position).getid();
//                        arrayList_storeitems_id.clear();
//                        arrayList_storeitems_id.add(selectedstoreid);
//
//                        Log.e("selectd--"," "+selectedstore);
//                        Log.e("selectd id--","  "+selectedstoreid);
//
//                        notifyDataSetChanged();
//                    }
//                });
//
//                notifyDataSetChanged();
//
//
//                if (storeItems.get(position).getStatus() == true) {
//
//                    holder.img_check.setVisibility(View.VISIBLE);
//                } else {
//                    holder.img_check.setVisibility(View.GONE);
//                }
//            }
//
//
//            if(singleselect==false) {
//
//                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                        storeItems.get(position).setStatus(true);
//
//                        selectedstore=storeItems.get(position).getStore_name();
//                        arrayList_storeitems.add(selectedstore);
//                        selectedstoreid=storeItems.get(position).getid();
//                        arrayList_storeitems_id.add(selectedstoreid);
//                        Log.e("selectd--"," "+selectedstore);
//                        Log.e("selectd id--","  "+selectedstoreid);
//
//                        notifyDataSetChanged();
//                    }
//                });
//
//                notifyDataSetChanged();
//
//
//                if (storeItems.get(position).getStatus() == true) {
//
//                    holder.img_check.setVisibility(View.VISIBLE);
//                }

          //  }

            return convertView;

        }

        class Holdlercategorylist
        {

            TextView tv;
            ImageView img_check;
            RelativeLayout rel_list;
        }
    }
    private void getcategorylist() {

        loading = new ProgressDialog(AddNewAuctionActivity.this,R.style.AppCompatAlertDialogStyle);
        loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        loading.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.get_category,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();

                        Log.e("respose cate===",""+response);

                        try {
                            JSONObject main_obj = new JSONObject(response);
                            JSONArray ary_products= main_obj.getJSONArray("data");


                            for (int i = 0; i < ary_products.length(); i++) {
                                JSONObject obj = ary_products.getJSONObject(i);
                                String id = obj.getString("id");
                                String title = obj.getString("cat_name");

                                categoryList.add(title);
                                List categorylist = new ArrayList(new LinkedHashSet(categoryList));
                                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddNewAuctionActivity.this,R.layout.dropdownitem,categorylist);
                                dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                                spinner1.setAdapter(dataAdapter);

                                Model_Categories f = new Model_Categories();

                                f.setId(id);
                                f.setTitle(title);


                                al_category.add(f);

                            }


                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data

                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddNewAuctionActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
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
        requestQueue.add(stringRequest);
    }

    private void addnewauction() {

        final String productname = ed_productname.getText().toString().trim();
        final String productcondition = ed_condition.getText().toString().trim();
        final String openprice = ed_openingprice.getText().toString().trim();


        if (productname.equals("")) {

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(AddNewAuctionActivity.this, "Please enter Product Name.");

            alertmsg.dialog.show();

            return;
        }

        if (productcondition.equals("")) {

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(AddNewAuctionActivity.this, "Please enter Product Condition.");

            alertmsg.dialog.show();

            return;
        }

        Log.e("bitmap value=====", "" + bitmap);
        try {
            if (bitmap == null) {

//               bitmap =((BitmapDrawable)user_img.getDrawable()).getBitmap();
//
//                Log.e("bitmap null=====",""+bitmap);
//                userimg = getStringImage(bitmap);
//
//                AlertDialogMsg alertmsg = new
//                        AlertDialogMsg(Business_AccountActivity.this, "Please select image.");
//
//                alertmsg.dialog.show();

                BitmapDrawable drawable = (BitmapDrawable) img_1.getDrawable();
                bitmap = drawable.getBitmap();
                img1 = getStringImage(bitmap);
                return;
            }
        } catch (NullPointerException e) {

        }
        try {
            if (bitmap != null) {
                Log.e("bitmap not null=====", "" + bitmap);
                img1 = getStringImage(bitmap);

            }
        } catch (NullPointerException e) {

        }

        try {
            if (bitmap1 == null) {

                BitmapDrawable drawable = (BitmapDrawable) img_2.getDrawable();
                bitmap1 = drawable.getBitmap();
                img2 = getStringImage(bitmap1);
                return;
            }
        } catch (NullPointerException e) {

        }
        try {
            if (bitmap1 != null) {
                Log.e("bitmap not null=====", "" + bitmap1);
                img2 = getStringImage(bitmap1);

            }
        } catch (NullPointerException e) {

        }

        try {
            if (bitmap2 == null) {

                BitmapDrawable drawable = (BitmapDrawable) img_3.getDrawable();
                bitmap2 = drawable.getBitmap();
                img3 = getStringImage(bitmap2);
                return;
            }
        } catch (NullPointerException e) {

        }
        try {
            if (bitmap2 != null) {
                Log.e("bitmap not null=====", "" + bitmap2);
                img3 = getStringImage(bitmap2);

            }
        } catch (NullPointerException e) {

        }







        if (openprice.equals("")) {

            AlertDialogMsg alertmsg = new
                    AlertDialogMsg(AddNewAuctionActivity.this, "Please enter Opening Price.");

            alertmsg.dialog.show();

            return;
        }

        else {

            loading = new ProgressDialog(AddNewAuctionActivity.this,R.style.AppCompatAlertDialogStyle);
            loading.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            loading.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, RegisterUrl.add_auction,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            loading.dismiss();
                            Toast.makeText(AddNewAuctionActivity.this,"Product has been saved successfully.",Toast.LENGTH_SHORT).show();

                            Intent intnt = new Intent(AddNewAuctionActivity.this, UserAccountActivity.class);
                            startActivity(intnt);
                            Log.e("respose add new auction===",""+response);

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(AddNewAuctionActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> map = new HashMap<String,String>();

                    map.put("access_token",access_tokn);
                    map.put("product_name",productname);
                    map.put("category_id",categoryid);
                    map.put("product_condition",productcondition);
                    map.put("opening_price",openprice);
                    map.put("product_image1",img1);
                    map.put("product_image2",img2);
                    map.put("product_image3",img3);
                    map.put("level_id","");
                    return map;
                }
            };

             RequestQueue requestQueue = Volley.newRequestQueue(this);
             stringRequest.setRetryPolicy(new DefaultRetryPolicy(500000,
             DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
             DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
             requestQueue.add(stringRequest);


//            Intent intent1 = new Intent(ContactusActivity.this, SettingActivity.class);
//            startActivity(intent1);

        }


    }
}
