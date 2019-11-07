package com.wxy.graph;

/**
 * <p>图接口</p>
 *
 * @author wxy
 * @version 1.0
 * @create 2019/11/7 21:21
 */
public interface Graph<E> {

    String DIRECTED = "directed";
    String UNDIRECTED = "undirected";
    String NETWORK = "network";
    int INFINITY = 65535;

    /** 按照图的类型生成对应的图 */
    void createGraph(String definition);

    /** 若图存在，则销毁 */
    void destroyGraph();

    /** 若图中存在顶点u，则返回其在图中的位置 */
    int locateVex(E e);

    /** 返回顶点vertex相邻的顶点,若无则返回空 */
    E fristAdjvex(E e);

    /** 在图中增添新的顶点 */
    boolean insertVex(Vertex vertex);

    /** 删除顶点vertex及其相关的弧 */
    boolean deleteVex(Vertex vertex);

    /** 在图中增添弧<v,w>,若图为无向图，还需增添弧<w,v> */
    boolean insertArc(int v, int w);

    /** 在图中删除弧<v,w>,若图为无向图，还需删除弧<w,v> */
    boolean deleteArc(int v, int w);

    /** 对图进行深度优先遍历，在遍历过程中对每个顶点进行调用 */
    void traverseOfDFS();

    /** 对图进行广度优先遍历，在遍历过程中对每个顶点进行调用 */
    void traverseOfHFS();
}
