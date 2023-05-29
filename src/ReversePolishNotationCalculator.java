import java.util.ArrayDeque;
import java.util.Deque;

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        Deque<Integer> numbers = new ArrayDeque<>();

        if (str.isBlank() || str.isEmpty()) {
            throw new NullPointerException("You mustn't pass an empty string");
        }
        if (str.contains("0")) {
            throw new IllegalArgumentException("You mustn't pass zero operands");
        }

            String[] parts = str.split(" ");
            int index = 0;

            while (index != parts.length) {

                if (parts[index].isBlank()) {
                    index++;
                    continue;
                }

                if (isOperation(parts[index])) {
                    int operandOne = numbers.pop();
                    int operandTwo = numbers.pop();

                    if (parts[index].equals("+")) {
                        numbers.push(operandOne + operandTwo);
                    } else if (parts[index].equals("-")) {
                        numbers.push(operandTwo - operandOne);
                    } else if (parts[index].equals("*")) {
                        numbers.push(operandOne * operandTwo);
                    }

                } else {
                    numbers.push(Integer.parseInt(parts[index]));
                }

                index++;
            }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
} 