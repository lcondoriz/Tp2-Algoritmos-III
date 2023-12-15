package edu.fiuba.algo3.log;

import java.io.IOException;

public class Logeador {
    public static void agregarALog(Log log, String cadena) {
        if (log != null) {
            try {
                log.addLine(cadena);
                System.out.println(cadena);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
