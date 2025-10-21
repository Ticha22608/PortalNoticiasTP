package com.semlengtp.portalnoticias
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuarios_entity WHERE nombre = :nombre LIMIT 1")
    fun encontrarNombre(nombre: String): Usuario?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(usuario: Usuario): Long

    @Query("SELECT * FROM usuarios_entity WHERE nombre = :nombre AND contrasena = :contrasena LIMIT 1")
    fun encontrarExacto(nombre: String, contrasena: String): Usuario?
}