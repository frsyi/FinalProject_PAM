package com.example.finalproject_pam.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MahasiswaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMahasiswa(mahasiswa: Mahasiswa)

    @Update
    suspend fun updateMahasiswa(mahasiswa: Mahasiswa)

    @Delete
    suspend fun deleteMahasiswa(mahasiswa: Mahasiswa)

    @Query("SELECT * FROM tblMahasiswa WHERE id = :mahasiswaId")
    fun getMahasiswaById(mahasiswaId: Int): Flow<Mahasiswa>

    @Query("SELECT * from tblMahasiswa ORDER BY nama ASC")
    fun getAllMahasiswa(): Flow<List<Mahasiswa>>
}

@Dao
interface TugasDao {
    @Insert
    suspend fun insertTugas(tugas: Tugas)

    @Update
    suspend fun updateTugas(tugas: Tugas)

    @Delete
    suspend fun deleteTugas(tugas: Tugas)

    @Query("SELECT * FROM tblTugas WHERE mahasiswaId = :mahasiswaId")
    fun getAllTugasByMahasiswaId(mahasiswaId: Int): Flow<List<Tugas>>

    @Query("SELECT * FROM tblTugas WHERE id = :tugasId")
    fun getTugasById(tugasId: Int): Flow<Tugas?>
}