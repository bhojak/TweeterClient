package com.bhupen.twitterclient.shared.api;

import com.bhupen.twitterclient.dataTypes.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

//    @GET("users")
//    Observable<List<User>> getUsersList();


    @GET("/1.1/users/show.json")
    Call<User> show(@Query("user_id") long id);

}
