package com.lucia.projecto01_final

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp

@Composable
fun UI(miViewModel: MyViewModel) {
    SimonDice(miViewModel)
}

@Composable
fun SimonDice(miViewModel: MyViewModel) {

    var botonActual by remember { mutableStateOf("") }

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
            BotonColor(miViewModel = miViewModel, Colors.ROJO, onClick = { color -> botonActual = color.nom })
            BotonColor(miViewModel = miViewModel, Colors.AZUL, onClick = { color -> botonActual = color.nom })
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            BotonColor(miViewModel = miViewModel, Colors.VERDE, onClick = { color -> botonActual = color.nom })
            BotonColor(miViewModel = miViewModel, Colors.AMARILLO, onClick = { color -> botonActual = color.nom })
        }

        Text(text = "Boton: ${Datos.secuenciaJugador.lastOrNull()?.toString() ?: " "} - $botonActual",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            BotonComenzar(miViewModel)
        }
    }
}

/**
 * Crea un boton para comenzar la ronda
 */
@Composable
fun BotonComenzar(miViewModel: MyViewModel) {

    var _activo by remember { mutableStateOf(miViewModel.estadoLiveData.value!!.btnComenzar_activo) }

    miViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = miViewModel.estadoLiveData.value!!.btnComenzar_activo
    }

    TextButton(
        onClick = {
            miViewModel.estadoLiveData.value = Estados.GENERANDO
        },
        enabled = _activo,
        modifier = Modifier
            .padding(16.dp)) {
        Text(text = "Comenzar")
    }
}

/**
 * Crea un boton apartir de su color correspondiente
 */
@Composable
fun BotonColor(miViewModel: MyViewModel, enum_color: Colors,onClick: (Colors) -> Unit) {

    var _color by remember { mutableStateOf(enum_color.color) }

    var _activo by remember { mutableStateOf(miViewModel.estadoLiveData.value!!.btnColor_activo) }

    val iluminado by miViewModel.iluminadoFlow.collectAsState()

    miViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = miViewModel.estadoLiveData.value!!.btnColor_activo
    }

    LaunchedEffect(iluminado) {
        if(iluminado == enum_color.num){
            _color = enum_color.color_suave // Color iluminado
        }else{
            _color = enum_color.color // Color normal
        }
    }

    Button(
        onClick = {
            onClick(enum_color)
            miViewModel.añadirColorSecuenciaJugador(enum_color.num)
            miViewModel.estadoLiveData.value = Estados.COMPROBANDO
        },
        colors = ButtonDefaults.buttonColors(_color),
        enabled = _activo,
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .padding(5.dp)
    ) {}
}