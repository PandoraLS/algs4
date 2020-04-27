package algs4;
import algs4.Graph;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DepthFirstSearch {
    private boolean[] marked; // 用于记录节点是否走过
    private int count;      // 与s相连的顶点

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        count++; // 用于统计和v连通的顶点的个数
        marked[v] = true; //该顶点是否走过
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyCG_不连通图.txt");
        Graph G = new Graph(path);
        System.out.println(G.toString());

        int s = 0;
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                System.out.println(v + " ");
        }
        System.out.println("验证是否连通");
        if (search.count() != G.V()){
            System.out.println("Not connected");
            System.out.println("以" + s + "为起点的连通部分search.count: " + search.count + "  G.V(): " + G.V());
        }else {
            System.out.println("connected");
        }
    }

}
