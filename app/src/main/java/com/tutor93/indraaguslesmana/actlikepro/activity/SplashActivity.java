package com.tutor93.indraaguslesmana.actlikepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.FacebookSdk;
import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.adapter.SplashPagerAdapter;
import com.tutor93.indraaguslesmana.actlikepro.utility.Helpers;
import com.viewpagerindicator.PageIndicator;

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

        SplashPagerAdapter pagerAdapter = new SplashPagerAdapter(getSupportFragmentManager());

        View root = findViewById(android.R.id.content);
        ViewPager viewPager = (ViewPager)findViewById(R.id.splash_viewpager);
        PageIndicator pageIndicator = (PageIndicator)findViewById(R.id.splash_viewpager_indicator);

        if(viewPager != null) {
            viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
                @Override
                public void transformPage(View page, float position) {
                    float alpha;

                    if(position <= -1.0f || position >= 1.0f) {
                        alpha = 0.0f;
                    }
                    else if( position == 0.0f ) {
                        alpha = 1.0f;
                    }
                    else {
                        alpha = 1.0f - Math.abs(position);
                    }

                    page.setAlpha(alpha);
                }
            });
            viewPager.setAdapter(pagerAdapter);
            viewPager.setClipToPadding(false);
            viewPager.setOffscreenPageLimit(pagerAdapter.getCount());
        }

        if(pageIndicator != null) {
            pageIndicator.setViewPager(viewPager);
        }

        Helpers.useBackground(root, true);
    }


}
