package com.example.finalproject_pam.ui.halamanMahasiswa

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.modelMahasiswa.DetailsMahasiswaViewModel
import com.example.finalproject_pam.modelMahasiswa.PenyediaViewModel
import com.example.finalproject_pam.modelTugas.HomeTugasViewModel
import com.example.finalproject_pam.navigasi.DestinasiNavigasi

object DetailMahasiswaDestination : DestinasiNavigasi {
    override val route = "item_detail_mahasiswa"
    override val titleRes = R.string.detail_mahasiswa
    const val mahasiswaIdArg = "itemId"
    val routeWithArgs = "$route/{$mahasiswaIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailMahasiswaScreen(
    navigateToEditItem: (Int) -> Unit,
    navigateBack: () -> Unit,
    navigateToEntryTugas: () -> Unit,
    onDetailTugasClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: DetailsMahasiswaViewModel = viewModel(factory = PenyediaViewModel.Factory),
    viewModelTugas: HomeTugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val uiState = viewModel.uiState.collectAsState()
//    val uiTugasState = viewModelTugas.uiTugasState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
}