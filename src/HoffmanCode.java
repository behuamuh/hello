import java.util.*;

public class HoffmanCode {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        CodeTree tree = new CodeTree();
        tree.print(input);


    }
}
class CodeTree{
    private int size = 0;
    private Node root = new Node('R');
    public void print(String input){
        load(input);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            result.append(getCode(c));
        }
        System.out.println(size + " " + result.length());
        printTree();
        System.out.println(result.toString());
    }
    private void printTree(){
        Node point = root;
        while (true){
            if (point.left == null)
                return;
            else{
                char sym = point.left.sym;
                System.out.println(sym + ": " + getCode(sym));
            }
            if (point.rigth == null)
                return;

            else {
                char sym = point.rigth.sym;
                if (sym == 'U')
                    return;
                System.out.println(sym + ": " + getCode(sym));
            }
            point = point.rigth;

        }
    }
    private void load(String input){
        if (input == null)
            return;
        input = input.replaceAll("![a-z]", "");
        if (input.length() == 0)
            return;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if (map.containsKey(c))
                map.replace(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }
        Queue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        queue.addAll(map.entrySet());
        while (!queue.isEmpty()){
            add(new Node(queue.poll().getKey()));
        }
    }
    private void add(Node node){
        if (node == null)
            return;
        size++;
        Node point = root;
        while (true){
            if (point.left == null){
                point.left = node;
                break;
            }
            if (point.rigth == null){
                point.rigth = node;
                break;
            }
            point = point.rigth;
        }
    }
    public String getCode(char sym){
        StringBuilder result = new StringBuilder();
        Node point = root;
        while (true){
            if (point.left == null)
                break;
            if (point.left.sym == sym){
                result.append(0);
                return result.toString();
            }
            if (point.rigth == null)
                break;
            if (point.rigth.sym == sym){
                result.append(1);
                return result.toString();
            }
            point = point.rigth;
            result.append(1);
        }
        return "";
    }
}
class Node{
    char sym;
    Node rigth;
    Node left;


    public Node(char sym) {
        this.sym = sym;
    }

}