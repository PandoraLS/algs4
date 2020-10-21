package algs4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 辅助数组，将两个有序数组合并
 */
public class Merge {
    private Merge() {}

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);

        // copy to aux[]                aux[] 是临时数组
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        // merge back to a[],先把小的放进a[k],遍历+赋值
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++){
            if      (i > mid)           a[k] = aux[j++];  // 如果i走完了,j就继续走
            else if (j > hi)            a[k] = aux[i++];  // 如果j走完了,i就继续走
            else if (aux[j] < aux[i])   a[k] = aux[j++];  // 将较小的值赋给a[k]
            else                        a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
    }

    private static void sort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(int[] a){
        int[] aux = new int[a.length];
        sort(a, aux, 0, a.length - 1);
        for (int i = 0; i < a.length; i ++){
            System.out.print(a[i] + "  ");
        }
        System.out.println();
        assert isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (a[i] < a[i-1]) return false;
        return true;
    }

    public static void main (String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyW.txt");
        String s = Files.readString(path);
        String[] strings = s.split("\\s+");
        int[] vals = new int[strings.length];
        for (int i = 0; i < strings.length; i++){
            vals[i] = Integer.parseInt(strings[i]);
        }
        sort(vals);
    }
}
