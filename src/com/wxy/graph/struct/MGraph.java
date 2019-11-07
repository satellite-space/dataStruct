package com.wxy.graph.struct;

import com.wxy.graph.Graph;
import com.wxy.graph.Vertex;

import java.util.Arrays;
import java.util.Scanner;

/**
 * <p>邻接矩阵</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/7 21:32
 */
public class MGraph<E> implements Graph<E> {

    private Object[] vexs;
    private int[][] arc;
    private int numVertex, numEdge;

    public MGraph(Object[] vexs, int numEdge) {
        this.numVertex = vexs.length;
        this.numEdge = numEdge;
        this.vexs = vexs;
        arc = new int[numVertex][numVertex];
    }

    @Override
    public void createGraph(String definition) {
        switch (definition) {
            case UNDIRECTED :
                createUndirected();
                break;
            case DIRECTED :
                createDirected();
                break;
            case NETWORK :
                createNetwork();
                break;
        }
    }

    private void createNetwork() {
        Scanner scan = new Scanner(System.in);

        for (int i = 0; i < numVertex; i++) {
            for (int j = 0; j < numVertex; j++) {
                arc[i][j] = INFINITY;
            }
        }

        for (int k = 0; k < numEdge; k++) {
            System.out.println("请输入网图第" + k + "条边的起点和终点的下标及权值:");
            int i = scan.nextInt();
            int j = scan.nextInt();
            int w = scan.nextInt();
            arc[i][j] = arc[j][i] = w;
        }
    }

    private void createDirected() {
        Scanner scan = new Scanner(System.in);
        for (int k = 0; k < numEdge; k++) {
            System.out.println("请输入有向图第" + k + "条边的起点和终点的下标:");
            int i = scan.nextInt();
            int j = scan.nextInt();
            arc[i][j] = 1;
        }
    }

    private void createUndirected() {
        Scanner scan = new Scanner(System.in);
        for (int k = 0; k < numEdge; k++) {
            System.out.println("请输入无向图第" + k + "条边的起点和终点的下标:");
            int i = scan.nextInt();
            int j = scan.nextInt();
            arc[i][j] = arc[j][i] = 1;
        }
    }

    @Override
    public void destroyGraph() {
        for (int i = 0; i < numVertex; i++) {
            for (int j = 0; j < numVertex; j++) {
                arc[i][j] = 0;
            }
        }
        this.vexs = null;
        this.numVertex = this.numEdge = 0;
    }

    @Override
    public int locateVex(E e) {
        for (int i = 0; i < numVertex; i++) {
            if (e.equals(vexs[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E fristAdjvex(E e) {
        for (int i = 0; i < numVertex - 1; i++) {
            if (e.equals(vexs[i])) {
                return (E) vexs[i];
            }
        }
        return null;
    }

    @Override
    public boolean insertVex(Vertex vertex) {
        this.vexs = Arrays.copyOf(vexs, numVertex + 1);
        this.numVertex = vexs.length;
        this.vexs[numVertex - 1] = vertex;
        return true;
    }

    @Override
    public boolean deleteVex(Vertex vertex) {
        this.vexs[numVertex - 1] = null;
        this.numVertex -= 1;
        return true;
    }

    @Override
    public boolean insertArc(int v, int w) {
        // 若为无向图,则反向边也要添加，若为有向图，只增添一条边
        arc[v][w] = arc[w][v] = 1;
        this.numEdge += 1;
        return true;
    }

    @Override
    public boolean deleteArc(int v, int w) {
        arc[v][w] = arc[w][v] = 0;
        this.numEdge -= 1;
        return true;
    }

    @Override
    public void traverseOfDFS() {

    }

    @Override
    public void traverseOfHFS() {

    }
}
