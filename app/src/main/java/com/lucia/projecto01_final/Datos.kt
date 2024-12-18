package com.lucia.projecto01_final

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData

/**
 * Colores del juego is sus valores correspondientes
 */
enum class Colors (val num: Int, var nom: String, val color: Color, val color_suave: Color) {
    ROJO(1,"rojo",Color.Red,Color(0xFFFEA9A9)),
    VERDE(2,"verde",Color.Green,Color(0xFFABFEA9)),
    AMARILLO(3,"amarillo",Color.Yellow,Color(0xFFFDFEA9)),
    AZUL(4,"azul",Color.Blue,Color(0xFFA9BEFE));
}

/**
 * Estados posibles del juego
 */
enum class Estados(val btnComenzar_activo: Boolean, val btnColor_activo: Boolean) {
    INICIO(btnComenzar_activo = true, btnColor_activo = false),
    GENERANDO(btnComenzar_activo = false, btnColor_activo = false),
    MOSTRANDO(btnComenzar_activo = false, btnColor_activo = false),
    ADIVINANDO(btnComenzar_activo = false, btnColor_activo = true),
    COMPROBANDO(btnComenzar_activo = false, btnColor_activo = false),
    ACTAULIZANDO_PERDIDO(btnComenzar_activo = false, btnColor_activo = false),
    ACTAULIZANDO_GANADO(btnComenzar_activo = false, btnColor_activo = false)
}

/**
 * Varios datos del programa
 */
object Datos {
    val rondaLiveData: MutableLiveData<Int> = MutableLiveData(0)

    var secuenciaMaquina = mutableListOf<Int>() // secuencia generada por el programa
    var secuenciaJugador = mutableListOf<Int>() // secuencia generada por el jugador

    var rondasConsecutivas = 0

    data class Record(var numRondas: Int = 0) {}
    var record = Record()
}