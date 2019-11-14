package com.wxy.graph.algorithm;

import com.wxy.graph.struct.TopoAdjListGraph;
import com.wxy.stack.Stack;
import com.wxy.stack.impl.ArrayStack;

/**
 * <p>文件描述</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/14 20:05
 */
public class TopologicalSort {

    private static int[] etv;
    private static int[] ltv;
    private static Stack stack;
    private static Stack stack2;

    /**
     *  对有向图进行拓扑排序，有回路返回false，无回路返回true
     * @param graph
     * @return
     */
    public static boolean topologicalSort(TopoAdjListGraph graph) {
        stack = new ArrayStack(graph.numVertexes);
        stack2 = new ArrayStack(graph.numVertexes);
        for (int i = 0; i < graph.numVertexes; i++) {
            if (0 == graph.adjList[i].in) {
                stack.push(i);
            }
        }

        // 初始化事件最早发生时间
        etv = new int[graph.numVertexes];
        for (int i = 0; i < graph.numVertexes; i++) {
            etv[i] = 0;
        }

        int count = 0;
        while (!stack.isEmpty()) {
            int pop = (int) stack.pop();
            System.out.print(graph.adjList[pop].data + " -> ");
            count++;
            stack2.push(pop);

            for (TopoAdjListGraph.EdgeNode e = graph.adjList[pop].firstedge; null != e; e = e.next) {
                int k = e.adjvex;
                if ((--graph.adjList[k].in) == 0) {
                    stack.push(k);
                }
                if ((etv[pop] + e.weight) > etv[k]) {
                    etv[k] = etv[pop] + e.weight;
                }
            }
        }

        return !(count < graph.numVertexes);
    }

    public static void criticalPath(TopoAdjListGraph graph) {
        if(!topologicalSort(graph)) {
            throw new RuntimeException("不是拓扑图");
        }
        ltv = new int[graph.numVertexes];
        for (int i = 0; i < graph.numVertexes; i++) {
            ltv[i] = etv[graph.numVertexes - 1];
        }
        while (!stack2.isEmpty()) {
            int pop = (int) stack2.pop();
            for (TopoAdjListGraph.EdgeNode e = graph.adjList[pop].firstedge; null != e; e = e.next) {
                int k = e.adjvex;
                if (ltv[k] - e.weight < ltv[pop]) {
                    ltv[pop] = ltv[k] - e.weight;
                }
            }
        }
        System.out.println();
        for (int j = 0; j < graph.numVertexes; j++) {
            for (TopoAdjListGraph.EdgeNode e = graph.adjList[j].firstedge; null != e; e = e.next) {
                int k = e.adjvex;
                int ete = etv[j];
                int lte = ltv[k] - e.weight;
                if (ete == lte) {
                    System.out.print("<v" + graph.adjList[j].data + ", v" + graph.adjList[k].data + "> length: " + e.weight + " , ");
                }
            }
        }
    }
}
