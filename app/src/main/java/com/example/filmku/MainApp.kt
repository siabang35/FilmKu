package com.example.filmku

import android.app.Application
import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.filmku.di.appModules
import org.koin.core.context.startKoin
import com.example.filmku.di.networkModules
import com.example.filmku.di.repositoryModules
import com.example.filmku.di.viewModelModules
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inisialisasi Koin untuk manajemen dependensi di seluruh aplikasi
        startKoin {
            modules(
                listOf(
                    appModules,
                    viewModelModules,
                    networkModules,
                    repositoryModules
                )
            )
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set tampilan utama aplikasi menggunakan Compose
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    // Gantilah konten Composable ini dengan tampilan sesungguhnya aplikasi Anda
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        // Contoh penggunaan BasicTextField, gantilah sesuai kebutuhan
        BasicTextField(
            value = remember { mutableStateOf("Your app content here") }.value,
            onValueChange = { /*TODO: Implementasikan aksi yang sesuai*/ }
        )
    }
}

@Preview
@Composable
fun AppPreview() {
    // Tampilkan pratinjau dari tampilan utama aplikasi
    AppContent()
}
