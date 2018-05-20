import java.util.ArrayList;
import java.util.Scanner;

/*По данному числу 1≤n≤10^9 найдите максимальное число kk,
для которого nn можно представить как сумму kk различных натуральных слагаемых.
Выведите в первой строке число kk, во второй — kk слагаемых.*/
public class VariousTerms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        variousTerms(num);
    }
    public static void variousTerms(int num){
        ArrayList<Integer> list = new ArrayList<>();
        if ( num < 1)
            return;
        int term = 1;
        int residue = num;
        while(true){
            if ( term * 2 < residue){
                list.add(term);
                residue -= term;
                term++;
            }
            else {
                list.add(residue);
                break;
            }
        }
        System.out.println(list.size());
        for (int i: list
             ) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
