package com.unadm.comp;

public class Test extends Object {
    public static void main(String args[]) {
        int vertice = 11, aristas = 18;
        int fuente = 1, destino = 11;
        //para p,q y cap, la cantidad de datos esta dada por: 2*aristas+1
        int p[] = {0, 1, 1, 1, 2, 3, 3, 3, 3, 4, 5, 5, 6, 6, 7, 8, 9, 10,
                10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int q[] = {0, 2, 3, 4, 5, 2, 5, 7, 6, 7, 9, 8, 9, 7, 10, 11, 8, 9,
                11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int cap[] = {0, 6, 8, 4, 8, 3, 2, 14, 3, 10, 1, 10, 10, 12, 6, 8, 9, 10,
                12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int cortemin[] = new int[vertice + 1];
        int flujovertice[] = new int[vertice + 1];
        int flujoarista[] = new int[aristas + aristas + 1];
        Maxflow.FlujoMaxCorteMin(vertice, aristas, p, q, cap, fuente, destino, cortemin, flujoarista,
                flujovertice);
        System.out.print("Vértices del corte mínimo: ");
        for (int i = 1; i <= vertice; i++)
            if (cortemin[i] == 1)
                System.out.print(" " + i);
        System.out.println("\n\nCantidad de flujo por cada uno de los vértices:" +
                "\n\n Verticeflujo");
        for (int i = 1; i <= vertice; i++)
            System.out.printf("%4d%7d\n", i, flujovertice[i]);
        System.out.println("\nCantidad de flujo por cada una de las aristas:\n\n de hasta flujo");
        for (int i = 1; i <= flujoarista[0]; i++)
            System.out.printf("%2d%6d%7d\n", p[i], q[i], flujoarista[i]);
        System.out.print("\nNOTA: En aquellas aristas que no aparecen, el flujo es cero (0)");
    }
}