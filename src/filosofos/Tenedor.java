// Clase pasiva: Recurso compartido
package filosofos;

import java.util.Random;
import utilidades.Log;
import vistas.Clase10Control;

/**
 * Recurso compratido por los hilos Filosofos,
 * Se crean 5 instancias con n-1 comensales donde n es el numero de filosofo con el total de los tenedores,
 * Cada tenedor tiene su ID y a cada filosofo le corresponde 2 tenedores concretos.
 */
public class Tenedor {
    // Variable para generar numeros aleatorios:
    private Random random = new Random();
    // ID del Tenedor
    private int id;
    // Está ocupado el tenedor o no?:
    private boolean libre = true;
    
    /**
     * Constructo de la clase Tenedor
     */
    public Tenedor(int id){
        this.id = id;
    }
    
    // Crear métodos synchronized => Monitores
    // Solo puede acceder un Thread a la vez.
    /**
     * Monitor para coger el tenedor derecho y poder seguir el proceso de ejecucion de los filosofos.
     */
    public synchronized void cogerTenedor(int id_f, Log log) throws InterruptedException{
        while(!libre) 
            this.wait();
        System.out.println("El Filosofo " + (id_f+1) + " coge el tenedor " + (id+1));
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface grafica:
        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filosofo " + (id_f+1) + " coge el tenedor " + (id+1));
        libre = false;
    }
    /**
     * Monitor para coger el tenedor izquierdo y poder seguir el proceso de ejecucion de los filosofos,
     * Pero si no consigue cogerlo en un tiempo x retornara false y tendra que salir a pensar y no podra comer,
     * Tendra que volver a empezar el proceso de comer.
     */
    public synchronized boolean cogerTenedorIzqdo(int id_f, Log log) throws InterruptedException{
        while(!libre){
            this.wait(random.nextInt(1000) + 500); // Solo espera aleatoriamente entre 0.5 y 1 seg y si no, retorna false
            return false;
        } 
        System.out.println("El Filosofo " + (id_f+1) + " coge el tenedor " + (id+1));
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface grafica:
        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filosofo " + (id_f+1) + " coge el tenedor " + (id+1));
        libre = false;
        return true;
    }
    /**
     * Monitor para soltar un tenedor izquierdo o derecho y salir a pensar.
     * 
     * @param id_f ID del Filosofo
     * @param log Clase Log para escribir el log en la interface grafica
     * @throws InterruptedException Posibles errores
     */
    public synchronized void soltarTenedor(int id_f, Log log) throws InterruptedException {
        libre = true;
        System.out.println("El Filosofo " + (id_f+1) + " suelta el tenedor " + (id+1));
        // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface grafica:
        if (Clase10Control.getjTextArea_Log()!=null) log.escribirLog(" El Filosofo " + (id_f+1) + " suelta el tenedor " + (id+1));
        this.notify();
    }
}
