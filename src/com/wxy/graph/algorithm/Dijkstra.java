package com.wxy.graph.algorithm;

import com.wxy.graph.Graph;
import com.wxy.graph.struct.MGraph;

/**
 * <p>
 *     图的最短路径
 *         迪杰斯特拉(Dijkstra)算法
 * </p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/12 22:06
 */
public class Dijkstra {

    public static void shortPathTable_Dijkstra(MGraph graph, int v0) {
        int[] patharc = new int[graph.numVertex];
        int[] shortPathTable = new int[graph.numVertex];
        boolean[] flags = new boolean[graph.numVertex];
        for (int v = 0; v < graph.numVertex; v++) {
            flags[v] = false;
            patharc[v] = 0;
            shortPathTable[v] = graph.arc[v0][v];
        }
        shortPathTable[v0] = 0;
        flags[v0] = true;

        for (int v = 1; v < graph.numVertex; v++) {
            int min = Graph.INFINITY;
            int k = 0;
            for (int w = 0; w < graph.numVertex; w++) {
                if (!flags[w] && shortPathTable[w] < min) {
                    min = shortPathTable[w];
                    k = w;
                }
            }
            flags[k] = true;

            for (int w = 0; w < graph.numVertex; w++) {
                if (!flags[w] && (min + graph.arc[k][w]) < shortPathTable[w]) {
                    shortPathTable[w] = min + graph.arc[k][w];
                    patharc[w] = k;
                }
            }
        }
    }
}
