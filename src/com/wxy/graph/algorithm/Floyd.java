package com.wxy.graph.algorithm;

import com.wxy.graph.struct.MGraph;

/**
 * <p>
 *     图的最短路径
 *         弗洛伊德(Floyd)算法
 * </p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/12 22:20
 */
public class Floyd {

    public static void shortPathTable_Dijkstra(MGraph graph) {
        int[][] patharc = new int[graph.numVertex][graph.numVertex];
        int[][] shortPathTable = new int[graph.numVertex][graph.numVertex];
        for (int v = 0; v < graph.numVertex; v++) {
            for (int w = 0; w < graph.numVertex; w++) {
                shortPathTable[v][w] = graph.arc[v][w];
                patharc[v][w] = w;
            }
        }

        for (int k = 0; k < graph.numVertex; k++) {
            for (int v = 0; v < graph.numVertex; v++) {
                for (int w = 0; w < graph.numVertex; w++) {
                    if (shortPathTable[v][w] > (shortPathTable[v][k] + shortPathTable[k][w])) {
                        shortPathTable[v][w] = shortPathTable[v][k] + shortPathTable[k][w];
                        patharc[v][w] = patharc[v][k];
                    }
                }
            }
        }

        // 最短路径
        for (int v = 0; v < graph.numVertex; v++) {
            for (int w = v+1; w < graph.numVertex; w++) {
                System.out.print("v" + v + "-v" + w + " weight: " + shortPathTable[v][w]);
                int k = patharc[v][w];
                System.out.println("path: " + v);
                while (k != w) {
                    System.out.print(" -> " + k);
                    k = patharc[k][w];
                }
                System.out.print(" -> " + w);
                System.out.println();
            }
            System.out.println();
        }
    }
}
