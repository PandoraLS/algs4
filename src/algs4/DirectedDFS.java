package algs4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectedDFS {
    private boolean[] marked;       //marked[v]=true 表示 源点s可以到达顶点v
    private int count;    // 有多少个源点s可以到达顶点v,这里面并没有写,

    /**
     * 源点s深度优先搜索
     * @param G : 有向图 G
     * @param s : 起点 s
     */
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Digraph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)){
            if (!marked[w])
                dfs(G, w);
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyDG.txt");
        Digraph G = new Digraph(path);
        System.out.println(G.toString());

        int s = 0;  //源点s
        DirectedDFS dfs = new DirectedDFS(G, s);
        System.out.println("Directed Graph Depth First Paths ...");

        for (int  v = 0; v < G.V(); v++) {
            if (dfs.marked(v))
                System.out.print(v + " ");
        }
        System.out.println();
    }
}
