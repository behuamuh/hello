import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/*По данным nn отрезкам необходимо найти множество точек минимального размера,
для которого каждый из отрезков содержит хотя бы одну из точек.

В первой строке дано число 1≤n≤1001≤n≤100 отрезков.
Каждая из последующих nn строк содержит по два числа 0≤l≤r≤1090≤l≤r≤109,
задающих начало и конец отрезка. Выведите оптимальное число mm точек и сами mm точек.
Если таких множеств точек несколько, выведите любое из них.*/

public class LegPoints {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        Leg[] legs = new Leg[size];
        for (int i = 0; i < size; i++){
            legs[i] = new Leg(scanner.nextInt(), scanner.nextInt());
        }
        points(legs);
    }
    public static void points(Leg[] legs){
        Arrays.sort(legs, new Comparator<Leg>() {
            @Override
            public int compare(Leg o1, Leg o2) {
                return o1.a2 - o2.a2;
            }
        });
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < legs.length;){
            int point = legs[i].a2;
            result.add(point);
            while (i < legs.length && legs[i].contains(point)){
                i++;
            }
        }
        System.out.println(result.size());
        for (int i: result
                ) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
class Leg{
    public int a1;
    public int a2;

    public Leg(int a1, int a2) {
        this.a1 = Math.min(a1, a2);
        this.a2 = Math.max(a1, a2);
    }
    public boolean contains(int a){
        return a >= a1 && a <= a2;
    }

}