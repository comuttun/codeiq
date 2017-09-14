package jp.comutt.codeiq.q3118;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        new LogicTest().runTest();

        List<List<Integer>> matrix = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                matrix.add(scanner.nextLine().chars()
                        .mapToObj(c -> (char)c)
                        .mapToInt(Character::getNumericValue)
                        .boxed()
                        .collect(Collectors.toList()));

                if (matrix.size() == matrix.get(0).size()) {
                    break;
                }
            }
        }

        System.out.println(new Logic().solve(matrix, 0, 0, 0).getAsInt());
    }

    public static class Logic {

        private int minSum = Integer.MAX_VALUE;

        public OptionalInt solve(List<List<Integer>> matrix, int x, int y, int prevSum) {
            if (prevSum > minSum) {
                return OptionalInt.empty();
            }

            if (x == (matrix.get(0).size() - 1) && (y == matrix.size() - 1)) {
                int sum = prevSum + matrix.get(y).get(x);
                if (minSum > sum) {
                    minSum = sum;
                    return OptionalInt.of(sum);
                } else {
                    return OptionalInt.empty();
                }
            }

            OptionalInt nextRight = OptionalInt.empty();
            if (x + 1 < matrix.get(0).size()) {
                nextRight = OptionalInt.of(matrix.get(y).get(x + 1));
            }

            OptionalInt nextDown = OptionalInt.empty();
            if (y + 1 < matrix.size()) {
                nextDown = OptionalInt.of(matrix.get(y + 1).get(x));
            }

            if (nextRight.isPresent() && nextDown.isPresent()) {
                return Stream.of(goRight(matrix, x, y, prevSum), goDown(matrix, x, y, prevSum))
                        .parallel()
                        .filter(OptionalInt::isPresent)
                        .mapToInt(OptionalInt::getAsInt)
                        .min();
            } else if (nextRight.isPresent()) {
                return goRight(matrix, x, y, prevSum);
            } else if (nextDown.isPresent()) {
                return goDown(matrix, x, y, prevSum);
            } else {
                return OptionalInt.empty();
            }
        }

        private OptionalInt goRight(List<List<Integer>> matrix, int x, int y, int prevSum) {
            return solve(matrix, x + 1, y, prevSum + matrix.get(y).get(x));
        }

        private OptionalInt goDown(List<List<Integer>> matrix, int x, int y, int prevSum) {
            return solve(matrix, x, y + 1, prevSum + matrix.get(y).get(x));
        }

    }

    public static class LogicTest {

        private final Logic sut = new Logic();

        public void runTest() {
            if (!getClass().desiredAssertionStatus()) {
                return;
            }

            List<List<Integer>> matrix = Arrays.asList(
                    Arrays.asList(5, 6, 7),
                    Arrays.asList(1, 3, 3),
                    Arrays.asList(5, 0, 2)
            );

            assertEquals(sut.solve(matrix, 0, 0, 0), 11);
        }


        private static void assertEquals(Object actual, Object expected) {
            if (actual instanceof Optional) {
                Optional<?> optActual = (Optional<?>) actual;
                if (!optActual.isPresent()) {
                    throw new AssertionError("expected " + expected + " but was not present");
                }

                if (!Objects.equals(optActual.get(), expected)) {
                    throw new AssertionError("expected " + expected + " but was " + actual);
                }
            } else if (actual instanceof OptionalInt) {
                OptionalInt optActual = (OptionalInt) actual;
                if (!optActual.isPresent()) {
                    throw new AssertionError("expected " + expected + " but was not present");
                }

                if (!Objects.equals(optActual.getAsInt(), expected)) {
                    throw new AssertionError("expected " + expected + " but was " + actual);
                }
            } else {
                if (!Objects.equals(actual, expected)) {
                    throw new AssertionError("expected " + expected + " but was " + actual);
                }
            }
        }
    }

}
