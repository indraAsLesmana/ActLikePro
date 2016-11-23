package com.tutor93.indraaguslesmana.actlikepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.FacebookSdk;
import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.utility.Helpers;

/**
 * Created by indraaguslesmana on 11/22/16.
 */

public class SplashActivity extends AppCompatActivity{

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, SplashActivity.class);
        caller.startActivity(intent);
        caller.finish();
        caller.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_splash);

        View root = findViewById(android.R.id.content);


        Helpers.useBackground(root, true);
    }
}
