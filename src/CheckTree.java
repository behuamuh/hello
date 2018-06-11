import java.util.Scanner;

public class CheckTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        TNode[] treeNodes = new TNode[n];
        if (n < 1) {
            System.out.println("CORRECT");
            return;
        }
        for (int i = 0; i < n; i++){
            treeNodes[i] = new TNode(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
        if (isCorrect(treeNodes))
            System.out.println("CORRECT");
        else
            System.out.println("INCORRECT");

    }
    public static boolean isCorrect(TNode[] tree){
        return check(tree, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public static boolean check(TNode[] tree, int index, int min, int max){
        if (index == -1)
            return true;
        TNode node = tree[index];
        if (node.key <= min || node.key > max)
            return false;
        return check(tree, node.left, min, node.key) && check(tree, node.right, node.key, max);

    }

}

class TNode{
    final int key;
    int left;
    int right;

    public TNode(int key, int left, int right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}
