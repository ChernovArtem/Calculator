package action;

import java.util.Scanner;

/**
 * Работа с консолью
 */
public class Console {

    /** сканер для чтения с консоли */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Чтение с консоли
     * @return прочитанные значения с консоли
     */
    public String read() {
        return scanner.nextLine();
    }

    /**
     * Запись в консоль
     * @param obj объект/строка, которую нужно написать на консоли для пользователя
     */
    public void write(Object obj) {
        System.out.println(obj.toString());
    }
}
