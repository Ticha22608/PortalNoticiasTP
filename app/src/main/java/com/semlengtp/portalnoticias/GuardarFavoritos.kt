package com.semlengtp.portalnoticias

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GuardarFavoritos {
    private const val PREFS = "prefs_favoritos"
    private const val KEY = "favoritos_json"

    private val gson = Gson()

    private val tipoLista = object : TypeToken<MutableList<Noticia>>() {}.type


    fun getTodos(ctx: Context): MutableList<Noticia> {
        val json = ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY, null)
        return if (json.isNullOrEmpty()) mutableListOf()
        else gson.fromJson(json, tipoLista)
    }

    private fun clave(n: Noticia): String? = n.imagen ?: n.titulo

    fun guardarTodos(ctx: Context, lista: MutableList<Noticia>) {
        val json = gson.toJson(lista)
        ctx.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY, json)
            .apply()
    }

    fun esFavorito(ctx: Context, n: Noticia): Boolean {
        val key = clave(n) ?: return false
        return getTodos(ctx).any { (it.imagen ?: it.titulo) == key }
    }

    fun agregarAFavoritos(ctx: Context, n: Noticia): Boolean {
        val key = clave(n) ?: return false
        val lista = getTodos(ctx)
        val existe = lista.any { (it.imagen ?: it.titulo) == key }
        if (existe) return false
        lista.add(n)
        guardarTodos(ctx, lista)
        return true
    }

    fun quitarFavorito(ctx: Context, n: Noticia): Boolean {
        val key = clave(n) ?: return false
        val lista = getTodos(ctx)
        val idx = lista.indexOfFirst { (it.imagen ?: it.titulo) == key }
        if (idx < 0) return false
        lista.removeAt(idx)
        guardarTodos(ctx, lista)
        return true
    }


    fun alternarFavorito(ctx: Context, n: Noticia): Boolean {
        val estaba = esFavorito(ctx, n)
        if (estaba) quitarFavorito(ctx, n) else agregarAFavoritos(ctx, n)
        return !estaba
    }

}