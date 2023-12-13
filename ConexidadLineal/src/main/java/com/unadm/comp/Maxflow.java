package com.unadm.comp;

public class Maxflow {
    //valores de retorno
    public static void FlujoMaxCorteMin(int vertice, int aristas, int p[],
                                        int q[], int cap[], int fuente, int destino,
                                        int cortemin[], int flujoarista[], int flujovertice[]) {
        int primarista[] = new int[vertice + 1];
        int mapeoi[] = new int[vertice + 1];
        int mapeoj[] = new int[vertice + 1];
        int flujo, aux, aristas2, NY, i, j;
        int entrada = 0, salida = 0, parm = 0, aristas1 = 0, cont1 = 0, cont2 = 0;
        int fin = 0, NP = 0, NQ = 0, NU = 0, NV = 0, NW = 0, NX = 0;
        boolean termino, A, B, C, G;
        boolean D = false, E = false, F = false;
        // Creación de aristas sustitutas
        j = aristas;
        for (i = 1; i <= aristas; i++) {
            j++;
            p[aristas + i] = q[i];
            q[aristas + i] = p[i];
            cap[aristas + i] = 0;
        }
        aristas = aristas + aristas;
        // Inicialización
        for (i = 1; i <= vertice; i++)
            primarista[i] = 0;
        flujo = 0;
        for (i = 1; i <= aristas; i++) {
            flujoarista[i] = 0;
            j = p[i];
            if (j == fuente)
                flujo += cap[i];
            primarista[j]++;
        }
        flujovertice[fuente] = flujo;
        NY = 1;
        for (i = 1; i <= vertice; i++) {
            j = primarista[i];
            primarista[i] = NY;
            mapeoi[i] = NY;
            NY += j;
        }
        termino = false;
        A = true;
        // Ordenamiento de aristas en orden lexicográfico
        etiqueta1:
        while (true) {
            aux = 0;
            B = false;
            etiqueta2:
            while (true) {
                if (!B) {
                    if ((aux < 0) && A) {
                        if (aux != -1) {
                            if (NY < 0) NP++;
                            NQ = cont2;
                            cont2 = NP;
                            aux = -1;
                        } else {
                            if (NY <= 0) {
                                if (cont1 > 1) {
                                    cont1--;
                                    cont2 = cont1;
                                    A = false;
                                    continue etiqueta2;
                                }
                                if (aristas1 == 1)
                                    aux = 0;
                                else {
                                    NP = aristas1;
                                    aristas1--;
                                    NQ = 1;
                                    aux = 1;
                                }
                            } else
                                aux = 2;
                        }
                    } else {
                        if (A)
                            if (aux > 0) {
                                if (aux <= 1) cont2 = cont1;
                                A = false;
                            }
                        if (A) {
                            aristas1 = aristas;
                            cont1 = 1 + aristas / 2;
                            cont1--;
                            cont2 = cont1;
                        }
                        A = true;
                        NP = cont2 + cont2;
                        if (NP < aristas1) {
                            NQ = NP + 1;
                            aux = -2;
                        } else {
                            if (NP == aristas1) {
                                NQ = cont2;
                                cont2 = NP;
                                aux = -1;
                            } else {
                                if (cont1 > 1) {
                                    cont1--;
                                    cont2 = cont1;
                                    A = false;
                                    continue etiqueta2;
                                }
                                if (aristas1 == 1)
                                    aux = 0;
                                else {
                                    NP = aristas1;
                                    aristas1--;
                                    NQ = 1;
                                    aux = 1;
                                }
                            }
                        }
                    }
                }
                G = false;
                C = false;
                if ((aux < 0) && !B) {
                    NY = p[NP] - p[NQ];
                    if (NY == 0) NY = q[NP] - q[NQ];
                    continue etiqueta2;
                } else {
                    if ((aux > 0) || B) {
                        // intercambio de dos aristas
                        B = false;
                        NY = p[NP];
                        p[NP] = p[NQ];
                        p[NQ] = NY;
                        flujo = cap[NP];
                        cap[NP] = cap[NQ];
                        cap[NQ] = flujo;
                        NY = q[NP];
                        q[NP] = q[NQ];
                        q[NQ] = NY;
                        flujo = flujoarista[NP];
                        flujoarista[NP] = flujoarista[NQ];
                        flujoarista[NQ] = flujo;
                        if (aux > 0)
                            continue etiqueta2;
                        if (aux == 0) {
                            C = true;
                        } else {
                            mapeoj[NV] = NQ;
                            G = true;
                        }
                    } else if (termino) {
                        //Obtención del flujo máximo en cada arista
                        j = 0;
                        for (i = 1; i <= aristas; i++)
                            if (flujoarista[i] > 0) {
                                j++;
                                p[j] = p[i];
                                q[j] = q[i];
                                flujoarista[j] = flujoarista[i];
                            }
                        flujoarista[0] = j;
                        return;
                    }
                }
                if (!G && !C) {
                    //Realiza el cruce de referencias entre aristas
                    for (i = 1; i <= aristas; i++) {
                        NV = q[i];
                        p[i] = mapeoi[NV];
                        mapeoi[NV]++;
                    }
                }
                etiqueta3:
                while (true) {
                    if (!G) {
                        if (!C) {
                            aux = 0;
                            for (i = 1; i <= vertice; i++) {
                                if (i != fuente)
                                    flujovertice[i] = 0;
                                mapeoj[i] = aristas + 1;
                                if (i < vertice) mapeoj[i] = primarista[i + 1];
                                cortemin[i] = 0;
                            }
                            entrada = 0;
                            salida = 1;
                            mapeoi[1] = fuente;
                            cortemin[fuente] = -1;
                            while (true) {
                                entrada++;
                                if (entrada > salida)
                                    break;
                                NU = mapeoi[entrada];
                                aristas2 = mapeoj[NU] - 1;
                                fin = primarista[NU] - 1;
                                while (true) {
                                    fin++;
                                    if (fin > aristas2)
                                        break;
                                    NV = q[fin];
                                    flujo = cap[fin] - flujoarista[fin];
                                    if ((cortemin[NV] != 0) || (flujo == 0))
                                        continue;
                                    if (NV != destino) {
                                        salida++;
                                        mapeoi[salida] = NV;
                                    }
                                    cortemin[NV] = -1;
                                }
                            }
                            if (cortemin[destino] == 0) {
                                // Salidas
                                for (i = 1; i <= vertice; i++)
                                    cortemin[i] = -cortemin[i];
                                for (i = 1; i <= aristas; i++) {
                                    NU = q[p[i]];
                                    if (flujoarista[i] < 0)
                                        flujovertice[NU] -= flujoarista[i];
                                    p[i] = NU;
                                }
                                flujovertice[fuente] = flujovertice[destino];
                                termino = true;
                                continue etiqueta1;
                            }
                            cortemin[destino] = 1;
                        }
                        while (true) {
                            if (!C) {
                                entrada--;
                                if (entrada == 0)
                                    break;
                                NU = mapeoi[entrada];
                                NP = primarista[NU] - 1;
                                NQ = mapeoj[NU] - 1;
                            }
                            C = false;
                            while (NP != NQ) {
                                NV = q[NQ];
                                if ((cortemin[NV] <= 0) || (cap[NQ] == flujoarista[NQ])) {
                                    NQ--;
                                    continue;
                                } else {
                                    q[NQ] = -NV;
                                    cap[NQ] -= flujoarista[NQ];
                                    flujoarista[NQ] = 0;
                                    NP++;
                                    if (NP < NQ) {
                                        p[p[NP]] = NQ;
                                        p[p[NQ]] = NP;
                                        B = true;
                                        continue etiqueta2;
                                    }
                                    break;
                                }
                            }
                            if (NP >= primarista[NU])
                                cortemin[NU] = NP;
                        }
                        NW = 0;
                        for (i = 1; i <= salida; i++)
                            if (cortemin[mapeoi[i]] > 0) {
                                NW++;
                                mapeoi[NW] = mapeoi[i];
                            }
                        // Encuentra el flujo más factible
                        aux = -1;
                        NX = 1;
                    }
                    etiqueta4:
                    while (true) {
                        if (!G) {
                            if (!F) {
                                if (!D && !E)
                                    NU = mapeoi[NX];
                                if ((flujovertice[NU] <= 0) || D || E) {
                                    if (!E) {
                                        D = false;
                                        NX++;
                                        if (NX <= NW) continue etiqueta4;
                                        parm = 0;
                                    }
                                    E = false;
                                    NX--;
                                    if (NX != 1) {
                                        NU = mapeoi[NX];
                                        if (flujovertice[NU] < 0) {
                                            E = true;
                                            continue etiqueta4;
                                        }
                                        if (flujovertice[NU] == 0) {
                                            //Flujos acumulados
                                            aristas2 = aristas + 1;
                                            if (NU < vertice)
                                                aristas2 = primarista[NU + 1];
                                            fin = mapeoj[NU];
                                            mapeoj[NU] = aristas2;
                                            while (true) {
                                                if (fin == aristas2) {
                                                    E = true;
                                                    continue etiqueta4;
                                                }
                                                j = p[fin];
                                                flujo = flujoarista[j];
                                                flujoarista[j] = 0;
                                                cap[j] -= flujo;
                                                flujoarista[fin] -= flujo;
                                                fin++;
                                            }
                                        }
                                        if (primarista[NU] > cortemin[NU]) {
                                            fin = mapeoj[NU];
                                            do {
                                                j = p[fin];
                                                flujo = flujoarista[j];
                                                if (flujovertice[NU] < flujo)
                                                    flujo = flujovertice[NU];
                                                flujoarista[j] -= flujo;
                                                flujovertice[NU] -= flujo;
                                                NV = q[fin];
                                                flujovertice[NV] += flujo;
                                                fin++;
                                            }
                                            while (flujovertice[NU] > 0);
                                            flujovertice[NU] = -1;
                                            E = true;
                                            continue etiqueta4;
                                        }
                                        fin = cortemin[NU] + 1;
                                        F = true;
                                        continue etiqueta4;
                                    }
                                    for (i = 1; i <= aristas; i++) {
                                        NV = -q[i];
                                        if (NV >= 0) {
                                            q[i] = NV;
                                            j = p[i];
                                            cap[i] -= flujoarista[j];
                                            flujo = flujoarista[i] - flujoarista[j];
                                            flujoarista[i] = flujo;
                                            flujoarista[j] = -flujo;
                                        }
                                    }
                                    continue etiqueta3;
                                }
                                // Se da el flujo máximo a la arista saliente del vertice
                                fin = cortemin[NU] + 1;
                            }
                        }
                        while (true) {
                            if (!G) {
                                F = false;
                                fin--;
                                if (fin < primarista[NU])
                                    break;
                                NV = -q[fin];
                                if (flujovertice[NV] < 0)
                                    continue;
                                flujo = cap[fin] - flujoarista[fin];
                                if (flujovertice[NU] < flujo)
                                    flujo = flujovertice[NU];
                                flujoarista[fin] += flujo;
                                flujovertice[NU] -= flujo;
                                flujovertice[NV] += flujo;
                                parm = 1;
                                NP = p[fin];
                                NQ = mapeoj[NV] - 1;
                                if (NP < NQ) {
                                    p[p[NP]] = NQ;
                                    p[p[NQ]] = NP;
                                    B = true;
                                    continue etiqueta2;
                                }
                                if (NP == NQ)
                                    mapeoj[NV] = NQ;
                            }
                            G = false;
                            if (flujovertice[NU] > 0)
                                continue;
                            if (cap[fin] == flujoarista[fin]) fin--;
                            break;
                        }
                        cortemin[NU] = fin;
                        if (parm != 0) {
                            D = true;
                            continue etiqueta4;
                        }
                        //Se eliminan los excesos de flujo entrantes de los vertices
                        fin = mapeoj[NU];
                        do {
                            j = p[fin];
                            flujo = flujoarista[j];
                            if (flujovertice[NU] < flujo) flujo = flujovertice[NU];
                            flujoarista[j] -= flujo;
                            flujovertice[NU] -= flujo;
                            NV = q[fin];
                            flujovertice[NV] += flujo;
                            fin++;
                        } while (flujovertice[NU] > 0);
                        flujovertice[NU] = -1;
                        E = true;
                        continue etiqueta4;
                    }
                }
            }
        }
    }
}
