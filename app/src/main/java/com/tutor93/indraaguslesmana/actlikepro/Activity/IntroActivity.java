package com.tutor93.indraaguslesmana.actlikepro.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tutor93.indraaguslesmana.actlikepro.Api.gitapi;
import com.tutor93.indraaguslesmana.actlikepro.Model.gitmodel;
import com.tutor93.indraaguslesmana.actlikepro.R;
import com.tutor93.indraaguslesmana.actlikepro.likeaPro;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by indraaguslesmana on 11/17/16.
 */

public class IntroActivity extends AppCompatActivity {
    private View mProgressBar;
    private View mContentContainer;
    private View mButtonRetry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        String API = "https://api.github.com";

        mProgressBar = findViewById(R.id.intro_progressbar);
        mContentContainer = findViewById(R.id.intro_notification_container);
        mButtonRetry = findViewById(R.id.intro_retry_button);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        gitapi git = restAdapter.create(gitapi.class);

        git.getFeed("mojombo", new Callback<gitmodel>() {
            @Override
            public void success(gitmodel gitmodel, Response response) {
                Toast.makeText(IntroActivity.this, "Success", Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.VISIBLE);
                mContentContainer.setVisibility(View.GONE);
                mButtonRetry.setVisibility(View.GONE);
            }

            @Override
            public void failure(RetrofitError error) {
                mProgressBar.setVisibility(View.GONE);
                mContentContainer.setVisibility(View.VISIBLE);
                mButtonRetry.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mButtonRetry.callOnClick(); //TODO : still error if CallOnClick run on emulator, but in real device blank on Android Monitor
    }
}
