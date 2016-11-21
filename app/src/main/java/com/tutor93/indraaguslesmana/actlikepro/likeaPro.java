package com.tutor93.indraaguslesmana.actlikepro;

import android.app.Application;
import android.util.Log;

import com.tutor93.indraaguslesmana.actlikepro.Api.gitapi;
import com.tutor93.indraaguslesmana.actlikepro.utility.Constant;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by indraaguslesmana on 11/17/16.
 */

public class likeaPro extends Application {
    private static gitapi sAPIService;
    private static final String API = "https://api.github.com";

    public static void logError(String message, Throwable throwable) {
        if (Constant.ENABLE_LOG) {
            Log.e(Constant.TAG_LOG_ERROR, message, throwable);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sAPIService = (new RestAdapter.Builder()
                .setEndpoint(API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        String errorDescription = null; //TODO : from biyu references, not like this. is just for practice

                        if (cause.getKind().equals(RetrofitError.Kind.NETWORK)) {
                            errorDescription = getString(R.string.network_error);
                        } else {
                            if (cause.getResponse() == null) {
                                errorDescription = getString(R.string.network_error);
                            }
                        }

                        logError(errorDescription, cause);
                        return new Exception(errorDescription);
                    }
                })
                .build()).create(gitapi.class);

    }

    public static gitapi getService() {
        return sAPIService;
    };
}
