import java.math.BigInteger;
import java.util.Scanner;

public class MyHashStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        MyHash hashTable = new MyHash(m);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++){
            String oper = scanner.next();
            if ("add".equals(oper)){
                hashTable.add(scanner.next());
            }
            if ("find".equals(oper)){
                hashTable.find(scanner.next());
            }
            if ("del".equals(oper)){
                hashTable.dell(scanner.next());
            }
            if ("check".equals(oper)){
                hashTable.check(scanner.nextInt());
            }
        }
    }
}
class MyHash{
    MyNode[] table;
    private int m;
    private final int p = 1000000007;
    public MyHash(int m) {
        this.m = m;
        table = new MyNode[m];
    }

    public void add (String string){
        int index = hash(string);
        MyNode node = new MyNode(string);
        MyNode pivot = table[index];
        while (pivot != null){
            if (pivot.data.equals(string))
                return;
            pivot = pivot.next;
        }
        node.next = table[index];
        table[index] = node;
    }
    public void dell (String string){
        int index = hash(string);
        MyNode pivot = table[index];
        if (pivot == null)
            return;
        if (pivot.data.equals(string)){
            table[index] = pivot.next;
            return;
        }
        while (pivot.next != null){
            if (pivot.next.data.equals(string)) {
                pivot.next = pivot.next.next;
                return;
            }
            pivot = pivot.next;
        }
    }
    public void find (String string){
        int index = hash(string);
        MyNode pivot = table[index];
        while (pivot != null){
            if (pivot.data.equals(string)) {
                System.out.println("yes");
                return;
            }
            pivot = pivot.next;
        }
        System.out.println("no");
    }
    public void check (int index){
        MyNode pivot = table[index];
        StringBuilder builder = new StringBuilder();
        while (pivot != null){
            builder.append(pivot.data);
            builder.append(" ");
            pivot = pivot.next;
        }
        System.out.println(builder.toString().trim());
    }
    private int hash(String s){
        BigInteger result = BigInteger.ZERO;
        for( int i = 0; i < s.length(); i++){
            BigInteger x = new BigInteger ("263");
            BigInteger ch = new BigInteger((int)s.charAt(i) + "");
            BigInteger sI = x.pow(i).multiply(ch);
            result = result.add(sI);
        }
        result = result.mod(new BigInteger(p + ""));
        result = result.mod(new BigInteger(m + ""));
        return result.intValue();
    }
}
class MyNode{
    public final String data;
    MyNode next;

    public MyNode(String data) {
        this.data = data;
    }
}
