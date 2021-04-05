package exception;

/**
 * Исключение при делении на ноль
 */
public class DivideByZeroException extends RuntimeException {

    /**
     * Обычный конструктор с передачей сообщения
     * @param message сообщения для передачи
     */
    public DivideByZeroException(String message) {
        super(message);
    }
}
