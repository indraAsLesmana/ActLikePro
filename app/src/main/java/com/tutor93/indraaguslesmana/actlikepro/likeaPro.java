package com.tutor93.indraaguslesmana.actlikepro;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;

import com.tutor93.indraaguslesmana.actlikepro.api.gitapi;
import com.tutor93.indraaguslesmana.actlikepro.utility.Constant;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;

/**
 * Created by indraaguslesmana on 11/17/16.
 */

public class likeaPro extends Application {
    private static likeaPro sInstance;
    private static gitapi sAPIService;
    private static final String API = "https://api.github.com";
    private static Typeface sRegularFont;
    private static Typeface sBoldFont;
    private static SharedPreferences sPreferences;

    public static void log(String message, Throwable throwable) {
        if(Constant.ENABLE_LOG) {
            Log.v(Constant.TAG_LOG_VERBOSE, message, throwable);
        }
    }

    public static void logError(String message, Throwable throwable) {
        if (Constant.ENABLE_LOG) {
            Log.e(Constant.TAG_LOG_ERROR, message, throwable);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        sRegularFont = Typeface.createFromAsset(getAssets(), Constant.APP_FONT_REGULAR);
        sBoldFont = Typeface.create(sRegularFont, Typeface.BOLD);
        sPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);

    }

    public static void setUserSession (String userName, String userEmail, String userImage){
        SharedPreferences.Editor editor = sPreferences.edit();
        editor.putString(Constant.PREFERENCE_USER_NAME, userName);
        editor.putString(Constant.PREFERENCE_USER_EMAIL, userEmail);
        editor.putString(Constant.PREFERENCE_USER_IMAGE, userImage);
        editor.apply();
    }

    public static gitapi getService() {
        sAPIService = (new RestAdapter.Builder()
                .setEndpoint(API)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(RetrofitError cause) {
                        String errorDescription = null; //TODO : from biyu references, not like this. is just another way

                        if (cause.getKind().equals(RetrofitError.Kind.NETWORK)) {
//                            errorDescription = getString(R.string.network_error);
                            errorDescription = "Network error";
                        } else {
                            if (cause.getResponse() == null) {
//                                errorDescription = getString(R.string.network_error);
                            errorDescription = "Cannot reach Server";
                            }
                        }

                        logError(errorDescription, cause);
                        return new Exception(errorDescription);
                    }
                })
                .build()).create(gitapi.class);

        return sAPIService;
    };

    public static likeaPro getInstance() {
        return sInstance;
    }

    public static Typeface getRegularFont() {
        return sRegularFont;
    }

    public static Typeface getBoldFont() {
        return sBoldFont;
    }

    public static String getUsername (){
        return sPreferences.getString(Constant.PREFERENCE_USER_NAME, null);
    }
    public static String getUsermail (){
        return sPreferences.getString(Constant.PREFERENCE_USER_EMAIL, null);
    }
    public static String getUserImage (){
        return sPreferences.getString(Constant.PREFERENCE_USER_IMAGE, null);
    }

    private void relaunchApplication() {
        Application application = likeaPro.getInstance();
        Intent intent = application.getPackageManager()
                .getLaunchIntentForPackage(application.getPackageName());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        application.startActivity(intent);
    }
}
