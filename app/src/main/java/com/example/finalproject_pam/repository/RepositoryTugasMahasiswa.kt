package com.example.finalproject_pam.repository

import com.example.finalproject_pam.data.Mahasiswa
import kotlinx.coroutines.flow.Flow

interface RepositoryMahasiswa {
    fun getAllMahasiswaStream(): Flow<List<Mahasiswa>>

    fun getMahasiswaStream(id: Int): Flow<Mahasiswa?>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)
}