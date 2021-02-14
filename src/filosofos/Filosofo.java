// Clase Activa: Thread
package filosofos;

import java.awt.Color;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextField;
import logica.Principal;
import utilidades.Log;
import vistas.Clase10Control;

/**
 * Hilo Filosofo: se ejecuta de manera concurrente gestionados por monitores ,
 * La finalidad del programa es crear un algoritmo que permita que los filosofos coman y piensen,
 * para ello, el problema se resuelve que siempre haya un filosofo como minimo comiendo,
 * lo ideal que haya dos. 
 * El algoritmo tiene un recurso compartido, el portero, el cual deja pasar a todos los filosofos
 * menos a uno (n-1) y despues otro recurso, el tenedor que es necesario tener por duplicado para
 * que cada filosofo pueda comer. Adeas el algoritmo se completa creando una regla en la que si un
 * filosofo no consigue el segundo tenedor en x tiempo aleatorio, este debe abandonar su turno de comida,
 * salir y ponerse a la cola.
 */

public class Filosofo extends Thread {
    // Variable para generar numeros aleatorios:
    private Random random = new Random();
    // Variable para la ID del Filosofo:
    private int id;
    // Variables para los tenedores:
    private Tenedor izqda, dcha;
    // Variable para el comensal:
    private Portero_del_Comedor comensal;
    // Variables para los elementos graficos:
    private JLabel jLabel_F, jLabel_T_dcha, jLabel_T_izqda;
    private Log log; // Para escribir en el Log
    private JTextField jTextField_C; // Contador de comidas
    // Variable publica y estatica para que se pueda detener el metodo run() de esta clase:
    public static boolean finalizado = false;
    
    /**
     * Esta clase pone en marcha los hilos Filosofos
     * Metodo run() del Thread
     */
    public Filosofo(int id, Tenedor dcha, Tenedor izqda, Portero_del_Comedor comensal, JLabel jLabel_F, 
            JLabel jLabel_T_dcha, JLabel jLabel_T_izqda, Log log, JTextField jTextField_C){
        // Se asignan los valores recibidos a las variables
        this.id = id;
        this.dcha = dcha;
        this.izqda = izqda;
        this.comensal = comensal;
        this.jLabel_F = jLabel_F;
        this.jLabel_T_dcha = jLabel_T_dcha;
        this.jLabel_T_izqda = jLabel_T_izqda;
        this.log = log; 
        this.jTextField_C = jTextField_C;
    }
    
    /**
     * Metodo que se ejecuta indefinidamente, una por cada hilo creado en {@link Principal}
     */
    @Override
    public void run(){
        while(true){ // Se repite infinitamente While
            
            try { 
                // Obtener el comensal para poder comer:
                comensal.cogerComensal(id, log);
                    this.jLabel_F.setBackground(Color.PINK); // Componente grafico
                // Obtener el Tenedor Derecho:
                dcha.cogerTenedor(id, log);
                    this.jLabel_F.setBackground(Color.CYAN); // Componente grafico
                    this.jLabel_T_dcha.setBackground(Color.BLUE); // Componente grafico
                    
                // Obtener el Tenedor Izquierdo:
                if (!izqda.cogerTenedorIzqdo(id, log)){
                    // -----------------------------------------------------------------------------------------------------------------
                    // Si no se consigue el izquierdo: el filosofo tendra que volver a casilla de salida y volver a obtener el comensal:
                    System.out.println("El Filosofo " + (id+1) + " tendra que soltar el tenedor " + (id+1) + " por exceso de tiempo y salir a pensar.");
                    // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface grafica:
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filosofo " + (id+1) + " tendra que soltar el tenedor " + (id+1) + " por exceso de tiempo y salir a pensar.");
                    // Como no ha conseguido el Tenedor izquierdo suelta el derecho
                    dcha.soltarTenedor(id, log);
                        this.jLabel_T_dcha.setBackground(Color.LIGHT_GRAY); // Componente grafico
                    // Como no ha conseguido el Tenedor izquierdo suelta el comensal
                    comensal.soltarComensal(id, log);
                    // Y ahora el Filosofo piensa 
                    System.out.println("El Filosofo " + (id+1) + " esta pensando.");
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filosofo " + (id+1) + " esta pensando.");
                    try {
                        // El tiempo que tarda el filosofo en pensar, entre 100 y 1000 milisegundos:
                        Filosofo.sleep(random.nextInt(1000) + 100);
                    } catch (InterruptedException ex) {
                        System.out.println("Error. Descripcion: " + ex.toString());
                        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog("\n\n Error. Descripcion: " + ex.toString() + "\n\n");
                    }
                    // Fin de Ahora el Filosofo piensa 
                    continue; // Se vuelve a poner en la casilla de salida y volver a obtener el comensal.
                    // -----------------------------------------------------------------------------------------------------------------
                }
                    // Si ha conseguido el Tenedor Izquierdo. El filosofo sigue adelante:
                    this.jLabel_T_izqda.setBackground(Color.BLUE); // Componente grafico
                    // Y ahora el Filosofo come =========================================================================================
                        this.jLabel_F.setBackground(Color.ORANGE); // Componente grafico
                        this.jLabel_F.setForeground(Color.BLUE); // Componente grafico
                    jTextField_C.setText(" " + (++Clase10Control.filoCount[id])); // Su contador de comidas incrementa una unidad.
                    System.out.println("El Filosofo " + (id+1) + " esta comiendo.");
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filosofo " + (id+1) + " esta comiendo.");
                    // Simular el tiempo que tarda el filosofo en comer, entre 0.5 y 1 segundos:
                    try {
                        sleep(random.nextInt(1000) + 500);
                    } catch (InterruptedException ex) {
                        System.out.println("Error. Descripcion: " + ex.toString());
                        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog("\n\n Error. Descripcion: " + ex.toString() + "\n\n");
                    } // Fin de Simular el tiempo que tarda el filosofo en comer, entre 0.5 y 1 segundos
                        this.jLabel_F.setBackground(Color.WHITE); // Componente grafico
                        this.jLabel_F.setForeground(Color.BLACK); // Componente grafico
                    // Fin de Ahora el Fisolofo come ====================================================================================
                // Suelta el Tenedor izquierdo:
                izqda.soltarTenedor(id, log);
                    this.jLabel_T_izqda.setBackground(Color.LIGHT_GRAY); // Componente grafico
                // Suelta el Tenedor derecho:
                dcha.soltarTenedor(id, log);
                    this.jLabel_T_dcha.setBackground(Color.LIGHT_GRAY); // Componente grafico
                // Suelta el comensal:
                comensal.soltarComensal(id, log);
                // Ahora el Fisolofo piensa *********************************************************************************************
                System.out.println("El Filosofo " + (id+1) + " esta pensando.");
                if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filosofo " + (id+1) + " esta pensando.");
                // El tiempo que tarda el Fisolofo en pensar, entre 100 y 1000 milisegundos:
                try {
                    Filosofo.sleep(random.nextInt(1000) + 100);
                } catch (InterruptedException ex) {
                    System.out.println("Error. Descripcion: " + ex.toString());
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog("\n\n Error. Descripcion: " + ex.toString() + "\n\n");
                }
                // Fin de Ahora el Fisolofo piensa **************************************************************************************
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                System.err.println("Se ha producido un error. Descripcion: " + ex.toString());
                try {
                    if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog("\n\n Se ha producido un error. Descripcion: " + ex.toString() + "\n\n");
                } catch (InterruptedException ex1) {
                    Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex1);
                }
            } // Fin del try / catch
            
            if(finalizado){ // Si se ha pulsado el boton en la interface de 'Pausar' (public static boolean finalizado = true):
               break; // Se sale
            }
            
        }  // Fin de Se repite infinitamente While

        // Se ha pulsado el boton de la interface 'Pausar' (public static boolean finalizado = true):
        System.out.println("La cena ha terminado para todos: El Filosofo " + (id+1) +" se ha puesto a pensar.\n\nPulsar Iniciar para continuar.\n\n");
        try {
            if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" La cena ha terminado para todos: El Filosofo " + (id+1) +" se ha puesto a pensar.\n\n Pulsar Iniciar para continuar.\n\n");
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
