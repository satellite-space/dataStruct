package com.wxy.graph.algorithm;

import com.wxy.graph.Graph;
import com.wxy.graph.struct.MGraph;

/**
 * <p>最小生成树——普里姆(Prim)算法</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/11 21:38
 */
public class Prim {

    /**
     *  最小生成树的普里姆算法
     * @param graph 图
     */
    public static void miniSpanTree_Prim(MGraph graph) {
        int[] adjvex = new int[graph.numVertex]; // 保存相关顶点的下标
        int[] lowcost = new int[graph.numVertex]; //保存相关顶点的权值
        lowcost[0] = 0;
        adjvex[0] = 0;

        for (int i = 1; i < graph.numVertex; i++) {
            lowcost[i] = graph.arc[0][i];
            adjvex[i] = 0;
        }
        for (int i = 1; i < graph.numVertex; i++) {
            int min = Graph.INFINITY;
            int j = 1;
            int k = 0;
            while (j < graph.numVertex) {
                if (lowcost[j] != 0 && lowcost[j] < min) {
                    min = lowcost[j];
                    k = j;
                }
                j++;
            }
            System.out.println("(" + adjvex[k] + ", " + k + ")");
            lowcost[k] = 0;
            for (j = 1; j < graph.numVertex; j++) {
                if (lowcost[j] != 0 && graph.arc[k][j] < lowcost[j]) {
                    lowcost[j] = graph.arc[k][j];
                    adjvex[j] = k;
                }
            }
        }
    }
}
