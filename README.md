# SimonDice

## Diagrama

### Diagrama de Secuencia

![Diagrama Secuencia](/img/diagrama_secuencia.png)

### Diagrama de Estados

![Diagrama Estados](img/digrama_estados.png)

---

## Explicacion de la aplicación

### Base del Programa

Este respositorio continua el programa del respositorio [Proyecto01](https://github.com/LuciaPosada/Proyecto01), donde el programa aun no seguia el modelo MVVVM, debido al cambio a esta arquitectura, decidi crear un nuevo repositorio.

### Datos (Model)

- Enum Colors:
    - Colores utilizados por el juego, relevantes para los botones (graficos), la generacion de secuencias y su muestra por pantalla
 
- Enum Estados:
    - Estados por los que pasa el juego a lo largo de una ronda, relevantes para manajar las acciones del juego y la habilitación de los botones 
       
- Object Datos:
    - Coleccion de todos los datos relevantes para la ejecucion del juego que varian a lo largo de este
