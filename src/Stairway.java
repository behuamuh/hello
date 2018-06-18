import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*лестница
Даны число 1≤n≤10^2 ступенек лестницы и целые числа −10^4≤a1,…,an≤10^4,
которыми помечены ступеньки. Найдите максимальную сумму, которую можно получить, идя по лестнице снизу вверх (от нулевой до n-й ступеньки),
каждый раз поднимаясь на одну или две ступеньки.*/
public class Stairway {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n+1];
        arr[0] = 0;
        for (int i = 1; i < n+1; i++)
            arr[i] = scanner.nextInt();
        System.out.println(maxWayArr(arr));//with array
        System.out.println(maxWay(arr));//simple
        System.out.println(maxWayRec(arr, arr.length - 1, new HashMap<Integer, Integer>()));//with recursion
    }

    public static int maxWayArr(int[] a) {
        int[] res = new int[a.length];
        res[0] = 0;
        res[1] = a[1];
        for (int i = 2; i < a.length; i++){
            res[i] = Math.max(res[i-2]+a[i], res[i-1]+a[i]);
        }
        return res[a.length - 1];
    }
    public static int maxWay(int[] a) {
        int r0 = 0;
        int r1 = a[1];
        int r2 = 0;
        for (int i = 2; i < a.length; i++){
            r2 = Math.max(r0+a[i], r1+a[i]);
            r0 = r1;
            r1 = r2;
        }
        return r1;
    }
    public static int maxWayRec(int[] a, int index, HashMap<Integer, Integer> map) {
        if(index < 2)
            return a[index];
        if (map.containsKey(index))
            return map.get(index);
        map.put(index, Math.max(a[index]+maxWayRec(a,index-1, map), a[index]+maxWayRec(a, index-2, map)));
        return map.get(index);
    }
}
