package com.example.finalproject_pam.repository

import com.example.finalproject_pam.data.Mahasiswa
import com.example.finalproject_pam.data.MahasiswaDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoryMahasiswa(private val mahasiswaDao: MahasiswaDao) : RepositoryMahasiswa {

    override fun getAllMahasiswaStream(): Flow<List<Mahasiswa>> = mahasiswaDao.getAllMahasiswa()

    override fun getMahasiswaStream(id: Int): Flow<Mahasiswa?> {
        return mahasiswaDao.getMahasiswaById(id)
    }

    override suspend fun insertMahasiswa(mahasiswa: Mahasiswa) = mahasiswaDao.insertMahasiswa(mahasiswa)

    override suspend fun deleteMahasiswa(mahasiswa: Mahasiswa) = mahasiswaDao.deleteMahasiswa(mahasiswa)

    override suspend fun updateMahasiswa(mahasiswa: Mahasiswa) {
        mahasiswaDao.updateMahasiswa(mahasiswa)
    }
}