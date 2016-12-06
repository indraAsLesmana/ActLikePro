package com.tutor93.indraaguslesmana.actlikepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.tutor93.indraaguslesmana.actlikepro.MainActivity;
import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.api.AuthResponse;
import com.tutor93.indraaguslesmana.actlikepro.likeaPro;
import com.tutor93.indraaguslesmana.actlikepro.model.gitmodel;
import com.tutor93.indraaguslesmana.actlikepro.utility.Constant;
import com.tutor93.indraaguslesmana.actlikepro.utility.Helpers;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by indraaguslesmana on 11/22/16.
 */

public class RegistrationActivity extends AppCompatActivity {

    private TextInputLayout mNameLayout;
    private TextInputLayout mEmailLayout;

    private static final int DISPLAY_SUCCESS_MESSAGE = 0;
    private static final int DISPLAY_FORM_REGISTRATION = 1;

    private ViewFlipper mViewFlipper;

    private static final String EXTRA_PRE_USER = "RegistrationActivity::mPreFilledUser";
    private AuthResponse.FacebookUser mPreFilledUser;
    private EditText mName;
    private EditText mEmail;
    private TextView mBtnSignUp;
    private TextView mRgsMessage;
    private Toolbar toolbar;

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

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setToolbar();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        mViewFlipper = (ViewFlipper) findViewById(R.id.registration_view_flipper);
        mBtnSignUp = (TextView)findViewById(R.id.registration_signup);
        mNameLayout = (TextInputLayout) findViewById(R.id.registration_form_name);
        mEmailLayout = (TextInputLayout) findViewById(R.id.registration_form_mail);
        mRgsMessage = (TextView) findViewById(R.id.registration_message);
        final View rootView = getWindow().getDecorView().getRootView();

        //setup animate for view vlipper
        mViewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
        mViewFlipper.setOutAnimation(this, R.anim.slide_out_from_left);

        setupForm();

        if (mBtnSignUp != null) {
            mBtnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (checValidity()) {
                        sendDataToAPI();
                        Helpers.hideSoftKeyboard(rootView);
                    }

                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewFlipper.setDisplayedChild(DISPLAY_FORM_REGISTRATION);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_from_right);
    }

    private boolean checValidity() {
        if (mName.getText().toString().isEmpty()) {
            putError(mNameLayout, R.string.please_input_name);
            return false;
        } else {
            clearError(mNameLayout);
        }

        if (mEmail.getText().toString().isEmpty()) {
            putError(mEmailLayout, R.string.please_input_mail);
            return false;
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(mEmail.getText().toString()).matches()) {
                putError(mEmailLayout, R.string.invalid_email_address);
                return false;
            }
            clearError(mEmailLayout);
        }
        return true;
    }

    private void setupForm(){
        mName = mNameLayout.getEditText();
        mEmail = mEmailLayout.getEditText();
    }

    // helper to display error message on TextInputLayout
    private void putError(TextInputLayout view, int stringRes) {
        view.setErrorEnabled(true);
        view.setError(getString(stringRes));
        view.requestFocus();
    }

    // helper to clear error message on TextInputLayout
    private void clearError(TextInputLayout view) {
        view.setErrorEnabled(false);
    }

    private void sendDataToAPI() {
        likeaPro.getService().getFeed("ivey", new Callback<gitmodel>() {
            @Override
            public void success(gitmodel gitmodel, Response response) {

                // hide back button on toolbar
                if(getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setDisplayShowHomeEnabled(false);

                }

                String mUsername = mName.getText().toString();
                String mUsermail = gitmodel.getEmail().toString();
                String mUserAvatar = gitmodel.getAvatarUrl();
//                likeaPro.setUserSession("ivey", "ivey@gweezlebur.com");
                likeaPro.setUserSession(mUsername, mUsermail, mUserAvatar);

                if (mUsername.equalsIgnoreCase(gitmodel.getLogin())){
                    mRgsMessage.setText(R.string.login_success);
                    mViewFlipper.setDisplayedChild(DISPLAY_SUCCESS_MESSAGE);

                    new Handler().postDelayed(new Runnable() { // make delay to next activity.
                        @Override
                        public void run() {
                        }

                    }, 3000);

                    MainActivityDrawer.start(RegistrationActivity.this);

                }else {
                    mRgsMessage.setText(R.string.login_failed);
                    mViewFlipper.setDisplayedChild(DISPLAY_SUCCESS_MESSAGE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                           mViewFlipper.setDisplayedChild(1);
                            setToolbar();
                        }
                    }, 1500);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Helpers.showToast(RegistrationActivity.this, error.getMessage(), true);
            }
        });
    }

    private void setToolbar() {
        if(toolbar != null) {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.color_secondary));
            setSupportActionBar(toolbar);
        }
    }

}
