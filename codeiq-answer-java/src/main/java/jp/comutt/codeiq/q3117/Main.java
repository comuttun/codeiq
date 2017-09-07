package jp.comutt.codeiq.q3117;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new LogicTest().runTest();
        Logic logic = new Logic();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                System.out.println(logic.solve(Integer.valueOf(scanner.nextLine())));
            }
        }
    }


    public static class Logic {

        private static final int MIN_VALUE = 1;
        private static final int MAX_VALUE = 1_000_000;

        private static final int MIN_INPUT_VALUE = 2;
        private static final int MAX_INPUT_VALUE = 1_000;

        private final List<Integer> SUMS = new ArrayList<>();

        public Logic() {
            for (int i = MIN_VALUE; i < MAX_VALUE; i++) {
                SUMS.add(i * 2 + 1);
            }
        }

        public int solve(int inputNumber) {
            if (inputNumber < MIN_INPUT_VALUE || MAX_INPUT_VALUE < inputNumber) {
                throw new IllegalArgumentException("inputNumber x must be " + MIN_INPUT_VALUE + " <= x <= " + MAX_INPUT_VALUE);
            }

            return (int) SUMS.stream().parallel().filter(s -> s % inputNumber == 0).count();
        }

    }

    public static class LogicTest {

        public void runTest() {
            if (!getClass().desiredAssertionStatus()) {
                // assertion disabled
                return;
            }

            Logic logic = new Logic();
            try {
                logic.solve(1);
                throw new AssertionError("never reach here");
            } catch (Exception e) {
                assert e instanceof IllegalArgumentException;
            }

            try {
                logic.solve(1_001);
                throw new AssertionError("never reach here");
            } catch (Exception e) {
                assert e instanceof IllegalArgumentException;
            }

            assert logic.solve(307) == 3257;
            assert logic.solve(456) == 0;
            assert logic.solve(545) == 1835;
            assert logic.solve(165) == 6061;
        }
    }

}
