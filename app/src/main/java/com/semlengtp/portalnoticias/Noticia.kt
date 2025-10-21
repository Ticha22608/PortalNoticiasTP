package com.semlengtp.portalnoticias
import com.google.gson.annotations.SerializedName

data class Noticia(
    @SerializedName("publish_date") val fecha: String,
    @SerializedName("title")val titulo: String,
    @SerializedName("summary") val descripcion : String,
    @SerializedName("image")val imagen: String?,
    @SerializedName("source") val fuente: String
)

data class NoticiasResponse(
   @SerializedName("news") val noticias: List<Noticia>
)