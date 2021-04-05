package action;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Парсинг строки из строки в выражения в виде обратной польской записи
 */
public class Parsing extends Operators {

    /** Стек для хранения знаков операций и скобок */
    private final Deque<String> signStack = new ArrayDeque<>();

    /**
     * Парсинг строки
     * @param expr математическое выражение, которое нужно распарсить
     * @return распарсенный список в виде обратной польской записи
     */
    public List<String> parse(String expr) {
        //сюда будет записываться результат
        final List<String> resultList = new LinkedList<>();

        //убираем пробелы, чтобы не мешались, они не нужны
        expr = expr.replaceAll(" ", "");

        StringBuilder builder = new StringBuilder();
        for (char chr : expr.toCharArray()) {
            if (isOperator(chr)) {
                if (builder.length() > 0) {
                    //если есть число, то записываем его в список и создаем новый builder для сбора нового числа
                    resultList.add(builder.toString());
                    builder = new StringBuilder();
                } else if (chr == '-' && !signStack.isEmpty() && signStack.peek().equals("(")) {
                    //знак '-' не оператор, а отрицательное число, заключенный в скобки
                    builder.append(chr);
                    continue;
                }

                poolStackBySign(resultList, chr);

                //записываем в стек все операторы, кроме ')'
                if (chr != ')') {
                    signStack.push(String.valueOf(chr));
                }
            } else {
                //не оператор, значит число, собираем его пока в одну строку
                builder.append(chr);
            }
        }

        //записываем последнее число, если оно есть
        if (builder.length() > 0) {
            resultList.add(builder.toString());
        }

        //выталкиваем все знаки операторов из стека в общий список
        while (signStack.peek() != null) {
            resultList.add(signStack.poll());
        }

        return resultList;
    }

    /**
     * Выбрасываем операторы из стека в общий список
     * @param resultList
     * @param sign
     */
    private void poolStackBySign(List resultList, char sign) {

        //проверяем стек на пустоту и если пуст, то ловить тут нечего
        if (signStack.isEmpty()) {
            return;
        }

        switch (sign) {
            case ')':
                while (!signStack.peek().equals("(")) {
                    resultList.add(signStack.poll());
                }
                signStack.poll();
                break;
            case '-':
            case '+':
                if (signStack.peek().equals("-") || signStack.peek().equals("+")) {
                    resultList.add(signStack.poll());
                    break;
                }
            case '*':
            case '/':
                if (signStack.peek().equals("*") || signStack.peek().equals("/")) {
                    resultList.add(signStack.poll());
                }
                break;
        }
    }
}
