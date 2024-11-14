package com.lucia.projecto01_final

/**
 * Colores del juego is sus valores correspondientes
 */
enum class Colors (val num: Int,var nom: String) {
    ROJO(1,"rojo"),
    VERDE(2,"verde"),
    AMARILLO(3,"amarillo"),
    AZUL(4,"azul");
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