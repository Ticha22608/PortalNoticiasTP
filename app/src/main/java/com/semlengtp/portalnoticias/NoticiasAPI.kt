package com.semlengtp.portalnoticias

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticiasAPI {
    @GET ("search-news")
    suspend fun obtenerNoticias(
        @Query("language") idioma: String = "es",
        @Query("source-countries") pais: String = "ar",
        @Query("number") cantidad: Int = 5,
        @Query("api-key") apiKey: String = "b388089098ea4128b8c0d6005d1070de"
    ) : Response <NoticiasResponse>
}