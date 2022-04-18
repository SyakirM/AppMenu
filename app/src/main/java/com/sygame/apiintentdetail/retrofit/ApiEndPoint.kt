package com.sygame.apiintentdetail.retrofit

import com.sygame.apiintentdetail.DataModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndPoint {
    @GET("filter.php?c=Seafood")
    fun getData(): Call<DataModel>
}