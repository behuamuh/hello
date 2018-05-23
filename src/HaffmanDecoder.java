import java.util.Scanner;

/*Восстановите строку по её коду и беспрефиксному коду символов.

В первой строке входного файла заданы два целых числа kk и ll через пробел — количество различных букв, встречающихся в строке,
и размер получившейся закодированной строки, соответственно. В следующих kk строках записаны коды букв в формате "letter: code".
Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке.
В качестве букв могут встречаться лишь строчные буквы латинского алфавита;
каждая из этих букв встречается в строке хотя бы один раз. Наконец, в последней строке записана закодированная строка.
Исходная строка и коды всех букв непусты. Заданный код таков, что закодированная строка имеет минимальный возможный размер.


В первой строке выходного файла выведите строку ss. Она должна состоять из строчных букв латинского алфавита.
Гарантируется, что длина правильного ответа не превосходит 104104 символов.*/
public class HaffmanDecoder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantity = scanner.nextInt();
        int size = scanner.nextInt();
        char[] sym = new char[quantity];
        String[] code = new String[quantity];
        for (int i = 0; i < quantity; i++){
            sym[i] = scanner.next().charAt(0);
            code[i] = scanner.next();
        }
        String builder = scanner.next();
        StringBuilder result = new StringBuilder();
        while (builder.length() > 0) {
            for (int i = 0; i < quantity; i++) {
                if (builder.startsWith(code[i])) {
                    builder = builder.replaceFirst(code[i] + "", "");
                    result.append(sym[i]);
                    break;
                }
            }
        }
        System.out.println(result.toString());
    }
}
