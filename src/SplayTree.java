public class SplayTree {
    private SNode root;
    public void find(long key){
        SNode point = root;
        while (point.right != null && point.left != null){
            if (key == point.key){
                System.out.println("Found");
                splay(point);
                return;
            }
            if (key > point.key) {
                if (point.right != null)
                    point = point.right;
                else {
                    System.out.println("Not found");
                    splay(point);
                    return;
                }
            }
            else {
                if (point.left != null)
                    point = point.left;
                else {
                    System.out.println("Not found");
                    splay(point);
                    return;
                }
            }
        }

    }
    private void splay(SNode node){
        if (node == null) return;
        SNode parent = node.parent;
        while (parent != null){
            SNode grandPa = parent.parent;
            if (grandPa == null){
                if (node == parent.left)
                    zig(node);
                else
                    zag(node);
            }
            else {
                if (parent == grandPa.left){
                    if (node == parent.left)
                        zigzig(node);
                    else
                        zagzig(node);
                }
                else {
                    if (node == parent.left)
                        zigzag(node);
                    else
                        zagzag(node);
                }
            }
            parent = node.parent;
        }
        root = node;
    }
    private void zig(SNode node){
        SNode parent = node.parent;
        root = node;
        node.parent = null;
        parent.left = node.right;
        node.right.parent = parent;
        node.right = parent;
        parent.parent = node;
    }
    private void zag(SNode node){
        SNode parent = node.parent;
        root = node;
        node.parent = null;
        parent.right = node.left;
        node.left.parent = parent;
        node.right = parent;
        parent.parent = node;
    }
    private void zigzig(SNode node){
        SNode parent = node.parent;
        SNode grandPa = parent.parent;
        if (grandPa.parent != null){
            if (grandPa == grandPa.parent.left)
                grandPa.parent.left = node;
            else
                grandPa.parent.right = node;
        }
        node.parent = grandPa.parent;
        grandPa.left = parent.right;
        parent.right.parent = grandPa;
        parent.left = node.right;
        node.right.parent = parent;
        parent.right = grandPa;
        grandPa.parent = parent;
        node.right = parent;
        parent.parent = node;
    }
    private void zagzag(SNode node){
        SNode parent = node.parent;
        SNode grandPa = parent.parent;
        if (grandPa.parent != null){
            if (grandPa == grandPa.parent.left)
                grandPa.parent.left = node;
            else
                grandPa.parent.right = node;
        }
        node.parent = grandPa.parent;
        grandPa.right = parent.left;
        parent.left.parent = grandPa;
        parent.right = node.left;
        node.left.parent = parent;
        parent.left = grandPa;
        grandPa.parent = parent;
        node.left = parent;
        parent.parent = node;
    }
    private void zigzag(SNode node){
        SNode parent = node.parent;
        SNode grandPa = parent.parent;
        if (grandPa.parent != null){
            if (grandPa == grandPa.parent.left)
                grandPa.parent.left = node;
            else
                grandPa.parent.right = node;
        }
        node.parent = grandPa.parent;
        grandPa.right = node.left;
        node.left.parent = grandPa;
        parent.left = node.right;
        node.right.parent = parent;
        grandPa.parent = node;
        parent.parent = node;
        node.left = grandPa;
        node.right = parent;
    }
    private void zagzig(SNode node){
        SNode parent = node.parent;
        SNode grandPa = parent.parent;
        if (grandPa.parent != null){
            if (grandPa == grandPa.parent.left)
                grandPa.parent.left = node;
            else
                grandPa.parent.right = node;
        }
        node.parent = grandPa.parent;
        grandPa.left = node.right;
        node.right.parent = grandPa;
        parent.right = node.left;
        node.left.parent = parent;
        grandPa.parent = node;
        parent.parent = node;
        node.right = grandPa;
        node.left = parent;
    }
    public static void main(String[] args) {

    }
}
class SNode{
    final long key;
    SNode parent;
    SNode left;
    SNode right;

    public SNode(long key) {
        this.key = key;
    }
}