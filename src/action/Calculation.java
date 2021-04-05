package action;

import exception.DivideByZeroException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Класс производящий математические вычисления
 */
public class Calculation extends Operators {

    /** Стек, где складываются числа, до которых не дошли операции */
    private final Deque<Number> deque = new ArrayDeque<>();

    /**
     * Вычисление результата математического выражения
     * @param list список распарсенного выражения в виде обратной польской записи
     * @return результат вычислений
     */
    public Number calculate(List<String> list) {

        list.forEach(elem -> calculateAndPushInStack(elem));

        final Number number = deque.poll();
        if (number.doubleValue() % number.longValue() == 0) {
            //если остатака нет, то это целое число и нам не нужны значения после запятой
            return number.longValue();
        } else {
            return number.doubleValue();
        }
    }

    /**
     * Переводим строку в число, а если знак операции, то выполняем операцию
     * @param elem элемент списка, может быть как число, так и знак операции
     */
    private void calculateAndPushInStack(String elem) {
        final Double number;
        if (!isOperator(elem)) {
            number = Double.valueOf(elem);
        } else {
            number = operation(elem);
        }
        deque.push(number);
    }

    /**
     * Берем из стека два числа и выполняем операциию по указанному знаку
     * @param sign знак для выполнения операции
     * @return результат операции
     */
    private Double operation(String sign) {
        Double number2 = deque.poll().doubleValue();
        Double number = deque.poll().doubleValue();

        switch (sign) {
            case "+":
                number += number2;
                break;
            case "-":
                number -= number2;
                break;
            case "*":
                number *= number2;
                break;
            case "/":
                if (number2 == 0) {
                    throw new DivideByZeroException("Divide is zero. You can't divide by it!");
                }
                number /= number2;
                break;
        }
        return number;
    }
}
