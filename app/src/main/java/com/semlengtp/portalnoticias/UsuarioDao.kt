package com.semlengtp.portalnoticias
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios_entity WHERE nombre = :nombre")
    suspend  fun obtenerPorNombre(nombre:String):Usuario?

    @Insert
     suspend fun insertar(Usuario:Usuario)
}