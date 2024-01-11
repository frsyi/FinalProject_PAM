package com.example.finalproject_pam.ui.halamanMahasiswa

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject_pam.R
import com.example.finalproject_pam.data.Mahasiswa
import com.example.finalproject_pam.data.Tugas
import com.example.finalproject_pam.modelMahasiswa.DetailsMahasiswaViewModel
import com.example.finalproject_pam.modelMahasiswa.ItemDetailsMahasiswaUiState
import com.example.finalproject_pam.modelMahasiswa.PenyediaViewModel
import com.example.finalproject_pam.modelMahasiswa.toMahasiswa
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
   // val uiTugasState = viewModelTugas.uiTugasState.collectAsState()

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
                    contentDescription = stringResource(R.string.entry_tugas)
                )
            }
        },

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
    Column(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
    ) {
        var deleteConfirmationrequired by rememberSaveable { mutableStateOf(false) }
        ItemDetailsMahasiswa(
            mahasiswa = itemDetailsMahasiswaUiState.detailMahasiswa.toMahasiswa(),
            onEdit = onEdit,
            onDelete = { deleteConfirmationrequired = true },
            modifier = Modifier.fillMaxWidth()
        )

        if (deleteConfirmationrequired) {
            DeleteConfirmationDialog(
                onDeleteConfirm = {
                    deleteConfirmationrequired = false
                    onDelete()
                },
                onDeleteCancel = { deleteConfirmationrequired = false },
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_medium)))
        val uiStateTugas by viewModelTugas.homeTugasUiState.collectAsState()
        BodyHomeTugas(
            itemTugas = uiStateTugas.listTugas,
            modifier = Modifier
                .fillMaxSize(),
            onTugasClick = onDetailClick
        )
    }
}

@Composable
fun ItemDetailsMahasiswa(
    mahasiswa: Mahasiswa,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier, colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium))
        ) {
            ItemDetailsRow(
                labelResID = R.string.namaMhs1,
                itemDetail = mahasiswa.nama,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
            ItemDetailsRow(
                labelResID = R.string.nim1,
                itemDetail = mahasiswa.nim,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
            ItemDetailsRow(
                labelResID = R.string.semester1,
                itemDetail = mahasiswa.semester,
                modifier = Modifier.padding(
                    horizontal = dimensionResource(id = R.dimen.padding_medium)
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = onEdit,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(R.string.edit_mahasiswa)
                    )
                }
                IconButton(
                    onClick = onDelete,
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete)
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemDetailsRow(
    @StringRes labelResID: Int, itemDetail: String, modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = stringResource(labelResID))
        Spacer(modifier = Modifier.weight(1f))
        Text(text = itemDetail, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit, onDeleteCancel: () -> Unit, modifier: Modifier = Modifier
){
    AlertDialog(
        onDismissRequest = { /*Do nothing*/ },
        title = { Text(stringResource(R.string.attention)) },
        text = { Text(stringResource(R.string.delete)) },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = stringResource(R.string.no))
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = stringResource(R.string.yes))
            }
        },
    )
}

@Composable
fun BodyHomeTugas(
    itemTugas: List<Tugas>,
    modifier: Modifier = Modifier,
    onTugasClick: (Int) -> Unit = {}
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        if (itemTugas.isEmpty()){
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListTugas(
                itemTugas = itemTugas,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onTugasClick(it.id) }
            )
        }
    }
}

@Composable
fun ListTugas(
    itemTugas : List<Tugas>,
    modifier: Modifier = Modifier,
    onItemClick: (Tugas) -> Unit
){
    LazyColumn(modifier = Modifier){
        items(items = itemTugas, key = {it.id}){person ->
            DataTugas(
                tugas = person,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(person) }
            )
        }
    }
}

@Composable
fun DataTugas(
    tugas: Tugas,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_Large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = tugas.namaTugas,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
                Text(
                    text = tugas.deadline,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = tugas.detailTugas,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
@Preview
fun DataTugasPreview() {
    val sampleTugas = Tugas(id = 1, mahasiswaId = 1, namaTugas = "PAM", detailTugas = "Membuat operasi CRUD", deadline = "01-01-2025")
    DataTugas(tugas = sampleTugas)
}