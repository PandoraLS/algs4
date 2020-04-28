package algs4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Stack;

public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public ArrayList<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        // path因为是压入到栈中,所以需要先逆序弹出
        ArrayList<Integer> res_path = new ArrayList<>();
        while (!path.empty()) {
            res_path.add(path.pop());
        }
        return res_path;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyCG.txt");
        Graph G = new Graph(path);
        System.out.println(G.toString());

        int s = 0;
        DepthFirstPaths dfPath = new DepthFirstPaths(G, s);
        System.out.println("Depth First Pahts.....");
        for (int v = 0; v < G.V(); v++) {
            if (dfPath.hasPathTo(v)) {
                System.out.printf("%d to %d: ", s, v);
                for (int x : dfPath.pathTo(v)) {
                    if (x == s) System.out.print(" " + x);
                    else        System.out.print(" " + x);
                }
                System.out.println();
            }
            else{
                System.out.printf("%d to %d: not connected\n", s, v);
            }
        }
    }
}
