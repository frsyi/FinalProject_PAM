package com.example.finalproject_pam.repository

import com.example.finalproject_pam.data.Mahasiswa
import com.example.finalproject_pam.data.MahasiswaDao
import com.example.finalproject_pam.data.Tugas
import com.example.finalproject_pam.data.TugasDao
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

class OfflineRepositoryTugas(private val tugasDao: TugasDao) : RepositoryTugas {

    override fun getAllTugasStream(mahasiswaId: Int): Flow<List<Tugas>> = tugasDao.getAllTugasByMahasiswaId(mahasiswaId)

    override fun getTugasStream(tugasId: Int): Flow<Tugas?> {
        return tugasDao.getTugasById(tugasId)
    }

    override suspend fun insertTugas(tugas: Tugas) = tugasDao.insertTugas(tugas)

    override suspend fun deleteTugas(tugas: Tugas) = tugasDao.deleteTugas(tugas)

    override suspend fun updateTugas(tugas: Tugas) {
        tugasDao.updateTugas(tugas)
    }
}