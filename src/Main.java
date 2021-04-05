import action.Console;
import action.Validation;
import action.Parsing;
import action.Calculation;
import exception.DivideByZeroException;
import exception.WrongMathExpressionException;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        final Console console = new Console();

        try {
            console.write("Enter mathematical expression:");
            String expr = console.read();

            Validation validation = new Validation();
            validation.validMathExpression(expr);

            Parsing parsing = new Parsing();
            List<String> list = parsing.parse(expr);

            Calculation calculation = new Calculation();
            Number result = calculation.calculate(list);

            console.write("= " + result);
        } catch (WrongMathExpressionException | DivideByZeroException ex) {
            console.write(ex.getMessage());
        }
    }
}
