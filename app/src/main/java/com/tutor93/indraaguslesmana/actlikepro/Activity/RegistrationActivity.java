package com.tutor93.indraaguslesmana.actlikepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.api.AuthResponse;
import com.tutor93.indraaguslesmana.actlikepro.utility.Helpers;

import org.w3c.dom.Text;

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
        TextView btnSignup = (TextView)findViewById(R.id.registration_signup);
        mNameLayout = (TextInputLayout) findViewById(R.id.registration_form_name);
        mEmailLayout = (TextInputLayout) findViewById(R.id.registration_form_mail);

        setupForm();

        if(btnSignup != null){
            btnSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(checValidity()){
                        mViewFlipper.setDisplayedChild(DISPLAY_SUCCESS_MESSAGE);
                        Helpers.hideSoftKeyboard(getCurrentFocus());
                    }

                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewFlipper.setDisplayedChild(DISPLAY_FORM_REGISTRATION);
        Helpers.hideSoftKeyboard(getCurrentFocus()); // TODO : on starting, keyboard always showed. becouse getCurrentfFokus return null
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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
}
