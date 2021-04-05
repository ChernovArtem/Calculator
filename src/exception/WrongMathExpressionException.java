package exception;

/**
 * Исключения при валидации математической строки
 */
public class WrongMathExpressionException extends Exception {

    /**
     * Обычный конструктор с передачей сообщения
     * @param message сообщения для передачи
     */
    public WrongMathExpressionException(String message) {
        super(message);
    }
}
