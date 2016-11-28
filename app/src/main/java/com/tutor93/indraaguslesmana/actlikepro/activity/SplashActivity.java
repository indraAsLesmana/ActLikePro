package com.tutor93.indraaguslesmana.actlikepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.adapter.SplashPagerAdapter;
import com.tutor93.indraaguslesmana.actlikepro.api.AuthRequest;
import com.tutor93.indraaguslesmana.actlikepro.utility.Constant;
import com.tutor93.indraaguslesmana.actlikepro.utility.Helpers;
import com.viewpagerindicator.PageIndicator;

import java.util.Arrays;


/**
 * Created by indraaguslesmana on 11/22/16.
 */

public class SplashActivity extends AppCompatActivity {
    private CallbackManager mFBcallbackManager;
    private LoginButton mBtnRealFB;

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, SplashActivity.class);
        caller.startActivity(intent);
        caller.finish();
        caller.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        mFBcallbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_splash);

        SplashPagerAdapter pagerAdapter = new SplashPagerAdapter(getSupportFragmentManager());

        View root = findViewById(android.R.id.content);
        ViewPager viewPager = (ViewPager) findViewById(R.id.splash_viewpager);
        PageIndicator pageIndicator = (PageIndicator) findViewById(R.id.splash_viewpager_indicator);
        View btnSignUpFacebook = findViewById(R.id.signup_facebook);
        View btnSignUp = findViewById(R.id.signup_classic);
        mBtnRealFB = (LoginButton) findViewById(R.id.splash_sign_up_facebook_real_button);

        if (viewPager != null) {
            viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
                @Override
                public void transformPage(View page, float position) {
                    float alpha;

                    if (position <= -1.0f || position >= 1.0f) {
                        alpha = 0.0f;
                    } else if (position == 0.0f) {
                        alpha = 1.0f;
                    } else {
                        alpha = 1.0f - Math.abs(position);
                    }

                    page.setAlpha(alpha);
                }
            });
            viewPager.setAdapter(pagerAdapter);
            viewPager.setClipToPadding(false);
            viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
        }

        if (pageIndicator != null) {
            pageIndicator.setViewPager(viewPager);
        }

        if (btnSignUpFacebook != null) {
            btnSignUpFacebook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBtnRealFB != null) {
                        LoginManager.getInstance().logOut();
                        mBtnRealFB.performClick();
                    }
                }
            });
        }

        if (btnSignUp != null) {
            btnSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RegistrationActivity.start(SplashActivity.this);
                }
            });
        }

        if (mBtnRealFB != null) {
            mBtnRealFB.setReadPermissions(Arrays.asList(Constant.FACEBOOK_READ_PERMISSIONS));
            mBtnRealFB.registerCallback(mFBcallbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    checkForFacebookAccount(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {

                }

                @Override
                public void onError(FacebookException error) {

                }
            });
        }


        Helpers.useBackground(root, true);
    }

    private void checkForFacebookAccount(AccessToken token) {
        Helpers.showProgressDialog(this, R.string.progress_body_login);

        AuthRequest request = new AuthRequest();
        request.facebook_id = token.getUserId();
        request.facebook_token = token.getToken();
    }


}
