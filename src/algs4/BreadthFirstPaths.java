package algs4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo; // edgeTo[w]=v表示的是w节点的上一个顶点是v
    private int[] distTo; // s到v最短路径有多长
    private final int s;  // bfs的起点

    /**
     * 无向图的广度优先搜索
     * @param G: Graph
     * @param s: 无向图搜索的起点
     */
    public BreadthFirstPaths(Graph G, int s) {
        this.s = s;
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        LinkedList<Integer> q = new LinkedList<Integer>();  //用链表表示队列q
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.add(s); // 入队

        while (!q.isEmpty()) {
            int v = q.poll(); // 出队
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1; // 路径长度加1
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int distTo(int v) {
        return distTo[v];
    }

    /**
     * 起点s到节点v的路径
     * @param v
     * @return
     */
    public ArrayList<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        // 从v节点一直向前(s)找路径
        // 这样找到的路径是从v到s的路径
        for (int x = v; x != s; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);

        // path是从v到s的，逐个弹出之后就是s->v的路径了
        ArrayList<Integer> res_path = new ArrayList<>();
        while (!path.empty()) {
            res_path.add(path.pop());
        }
        return res_path;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyCG.txt");
        Graph G = new Graph(path);
        System.out.println(G.toString()); //toString()的输出节点顺序是按照连接点的先后顺序

        int s = 0; //广度优先搜索的起点
        BreadthFirstPaths bfPath = new BreadthFirstPaths(G, s);
        System.out.println("Bread First Paths...");

        for (int v = 0; v < G.V(); v++) {
            if (bfPath.hasPathTo(v)) {
                System.out.printf("%d to %d: ", s, v);
                ArrayList<Integer> tmp_path = bfPath.pathTo(v);
                for (int x : tmp_path) {        // 将s到v路径上的点逐个输出
//                    System.out.printf(" " + x);
                    if (x == s) System.out.printf(" " + x);
                    else System.out.printf("-" + x);
                }
                System.out.println();
            } else {
                System.out.printf("%d to %d: not connected\n", s, v);
            }
        }
    }
}


/*
6 个顶点, 8 条边
0: 5 1 2
1: 2 0
2: 4 3 1 0
3: 2 4 5
4: 2 3
5: 0 3

Bread First Paths...
0 to 0:  0
0 to 1:  0-1
0 to 2:  0-2
0 to 3:  0-5-3
0 to 4:  0-2-4
0 to 5:  0-5
*/
