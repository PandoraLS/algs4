package algs4;

/**
 * 参考：https://www.geeksforgeeks.org/binary-search-tree-data-structure/
 */
public class BinarySearchTree {

    // 包含左右孩子树节点的Node,其值是key
    static class Node {
        int key;
        Node left, right;
        public Node(int item) {
            key = item;
            left = right = null;
        }
    }

    Node root; // 根节点

    BinarySearchTree() {
        root = null;
    }

    // 该函数调用insertRec()
    void insert(int key) {
        root = insertRec(root, key);
    }

    // 将新的key插入BST
    private Node insertRec(Node root, int key) {
        if (root == null) { // 空树返回新节点
            root = new Node(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    // 该函数主要调用InorderRec()
    void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    public Node search(int key) {
        return searchRec(root, key);
    }


    private Node searchRec(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        // 待查找值较小
        if (key < root.key)
            return searchRec(root.left, key);

        // 待查找值较大
        return searchRec(root.right, key);
    }

    public void delete(int key) {
        root = deleteRec(root, key);
    }

    private Node deleteRec(Node root, int key) {
        if (root == null) return root; //没查到的情况
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        // 如果key与root.key相等,该节点会被删除
        else{
            // 该节点有0或1个child,直接返回其子节点(这其实是把当前节点删除,子节点不上来)
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            // 该节点有2个child的情况,则找到右子树最小节点来替换此节点
            root.key = minValue(root.right);

            // 删除右子树最小的节点
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    public int minValue(Node root){
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        /* Let us create following BST
              50
           /     \
          30      70
         /  \    /  \
       20   40  60   80 */
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        System.out.println("给定树的中序遍历：");
        tree.inorder();

        System.out.println("\n 删除 20");
        tree.delete(20);
        System.out.println("\n给定树的中序遍历：");
        tree.inorder();

        System.out.println("\n 删除 30");
        tree.delete(30);
        System.out.println("\n给定树的中序遍历：");
        tree.inorder();

        System.out.println("\n 插入 35");
        tree.insert(35);
        System.out.println("\n给定树的中序遍历：");
        tree.inorder();

        Node res = tree.search(1);
        if (res != null)
            System.out.println("res的值:" + res.key);
        else
            System.out.println("没有查到数字：" + 1);

        Node res2 = tree.search(35);
        if (res2 != null)
            System.out.println("res的值:" + res2.key);
        else
            System.out.println("没有查到数字：" + 35);
    }
}
