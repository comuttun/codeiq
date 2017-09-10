package jp.comutt.codeiq.q3117;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        LogicTest test = new LogicTest();
        test.runTest();
        test.runTest2();

        Logic logic = new Logic();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                System.out.println(logic.solveCached(Integer.valueOf(scanner.nextLine())));
            }
        }
    }


    public static class Logic {

        private static final int MIN_VALUE = 1;
        private static final int MAX_VALUE = 1_000_000;

        private static final int MIN_INPUT_VALUE = 2;
        private static final int MAX_INPUT_VALUE = 1_000;

        private final List<Integer> SUMS = new ArrayList<>();
        private final Map<Integer, Integer> RESULTS = new HashMap<>();

        public Logic() {
            IntStream.range(MIN_VALUE, MAX_VALUE + 1).forEach(i -> {
                SUMS.add(i * 2 + 1);
            });

            solveAll();
        }

        private int solve(int inputNumber) {
            if (inputNumber < MIN_INPUT_VALUE || MAX_INPUT_VALUE < inputNumber) {
                throw new IllegalArgumentException("inputNumber x must be " + MIN_INPUT_VALUE + " <= x <= " + MAX_INPUT_VALUE);
            }

            if (inputNumber % 2 == 0) {
                return 0;
            }

//            int start = (inputNumber - 1) / 2 - MIN_VALUE;
//            return (int) SUMS.subList(start, SUMS.size()).stream().parallel().filter(s -> s % inputNumber == 0).count();
            return (int) (1.0 * (MAX_VALUE * 2 - 1) / inputNumber / 2 + 0.5);
        }

        private void solveAll() {
            IntStream.range(MIN_INPUT_VALUE, MAX_INPUT_VALUE + 1).parallel().forEach(i -> {
                RESULTS.put(i, solve(i));
            });
        }

        public int solveCached(int inputNumber) {
            return RESULTS.get(inputNumber);
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

        public void runTest2() {
            if (!getClass().desiredAssertionStatus()) {
                // assertion disabled
                return;
            }

            Logic logic = new Logic();
            long startTime = System.currentTimeMillis();
            for (int i = 2; i < 1_000; i++) {
                System.out.println(logic.solve(i));
            }
            long endTime = System.currentTimeMillis();
            System.out.println(endTime - startTime + " ms");
        }

    }

}
