package com.semlengtp.portalnoticias

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticiasAPI {
    @GET ("news/top")
   suspend fun obtenerNoticias(
        @Query("locale") locale: String = "ar",
        @Query("api_token") apiKey: String = "6NRllUOJ53ZA1Ec0Dx5tSAOFyokTWk5IVDXe6E7M"
    ) : Response <NoticiasResponse>
}