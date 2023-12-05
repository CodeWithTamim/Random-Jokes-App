package com.cwtstudio.randomjokes.Interfaces;

import com.cwtstudio.randomjokes.Models.JokesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/random_ten")
    Call<List<JokesModel>> getJokes();




}
