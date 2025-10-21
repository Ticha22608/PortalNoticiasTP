
package com.semlengtp.portalnoticias
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "usuarios_entity")
data class Usuario(
    @ColumnInfo(name="nombre") var nombre: String,
    @ColumnInfo (name="contrasena") var contrasena: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)
