/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vn.concurrencia;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class HilosSencillos {
    static int contCompartido = 0;
    
    HiloA hiloA1,  hiloA2;
    HiloB hiloB1,  hiloB2;

    public HilosSencillos() {
        this.hiloA1 = new HiloA();
        this.hiloA2 = new HiloA();
        this.hiloB1 = new HiloB();
        this.hiloB2 = new HiloB();
    }    
    public static class HiloA implements Runnable {

        @Override
        public void run() {
            for (long i = 0; i < 50000000; i++) {
                contCompartido++;
                if ( i % 100000 == 0) {
                    System.out.println("Instrucción A:    " + i 
                            + " - contador = " + contCompartido);                
                }
            }
        }        
    }
    public static class HiloB implements Runnable {

        @Override
        public void run() {
            // El contador principal, j, en variable local
            for (long j = 0; j < 80000000; j++) {   
                contCompartido++;
                if ( j % 100000 == 0) {
                    System.out.println("->Ins B:" + j 
                            + " ,c=" + contCompartido);
                }
            }
        }        
    }
    
    public void ejecutarHilosStartABenParalelo() {
        System.out.println("\n**** THREAD - START ****\n");
        Thread threadHiloA1 = new Thread(hiloA1);
        Thread threadHiloA2 = new Thread(hiloA2);
        Thread threadHiloB1 = new Thread(hiloB1);
        Thread threadHiloB2 = new Thread(hiloB2);
        
        threadHiloA1.start();       threadHiloA2.start();
        threadHiloB1.start();       threadHiloB2.start();
        while(threadHiloA1.isAlive() || threadHiloA2.isAlive()
                || threadHiloB1.isAlive() || threadHiloB2.isAlive())  ;
        
        System.out.println("\n**** end - START ****\n");
    }
    public void ejecturarHilosRunABenSerie() {
        System.out.println("\n---- THREAD - RUN ----\n");
        // SIN HILOS, ejecutamos uno detrás de otro
        hiloA1.run();
        hiloA2.run();
        hiloB1.run();
        hiloB2.run();
        
        System.out.println("\n---- end - RUN ----\n");
    }
    
    
}
