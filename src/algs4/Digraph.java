package algs4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Digraph {
    private int V;  // 有向图中的顶点数
    private int E;          // 有向图中边的数目
    private ArrayList<ArrayList<Integer>> adj; // adj[v]表示顶点v的邻接表
    private int[] indegree;  // indegree[v] 顶点v的入度

    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("顶点数量必须是非负的");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = new ArrayList<ArrayList<Integer>>();
        for (int v = 0; v < V; v++) {
            adj.add(new ArrayList<>());
        }
    }

    public Digraph(Path path) throws IOException {
        String s = Files.readString(path);
        String[] strings = s.split("\\s+");
        V = Integer.parseInt(strings[0]); // 顶点数
        int edge_cnt = Integer.parseInt(strings[1]); // 边数
        indegree = new int[V];
        adj = new ArrayList<>();
        for (int v = 0; v < V; v++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 2; i < strings.length; i += 2) {
            int v = Integer.parseInt(strings[i]);
            int w = Integer.parseInt(strings[i+1]);
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

    public void addEdge(int v, int w) {
        adj.get(v).add(w);
        indegree[w]++;
        E++;
    }

    public int outdegree(int v) {
        return adj.get(v).size();
    }

    public int indegree(int v) {
        return indegree[v];
    }

    public ArrayList<Integer> adj (int v) {return adj.get(v);}

    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj.get(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " 个顶点, " + E + " 条边 \n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj.get(v)) {
                s.append(w + " ");
            }
            s.append('\n');
        }
        return s.toString();
    }

    public static void main(String[] args) throws IOException{
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyDG.txt");
        Digraph G = new Digraph(path);
        System.out.println(G.toString());
        System.out.println("有向图反转:");
        System.out.println(G.reverse().toString());
    }

}
