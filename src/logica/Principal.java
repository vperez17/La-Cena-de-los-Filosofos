
package logica;

import utilidades.Log;
import filosofos.Filosofo;
import filosofos.Tenedor;
import filosofos.Portero_del_Comedor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import vistas.Clase10Control;
import vistas.Vista10Control;

/**
 * Esta clase genera las instancias de los 5 hilos Filosofos,
 * Estos 5 hilos se ejecutan de manera concurrente gestionados por monitores ,
 * La finalidad del programa es crear un algoritmo que permita que los filosofos coman y piensen,
 * para ello, el problema se resuelve que siempre haya un filosofo como mínimo comiendo,
 * lo ideal que haya dos. 
 * El algoritmo tiene un recurso compartido, el portero, el cual deja pasar a
 * n-1 filosofos y después otro recurso, el tenedor, que es necesario tener por duplicado para
 * que cada filosofo pueda comer. Ademas el algoritmo se completa creando una regla en la que si un
 * filosofo no consigue el segundo tenedor en x tiempo aleatorio, este debe abandonar su turno de comida,
 * salir y ponerse a la cola.
 * Ademas si se selecciona crear un Log en el area de texto de la interface grafica {@link Vista10Control}
 * todos los hilos de manera concurrente tendran que ir escribiendo en el log sus acciones, con lo cual 
 * puede relentizar un poco la ejecucion del programa.
 */
public class Principal {
    
    private JLabel[] jLabel_F = new JLabel[5];
    private JLabel[] jLabel_T = new JLabel[5];
    private JTextField[] jTextField_C = new JTextField[5];

    public Principal(Clase10Control clase10Control) { 
        this.jLabel_F = clase10Control.getjLabel_F();
        this.jLabel_T = clase10Control.getjLabel_T();
        this.jTextField_C = clase10Control.getjTextField_C();
        
        // Se crea el Array para contener las 5 instancias de Tenedores:
        Tenedor[] tenedor = new Tenedor[5];
        // Se crea el Array para contener las 5 instancias de Filosofos:
        Filosofo[] filosofo = new Filosofo[5];
        // Se crea una sola instancia de Portero_del_Comedor:
        Portero_del_Comedor comensal = new Portero_del_Comedor();
        // Se crea una sola instancia de Log:
        Log log = new Log();
        // Se crea la instancia del manejeador de excepciones para los Thread:
        ManejadorExcepciones manejador=new ManejadorExcepciones(log);
        
        // Se crean las 5 instancias de Tenedores:
        for(int i=0; i<tenedor.length; i++){
            tenedor[i] = new Tenedor(i);
        }
        
        // Se crean las 5 instancias de Filosofos:
        for(int i=0; i<filosofo.length; i++){
            /* El filosofo coge el tenedor de la izquierda 
            *  y el de la derecha se contabiliza con el modulo(%)
            *  porque cuando llega a cuatro el siguiente es cero
            */
            // Ahora al filosofo se le pasa: un ID, un tenedor Dercho, un tenedor Izdo, el comensal, los componentes graficos correspondientes y un log
            filosofo[i] = new Filosofo(i, tenedor[i], tenedor[(i+1)%5], comensal, jLabel_F[i], jLabel_T[i], jLabel_T[(i+1)%5], log, jTextField_C[i]);
        }
        
        // Se echa a andar todos los Filosofos:
        for(int i=0; i<filosofo.length; i++){
            filosofo[i].setUncaughtExceptionHandler(manejador);
            filosofo[i].start();
        }
    }

    
}
