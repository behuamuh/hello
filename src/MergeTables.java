import java.util.Scanner;

/*бъединение таблиц
Ваша цель в данной задаче — реализовать симуляцию объединения таблиц в базе данных.
В базе данных есть n таблиц, пронумерованных от 1 до n, над одним и тем же множеством столбцов (атрибутов).
Каждая таблица содержит либо реальные записи в таблице, либо символьную ссылку на другую таблицу.
Изначально все таблицы содержат реальные записи, и i-я таблица содержит r i записей.
Ваша цель — обработать m запросов типа(destination i, source i):
1. Рассмотрим таблицу с номером destination i. Пройдясь по цепочке символьных ссылок, найдём номер реальной таблицы,
на которую ссылается эта таблица:
пока таблица destination i содержит символическую ссылку:
destination i← symlink(destination i)
2. Сделаем то же самое с таблицей source i.
3. Теперь таблицы destination i и source i содержат реальные записи.
Если destination i != source i, скопируем все записи из таблицы source i в таблицу destination i,
очистим таблицу source i и пропишем в неё символическую ссылку на таблицу destination i.
4. Выведем максимальный размер среди всех n таблиц. Размером таблицы называется число строк в ней.
Если таблица содержит
символическую ссылку, считаем её размер равным нулю.
Формат входа.
Первая строка содержит числа n и m — число таблиц и число запросов, соответственно.
Вторая строка содержит n целых чисел r 1, . . . , r n — размеры таблиц.
Каждая из последующих m строк содержит два номера таблиц destination i и source i, которые необходимо объединить.
Формат выхода.
Для каждого из m запросов выведите максимальный размер таблицы после соответствующего объединения.*/

public class MergeTables {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Table[] tables = new Table[n+1];
        tables[0] = new Table(0);
        for (int i = 1 ; i <= n ; i++) {
            tables[i] = new Table(scanner.nextInt());
            tables[0].entry = Math.max(tables[i].entry, tables[0].entry);
        }
        for (int i = 0; i < m; i++)
            union(tables[scanner.nextInt()], tables[scanner.nextInt()], tables);

    }
    public static Table find(Table table){
        if (table == null)
            throw new IllegalArgumentException();
        if (table.root != table)
            table.root = find(table.root);
        return table.root;
    }
    public static void union(Table dest, Table source, Table[] tables){
        if (dest == null || source == null)
            throw new IllegalArgumentException();
        Table dRoot = find(dest);
        Table sRoot = find(source);
        if (dRoot == sRoot)
            System.out.println(tables[0].entry);
        else {
            dRoot.entry += sRoot.entry;
            sRoot.root = dRoot;
            tables[0].entry = Math.max(tables[0].entry, dRoot.entry);
            System.out.println(tables[0].entry);
        }
    }

}
class Table{
    public Table root;
    public int entry;

    public Table(int entry) {
        this.entry = entry;
        root = this;
    }
}