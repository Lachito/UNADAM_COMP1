package com.unadm.comp;

public class Prueba_ConexidadLineal extends Object {
    public static void main(String args[]) {
        int conex;
        int vertice = 7;
        int aristas = 12;
        int p[] = {0, 4, 6, 4, 3, 6, 3, 2, 6, 2, 1, 3, 2};
        int q[] = {0, 7, 4, 5, 7, 3, 5, 7, 2, 5, 4, 1, 1};
        conex = Conexidad.ConexidadLineal(vertice, aristas, p, q);
        System.out.println("La conexidad lineal delagráfica G es = " + conex);
    }
}