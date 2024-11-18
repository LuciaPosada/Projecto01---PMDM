package com.lucia.projecto01_final

import androidx.compose.ui.graphics.Color

/**
 *
 */
enum class Dificultades (val velocidad : Int) {
    FACIL(800),
    NORMAL(500),
    DIFICIL(200)
}

/**
 * Colores del juego is sus valores correspondientes
 */
enum class Colors (val num: Int, var nom: String, val color: Color, val color_suave: Color = Color.Transparent) {
    ROJO(1,"rojo",color = Color.Red),
    VERDE(2,"verde",color = Color.Green),
    AMARILLO(3,"amarillo",color = Color.Yellow),
    AZUL(4,"azul",color = Color.Blue);
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
    var secuenciaMaquina = mutableListOf<Int>() // secuencia generada por el programa
    var secuenciaJugador = mutableListOf<Int>() // secuencia generada por el jugador

    var rondasConsecutivas = 0

    data class Record(var numRondas: Int = 0) {}
    var record = Record()
}