package com.wxy.graph;

import com.wxy.graph.struct.AdjListGraph;
import com.wxy.graph.struct.KruGraph;
import com.wxy.graph.struct.MGraph;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/10 17:04
 */
public class Main {
    public static void main(String[] args) {
        // testMGraph();
        // testAdjListGraph();
        // testPrim();
        testKruskal();
    }

    private static void testKruskal() {
        KruGraph graph = new KruGraph(15);
        graph.kruskal();
    }

    private static void testPrim() {
        Object[] arr = new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        MGraph graph = new MGraph(arr, 15);
        graph.createGraph(Graph.NETWORK);
        graph.prim();
    }

    private static void testAdjListGraph() {
        AdjListGraph graph = new AdjListGraph(9, 15);
        graph.traverseOfDFS();
        System.out.println();
        graph.traverseOfHFS();
    }

    private static void testMGraph() {
        Object[] arr = new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        MGraph graph = new MGraph(arr, 15);
        graph.createGraph(Graph.UNDIRECTED);
        graph.traverseOfDFS();
        System.out.println();
        graph.traverseOfHFS();
    }
}
