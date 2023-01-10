package deque;

import java.util.stream.Collectors;

public class DequeUsing {

    public static void main(String[] args) {
        System.out.println("Sator arepo tenet opera rotas >> "
                + validPalindrome("Sator arepo tenet opera rotas"));
        System.out.println("Sator arepo >> "
                + validPalindrome("Sator arepo"));
        System.out.println("S >> "
                + validPalindrome("S"));
    }

    public static boolean validPalindrome(String s) {
        String string = s.toLowerCase();

        Deque<Character> deque = new Deque<>(
                string.chars().mapToObj(c -> (char) c).collect(Collectors.toList())
        );

        while (deque.size() > 0) {
            Character left = deque.removeFront();
            Character right = deque.removeTail();

            if (right != null && !left.equals(right)) {
                return false;
            }
        }

        return true;
    }
}
