
package main;

import filosofos.Filosofo;
import filosofos.Portero_del_Comedor;
import filosofos.Tenedor;
import javax.swing.JFrame;
import vistas.Vista10Control;

/**
 * Programa 2 de la tarea 02 de PSP de DAM, Problema de la cena de los Filosofos,
 * Se resuelve utilizando un Portero del Comedor con n-1 plazas o turnos en relacion al numero
 * de Filosofos y si un filosofo tarda en conseguir el segundo tenedor pierde el turno.
 * 
 * El programa ejecuta los hilos de forma concurrente mediante monitores.
 * 
 * Paquete: {@link vistas} contiene todas las interfaces graficas y sus clases relacionadas con la interface.
 * Paquete: {@link utilidades} contiene todas las clases que aportan utilidad a la ejecucion del programa.
 * Paquete: {@link logica} contiene todas las clases que tiene la estructura principal del programa.
 * Paquete: {@link filosofos} contiene todas las clases realcionadas con los hilos {@link Filosofo}.
 * y con los recursos {@link Portero_del_Comedor} y con los {@link Tenedor}.
 * Paquete: images contiene todas las imagenes del programa.
 * 
 */
public class Main {

    /**
     * @param args No contempla argumentos
     */
    public static void main(String[] args) {
        /**
         * Clase que crea la interface grafica del programa y el arranque del mismo:
         */
        JFrame v10control=new Vista10Control();
    }
    
}
