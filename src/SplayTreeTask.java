import java.io.*;
import java.util.*;

public class SplayTreeTask {
    final static long MOD = 1000000001L;
    static long sum = 0;
    public static int f(long x) {
        return (int)((sum + x) % MOD);
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        SplayTree tree = new SplayTree();
        for (int i = 0; i < n; i++){
            String command = scanner.next();
            if ("+".equals(command))
                tree.insert(f(scanner.nextLong()));
            if ("-".equals(command))
                tree.remove(f(scanner.nextLong()));
            if ("?".equals(command))
                if (tree.check(f(scanner.nextLong())))
                    System.out.println("Found");
                else
                    System.out.println("Not found");
            if ("s".equals(command)){
                sum = tree.sum(f(scanner.nextLong()), f(scanner.nextLong()));
                System.out.println(sum);
            }
        };
    }

}
class SNode {
    public long sum;
    public int key;
    public SNode left, right, parent;

    public SNode(int key) {
        this.key = key;
        this.sum = key;
        this.parent = this.left = this.right = null;
    }

    public SNode(int key, SNode parent) {
        this.key = key;
        this.sum = key;
        this.parent = parent;
        this.left = this.right = null;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public void setRight(SNode v) {
        if (v != null) {
            v.parent = this;
        }
        this.right = v;
    }

    public void setLeft(SNode v) {
        if (v != null) {
            v.parent = this;
        }
        this.left = v;
    }
}

class SplayTree {
    private SNode root;

    SplayTree() {
        this.root = null;
    }

    SplayTree(SNode root) {
        this.root = root;
        if (this.root != null) {
            this.root.parent = null;
            updateSum(this.root);
        }
    }

    public boolean check(int v) {
        boolean r = false;
        if (root != null) {
            r = checkInternal(root, v);
        }

        return r;
    }

    public void insert(int v) {
        if (root != null) {
            insertInternal(root, v);
        } else {
            root = new SNode(v);
        }
    }

    public void remove(int v) {
        if (root != null) {
            SNode u = removeInternal(root, v);
            if (u != null) {
                if (u.left != null) {
                    u.left.parent = null;
                }

                if (u.right != null) {
                    u.right.parent = null;
                }

                root = merge(u.left, u.right);
            }
        }
    }

    public long sum(int from, int to) {
        long sum = 0;
        SplayTree[] trees = split(from-1);
        SplayTree[] subTrees = trees[1].split(to);

        if (subTrees[0].root != null) {
            sum = subTrees[0].root.sum;
        }

        trees[1].root = merge(subTrees[0].root, subTrees[1].root);
        root = merge(trees[0].root, trees[1].root);

        return sum;
    }

    private SNode removeInternal(SNode root, int v) {
        SNode r = null;
        if (root != null) {
            if (root.key > v) {
                r = removeInternal(root.left, v);
            } else if (root.key < v) {
                r = removeInternal(root.right, v);
            } else {
                splay(root);
                r = root;
            }
        }

        return r;
    }

    private void insertInternal(SNode root, int v) {
        if (v < root.key) {
            if (root.left != null) {
                insertInternal(root.left, v);
            } else {
                root.left = new SNode(v, root);
                //      bubbleSum(root, v);
                splay(root.left);
            }
        } else if (v > root.key) {
            if (root.right != null) {
                insertInternal(root.right, v);
            } else {
                root.right = new SNode(v, root);
                //    bubbleSum(root, v);
                splay(root.right);
            }
        }
    }

    private boolean checkInternal(SNode root, int v) {
        boolean r = false;

        if (v < root.key) {
            if (root.left != null) {
                r = checkInternal(root.left, v);
            } else {
                splay(root);
            }
        } else if (v > root.key) {
            if (root.right != null) {
                r = checkInternal(root.right, v);
            } else {
                splay(root);
            }
        } else {
            r = true;
            splay(root);
        }

        return r;
    }

    private SplayTree[] split(int k) {
        SplayTree[] ans = new SplayTree[2];
        if (root != null) {
            checkInternal(root, k);
            if (root.key <= k) {
                ans[1] = new SplayTree(root.right);
                root.right = null;
                ans[0] = new SplayTree(root);
            } else {
                ans[0] = new SplayTree(root.left);
                root.left = null;
                ans[1] = new SplayTree(root);
            }
        } else {
            ans[0] = new SplayTree();
            ans[1] = new SplayTree();
        }
        return ans;
    }

    private SNode merge(SNode left, SNode right) {
        SNode t = right;
        if (left != null) {
            t = left;
            while (t.right != null) {
                t = t.right;
            }

            splay(t);
            t.setRight(right);
            updateSum(t);
        }

        return t;
    }

    private void splay(SNode v) {
        if (!v.isRoot()) {
            if (v.parent.left == v) {
                if (v.parent.isRoot()) {
                    zigLeft(v);
                } else if (v.parent.parent.left == v.parent) {
                    zigZigLeft(v);
                } else {
                    zigZagLeft(v);
                }
            } else {
                if (v.parent.isRoot()) {
                    zigRight(v);
                } else if (v.parent.parent.right == v.parent) {
                    zigZigRight(v);
                } else {
                    zigZagRight(v);
                }
            }

            splay(v);
        }
        root = v;
    }

    private void bubbleSum(SNode root, int v) {
        while (root != null) {
            root.sum += v;
            root = root.parent;
        }
    }

    private void updateSum(SNode v) {
        if (v != null) {
            v.sum = v.key;
            if (v.left != null) {
                v.sum += v.left.sum;
            }

            if (v.right != null) {
                v.sum += v.right.sum;
            }
        }
    }

    private void zigLeft(SNode v) {
        SNode t = v.right;
        SNode p = v.parent;

        v.setRight(p);
        p.setLeft(t);
        v.parent = null;

        updateSum(p);
        updateSum(v);
    }

    private void zigRight(SNode v) {
        SNode t = v.left;
        SNode p = v.parent;

        v.setLeft(p);
        p.setRight(t);
        v.parent = null;

        updateSum(p);
        updateSum(v);
    }

    private void zigZigLeft(SNode v) {
        SNode a = v.parent;
        SNode b = a.parent;

        if (b.parent !=  null) {
            if (b.parent.left == b) {
                b.parent.setLeft(v);
            } else {
                b.parent.setRight(v);
            }
        } else {
            v.parent = null;
        }

        a.setLeft(v.right);
        b.setLeft(a.right);
        v.setRight(a);
        a.setRight(b);

        updateSum(b);
        updateSum(a);
        updateSum(v);
    }

    private void zigZigRight(SNode v) {
        SNode a = v.parent;
        SNode b = a.parent;

        if (b.parent !=  null) {
            if (b.parent.left == b) {
                b.parent.setLeft(v);
            } else {
                b.parent.setRight(v);
            }
        } else {
            v.parent = null;
        }

        a.setRight(v.left);
        b.setRight(a.left);
        v.setLeft(a);
        a.setLeft(b);

        updateSum(b);
        updateSum(a);
        updateSum(v);
    }

    private void zigZagLeft(SNode v) {
        SNode b = v.parent;
        SNode a = b.parent;

        if (a.parent !=  null) {
            if (a.parent.left == a) {
                a.parent.setLeft(v);
            } else {
                a.parent.setRight(v);
            }
        } else {
            v.parent = null;
        }

        a.setRight(v.left);
        b.setLeft(v.right);
        v.setLeft(a);
        v.setRight(b);

        updateSum(b);
        updateSum(a);
        updateSum(v);
    }

    private void zigZagRight(SNode v) {
        SNode b = v.parent;
        SNode a = b.parent;

        if (a.parent != null) {
            if (a.parent.left == a) {
                a.parent.setLeft(v);
            } else {
                a.parent.setRight(v);
            }
        } else {
            v.parent = null;
        }

        a.setLeft(v.right);
        b.setRight(v.left);
        v.setLeft(b);
        v.setRight(a);

        updateSum(b);
        updateSum(a);
        updateSum(v);
    }
}