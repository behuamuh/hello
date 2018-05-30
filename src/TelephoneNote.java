import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*
* Телефонная книга
Реализовать структуру данных, эффективно обрабатывающую запро-
сы вида add number name, del number и find number.
Вход. Последовательность запросов вида add number
name, del number и find number, где number — те-
лефонный номер, содержащий не более семи знаков,
а name — короткая строка.
Выход. Для каждого запроса find number выведите соот-
ветствующее имя или сообщите, что такой записи нет.*/
public class TelephoneNote {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Map<Integer, String> note = new HashMap<>();
        for (int i = 0; i < n; i++){
            String oper = scanner.next();
            if ("add".equals(oper)){
                int num = scanner.nextInt();
                String name = scanner.next();
                if (note.containsKey(num))
                    note.replace(num, name);
                else
                    note.put(num, name);
            }
            if ("find".equals(oper)){
                int num = scanner.nextInt();
                if (note.containsKey(num)){
                    System.out.println(note.get(num));
                }
                else
                    System.out.println("not found");
            }
            if ("del".equals(oper)){
                int num = scanner.nextInt();
                if (note.containsKey(num))
                    note.remove(num);
            }
        }
    }
}
