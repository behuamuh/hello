import java.util.Scanner;

/*Система равенств и неравенств
Проверить, можно ли присвоить переменным целые значения, чтобы
выполнить заданные равенства вида x i=x j и неравенства вида
x p != x q.
Вход.
Число переменных n, а также список равенств вида
x i= x j и неравенства вида x p != x q.
Выход.
Проверить, выполнима ли данная система.
Формат входа.
Первая строка содержит числа n, e, d. Каждая из следующих e
строк содержит два числа i и j и задаёт равенство x i= x j.
Каждая из следующих d строк содержит два числа i и j и задаёт неравенство
x i != x j. Переменные индексируются с 1:
x 1, . . . , x n.
Формат выхода.
Выведите 1, если переменным x 1, . . . , x n можно присвоить целые значения,
чтобы все равенства и неравенства выполнились. В противном случае выведите 0
.*/
public class ProgrammAnaliz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int e = scanner.nextInt();
        int d = scanner.nextInt();
        Set[] sets = new Set[n+1];
        for (int i = 1; i < n+1; i++)
            sets[i] = new Set(i);
        int result = 1;
        for (int i = 0; i < e; i++){
            union(sets[scanner.nextInt()], sets[scanner.nextInt()]);
        }
        for (int i = 0; i < d; i++){
            if (find(sets[scanner.nextInt()]) == find(sets[scanner.nextInt()])){
                result = 0;
                break;
            }
        }
        System.out.println(result);
    }

    private static void union(Set set, Set set1) {
        if (find(set) != find(set1))
            set1.parent = set.parent;
    }

    private static Set find(Set set) {
        if (set.parent != set)
            set.parent = find(set.parent);
        return set.parent;
    }

}
class Set {
    Set parent;
    int n;

    public Set(int n) {
        this.n = n;
        parent = this;
    }
}
