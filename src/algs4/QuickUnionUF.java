package algs4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
QuickUnionUF.java并查集基本代码
后续会对find和union分别优化
find优化：路径压缩(所有节点直接接到root上)
union优化：加权(小树接到大树上)
*/
public class QuickUnionUF {
    private int[] parent;
    private int count;

    public QuickUnionUF(int n) {
        parent = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int count(){
        return count;
    }

    public int find(int p) {
        // 跟随链接找到根节点
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
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
        QuickUnionUF uf = new QuickUnionUF(n);
        long startTime = System.currentTimeMillis(); // 获取开始时间
        for (int i = 1; i < strings.length; i += 2) {
            int p = Integer.parseInt(strings[i]);
            int q = Integer.parseInt(strings[i+1]);
            if (uf.find(p) == uf.find(q)){
                System.out.println("already connected: " + p + " " + q);
                continue;
            }
            uf.union(p, q);
            System.out.println("union: " + p + " " + q);
        }
        System.out.println(uf.count() + " componets");
        long endTime = System.currentTimeMillis(); // 获取结束时间
        System.out.println("程序运行时间： " + (endTime - startTime) + " ms");
        // 程序运行时间： **** ms 在largeUF.txt运行的时间,1分钟都没跑完
    }

}
