package com.example.myapplication.retrofit;

import com.example.myapplication.data.response.RequestDetailResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://localhost/api/";

    @GET("getUniversities")
    Call<List<RequestDetailResponse>> getUniversities();

}