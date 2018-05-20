import java.util.*;

/*Обработка сетевых пакетов
Реализовать обработчик сетевых пакетов.
Вход. Размер буфера size и число пакетов n, а так-
же две последовательности arrival1, . . . , arrivaln и
duration1, . . . , durationn, обозначающих время поступ-
ления и длительность обработки n пакетов.
Выход. Для каждого из данных n пакетов необходимо
вывести время начала его обработки или −1, если пакет
не был обработан (это происходит в случае, когда пакет
поступает в момент, когда в буфере компьютера уже
находится size пакетов).*/
public class NetPackageHandler2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bufferSize = scanner.nextInt();
        int packageQuantity = scanner.nextInt();
        Package[] packages = new Package[packageQuantity];
        for (int i = 0; i < packageQuantity; i++) {
            packages[i] = new Package(scanner.nextInt(), scanner.nextInt());
        }
        packageHandler(bufferSize, packages);

    }

    public static void packageHandler(int buffer, Package[] packages) {
        LinkedList<Integer> queue = new LinkedList<>();
        int finish = 0;
        for (int i = 0; i < packages.length; i++){
            int ar = packages[i].ar;
            int dr = packages[i].dr;
            if (queue.size() == buffer){
                finish = queue.peek();
                if (ar < finish)
                    System.out.println(-1);
                else {
                    finish = Math.max(queue.peekLast(), ar);
                    queue.poll();
                    System.out.println(finish);
                    queue.add(finish + dr);
                }
            }

            if (queue.size() < buffer){
                finish = queue.peekLast() == null ? ar : Math.max(queue.peekLast(), ar);
                System.out.println(finish);
                queue.add(finish + dr);
            }

        }
    }
}
class Package{
    public int ar;
    public int dr;

    public Package(int ar, int dr) {
        this.ar = ar;
        this.dr = dr;
    }
}
