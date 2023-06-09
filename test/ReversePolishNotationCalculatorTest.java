import org.junit.jupiter.api.Assertions; // можно сделать статичный импорт import static org.junit.jupiter.api.Assertions.assertEquals  - тогда в тестах методы сверки можно сразу вызывать по assertEquals (мне кажатся так быстрее :))
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class ReversePolishNotationCalculatorTest {
    private static final ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();

    @Test // если передана пустая строка или строка с пробелами
    public void shouldGetStringAndThrowNullPointerException() { // я до таких тестов не додумался)
        NullPointerException exception = Assertions.assertThrows(
                NullPointerException.class,
                generateException(" ")
        );

        Assertions.assertEquals("You mustn't pass an empty string", exception.getMessage());
    }

    @Test // если любой из операндов равен нулю
    public void shouldNotCalculateIfOperandsAreZero() {
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                generateException("0")
        );

        Assertions.assertEquals("You mustn't pass zero operands", exception.getMessage());
    }

    @Test // если вычитание
    public void shouldCalculateIfMinus() {
        int answer = calculator.calculatePolishNotation("2 3 -");

        Assertions.assertEquals(-1, answer); // как вариант можно сделать как у меня - с объявлением переменных. Так быстрее менять переменные для проверки. 
                                             // + можно добавить тестовое сообщение о том какое значение ожидалось и какое получено - как в тренажере
    }

    @Test // если сложение
    public void shouldCalculateIfPlus() {
        int answer = calculator.calculatePolishNotation("1 1 +");

        Assertions.assertEquals(2, answer);
    }

    @Test // если умножение
    public void shouldCalculateIfMultiply() {
        int answer = calculator.calculatePolishNotation("2 2 *");

        Assertions.assertEquals(4, answer);
    }

    // приватный метод для создания исключения
    private Executable generateException(String string) {
        return () -> calculator.calculatePolishNotation(string);
    }
}
