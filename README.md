# AlTime
Al time es una aplicacion movil con el proposito de incentivarla concentracion y la motivacion de los estudiantes universitarios a la hora de realizar sus actividad des academicas, ademas de permitirle a los profesores entablar una comunicacion mas clara con los estudiantes y un conocimiento mas amplio del rendimiento de los mismo a partir de su plataforma web, en esta documentacion encontraras los elementos de la plataforma mobil.


## Clases
Todas las clases de Java presentes en la applicacion tienen la funcion de permitir las operaciones durante la aplicacion, solo hay presentes 4 clases con los nombre de Usuario, Evento, Noticia y Noticia 2, que cumplen funciones relacionadas a la subida de informacion a Firebase, 2 clases enfocadas a las operaciones en las 2 Activities de la App (LoginActivity.java y MainActivity.java) y las demas eran enfocadas al uso de los Fragments (IntroFragment, LogicaFragment, etc).


## Metodos
### OnCreateView
A lo largo de todos los Fragments se presentan los metodos OnCreateView para inicializacion de variables relacionadas a la visulizacion al crearse el Frgment.

### OnCreate
En los Activities y algunos Fragmets se presenta el metodo en OnCreate para la inicializacion de variables.

### barraProgreso
Este metodo cumple la funcion de realizar las operaciones que involucran las barras de felicidad y de energia de la mascota virtual.

### barraDeNavegacion
Este metodo cumple la funcion de realizar los cambios de fragment en el momento de oprimir la barra de navegacion inferior

