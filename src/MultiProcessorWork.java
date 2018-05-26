import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/*3.2  Параллельная обработка
Параллельная обработка
По данным n процессорам и m
задач определите, для каждой из задач,
каким процессором она будет обработана.
Вход. Число процессоров n и последовательность чисел
t 0, . . . , t m−1 , где t i — время, необходимое на обработку
i-й задачи.
Выход.
Для каждой задачи определите, какой процессор
и в какое время начнёт её обрабатывать, предполагая, что
каждая задача поступает на обработку первому освободив-
шемуся процессору*/
public class MultiProcessorWork {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[] tasks = new int[m];
        for (int i = 0; i < m; i++)
            tasks[i] = scanner.nextInt();
        multiWork(tasks, n);
    }
    public static void multiWork(int[] tasks, int n){
        PriorityQueue<Proc> queue = new PriorityQueue<>(new Comparator<Proc>() {
            @Override
            public int compare(Proc o1, Proc o2) {
                if (o1.time == o2.time)
                    return o1.id - o2.id;
                return Long.compare(o1.time, o2.time);
            }
        });
        for (int i = 0; i < n; i++)
            queue.add(new Proc(i));
        for (int i = 0; i < tasks.length; i++){
            Proc proc = queue.poll();
            System.out.println(proc.id + " " + proc.time);
            proc.time += tasks[i];
            queue.add(proc);
        }
    }
}
class Proc{
    final int id;
    long time;

    public Proc(int id) {
        this.id = id;
        time = 0;
    }
}