package com.lucia.projecto01_final

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lucia.projecto01_final.ui.theme.Projecto01FinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val miViewModel: MyViewModel = MyViewModel()

        /**
         * Lanza los metodos especificos para cada estado cuando detecta un cambio de estado
         */
        fun manejarEstados(estado: Estados): Unit {
            when (estado) {
                Estados.INICIO -> null     // En espera de acción del jugado
                Estados.GENERANDO -> {
                    miViewModel.generarSecuencia()
                    miViewModel.estadoLiveData.value = Estados.MOSTRANDO }
                Estados.MOSTRANDO -> {
                    miViewModel.toastSecuencia(this)
                    miViewModel.mostrarSecuencia()
                    miViewModel.estadoLiveData.value = Estados.ADIVINANDO }
                Estados.ADIVINANDO -> null    // En espera de acción del jugador
                Estados.COMPROBANDO -> miViewModel.comprovarAdivinacion()
                Estados.ACTAULIZANDO_PERDIDO -> {
                    miViewModel.resetearRonda()
                    miViewModel.resetearSecuencias(resetSMaquina = true)
                    miViewModel.estadoLiveData.value = Estados.INICIO }
                Estados.ACTAULIZANDO_GANADO -> {
                    miViewModel.incrementarRonda()
                    miViewModel.resetearSecuencias(resetSMaquina = false)
                    miViewModel.setNuevoRecord()
                    miViewModel.estadoLiveData.value = Estados.INICIO }
            }
        }

        miViewModel.estadoLiveData.observe(this) { estado ->
            manejarEstados(estado)
        }

        enableEdgeToEdge()
        setContent {
            Projecto01FinalTheme {
                UI(miViewModel)
            }
        }
    }
}
