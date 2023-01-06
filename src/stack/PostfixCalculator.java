package stack;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostfixCalculator {

    private static InvertedStack<Object> firstStack;
    private static final Stack<Integer> secondStack = new Stack<>();

    public static void main(String[] args) {
        System.out.println("8 2 + 5 * 9 + = " + calculate("8 2 + 5 * 9 + ="));
        System.out.println("1 2 + 3 * = " + calculate("1 2 + 3 * ="));
        System.out.println("10 2 + 3 * = " + calculate("10 2 + 3 * ="));
        System.out.println("1 2 + 30 * = " + calculate("1 2 + 30 * ="));
    }

    public static int calculate(String expression) {
        initialize(expression);

        Integer result = Integer.MIN_VALUE;

        while (firstStack.peek() != null) {
            Object value = firstStack.pop();

            if (value instanceof Integer) {
                secondStack.push((Integer) value);
            } else if (value instanceof Character && !value.equals('=')) {
                Integer firstOperand = secondStack.pop();
                Integer secondOperand = secondStack.pop();

                Integer val = doOperation(firstOperand, secondOperand, (Character) value);
                secondStack.push(val);
            }

            if (value instanceof Character && value.equals('=')) {
                result = secondStack.pop();
                break;
            }
        }

        return result;
    }

    private static void initialize(String expression) {
        List<Object> list = new LinkedList<>();

        Pattern integerPattern = Pattern.compile("\\d+");
        Matcher matcher = integerPattern.matcher(expression);

        boolean intChar = true;


        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '*') {
                list.add(c);
                continue;
            }

            if (c == '=') {
                list.add(c);
                break;
            }

            if (c == ' ') {
                intChar = true;
                continue;
            }

            if (intChar && intChar(c) && matcher.find()) {
                intChar = false;
                list.add(Integer.valueOf(matcher.group()));
            }
        }

        if (intChar && matcher.find()) {
            list.add(Integer.valueOf(matcher.group()));
        }

        firstStack = new InvertedStack<>(list);
    }

    private static Integer doOperation(Integer firstOperand, Integer secondOperand, Character operation) {
        if (operation.equals('+')) {
            return firstOperand + secondOperand;
        }

        if (operation.equals('*')) {
            return firstOperand * secondOperand;
        }

        return Integer.MIN_VALUE;
    }

    private static boolean intChar(char c) {
        boolean intChar;

        switch (Character.getNumericValue(c)) {
            case (0):
            case (1):
            case (2):
            case (3):
            case (4):
            case (5):
            case (6):
            case (7):
            case (8):
            case (9):
                intChar = true;
                break;

            default:
                intChar = false;
                break;
        }

        return intChar;
    }
}
