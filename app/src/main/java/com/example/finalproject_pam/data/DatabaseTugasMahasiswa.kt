package com.example.finalproject_pam.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Mahasiswa::class, Tugas::class], version = 1, exportSchema = false)
abstract class DatabaseTugasMahasiswa : RoomDatabase() {
    abstract fun mahasiswaDao(): MahasiswaDao
    abstract fun tugasDao(): TugasDao

    companion object{
        @Volatile
        private var Instance: DatabaseTugasMahasiswa? = null

        fun getDatabase(context: Context): DatabaseTugasMahasiswa{
            return (Instance?: synchronized(this){
                Room.databaseBuilder(context,
                    DatabaseTugasMahasiswa::class.java,
                    "tugasMahasiswa_database")
                    .build().also { Instance = it }
            })
        }
    }
}