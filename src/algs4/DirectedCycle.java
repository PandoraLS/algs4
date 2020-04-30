package algs4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class DirectedCycle {
    private boolean[] marked;   // marked[v] 顶点v是否被标记过
    private int[] edgeTo;       // edgeTo[w]=v 表示的是w节点的上一个顶点是v
    private boolean[] onStack;  // onStack[v] 顶点v是否在栈中？
    private Stack<Integer> cycle; // 有向环 (null表示没有环)

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null)
                dfs(G, v);
    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return; // 如果无环，直接return
            else if (!marked[w]) {  // 如果没有被走过,则mark一下继续dfs
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) { // 如果w在栈中[被走过],则trace back有向环,这个过程最好手动画一下
                cycle = new Stack<>(); //新建Stack
                for (int x = v; x != w; x = edgeTo[x]) { //将[v,w)添加到新的stack中
                    cycle.push(x);
                }
                cycle.push(w); // 将w添加进去stack
                cycle.push(v); // 将v添加到stack中,这样就构成了一个完整的cycle
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Stack<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyDG.txt");
        Digraph G = new Digraph(path);
        System.out.println(G.toString());

        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            System.out.println("有向图 cycle: ");
            for (int v : finder.cycle()) {
                System.out.print(v + " ");
            }
            System.out.println();
        } else {
            System.out.println("有向图中无环");
        }
        System.out.println();
    }
}
