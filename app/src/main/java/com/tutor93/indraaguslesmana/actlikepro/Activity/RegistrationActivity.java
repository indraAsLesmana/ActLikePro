package com.tutor93.indraaguslesmana.actlikepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.api.AuthResponse;

/**
 * Created by indraaguslesmana on 11/22/16.
 */

public class RegistrationActivity extends AppCompatActivity{

    private static final String EXTRA_PRE_USER = "RegistrationActivity::mPreFilledUser";
    private AuthResponse.FacebookUser mPreFilledUser;

    public static void start(Activity caller, boolean shouldFinish, AuthResponse.FacebookUser user) {
        Intent intent = new Intent(caller, RegistrationActivity.class);
        if(user != null) intent.putExtra(EXTRA_PRE_USER, user);
        caller.startActivity(intent);
        if(shouldFinish) caller.finish();
        caller.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }
}
