package com.wxy.graph.struct;

import com.wxy.stack.Queue;
import com.wxy.stack.impl.ArrayQueue;

import java.util.Scanner;

/**
 * <p>
 *     图——邻接表
 *     只实现网图的创建，其他方法等后续需要使用时再创建
 * </p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/7 22:10
 */
public class AdjListGraph {

    private class EdgeNode {
        int adjvex;
        int weight;
        EdgeNode next;
    }

    private class VertexNode {
        Object data;
        EdgeNode firstedge;
    }

    private VertexNode[] adjList;
    private int numVertexes, numEdges;

    private boolean[] visited;
    private Queue queue;

    public AdjListGraph(int numVertexes, int numEdges) {
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
            System.out.println("请输入第" + i + "个结点的信息");
            adjList[i].data = scan.next();
        }

        // 循环对每条边进行赋值
        EdgeNode node;
        for (int k = 0; k < numEdges; k++) {
            System.out.println("输入边(vi和vj上的顶点序号):");
            int i = scan.nextInt();
            int j = scan.nextInt();
            node = new EdgeNode();
            node.adjvex = j;
            node.next = adjList[i].firstedge;
            adjList[i].firstedge = node;

            node = new EdgeNode();
            node.adjvex = i;
            node.next = adjList[j].firstedge;
            adjList[j].firstedge = node;
        }
    }

    public void traverseOfDFS() {
        visited = new boolean[numVertexes];
        for (int i = 0; i < numVertexes; i++) {
            if (!visited[i]) {
                searchDFS(this, i);
            }
        }
    }

    private void searchDFS(AdjListGraph graph, int i) {
        visited[i] = true;
        System.out.print(graph.adjList[i].data + "\t");
        EdgeNode node = graph.adjList[i].firstedge;
        while (null != node) {
            if (!visited[node.adjvex]) {
                searchDFS(graph, node.adjvex);
            }
            node = node.next;
        }
    }

    public void traverseOfHFS() {
        queue = new ArrayQueue(this.numVertexes);
        visited = new boolean[this.numVertexes];
        searchHFS(this);
    }

    private void searchHFS(AdjListGraph graph) {
        for (int i = 0; i < graph.numVertexes; i++) {
            if (!visited[i]) {
                visited[i] = true;
                System.out.print(graph.adjList[i].data + "\t");
                queue.add(graph.adjList[i]);
                while (!queue.isEmpty()) {
                    queue.remove();
                    EdgeNode node = graph.adjList[i].firstedge;
                    while (null != node) {
                        if (!visited[node.adjvex]) {
                            visited[node.adjvex] = true;
                            System.out.print(graph.adjList[node.adjvex].data + "\t");
                            queue.add(graph.adjList[node.adjvex]);
                        }
                        node = node.next;
                    }
                }
            }
        }
    }
}
