import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SearchInText {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Searcher searcher = new Searcher(scanner.next(), scanner.next());
        searcher.search();
    }

}
class Searcher{
    private String text;
    private String pattern;
    private int p = 84673;
    private final int hashP;

    public Searcher(String pattern, String text) {
        this.text = text;
        this.pattern = pattern;
        loadPower();
        hashP = hash(pattern);
    }

    private Map<Integer, Integer> power = new HashMap<>();
    public void search(){
        if (pattern.length() >= text.length()) {
            if (text.equals(pattern))
                System.out.println(0);
            return;
        }
        int tempHash = hash(text.substring(0, pattern.length()));
        for (int i = 0; i <= text.length() - pattern.length(); i++){
            if (tempHash == hashP){
                if (text.substring(i, i + pattern.length()).equals(pattern))
                    System.out.print(i + " ");
            }
            if (i == text.length() - pattern.length())
                break;
            int delta = text.charAt(i + pattern.length()) * power.get(pattern.length() - 1);
            tempHash = delta + (tempHash - text.charAt(i))/ 263;
            tempHash = tempHash % p;
        }


    }
    private int hash(String s){
        if (s == null || s.length() == 0)
            throw new IllegalArgumentException();
        int sum = 0;
        for (int i = 0; i < s.length(); i++){
            sum = (sum + ((int)s.charAt(i) * power.get(i)) % p) % p;
        }
        return sum;
    }
    private void loadPower(){
        int x = 1;
        for (int i = 0; i < pattern.length(); i++){
            power.put(i, x);
            x = (x * 263) % p;
        }
    }
}