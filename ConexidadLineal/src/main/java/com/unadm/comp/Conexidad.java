package com.unadm.comp;

public class Conexidad {
    public static int ConexidadLineal(int vertice, int aristas, int p[], int q[]) {
        int i, j, conex, aristas2, fuente, destino;
        int cortemin[] = new int[vertice + 1];
        int aristai[] = new int[4 * aristas + 1];
        int aristaj[] = new int[4 * aristas + 1];
        int cap[] = new int[4 * aristas + 1];
        int flujoaristas[] = new int[4 * aristas + 1];
        int flujovertice[] = new int[4 * aristas + 1];
        conex = vertice;
        fuente = 1;
        aristas2 = aristas + aristas;
        for (destino = 2; destino <= vertice; destino++) {
            // Construir la red
            for (i = 1; i <= 4 * aristas; i++) {
                aristai[i] = 0;
                aristaj[i] = 0;
                cap[i] = 0;
            }
            // Ducplicado de aristas
            j = 0;
            for (i = 1; i <= aristas; i++) {
                j++;
                aristai[j] = p[i];
                aristaj[j] = q[i];
                cap[j] = 1;
                j++;
                aristai[j] = q[i];
                aristaj[j] = p[i];
                cap[j] = 1;
            }
            // Manda llamar el algoritmo de flujo máximo
            Maxflow.FlujoMaxCorteMin(vertice, aristas2, aristai, aristaj, cap, fuente, destino, cortemin, flujoaristas, flujovertice);
            if (flujovertice[fuente] < conex) conex = flujovertice[fuente];
        }
        return conex;
    }
}