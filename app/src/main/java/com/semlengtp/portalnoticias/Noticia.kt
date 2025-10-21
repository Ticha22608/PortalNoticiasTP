package com.semlengtp.portalnoticias
import com.google.gson.annotations.SerializedName

data class Noticia(
    @SerializedName("published_at") val fecha: String,
    @SerializedName("title")val titulo: String,
    @SerializedName("description") val descripcion : String,
    @SerializedName("image_url")val imagen: String,
    @SerializedName("source") val fuente: String
)

data class NoticiasResponse(
    val data: List<Noticia>
)