package main.a3_Advanced_Algorithms.a1_graph;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: Graph_Test
 * Package: main.a3_Advanced_Algorithms.a1_graph
 * Description:图测试类
 *
 * @Author: zgh296
 * @Create: 2023/7/2 - 9:30
 * @Version: v1.0
 */
public class Graph_Test {

    // 图的两种遍历
    @Test
    public void test() {

        // 建图
        Graph.Vertex v1 = new Graph.Vertex("v1");
        Graph.Vertex v2 = new Graph.Vertex("v2");
        Graph.Vertex v3 = new Graph.Vertex("v3");
        Graph.Vertex v4 = new Graph.Vertex("v4");
        Graph.Vertex v5 = new Graph.Vertex("v5");
        Graph.Vertex v6 = new Graph.Vertex("v6");

        v1.setEdges(List.of(new Graph.Edge(v3), new Graph.Edge(v2), new Graph.Edge(v6)));
        v2.setEdges(List.of(new Graph.Edge(v4)));
        v3.setEdges(List.of(new Graph.Edge(v4), new Graph.Edge(v6)));
        v4.setEdges(List.of(new Graph.Edge(v5)));
        v5.setEdges(List.of());
        v6.setEdges(List.of(new Graph.Edge(v5)));

        System.out.print("深度遍历：");
        Graph.dfs(v1);
        System.out.println();

        System.out.print("深度遍历：");
        Graph.dfs2(v1);
        System.out.println();

        System.out.print("广度遍历：");
        Graph.bfs(v1);
        System.out.println();

    }

    // 拓扑排序
    @Test
    public void test2() {

        // 建图
        Graph.Vertex v1 = new Graph.Vertex("网页基础");
        Graph.Vertex v2 = new Graph.Vertex("Java基础");
        Graph.Vertex v3 = new Graph.Vertex("JavaWeb");
        Graph.Vertex v4 = new Graph.Vertex("Spring框架");
        Graph.Vertex v5 = new Graph.Vertex("微服务框架");
        Graph.Vertex v6 = new Graph.Vertex("数据库");
        Graph.Vertex v7 = new Graph.Vertex("实战项目");

        v1.edges = List.of(new Graph.Edge(v3));
        v2.edges = List.of(new Graph.Edge(v3));
        v3.edges = List.of(new Graph.Edge(v4));
        v6.edges = List.of(new Graph.Edge(v4));
        v4.edges = List.of(new Graph.Edge(v5));
        v5.edges = List.of(new Graph.Edge(v7));
        v7.edges = List.of();

        Graph.Vertex[] v = new Graph.Vertex[]{v1, v2, v3, v4, v5, v6, v7};
        Graph.topological(v);
        System.out.println();
        Graph.topologicalDFS(v);

    }

    //  Dijkstra最短路径
    @Test
    public void test3() {

        Graph.Vertex v1 = new Graph.Vertex("v1");
        Graph.Vertex v2 = new Graph.Vertex("v2");
        Graph.Vertex v3 = new Graph.Vertex("v3");
        Graph.Vertex v4 = new Graph.Vertex("v4");
        Graph.Vertex v5 = new Graph.Vertex("v5");
        Graph.Vertex v6 = new Graph.Vertex("v6");

        v1.edges = List.of(new Graph.Edge(v3, 9), new Graph.Edge(v2, 7), new Graph.Edge(v6, 14));
        v2.edges = List.of(new Graph.Edge(v4, 15));
        v3.edges = List.of(new Graph.Edge(v4, 11), new Graph.Edge(v6, 2));
        v4.edges = List.of(new Graph.Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Graph.Edge(v5, 9));

        Graph.Vertex[] v = new Graph.Vertex[]{v1, v2, v3, v4, v5, v6};
        List<Graph.Vertex> list = new ArrayList<>(List.of(v1, v2, v3, v4, v5, v6));
        Graph.dijkstra(v1, v);
        System.out.println();
        Graph.dijkstra(list, v1);

    }

}
