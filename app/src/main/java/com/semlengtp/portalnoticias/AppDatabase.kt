package com.semlengtp.portalnoticias

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Usuario::class], version = 1)

abstract class AppDataBase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao

    companion object {
        private var INSTANCIA: AppDataBase? = null
        fun getDatabase(context: Context): AppDataBase {
            if (INSTANCIA == null) {
                synchronized(this){
                 INSTANCIA=  Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,"usuarios_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
        return INSTANCIA!!
    }
}
}
