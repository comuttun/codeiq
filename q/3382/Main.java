import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] input = scanner.nextLine().split(",");
            int width = Integer.valueOf(input[0]);
            int height = Integer.valueOf(input[1]);
            List<List<Integer>> cardList = makeCardList(width, height);
            cardList.forEach(row -> {
                System.out.println(row.stream().map(i -> Objects.nonNull(i) ? String.valueOf(i) : "--").collect(Collectors.joining(",")));
            });
        }
    }

    private static List<List<Integer>> makeCardList(int width, int height) {
        List<Integer> allCards = new ArrayList<>();
        for (int i = 11; i <= 99; i++) {
            allCards.add(i);
        }

        List<List<Integer>> rowList = new ArrayList<>();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                if (w == 0) {
                    rowList.add(new ArrayList<>());
                }

                if (allCards.size() > 0) {
                    rowList.get(h).add(allCards.remove(0));
                } else {
                    rowList.get(h).add(null);
                }
            }
        }

        return rowList;
    }

}
