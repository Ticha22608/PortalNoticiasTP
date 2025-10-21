package com.semlengtp.portalnoticias

import  retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstancia {
     val api: NoticiasAPI by lazy {
         Retrofit.Builder()
             .baseUrl("https://api.thenewsapi.com/v1/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(NoticiasAPI::class.java)

     }
}