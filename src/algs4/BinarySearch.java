package algs4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class BinarySearch {
    private BinarySearch() {}

    /**
     * 二分查找，返回下标，查不到就返回-1
     * @param a 数组
     * @param key 待查找的数字
     * @return 查找数字的下标，查不到就返回-1
     */
    public static int indexOf(int[] a, int key) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(algs4DataConfig.root_dir, "tinyW.txt");
        String s = Files.readString(path);
        String[] strings = s.split("\\s+");
        int[] vals = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            vals[i] = Integer.parseInt(strings[i]);
        }
        Arrays.sort(vals);
        assert BinarySearch.indexOf(vals, 11) == 1: "二分查找失败";
    }
}
