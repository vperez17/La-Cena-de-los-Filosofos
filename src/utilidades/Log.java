
package utilidades;

import vistas.Clase10Control;
import vistas.Vista10Control;

/**
 * Esta clase escribe un log en la interface grafica del programa {@link Vista10Control}
 * Recurso compratido por los hilos Filosofos,
 * Se crea una unica instancia
 */
public class Log {
   /**
    * Este metodo es un monitor al cual van accediendo los hilos para escribir sus acciones
    * en el log del programa, este componente puede ser activado o no desde la interface grafica.
    * 
    * @param log Mensaje que recibe el metodo para que sea annadido al componente grafico en la interface
    * @throws InterruptedException Error y su descripcion
    */
    public synchronized void escribirLog(String log) throws InterruptedException{
        Clase10Control.setjTextArea_Info(log);
    }
    
}
