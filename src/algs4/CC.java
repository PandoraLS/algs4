package algs4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CC {
    private boolean[] marked; // marked[v] 标记v是否走过
    private int[] id;  // id[v] 包含v的连通分量
    private int[] size;  // size[id] 给定连通分量的数量
    private int count; // 第count个连通分量

    /**
     * 计算无向图的连通分量
     * @param G : 图G的连通分量
     */
    public CC(Graph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    /**
     * depth-first search for Graph
     * @param G : 图
     * @param v : 顶点v
     */
    private void dfs(Graph G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public int id(int v) {
        return id[v];
    }

    public int size(int v) {
        return size[id[v]];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyG.txt");
        Graph G = new Graph(path);
        CC cc = new CC(G);

        int m = cc.count(); // 图中的连通分量的个数
        System.out.println(m + " 个连通分量");

        // 计算每个子图的连通分量
        ArrayList<Integer>[] components = (ArrayList<Integer>[]) new ArrayList[m];
        for (int i = 0; i < m; i++)
            components[i] = new ArrayList<Integer>();
        for (int v = 0; v < G.V(); v++)
            components[cc.id[v]].add(v);

        // 输出结果
        for (int i = 0; i < m; i++) {
            for (int v : components[i]){
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }
}
