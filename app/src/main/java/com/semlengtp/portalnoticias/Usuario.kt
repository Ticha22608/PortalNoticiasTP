package com.semlengtp.portalnoticias
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "usuarios-entity")
data class Usuario(
    @ColumnInfo(name="Nombre") var nombre: String,
    @ColumnInfo (name="Contraseña") var contraseña: String
) {
    @PrimaryKey(autoGenerate = true) val id: Int = 0
}
