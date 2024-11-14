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
     * Añade a la secuencia de colores un numero del 1 al 4 generado aleatoriamente
     */
    fun generarSecuencia(): Unit {
        Datos.secuenciaMaquina.add(Random.nextInt(4) + 1)
        Log.d("ESTADO-GENERANDO", "Secuencia maquina: "+Datos.secuenciaMaquina.toString())
    }

    fun añadirColorSecuenciaJugador(numColor: Int): Unit {
        Datos.secuenciaJugador.add(numColor)
        Log.d("ESTADO-ADIVINANDO", "Boton pulsado: "+numColor)
    }

    /**
     * Vacia las secuencias de colores creadas por el usuario y el propio programa
     */
    fun resetearSecuencias(resetSMaquina: Boolean): Unit {
        Datos.secuenciaJugador.clear()
        if (resetSMaquina == true){
            Datos.secuenciaMaquina.clear()
            Log.d("ESTADO-ACTAULIZANDO_PERDIDO", "Reseteando la secuencia maquina")
            Log.d("ESTADO-ACTAULIZANDO_PERDIDO", "Secuencia maquina: "+Datos.secuenciaMaquina.toString())
        }else{
            Log.d("ESTADO-ACTAULIZANDO_GANADO", "Secuencia maquina: "+Datos.secuenciaMaquina.toString())
        }
    }

    /**
     * Crea un Toast a partir de la secuencia de colores generada por la aplicacion
     * @param contexto Contexto para mostrar el Toast
     */
    fun toastSecuencia(contexto: Context): Unit {
        val toast = Toast.makeText(contexto, Datos.secuenciaMaquina.toString(), Toast.LENGTH_LONG)
        toast.show()
        Log.d("ESTADO-MOSTRANDO", "Se ha mostrado la secuencia")
    }

    /**
     * Compara el último elemento de la secuencia del jugador con el correspondiente en la secuencia generada
     * @return `true` si el último elemento coincide, `false` en caso contrario
     */
    fun compararSecuencias(): Boolean {
        val indice = Datos.secuenciaJugador.size - 1
        var comprobacion = Datos.secuenciaJugador[indice] == Datos.secuenciaMaquina[indice]
        Log.d("ESTADO-COMPROBANDO", "Se ha perdido la partida? "+!comprobacion)
        return comprobacion
    }

    /**
     * Comprueba que la secuencia del jugador sea del mismo tamaño a la secuencia maquina
     * @return `true` si tienen el mismo tamaño, `false` en caso contrario
     */
    fun comprobarRondaTerminada(): Boolean {
        var comprobacion = Datos.secuenciaJugador.size == Datos.secuenciaMaquina.size
        Log.d("ESTADO-COMPROBANDO", "Se ha acabado la ronda? "+comprobacion)
        return comprobacion
    }

    /**
     * Incrementa el numero de rondas superadas
     */
    fun incrementarRonda(): Unit {
        Datos.rondasConsecutivas ++
        Log.d("ESTADO-ACTAULIZANDO_GANADO", "Se ha aumentado el numero de la ronda")
        Log.d("ESTADO-ACTAULIZANDO_GANADO", "Ronda: "+Datos.rondasConsecutivas)
    }

    /**
     * Devuelve a cero el numero de rondas superadas
     */
    fun resetearRonda(): Unit {
        Datos.rondasConsecutivas = 0
        Log.d("ESTADO-ACTAULIZANDO_PERDIDO", "Reseteando el numero de la ronda")
        Log.d("ESTADO-ACTAULIZANDO_PERDIDO", "Ronda: "+Datos.rondasConsecutivas)
    }

    /**
     * Aumenta el record de darse el caso que se haya superado
     */
    fun setNuevoRecord(): Unit {
        if(comprobarRecord()){
            Datos.record.numRondas = Datos.rondasConsecutivas
            Log.d("ESTADO-ACTAULIZANDO_GANADO", "Record: "+Datos.record.numRondas)
        }
    }

    /**
     * Comprueba que si se ha superado el recod de rondas consecutivas
     * @return `true` si se ha superado el record, `false` en caso contrario
     */
    fun comprobarRecord(): Boolean{
        var comprobacion = Datos.record.numRondas < Datos.rondasConsecutivas
        Log.d("ESTADO-ACTAULIZANDO_GANADO", "Se ha superado el record? "+comprobacion)
        return comprobacion
    }

    /**
     * Controla los cambios de estado a realizar segun el imput del usuario
     */
    fun comprovarAdivinacion(): Unit {
        if (compararSecuencias()) {
            if (comprobarRondaTerminada()) {
                estadoLiveData.value = Estados.ACTAULIZANDO_GANADO
            }else{
                estadoLiveData.value = Estados.ADIVINANDO
            }
        } else {
            estadoLiveData.value = Estados.ACTAULIZANDO_PERDIDO
        }
    }


}