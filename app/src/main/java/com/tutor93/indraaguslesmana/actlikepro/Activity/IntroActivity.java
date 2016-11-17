package com.tutor93.indraaguslesmana.actlikepro.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tutor93.indraaguslesmana.actlikepro.R;

/**
 * Created by indraaguslesmana on 11/17/16.
 */

public class IntroActivity extends AppCompatActivity{
    private View mProgressBar;
    private View mContentContainer;
    private View mButtonRetry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }
}
