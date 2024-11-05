package com.lucia.projecto01_final

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun UI(miViewModel: MyViewModel) {
    SimonDice(miViewModel)
}

@Composable
fun SimonDice(miViewModel: MyViewModel) { // ToDo: Mover variables

    var botonActual by remember { mutableStateOf("") }
    val contexto = LocalContext.current

    var habilitarBtnColor by remember { mutableStateOf(false) }
    var habilitarBtnComenzar by remember { mutableStateOf(true) }

    fun ctrHabilitarBtn(): Unit {
        habilitarBtnColor = !habilitarBtnColor
        habilitarBtnComenzar = !habilitarBtnComenzar
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {

        Text(
            text = "Ronda: ${Datos.rondasConsecutivas} | Record: ${Datos.record.numRondas}",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CrearBotonColor(
                miViewModel = miViewModel,
                color = Colors.ROJO,
                enabled = habilitarBtnColor,
                onClick = { color -> botonActual = color.nom }
            )
            CrearBotonColor(
                miViewModel = miViewModel,
                color = Colors.AZUL,
                enabled = habilitarBtnColor,
                onClick = { color -> botonActual = color.nom }
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CrearBotonColor(
                miViewModel = miViewModel,
                color = Colors.VERDE,
                enabled = habilitarBtnColor,
                onClick = { color -> botonActual = color.nom }
            )
            CrearBotonColor(
                miViewModel = miViewModel,
                color = Colors.AMARILLO,
                enabled = habilitarBtnColor,
                onClick = { color -> botonActual = color.nom }
            )

        }

        Text(
            text = "Boton: ${Datos.secuenciaJugador.lastOrNull()?.toString() ?: " "} - $botonActual",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally))

        TextButton(
            onClick = {
                ctrHabilitarBtn()
                miViewModel.generarSecuencia()
                miViewModel.toastSecuencia(contexto);
            },
            enabled = habilitarBtnComenzar,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)) {
            Text(text = "Comenzar")
        }
    }
}

/**
 * Crea un boton apartir de su color correspondiente
 * @param color
 * @param secuenciaJugador
 * @param record
 * @param
 */
@Composable
fun CrearBotonColor(miViewModel: MyViewModel,color: Colors,enabled: Boolean, onClick: (Colors) -> Unit) {
    Button(
        onClick = {
            miViewModel.añadirColorSecuenciaJugador(color.num)
            onClick(color)
            Log.d("BotonColorClick", color.nom)
            miViewModel.estadoRonda()
        },
        colors = ButtonDefaults.buttonColors(
            when (color) {
                Colors.ROJO -> Color.Red
                Colors.VERDE -> Color.Green
                Colors.AMARILLO -> Color.Yellow
                Colors.AZUL -> Color.Blue
            }
        ),
        enabled = enabled,
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(5.dp)
    ) {}
}