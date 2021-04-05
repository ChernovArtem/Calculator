package action;

import exception.WrongMathExpressionException;
import java.util.regex.Pattern;

/**
 * Проверка математического выражения на корректность
 */
public class Validation {

    /**
     * Символы для проверки строки, полученной с консоли.
     * Проверяются все остальные символы, кроме пробелов, чисел, '.', '+', '-', '*', '/', '(' и ')'
     * Если найдется символ, помимо указанных, то будет исключение {@link WrongMathExpressionException}
     */
    private final Pattern exceptionCharacters = Pattern.compile("[^\\s\\d.+\\-*/()]");

    /** Отрицательные числа в скобках */
    private final Pattern negativeNumber = Pattern.compile("\\(\\s*-\\s*(\\d+|\\d*.\\s*\\d+)\\s*\\)");

    /** Все числа (дробные и целые) */
    private final Pattern digit = Pattern.compile("(\\d*\\.\\s*\\d+)|(\\d+)");

    /** Операторы выражений, бес скобок */
    private final Pattern operator = Pattern.compile("[+\\-*/]");

    /**
     * Проверяем выражение на корректность
     * @param expr математическое выражения для проверки
     * @throws WrongMathExpressionException выражение не корреткно
     */
    public void validMathExpression(String expr) throws WrongMathExpressionException {

        //проверяем на некорректные символы в выражении
        if (exceptionCharacters.matcher(expr).find()) {
            throw new WrongMathExpressionException("There are incorrect symbols in the math expression!");
        }

        expr = negativeNumber.matcher(expr).replaceAll("1");    //убираем отрицательные числа в скобках

        countBrackets(expr);

        expr = expr.replaceAll("\\(|\\)", "");          //убираем скобки
        expr = operator.matcher(expr).replaceAll("+");         //заменяем знаки операторов на + для проверки
        expr = digit.matcher(expr).replaceAll("n");            //заменяем числа на букву n для проверки
        expr = expr.replaceAll(" ", "");                //убираем все пробелы

        //проверяем и выбрасываем исключения
        if (expr.charAt(0) == '+') {
            throw new WrongMathExpressionException("You can't start an expression with an operator!");
        } else if (expr.charAt(expr.length() - 1) == '+') {
            throw new WrongMathExpressionException("You can't end an expression with an operator!");
        } else if (expr.contains("++")) {
            throw new WrongMathExpressionException("You can't specify multiple operators in an expression " +
                    "at the same place!");
        } else if (expr.contains("nn")) {
            throw new WrongMathExpressionException("You can't specify multiple numbers in the sameexpression " +
                    "without an operator in between!");
        }
    }

    /**
     * Считаем количество открывающих и закрывающих скобок
     * @param expr строка из которой считаем
     * @throws WrongMathExpressionException если кол-во скобок не равны
     */
    private void countBrackets(String expr) throws WrongMathExpressionException {
        int openBkt = 0;
        int closeBkt = 0;

        for (char chr : expr.toCharArray()) {
            if (chr == '(') {
                openBkt++;
            } else if (chr == ')') {
                closeBkt++;
            }
        }

        if (openBkt < closeBkt) {
            throw new WrongMathExpressionException("Missing opening bracket!");
        } else if (openBkt > closeBkt) {
            throw new WrongMathExpressionException("Missing closing bracket!");
        }
    }
}
