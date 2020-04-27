package algs4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 快排思路: 从数组中随机选一个数(最后一个)，比它小的放左边，比它大的放右边(交换)
 * 然后再在左右区域分别执行此操作
 * (交换)是通过左边的大数与右边的小数交换
 */
public class Quick {
    private Quick() {}

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
        assert isSorted(a, lo, hi);
    }

    private static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (a[i] < a[i-1]) return false;
        return true;
    }
    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j
    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {
            while (a[++i] < v){ //find item on lo to swap
                if (i == hi) break;
            }

            while (v < a[--j]){ // find右边swap的
                if (j == lo) break;
            }

            // 检查指针是否接触
            if (i >= j) break;

            exch(a, i, j);
        }

        // 这一步实际上是把随机选的那个数字与j所指的数字交换
        exch(a, lo, j);

        // 交换之后 a[lo..j-1] <= a[j] <= a[j+1..hi]
        return j;
    }

    // exchange a[i] and a[j]
    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void sort(int[] a) {
        System.out.println("原数组： ");
        for (int a_i: a){
            System.out.print(a_i + "  ");
        }
        System.out.println();
        System.out.println("排序后： ");
        sort(a, 0, a.length - 1);
        for (int a_i: a){
            System.out.print(a_i + "  ");
        }
        System.out.println();
        assert isSorted(a, 0, a.length - 1);
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyW.txt");
        String s = Files.readString(path);
        String[] string = s.split("\\s+");
        int[] vals = new int[string.length];
        for (int i = 0; i < string.length; i++){
            vals[i] = Integer.parseInt(string[i]);
        }

        sort(vals);
    }

}
