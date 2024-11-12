package com.lucia.projecto01_final

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lucia.projecto01_final.ui.theme.Projecto01FinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val miViewModel: MyViewModel = MyViewModel()

        miViewModel.estadoLiveData.observe(this) { estado ->
            miViewModel.manejarEstados(estado)
        }

        enableEdgeToEdge()
        setContent {
            Projecto01FinalTheme {
                UI(miViewModel)
            }
        }
    }
}
