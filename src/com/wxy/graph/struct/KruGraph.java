package com.wxy.graph.struct;

import com.wxy.graph.algorithm.Kruskal;

import java.util.Scanner;

/**
 * <p>边集数组结构——克鲁斯卡尔算法</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/11 22:41
 */
public class KruGraph {
    public int numEdges;
    public Edge[] edges;

    public class Edge {
        public int begin;
        public int end;
        public int weight;
    }

    public KruGraph(int numEdges) {
        this.numEdges = numEdges;
        edges = new Edge[numEdges];
        createKruGraph();
    }

    private void createKruGraph() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < numEdges; i++) {
            System.out.println("请输入第" + (i+1) + "边的下标和权值：");
            Edge edge = new Edge();
            edge.begin = scan.nextInt();
            edge.end = scan.nextInt();
            edge.weight = scan.nextInt();
            edges[i] = edge;
        }
    }

    public void kruskal() {
        Kruskal.miniSpanTree_Kruskal(this);
    }
}
