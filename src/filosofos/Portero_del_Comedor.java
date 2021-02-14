// Clase pasiva: Recurso compartido
package filosofos;

import utilidades.Log;
import vistas.Clase10Control;

/**
 * Recurso compratido por los hilos Filosofos,
 * Se crea una unica instancia con n-1 comensales donde n es el numero de filosofos
 */
public class Portero_del_Comedor {
    private int comensal = 4; // Es el número de comensales total de Filosofos menos 1
    
    /**
     * Monitor para coger un comensal de los 4 y poder seguir el proceso de ejecucion de los Filosofos.
     * 
     * @param id_f ID del filosofo
     * @param log Clase Log para escribir el log en la interface grafica
     * @throws InterruptedException Posibles errores
     */
    public synchronized void cogerComensal(int id_f, Log log) throws InterruptedException{
        while(comensal==0){ // Si no hay comensales libres toca esperar
            this.wait();
        } 
        System.out.println("El Filosofo " + (id_f+1) + " es el comensal " + comensal);
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface grafica:
        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filosofo " + (id_f+1) + " es el comensal " + comensal);
        comensal--; // Conteo de comensales
    }
    
    /**
     * Monitor para soltar un comensal de los 4 y poder seguir el proceso de ejecucion de los Filosofos.
     * 
     * @param id_f ID del filosofo
     * @param log Clase Log para escribir el log en la interface grafica
     * @throws InterruptedException Posibles errores
     */
    public synchronized void soltarComensal(int id_f, Log log) throws InterruptedException{
        comensal++; // Conteo de comensales
        System.out.println("El Filosofo " + (id_f+1) + " ya NO es el comensal " + comensal);
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface grafica:
        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filosofo " + (id_f+1) + " ya NO es el comensal " + comensal);
        this.notify(); // Notificacion al siguiente de que hay comensal disponible.
    }
}
