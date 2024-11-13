package com.lucia.projecto01_final

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MyViewModel : ViewModel(){

    val estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    /**
     *
     */
    fun manejarEstados(estado: Estados): Unit {
        when(estado){
            Estados.INICIO -> null //En espera de accion del jugador
            Estados.GENERANDO -> generarSecuencia()
            Estados.MOSTRANDO -> null // toastSecuencia(contexto)
            Estados.ADIVINANDO -> null //En espera de accion del jugador
            Estados.COMPROBANDO -> comprovarAdivinacion()
            Estados.ACTAULIZANDO_PERDIDO -> null // resetearRonda();resetearSecuencias(true)
            Estados.ACTAULIZANDO_GANADO -> null // incrementarRonda();resetearSecuencias(false);setNuevoRecord()
        }
    }

    /**
     * Añade a la secuencia de colores un numero del 1 al 4 generado aleatoriamente
     */
    fun generarSecuencia(): Unit {
        Datos.secuenciaMaquina.add(Random.nextInt(4) + 1)
        Log.d("BotonCrearClick", Datos.secuenciaMaquina.toString())
        estadoLiveData.value = Estados.MOSTRANDO

    }

    fun añadirColorSecuenciaJugador(numColor: Int): Unit {
        Datos.secuenciaJugador.add(numColor)
    }

    /**
     * Vacia las secuencias de colores creadas por el usuario y el propio programa
     */
    fun resetearSecuencias(resetSMaquina: Boolean): Unit {
        Datos.secuenciaJugador.clear()
        if (resetSMaquina == true){
            Datos.secuenciaMaquina.clear()
            Log.d("EstadoRonda", "Perdida")
        }else{
            Log.d("EstadoRonda", "Ganada")
        }
    }

    /**
     * Crea un Toast a partir de la secuencia de colores generada por la aplicacion
     * @param contexto Contexto para mostrar el Toast
     */
    fun toastSecuencia(contexto: Context): Unit {
        val toast = Toast.makeText(contexto, Datos.secuenciaMaquina.toString(), Toast.LENGTH_LONG)
        toast.show()
        estadoLiveData.value = Estados.ADIVINANDO
    }

    /**
     * Compara el último elemento de la secuencia del jugador con el correspondiente en la secuencia generada
     * @return `true` si el último elemento coincide, `false` en caso contrario
     */
    fun compararSecuencias(): Boolean {
        val indice = Datos.secuenciaJugador.size - 1
        return Datos.secuenciaJugador[indice] == Datos.secuenciaMaquina[indice]
    }

    /**
     * Comprueba que la secuencia del jugador sea del mismo tamaño a la secuencia maquina
     * @return `true` si tienen el mismo tamaño, `false` en caso contrario
     */
    fun comprobarRondaTerminada(): Boolean {
        return Datos.secuenciaJugador.size == Datos.secuenciaMaquina.size
    }

    /**
     * Incrementa el numero de rondas superadas
     */
    fun incrementarRonda() {
        Datos.rondasConsecutivas ++
    }

    /**
     * Devuelve a cero el numero de rondas superadas
     */
    fun resetearRonda() {
        Datos.rondasConsecutivas = 0
    }

    fun setNuevoRecord() {
        if(Datos.record.numRondas < Datos.rondasConsecutivas){
            Datos.record.numRondas = Datos.rondasConsecutivas
        }
    }

    fun comenzarPartida(contexto: Context) {
        generarSecuencia()
        toastSecuencia(contexto);
    }

    /**
     * Controla las acciones a realizar segun el estado de la ronda actual
     */
    fun comprovarAdivinacion(): Unit {

        Log.d("ComprobacionSecuencia",compararSecuencias().toString())
        if (compararSecuencias()) {
            Log.d("ComprobacionTerminarRonda",comprobarRondaTerminada().toString())
            if(comprobarRondaTerminada()){
                incrementarRonda()
                resetearSecuencias(false)
                setNuevoRecord()
                estadoLiveData.value = Estados.INICIO
            }
        }else{
            resetearRonda()
            resetearSecuencias(true)
            estadoLiveData.value = Estados.INICIO
        }
    }
}