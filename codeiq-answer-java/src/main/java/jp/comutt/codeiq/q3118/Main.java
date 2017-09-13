package jp.comutt.codeiq.q3118;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        System.out.println(new Logic().solve(matrix, 0, 0).get());
    }

    public static class Logic {

        public Optional<Integer> solve(List<List<Integer>> matrix, int x, int y) {
            if (x == (matrix.get(0).size() - 1) && (y == matrix.size() - 1)) {
                return Optional.of(matrix.get(y).get(x));
            }

            Optional<Integer> nextRight = Optional.empty();
            if (x + 1 < matrix.get(0).size()) {
                nextRight = Optional.of(matrix.get(y).get(x + 1));
            }

            Optional<Integer> nextDown = Optional.empty();
            if (y + 1 < matrix.size()) {
                nextDown = Optional.of(matrix.get(y + 1).get(x));
            }

            if (nextRight.isPresent() && nextDown.isPresent()) {
                if (nextRight.get() < nextDown.get()) {
                    return goRight(matrix, x, y);
                } else {
                    return goDown(matrix, x, y);
                }
            } else if (nextRight.isPresent()) {
                return goRight(matrix, x, y);
            } else if (nextDown.isPresent()) {
                return goDown(matrix, x, y);
            } else {
                return Optional.empty();
            }
        }

        private Optional<Integer> goRight(List<List<Integer>> matrix, int x, int y) {
            Optional<Integer> o = solve(matrix, x + 1, y);
            if (o.isPresent()) {
                return Optional.of(matrix.get(y).get(x) + o.get());
            } else {
                return Optional.empty();
            }
        }

        private Optional<Integer> goDown(List<List<Integer>> matrix, int x, int y) {
            Optional<Integer> o = solve(matrix, x, y + 1);
            if (o.isPresent()) {
                return Optional.of(matrix.get(y).get(x) + o.get());
            } else {
                return Optional.empty();
            }
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

            assertEquals(sut.solve(matrix, 0, 0), 11);
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
            } else {
                if (!Objects.equals(actual, expected)) {
                    throw new AssertionError("expected " + expected + " but was " + actual);
                }
            }
        }
    }

}
