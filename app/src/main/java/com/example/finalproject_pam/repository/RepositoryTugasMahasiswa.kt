package com.example.finalproject_pam.repository

import com.example.finalproject_pam.data.Mahasiswa
import com.example.finalproject_pam.data.Tugas
import kotlinx.coroutines.flow.Flow

interface RepositoryMahasiswa {
    fun getAllMahasiswaStream(): Flow<List<Mahasiswa>>

    fun getMahasiswaStream(id: Int): Flow<Mahasiswa?>

    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)

    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)
}

interface RepositoryTugas {
    fun getAllTugasStream(mahasiswaId: Int): Flow<List<Tugas>>

    fun getTugasStream(tugasId: Int): Flow<Tugas?>

    suspend fun insertTugas(tugas: Tugas)

    suspend fun deleteTugas(tugas: Tugas)

    suspend fun updateTugas(tugas: Tugas)
}