package com.zoptal.eauction.common;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by zotal.102 on 25/05/17.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static void setLocaleAr(Context context) {
//        Locale locale = new Locale("ar");
//        Locale.setDefault(locale);
//        Configuration config = new Configuration();
//        config.locale = locale;
//        context.getApplicationContext().getResources().updateConfiguration(config, null);
       // context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());



        Locale locale = new Locale("ar");
        Locale.setDefault(locale);

        Configuration config = context.getResources().getConfiguration();
        config.setLocale(locale);
        context.createConfigurationContext(config);

        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

    public static void setLocaleEn(Context context) {
        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }
}