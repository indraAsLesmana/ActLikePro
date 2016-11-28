package com.tutor93.indraaguslesmana.actlikepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ViewFlipper;

import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.api.AuthResponse;
import com.tutor93.indraaguslesmana.actlikepro.utility.Helpers;

/**
 * Created by indraaguslesmana on 11/22/16.
 */

public class RegistrationActivity extends AppCompatActivity {

    private static final int DISPLAY_SUCCESS_MESSAGE = 0;
    private static final int DISPLAY_FORM_REGISTRATION = 1;

    private ViewFlipper mViewFlipper;

    private static final String EXTRA_PRE_USER = "RegistrationActivity::mPreFilledUser";
    private AuthResponse.FacebookUser mPreFilledUser;

    public static void start(Activity caller) {
        start(caller, false, null);
    }

    public static void start(Activity caller, boolean shouldFinish, AuthResponse.FacebookUser user) {
        Intent intent = new Intent(caller, RegistrationActivity.class);
        if (user != null) intent.putExtra(EXTRA_PRE_USER, user);
        caller.startActivity(intent);
        if (shouldFinish) caller.finish();
        caller.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.color_secondary));
            setSupportActionBar(toolbar);
        }

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mViewFlipper = (ViewFlipper) findViewById(R.id.registration_view_flipper);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewFlipper.setDisplayedChild(DISPLAY_FORM_REGISTRATION);
        Helpers.hideSoftKeyboard(getCurrentFocus()); // TODO : keyboard still viewed, on form registration starting view.
    }
}
