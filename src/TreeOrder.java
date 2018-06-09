import java.util.Scanner;

public class TreeOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        TreeNode[] treeNodes = new TreeNode[n];
        for (int i = 0; i < n; i++){
            treeNodes[i] = new TreeNode(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
        inOrder(treeNodes, treeNodes[0]);
        System.out.println();
        preOrder(treeNodes, treeNodes[0]);
        System.out.println();
        postOrder(treeNodes, treeNodes[0]);
        System.out.println();
    }
    public static void inOrder(TreeNode[] tree, TreeNode node){
        if (node.left != -1)
            inOrder(tree, tree[node.left]);
        System.out.print(node.key + " ");
        if (node.right != -1)
            inOrder(tree, tree[node.right]);
    }
    public static void preOrder(TreeNode[] tree, TreeNode node){
        System.out.print(node.key + " ");
        if (node.left != -1)
            preOrder(tree, tree[node.left]);
        if (node.right != -1)
            preOrder(tree, tree[node.right]);
    }
    public static void postOrder(TreeNode[] tree, TreeNode node){
        if (node.left != -1)
            postOrder(tree, tree[node.left]);
        if (node.right != -1)
            postOrder(tree, tree[node.right]);
        System.out.print(node.key + " ");
    }
}

class TreeNode{
    final int key;
    int left;
    int right;

    public TreeNode(int key, int left, int right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}