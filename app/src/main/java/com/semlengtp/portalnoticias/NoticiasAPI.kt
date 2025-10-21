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
        @Query("api-key") apiKey: String = "6cda7bded3ed48be8595e9ad2de67e61"
    ) : Response <NoticiasResponse>
}