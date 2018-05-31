import java.util.Scanner;

public class SearchInText {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        search(scanner.next(), scanner.next());
    }

    public static void search(String pattern, String text){
        if (pattern.length() >= text.length()) {
            if (text.equals(pattern))
                System.out.println(0);
            return;
        }
        int hashP = hash(pattern);
        int tempHash = hash(text.substring(0, pattern.length()));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i <= text.length() - pattern.length(); i++){
            //System.out.println(tempHash);
            if (tempHash == hashP){
                if (text.charAt(i) == pattern.charAt(0))
                    if (text.substring(i, i + pattern.length()).equals(pattern))
                        builder.append(i + " ");
            }
            if (i == text.length() - pattern.length())
                break;
            int delta = text.charAt(i + pattern.length());
            tempHash = delta + (tempHash - text.charAt(i));
        }
        System.out.println(builder.toString());

    }
    private static int hash(String s){
        if (s == null || s.length() == 0)
            throw new IllegalArgumentException();
        int sum = 0;
        for (char c : s.toCharArray()) {
            sum += (int)c;
        }
        return sum;
    }
}