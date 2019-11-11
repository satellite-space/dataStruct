package com.wxy.graph.algorithm;

import com.wxy.graph.struct.KruGraph;

/**
 * <p>最小生成树——克鲁斯卡尔(Kruskal)算法</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/11 23:09
 */
public class Kruskal {

    public static void miniSpanTree_Kruskal(KruGraph graph) {
        KruGraph.Edge[] edges = graph.edges;
        int[] parent = new int[edges.length];
        for (int i = 0; i < graph.numEdges; i++) {
            int n = find(parent, edges[i].begin);
            int m = find(parent, edges[i].end);
            if (n != m) {
                parent[n] = m;
                System.out.println("("+ edges[i].begin +", " + edges[i].end + ") " + edges[i].weight);
            }
        }
    }

    private static int find(int[] parent, int f) {
        while (parent[f] > 0) {
            f = parent[f];
        }
        return f;
    }
}
