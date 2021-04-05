package action;

/**
 * Вынесенные операторы, т.к. повторяются
 */
public class Operators {

    /** Операторы выражений */
    private final String operators = "+-*/()";

    /**
     * Проверка символа, является ли он оператором
     * @param chr символ, который нужно проверить
     * @return если символ является оператором {@code true}
     */
    protected boolean isOperator(char chr) {
        return (operators.indexOf(chr) != -1);
    }

    /**
     * Проверка строки, является ли он оператором
     * @param str строка, которую нужно проверить
     * @return если строка является оператором {@code true}
     */
    protected boolean isOperator(String str) {
        return (operators.indexOf(str) != -1);
    }
}
