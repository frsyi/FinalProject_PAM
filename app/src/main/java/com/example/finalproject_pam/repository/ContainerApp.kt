package com.example.finalproject_pam.repository

import android.content.Context
import com.example.finalproject_pam.data.DatabaseTugasMahasiswa

interface ContainerApp {
    val repositoryMahasiswa: RepositoryMahasiswa
    val repositoryTugas: RepositoryTugas
}

class ContainerDataApp(private val context: Context) : ContainerApp {

    override val repositoryTugas: RepositoryTugas by lazy {
        OfflineRepositoryTugas(DatabaseTugasMahasiswa.getDatabase(context).tugasDao())
    }

    override val repositoryMahasiswa: RepositoryMahasiswa by lazy {
        OfflineRepositoryMahasiswa(DatabaseTugasMahasiswa.getDatabase(context).mahasiswaDao())
    }
}