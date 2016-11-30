package com.example.root.deatit13;


import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

/**
 * Created by root on 05.11.16.
 */

interface API {

    @POST("/koszgingi/index.php")
    Call<ServerResponse> operation(@Body ServerRequest request);

}