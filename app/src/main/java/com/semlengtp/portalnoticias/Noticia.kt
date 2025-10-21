package com.semlengtp.portalnoticias
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Noticia(
    @SerializedName("publish_date") val fecha: String,
    @SerializedName("title")val titulo: String,
    @SerializedName("summary") val descripcion : String,
    @SerializedName("image")val imagen: String?,
    @SerializedName("source") val fuente: String,
    @SerializedName("text") val cuerpo: String,
    @SerializedName("category") val categoria: String
) : Serializable

data class NoticiasResponse(
   @SerializedName("news") val noticias: List<Noticia>
)