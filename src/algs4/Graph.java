package algs4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    // 换行符,功能与"\n"相同,只是这么做可以避免windows和linux之间的区别

    private int V;
    private int E;
    private ArrayList<ArrayList<Integer>> adj;

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("顶点数量必须非负");
        this.V = V;
        this.E = 0;
        adj = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    public Graph(Path path) throws IOException {
        String s = Files.readString(path);
        String[] strings = s.split("\\s+"); // 该正则表达式匹配任意非空字符
        V = Integer.parseInt(strings[0]);  // 顶点数
        int edge_cnt = Integer.parseInt(strings[1]);  // 边数
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 2; i < strings.length; i += 2) {
            int v = Integer.parseInt(strings[i]);
            int w = Integer.parseInt(strings[i + 1]);
            edge_cnt += 1;
            addEdge(v, w);
        }
        assert edge_cnt == E : "读取的边数不匹配";
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void addEdge(int v, int w) {
        E++;
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    public int degree(int v) {
        return adj.get(v).size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " 个顶点, " + E + " 条边 " + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj.get(v)) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public ArrayList<Integer> adj(int v) {
        return adj.get(v);
    }

    public static void main (String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyG.txt");
        Graph G = new Graph(path);
        System.out.println(G.toString());
    }

}
