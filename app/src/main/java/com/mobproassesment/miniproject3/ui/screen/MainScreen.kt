package com.mobproassesment.miniproject3.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mobproassesment.miniproject3.R
import com.mobproassesment.miniproject3.model.Tanaman
import com.mobproassesment.miniproject3.network.TanamanApi
import com.mobproassesment.miniproject3.ui.theme.Miniproject3Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = viewModel()
    val data by viewModel.data
    val status by viewModel.status.collectAsState()
    when (status) {
        TanamanApi.ApiStatus.LOADING -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        TanamanApi.ApiStatus.SUCCESS -> {
            LazyVerticalGrid(
                modifier = modifier.fillMaxSize().padding(4.dp),
                columns = GridCells.Fixed(2),
            ) {
                items(data) { ListItem(tanaman = it) }
            }
        }
    }
}

@Composable
fun ListItem(tanaman: Tanaman) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .border(1.dp, Color.Gray),
            contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(TanamanApi.getTanamanUrl(tanaman.gambar))
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.gambar, tanaman.nama),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.loading_img),
            error = painterResource(id = R.drawable.broken_img),
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        )
        Column (modifier = Modifier.fillMaxWidth().padding(4.dp)
                .background(Color(red = 0f, green = 0f, blue = 0f, alpha = 0.5f))
                .padding(4.dp)
        ){
            Text(
                text = tanaman.nama,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}
@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    Miniproject3Theme {
        MainScreen()
    }
}