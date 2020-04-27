package algs4;

import java.io.IOException;
import java.util.Scanner;

/**
 * 由于是抄写的，可能相对书中有一定的简化
 */

public class UF {
    private int[] parent; // 分量id(以触点作为索引)
    private int[] rank;  // rank[i] 子树在i处的rank
    private int count;      // 分量的数量

    public UF(int n) {
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++){
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find (int p){
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];  // 如果数组下标和该下标的值相等
            p = parent[p];
        }
        return p;
    }

    public int count() {
        return count;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // 把小树添加到大树上
        if          (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
        else if     (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
        else{
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        UF uf = new UF(n);
        while (sc.hasNext()) {
            // sc终止输入windows下用Ctrl+Z，Unix下用Ctrl+D
            // 但是我不晓得为何我这里(windows 7)得用Ctrl+D
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (uf.find(p) == uf.find(q)) {
                System.out.println("already connected: " + p + " " + q);
                continue;
            }
            uf.union(p, q);
            System.out.println("union: " + p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
}


/*
10
4 3
4  3
3 8
3  8
6 5
6  5
9 4
9  4
2 1
2  1
8 9
5 0
5  0
7 2
7  2
6 1
6  1
1 0
 6 7
^D
2 components

Process finished with exit code 0
*/