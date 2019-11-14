package com.wxy.graph.struct;

import com.wxy.graph.algorithm.TopologicalSort;
import com.wxy.stack.Queue;
import com.wxy.stack.impl.ArrayQueue;

import java.util.Scanner;

/**
 * <p>
 *     拓扑图——邻接表
 *         只实现拓扑排序算法
 * </p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/7 22:10
 */
public class TopoAdjListGraph {

    public class EdgeNode {
        public int adjvex;
        public int weight;
        public EdgeNode next;
    }

    public class VertexNode {
        public int in;   // 表示顶点的入度
        public Object data;
        public EdgeNode firstedge;
    }

    public VertexNode[] adjList;
    public int numVertexes, numEdges;

    private boolean[] visited;
    private Queue queue;

    public TopoAdjListGraph(int numVertexes, int numEdges) {
        this.numVertexes = numVertexes;
        this.numEdges = numEdges;
        this.adjList = new VertexNode[numVertexes];
        for (int i = 0; i < numVertexes; i++) {
            adjList[i] = new VertexNode();
        }
        createNetwork();
    }

    private void createNetwork() {
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < numVertexes; i++) {
            System.out.println("请输入第" + i + "个结点的数据和入度数");
            adjList[i].data = scan.next();
            adjList[i].in = scan.nextInt();
        }

        // 循环对每条边进行赋值
        EdgeNode node;
        for (int k = 0; k < this.numEdges; k++) {
            System.out.println("输入边(vi,vj)上的顶点序号和边上的权值");
            int i = scan.nextInt();
            int j = scan.nextInt();
            int w = scan.nextInt();
            node = new EdgeNode();
            node.adjvex = j;
            node.weight = w;
            node.next = adjList[i].firstedge;
            adjList[i].firstedge = node;
        }
    }

    public void topo() {
        TopologicalSort.criticalPath(this);
    }
}
