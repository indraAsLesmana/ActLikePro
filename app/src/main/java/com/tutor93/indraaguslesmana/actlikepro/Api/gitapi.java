package com.tutor93.indraaguslesmana.actlikepro.api;

import com.tutor93.indraaguslesmana.actlikepro.model.gitmodel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface gitapi {
   @GET("/users/{user}")
   public void getFeed(@Path("user") String user, Callback<gitmodel> response);
}