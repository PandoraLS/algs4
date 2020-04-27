import algs4.BinarySearch;

public class hello {

    public static void main(String[] args) {
        // write your code here
        System.out.println("hello");
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i * 2;
        }

        System.out.println(BinarySearch.indexOf(a, 2));
        System.out.println(BinarySearch.indexOf(a, 3));
    }
}
