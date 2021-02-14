
package logica;

import utilidades.Log;
import java.util.logging.Level;
import java.util.logging.Logger;
import vistas.Clase10Control;

/**
 * Esta clase maneja las excepciones que puedan ser provocadas por la ejecucion de los hilos
 */
//manejador de excepciones para toda la aplicacion
public class ManejadorExcepciones implements Thread.UncaughtExceptionHandler {
    //implementa el metodo uncaughtException()
    @Override
    public void uncaughtException(Thread t, Throwable e){
        System.out.printf("Thread que lanza la excepcion: \n", t.getName());
        //muestra en consola el hilo que produce la excepcion
        e.printStackTrace();
        //muestra en consola la pila de llamadas
        
        // Anado este codigo para que los saque en el Log de la interface grafica
        if (Clase10Control.getjTextArea_Log()!=null) try {
            log.escribirLog("\n Thread que lanza la excepcion: " + t.getName() + "\n");
            log.escribirLog(e.toString() + "\n\n");
        } catch (InterruptedException ex) {
            Logger.getLogger(ManejadorExcepciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Fin Anado este codigo
    }
    // Anado este codigo: Un Constructor que recibe la clase Log que es la que escribe en la interface grafica

    public ManejadorExcepciones(Log log) {
        this.log = log;
    }
    // Variable que recibe la clase Log
    Log log;
    // Fin Anado este codigo
}
