# SimonDice

Este programa implementa un juego clásico de "Simón Dice". La arquitectura sigue principios de separación de responsabilidades (MVVM).

---

## Diagramas

### Diagrama de Secuencia

![Diagrama Secuencia](/img/diagrama_secuencia.png)

### Diagrama de Estados

![Diagrama Estados](img/digrama_estados.png)

---

## Explicacion de la aplicación

### Base del Programa

Este respositorio continua el programa del respositorio [Proyecto01](https://github.com/LuciaPosada/Proyecto01), donde el programa aun no seguia el modelo MVVVM, debido al cambio a esta arquitectura, decidi crear un nuevo repositorio para continuar el desarrollo de manera mas ordenada.

---

### Datos (Model)

Contiene los datos del programa

<details>
    <summary>Expandir</summary>
<br>

```bash

> enum Colors():
    Colores utilizados por el juego, relevantes para los botones (graficos), la generacion de secuencias y su
    muestra por pantalla
 
> enum Estados():
    Estados por los que pasa el juego a lo largo de una ronda, relevantes para manajar las acciones del juego y
    la habilitación de los botones 
       
> object Datos:
    Coleccion de todos los datos relevantes para la ejecucion del juego que varian a lo largo de este

```

</details>

---
 
### ViewModel

Contiene las funciones del programa + variables para los observers

<details>
    <summary>Expandir</summary>
<br>

```bash

> estadoLiveData:
    Valor para poder observar los cambios de estados del juego

> iluminadoFlow:
    Valor para poder observar llamadas a los botones para que se "iluminen"

> manejarEstados():
    Lanza los metodos especificos para cada estado cuando detecta un cambio de estado
 
> mostrarSecuencia():
    Muestra la secuencia generada "iluminando" los botones

> generarSecuencia():
    Añade a la secuencia de colores un numero del 1 al 4 generado aleatoriamente
 
> añadirColorSecuenciaJugador():
    Añade el identificador de color a la secuencia del jugador
 
> resetearSecuencias():
    Vacia las secuencias de colores creadas por el usuario y el propio programa

> compararSecuencias():
    Compara el último elemento de la secuencia del jugador con el correspondiente en la secuencia generada
 
> comprobarRondaTerminada():
    Comprueba que la secuencia del jugador sea del mismo tamaño a la secuencia maquina

> incrementarRonda(): 
    Incrementa el numero de rondas superadas

> resetearRonda():
    Devuelve a cero el numero de rondas superadas
 
> setNuevoRecord():
    Aumenta el record de darse el caso que se haya superado

> comprobarRecord():
    Comprueba que si se ha superado el recod de rondas consecutivas
 
> comprovarAdivinacion():
    Controla los cambios de estado a realizar segun el imput del usuario
 
> getRonda():
    Getter de la ronda actual

> getRecord():
    Getter del record

```

</details>

---

### UI (View)

Contiene las funciones composables para la interfaz

<details>
    <summary>Expandir</summary>
<br>

```bash

> UI()
    Punto de entrada de la UI

> SimonDice()
    Cuerpo principal de la interfaz
    Contiene:
        - Texto con información sobre la ronda y el récord
        - Botones para cada color (rojo, azul, verde y amarillo)
        - Un botón para comenzar la partida
        - Texto para mostrar qué botón ha sido seleccionado por el jugador

> BotonComenzar()
    Logica y diseño del boton comenzar
    Acciones al pulsar:
        - Cambia el estado del juego a *GENERANDO*, comenzando una nueva ronda
 
> BotonColor()
    Logica y diseño de los botones de colores
    Acciones al pulsar:
        - Cambia el estado del juego a *COMPROBANDO* para evaluar el resultado
        - Reporta el color seleccionado al uno de los Text de la interfaz
        - Añade el color del boton pulsado a la secuencia del jugador

```
    
</details>

---

### Mejoras

El programa requiere hacerle un refractor para modularizar mas la interfaz y modificar algunos metodos para que sean mas versatiles.


