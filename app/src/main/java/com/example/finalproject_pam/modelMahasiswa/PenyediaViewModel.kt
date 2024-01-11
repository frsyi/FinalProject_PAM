package com.example.finalproject_pam.modelMahasiswa

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.finalproject_pam.AplikasiTugasMahasiswa
import com.example.finalproject_pam.modelTugas.DetailTugasViewModel
import com.example.finalproject_pam.modelTugas.EditTugasViewModel
import com.example.finalproject_pam.modelTugas.EntryTugasViewModel
import com.example.finalproject_pam.modelTugas.HomeTugasViewModel

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomeMahasiswaViewModel(aplikasiTugasMahasiswa().container.repositoryMahasiswa)
        }
        initializer {
            EntryMahasiswaViewModel(aplikasiTugasMahasiswa().container.repositoryMahasiswa)
        }
        initializer {
            DetailsMahasiswaViewModel(
                createSavedStateHandle(),
                aplikasiTugasMahasiswa().container.repositoryMahasiswa
            )
        }
        initializer {
            EditMahasiswaViewModel(
                createSavedStateHandle(),
                aplikasiTugasMahasiswa().container.repositoryMahasiswa
            )
        }

        initializer {
            HomeTugasViewModel(
                createSavedStateHandle(),
                aplikasiTugasMahasiswa().container.repositoryTugas)
        }
        initializer {
            EntryTugasViewModel(aplikasiTugasMahasiswa().container.repositoryTugas)
        }
        initializer {
            DetailTugasViewModel(
                createSavedStateHandle(),
                aplikasiTugasMahasiswa().container.repositoryTugas
            )
        }
        initializer {
            EditTugasViewModel(
                createSavedStateHandle(),
                aplikasiTugasMahasiswa().container.repositoryTugas
            )
        }
    }
}

fun CreationExtras.aplikasiTugasMahasiswa(): AplikasiTugasMahasiswa =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiTugasMahasiswa)
