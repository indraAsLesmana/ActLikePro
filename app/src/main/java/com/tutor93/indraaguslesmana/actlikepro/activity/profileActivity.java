package com.tutor93.indraaguslesmana.actlikepro.activity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.likeaPro;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by indraaguslesmana on 12/7/16.
 */

public class profileActivity extends AppCompatActivity{
    private Toolbar toolbar;
    private ImageButton mProfileImage;
    protected RoundedCornersTransformation mTransformation;

    public static void start(Activity caller) {
        Intent intent = new Intent(caller, profileActivity.class);
        caller.startActivity(intent);
        caller.finish();
        caller.overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_from_left);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.material_design_profile_screen_xml_ui_design);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setToolbar();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        mProfileImage = (ImageButton) findViewById(R.id.user_profile_photo);
        mTransformation = new RoundedCornersTransformation(profileActivity.this,
                getResources().getDimensionPixelSize(R.dimen.image_card_rounded_size), 0);

        loadUser();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_from_right);
        MainActivityDrawer.start(profileActivity.this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


    private void setToolbar() {
        if(toolbar != null) {
            toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.color_secondary));
            setSupportActionBar(toolbar);
        }
    }

    private void loadUser(){
        if(likeaPro.getUserImage() != null) {
            Glide.with(getBaseContext())
                    .load(likeaPro.getUserImage())
                    .diskCacheStrategy(DiskCacheStrategy.RESULT)
                    .bitmapTransform(mTransformation)
                    .crossFade()
                    .into(mProfileImage);
        }
    }

}
