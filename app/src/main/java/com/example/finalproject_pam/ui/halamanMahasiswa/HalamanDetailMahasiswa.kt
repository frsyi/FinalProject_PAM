package com.example.finalproject_pam.ui.halamanMahasiswa

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.modelMahasiswa.DetailsMahasiswaViewModel
import com.example.finalproject_pam.modelMahasiswa.ItemDetailsMahasiswaUiState
import com.example.finalproject_pam.modelMahasiswa.PenyediaViewModel
import com.example.finalproject_pam.modelTugas.HomeTugasViewModel
import com.example.finalproject_pam.navigasi.DestinasiNavigasi
import com.example.finalproject_pam.navigasi.MahasiswaToAppBar
import kotlinx.coroutines.launch

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
    Scaffold(
        topBar = {
            MahasiswaToAppBar(
                title = stringResource(DetailMahasiswaDestination.titleRes),
                canNavigateBack = true,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToEntryTugas,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_Large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.detail_tugas)
                )
            }
        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { navigateToEditItem(uiState.value.detailMahasiswa.id) },
//                shape = MaterialTheme.shapes.medium,
//                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Edit,
//                    contentDescription = stringResource(R.string.edit_mahasiswa)
//                )
//            }
//        }

        modifier = modifier
    ) {innerPadding ->
        ItemDetailsBody(
            itemDetailsMahasiswaUiState = uiState.value,
            onEdit = { navigateToEditItem(uiState.value.detailMahasiswa.id) },
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigateBack()
                }
            },
            onDetailClick = onDetailTugasClick,
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        )
//        val uiStateTugas by viewModelTugas.homeTugasUiState.collectAsState()
//        BodyHomeTugas(
//            itemTugas = uiStateTugas.listTugas,
//            modifier = Modifier
//                .padding(innerPadding)
//                .fillMaxSize(),
//            onTugasClick = onDetailClick
//        )
    }
}

@Composable
private fun ItemDetailsBody(
    itemDetailsMahasiswaUiState: ItemDetailsMahasiswaUiState,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onDetailClick: (Int) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModelTugas: HomeTugasViewModel = viewModel(factory = PenyediaViewModel.Factory)
){

}



