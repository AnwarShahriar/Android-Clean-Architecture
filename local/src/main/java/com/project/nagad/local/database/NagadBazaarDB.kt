package com.project.nagad.local.database

import android.content.Context
import androidx.annotation.NonNull
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.project.nagad.local.model.UserInfoLocal

@Database(
    entities = [UserInfoLocal::class],
    version = 1,
    exportSchema = false
)
abstract class NagadBazaarDB : RoomDatabase() {

    companion object {
        private val LOCK = Any()
        private const val DATABASE_NAME = "nagad_bazaar.db"
        @Volatile
        private var INSTANCE: NagadBazaarDB? = null

        fun getInstance(@NonNull context: Context): NagadBazaarDB {

            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            NagadBazaarDB::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    abstract fun getUserInfoDao(): UserInfoDAO

}