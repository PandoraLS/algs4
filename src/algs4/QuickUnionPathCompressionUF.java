package algs4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
实际应用中的首选方式,路径压缩,如果需要进一步
*/
public class QuickUnionPathCompressionUF {
    private int[] parent;
    private int count;

    public QuickUnionPathCompressionUF(int n) {
        count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    public int count() {
        return count;
    }

    public int find(int p) {
        int root = p;
        while (root != parent[root]) // 找到树的根
            root = parent[root];
        while (p != root) { // 把当前节点直接接到root上
            int newp = parent[p];  // 先保存当前节点的父节点
            parent[p] = root;  // 当前节点p接到root上
            p = newp; // 当前节点变成其父节点(准备下一轮运算)
        }
        return root;
    }

    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent[rootP] = rootQ; // 这样会导致树越来越高,find越来越慢
        count--;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "largeUF.txt");
        String s = Files.readString(path);
        String[] strings = s.split("\\s+");
//        for (String string : strings) {
//            System.out.println(string);
//        }
        assert (strings.length & 1) == 1;
        int n = Integer.parseInt(strings[0]);
        QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(n);
        long startTime = System.currentTimeMillis(); // 获取开始时间
        for (int i = 1; i < strings.length; i += 2) {
            int p = Integer.parseInt(strings[i]);
            int q = Integer.parseInt(strings[i + 1]);
            if (uf.find(p) == uf.find(q)) {
                System.out.println("already connected: " + p + " " + q);
                continue;
            }
            uf.union(p, q);
            System.out.println("union: " + p + " " + q);
        }
        System.out.println(uf.count() + " componets");
        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + " ms");
        // 程序运行时间： 10926 ms 在largeUF.txt运行的时间
    }
}
