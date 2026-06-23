package main.a3_Advanced_Algorithms.a1_graph;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * ClassName: Graph
 * Package: main.a3_Advanced_Algorithms.a1_graph
 * Description:图类
 *
 * @Author: zgh296
 * @Create: 2023/7/2 - 8:53
 * @Version: v1.0
 */
public class Graph {

    /*
    一、图
        1.概念：图是由顶点（vertex）和边（edge）组成的数据结构
        2. 有向 vs 无向
        3.度：指与该顶点相邻的边的数量
            入度(进)、出度(出)
        4.权：边可以有权重，代表从源顶点到目标顶点的距离、费用、时间或其他度量。
        5.路径：路径被定义为从一个顶点到另一个顶点的一系列连续边
            不考虑权重，长度就是边的数量
            考虑权重，一般就是权重累加
        6.环：在有向图中，从一个顶点开始，可以通过若干条有向边返回到该顶点，那么就形成了一个环
        7.图的连通性：如果两个顶点之间存在路径，则这两个顶点是连通的，所有顶点都连通，则该图被称之为
    连通图，若子图连通，则称为连通分量
        8.图的表示
               无向图                       有向图
               - B -                       -> B ->
             /       \                   /         \
            A         D                 A           D
             \       /                   \         /
               - C -                       -> C ->
            用邻接矩阵表示：               用邻接矩阵表示：
              A B C D                      A B C D
            A 0 1 1 0                    A 0 1 1 0
            B 1 0 0 1                    B 0 0 0 1
            C 1 0 0 1                    C 0 0 0 1
            D 0 1 1 0                    D 0 0 0 0
            用邻接表表示：                 用邻接表表示：
            A -> B -> C                  A - B - C
            B -> A -> D                  B - D
            C -> A -> D                  C - D
            D -> B -> C                  D - empty
     */

    // 顶点类
    public static class Vertex {

        // 顶点名称
        String name;
        // 顶点关联边
        List<Edge> edges;
        // 是否被访问过
        boolean visited = false;
        // 入度
        int inDegree = 0;
        // 拓扑排序状态
        int status = 0; // 0：未访问 / 1：访问中 / 2：访问过
        // 距离
        int distance = INF;
        // 无穷大
        static final Integer INF = Integer.MAX_VALUE;
        // 最短路径的上一个顶点
        Vertex prev;

        public Vertex(String name) {
            this.name = name;
        }

        public Vertex(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }

        public String getName() {
            return name;
        }

        public void setEdges(List<Edge> edges) {
            this.edges = edges;
        }

        public boolean getVisited() {
            return this.visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

    }

    // 边类
    public static class Edge {

        // 边的终点
        Vertex linked;
        // 权重
        int weight;

        public Edge(Vertex linked) {
            this.linked = linked;
            weight = 1;
        }

        public Edge(Vertex linked, int weight) {
            this.linked = linked;
            this.weight = weight;
        }

        public Vertex getLinked() {
            return linked;
        }
    }

    /**
     * 图的深度遍历(当一个顶点后有多个顶点关联，则优先遍历最先添加的那个顶点)
     * @param v 起始顶点
     */
    public static void dfs(@NotNull Vertex v) {
        /*
            深度遍历：从起始顶点开始，找到其下一个顶点并将visited属性设置为true表示已被遍历，之后依次寻找，
        等再无顶点时，则向前返回，同时查找之前的顶点有没有另外的路径可以走，有的话重复此过程，没有则继续向前
        返回，直至所有顶点均被遍历，最后还要修改所有顶点的visited为false
         */
        dfs_Test(v);
        amendment(v);
    }

    /**
     * 图的递归深度遍历(当一个顶点后有多个顶点关联，则优先遍历最先添加的那个顶点)
     * @param v 起始顶点
     */
    private static void dfs_Test(Vertex v) {
        v.setVisited(true);
        System.out.print(v.getName() + " ");
        for (Edge edge : v.edges) {
            if (!edge.getLinked().getVisited()) {
                dfs_Test(edge.getLinked());
            }
        }
    }

    /**
     * 图的深度遍历(当一个顶点后有多个顶点关联，则优先遍历最后添加的那个顶点)
     * @param v 起始顶点
     */
    public static void dfs2(@NotNull Vertex v) {
        LinkedList<Vertex> stack = new LinkedList<>();
        stack.push(v);
        while (!stack.isEmpty()) {
            Vertex pop = stack.pop();
            pop.visited = true;
            System.out.print(pop.name + " ");
            for (Edge edge : pop.edges) {
                if (!edge.linked.visited) {
                    // 此处入栈顺序为添加顺序，因此出栈顺序反之，要想相同则应倒着遍历
                    stack.push(edge.linked);
                }
            }
        }
        amendment(v);
    }

    /**
     * 图的广度遍历(当一个顶点后有多个顶点关联，则优先遍历最先添加的那个顶点)
     * @param v 起始顶点
     */
    public static void bfs(Vertex v) {
        /*
            广度遍历：按层级依次遍历图。
            将首层的顶点加入队列，之后弹出，并找到其下一层的顶点加入队列，循环此操作
         */
        LinkedList<Vertex> queue = new LinkedList<>();
        queue.offer(v);
        v.visited = true;
        while(!queue.isEmpty()) {
            Vertex poll = queue.poll();
            System.out.print(poll.name + " ");
            for (Edge edge : poll.edges) {
                if (!edge.linked.visited) {
                    edge.linked.visited = true;
                    queue.offer(edge.linked);
                }
            }
        }
        amendment(v);
    }

    /**
     * 自动修正所有顶点为未访问
     * @param v 起始顶点
     */
    private static void amendment(Vertex v) {
        v.setVisited(false);
        for (Edge edge : v.edges) {
            if (edge.getLinked().getVisited()) {
                amendment(edge.getLinked());
            }
        }
    }

    /**
     * 拓扑排序
     * @param v 图中的所有顶点
     */
    public static void topological(Vertex ...v) {
        List<Vertex> graph = new ArrayList<>(List.of(v));
        // 1.统计每个顶点的入度
        for (Vertex vertex : graph) {
            for (Edge edge : vertex.edges) {
                edge.linked.inDegree++;
            }
        }
        // 2.将入度为0的顶点加入队列
        LinkedList<Vertex> queue = new LinkedList<>();
        for (Vertex vertex : graph) {
            if (vertex.inDegree == 0) {
                queue.offer(vertex);
            }
        }
        // 3. 队列中不断移除顶点，每移除一个顶点，把它相邻顶点入度减1，若减到0则入队
        List<String> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            result.add(poll.name);
            for (Edge edge : poll.edges) {
                edge.linked.inDegree--;
                if (edge.linked.inDegree == 0) {
                    queue.offer(edge.linked);
                }
            }
        }
        /*
            判断是否有环，有环的顶点在加入队列时，只能按照之前的顶点判断入度是否--，而不能判断后面跟它构成
        环的顶点的入度，因此最后有环的顶点入度永远无法减到0，因此永远不会进入队列，包括该顶点之后的顶点
            因此只需判断两个集合size是否相等即可！
         */
        if (result.size() != graph.size()) {
            System.out.println("出现环");
        } else {
            for (String key : result) {
                System.out.print(key + " ");
            }
        }
    }

    /**
     * 拓扑排序dfs
     * @param v 图中的所有顶点
     */
    public static void topologicalDFS(Vertex ...v) {
        List<Vertex> graph = new ArrayList<>(List.of(v));
        LinkedList<String> stack = new LinkedList<>();
        for (Vertex vertex : graph) {
            dfsForTopological(vertex, stack);
        }
        System.out.print(stack);
    }

    /**
     * 用于拓扑排序的dfs
     * @param vertex 顶点
     * @param stack 栈
     */
    private static void dfsForTopological(Vertex vertex, LinkedList<String> stack) {
        if (vertex.status == 2) {
            return;
        }
        if (vertex.status == 1) {
            throw new RuntimeException("发现环");
        }
        vertex.status = 1;
        for (Edge edge : vertex.edges) {
            dfsForTopological(edge.linked, stack);
        }
        vertex.status = 2;
        stack.push(vertex.name);
    }

    /**
     * Dijkstra最短路径
     * @param vStart 起始顶点
     * @param v 图中的所有顶点
     */
    public static void dijkstra(Vertex vStart, Vertex ...v) {
        /*
        算法描述：
            1. 将所有顶点标记为未访问。创建一个未访问顶点的集合。
            2. 为每个顶点分配一个临时距离值
                  对于初始顶点，将其设置为零
                  对于所有其他顶点，将其设置为无穷大。
            3. 每次选择最小临时距离的未访问顶点，作为新的当前顶点
            4. 对于当前顶点，遍历其所有未访问的邻居，并更新它们的临时距离为更小
                  例如，1->6 的距离是 14，而1->3->6 的距离是11。这时将距离更新为 11
                  否则，将保留上次距离值
            5. 当前顶点的邻居处理完成后，把它从未访问集合中删除
         */
        // 创建集合
        ArrayList<Vertex> list = new ArrayList<>(List.of(v));
        // 分配临时距离
        vStart.distance = 0;
        while (!list.isEmpty()) {
            // 选取当前顶点
            Vertex curr = chooseMinDistanceVertex(list);
            // 更新当前顶点邻居距离
            updateNeighboursDistance(curr, list);
            // 移除当前顶点
            list.remove(curr);
        }
        for (Vertex vertex : v) {
            System.out.println(vertex.name + " " + vertex.distance + " " + (vertex.prev != null ? vertex.prev.name : "null"));
        }
    }

    // 寻找最小距离
    private static Vertex chooseMinDistanceVertex(ArrayList<Vertex> list) {
        Vertex min = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).distance < min.distance) {
                min = list.get(i);
            }
        }
        return min;
    }

    // 更新顶点到邻居的距离
    private static void updateNeighboursDistance(Vertex curr, ArrayList<Vertex> list) {
        for (Edge edge : curr.edges) {
            Vertex n = edge.linked;
            if (list.contains(n)) {
                int distance = curr.distance + edge.weight;
                if (distance < n.distance) {
                    n.distance = distance;
                    n.prev = curr;
                }
            }
        }
    }

    /**
     * Dijkstra最短路径优化
     * @param graph 图所有顶点的集合
     * @param source 起始顶点
     */
    public static void dijkstra(List<Vertex> graph, Vertex source) {
        /*
        改进 - 优先级队列
            1. 创建一个优先级队列，放入所有顶点（队列大小会达到边的数量）
            2. 为每个顶点分配一个临时距离值
                   对于初始顶点，将其设置为零
                   对于所有其他顶点，将其设置为无穷大。
            3. 每次选择最小临时距离的未访问顶点，作为新的当前顶点
            4. 对于当前顶点，遍历其所有未访问的邻居，并更新它们的临时距离为更小，若距离更新需加入队列
                   例如，1->6 的距离是 14，而1->3->6 的距离是11。这时将距离更新为 11
                   否则，将保留上次距离值
            5. 当前顶点的邻居处理完成后，把它从队列中删除
         */
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(v -> v.distance));
            // 优先级队列使用小顶堆，比较器设置为距离，最小的距离在堆最上面
        source.distance = 0;
        // 压入队列
        for (Vertex v : graph) {
            queue.offer(v);
        }
        while (!queue.isEmpty()) {
            // 3. 选取当前顶点
            Vertex curr = queue.peek();
            // 4. 更新当前顶点邻居距离
            if (curr != null && !curr.visited) {
                updateNeighboursDistance(curr, queue);
            }
            // 5. 移除当前顶点
            queue.poll();
        }
        for (Vertex v : graph) {
            System.out.println(v.name + " " + v.distance + " " + (v.prev != null ? v.prev.name : "null"));
        }
    }

    // 更新顶点到邻居的距离
    private static void updateNeighboursDistance(Vertex curr, PriorityQueue<Vertex> queue) {
        for (Edge edge : curr.edges) {
            Vertex n = edge.linked;
            if (queue.contains(n)) {
                int dist = curr.distance + edge.weight;
                if (dist < n.distance) {
                    n.distance = dist;
                    n.prev = curr;
                    queue.offer(n);
                }
            }
        }
    }



}
