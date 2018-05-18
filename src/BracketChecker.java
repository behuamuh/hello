import java.util.Scanner;
import java.util.Stack;

public class BracketChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int res = bracketChecker(s);
        if (res == 0)
            System.out.println("Success");
        else
            System.out.println(res);

    }
    // return 0 if line correct
    // return index of first incorrect bracket
    private static int bracketChecker(String line){
        Stack<Character> symbols = new Stack<>();
        Stack<Integer> indexes = new Stack<>();
        for (int i = 0 ; i < line.length(); i++){
            char sym = line.charAt(i);
            if (sym == '(' || sym =='[' || sym == '{') {
                symbols.push(sym);
                indexes.push(i);
                continue;
            }
            if (sym == ')' || sym ==']' || sym == '}'){
                if (symbols.empty()) {
                    return i + 1;
                }
                char top = symbols.pop();
                indexes.pop();
                if ((sym == ')' && top != '(') ||
                        (sym == ']' && top != '[' ) ||
                            (sym == '}' && top != '{'))
                    return i + 1;
            }
        }
        if (!symbols.empty())
            return  indexes.pop() + 1;
        return 0;
    }

}
