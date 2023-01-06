package stack;

public class StackUsing {

    public static void main(String[] args) {
        String s;

        s = "(()((())()))";
        System.out.println("(()((())())) = " + validBoundSequence(s));

        s = "(()()(()";
        System.out.println("(()()(() = " + validBoundSequence(s));

        s = "())(";
        System.out.println("())( = " + validBoundSequence(s));

        s = "))((";
        System.out.println("))(( = " + validBoundSequence(s));

        s = "((())";
        System.out.println("((()) = " + validBoundSequence(s));

        s = "((()))";
        System.out.println("((())) = " + validBoundSequence(s));

        s = "(()())";
        System.out.println("(()()) = " + validBoundSequence(s));
    }

    public static boolean validBoundSequence(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if ('(' == c) {
                stack.push(c);
            } else if (')' == c && stack.pop() == null) {
                return false;
            }

            if (c != '(' && c != ')') {
                return false;
            }
        }

        return stack.size() == 0;
    }
}
