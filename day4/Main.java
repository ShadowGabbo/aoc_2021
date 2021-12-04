import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Main {
    public static class BingoPair {
        int number;
        boolean isMarked = false;

        BingoPair(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public boolean isMarked() {
            return isMarked;
        }

        public void setMarked(boolean marked) {
            isMarked = marked;
        }

        @Override
        public String toString() {
            return isMarked ? "1" : "0";
        }
    }

    public static class Bingo {
        private List<Integer> drawnNumbers;
        private List<List<List<BingoPair>>> grids;

        Bingo(List<Integer> drawnNumbers, List<List<List<BingoPair>>> grids) {
            this.drawnNumbers = drawnNumbers;
            this.grids = grids;
        }

        public List<Integer> getDrawnNumbers() {
            return drawnNumbers;
        }

        public List<List<List<BingoPair>>> getGrids() {
            return grids;
        }

        public int getWinningGrid() {
            int index = 0;
            for (List<List<BingoPair>> grid : grids) {
                if (isWinningGrid(grid)) return index;
                index++;
            }
            return -1;
        }

        public int getWinningGridExcept(List<Integer> except) {
            int index = 0;
            for (List<List<BingoPair>> grid : grids) {
                if (except.contains(index)) {
                    index++;
                    continue;
                }
                if (isWinningGrid(grid)) return index;
                index++;
            }
            return -1;
        }

        private boolean isWinningGrid(List<List<BingoPair>> grid) {
            for (List<BingoPair> line : grid) {
                if (line.stream().filter(b -> b.isMarked()).count() == line.size()) return true;
            }
            for (List<BingoPair> col : transposeGrid(grid)) {
                if (col.stream().filter(b -> b.isMarked()).count() == col.size()) return true;
            }
            return false;
        }

        public void markGridsWith(int number) {
            for (List<List<BingoPair>> grid : grids) {
                for (List<BingoPair> row : grid) {
                    for (BingoPair pair : row) {
                        if (pair.getNumber() == number) {
                            pair.setMarked(true);
                        }
                    }
                }
            }
        }

        private List<List<BingoPair>> transposeGrid(List<List<BingoPair>> grid) {
            List<List<BingoPair>> result = new ArrayList<>();
            for (int i = 0; i < grid.size(); i++) {
                List<BingoPair> newRow = new ArrayList<>();
                for (int j = 0; j < grid.get(i).size(); j++) {
                    newRow.add(grid.get(j).get(i));
                }
                result.add(newRow);
            }
            return result;
        }
    }

    public static void main(String[] args) throws Exception {
        Bingo values = readFile();
        long startTime = System.nanoTime();
        System.out.println("Part one: " + partOne(values));
        System.out.println("Part two: " + partTwo(values));
        long stopTime = System.nanoTime();
        System.out.println("Duration : " + TimeUnit.NANOSECONDS.toMillis(stopTime - startTime));
    }

    private static Bingo readFile() throws IOException {
        List<String> content = Files.readAllLines(Paths.get("input.txt"))
                .stream()
                .filter(c -> !c.equals(""))
                .collect(Collectors.toList());
        List<Integer> drawnNumbers = Arrays.stream(content.remove(0).split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        int currentLine = 0;

        List<List<List<BingoPair>>> grids = new ArrayList<>();
        List<List<BingoPair>> currentGrid = new ArrayList<>();

        for (String gridLine : content) {
            List<BingoPair> line = Arrays.stream(gridLine.split(" "))
                    .filter(s -> !s.equals(""))
                    .map(c -> new BingoPair(Integer.parseInt(c)))
                    .collect(Collectors.toList());
            currentGrid.add(line);
            if (currentLine % 5 == 4) {
                grids.add(currentGrid);
                currentGrid = new ArrayList<>();
            }
            currentLine++;
        }

        return new Bingo(drawnNumbers, grids);
    }

    private static int partOne(Bingo bingo) {
        int i = 0;
        int drawnNumber = 0;
        int sum = 0;
        while (bingo.getWinningGrid() == -1) {
            drawnNumber = bingo.getDrawnNumbers().get(i++);
            bingo.markGridsWith(drawnNumber);
        }
        for (List<BingoPair> row : bingo.getGrids().get(bingo.getWinningGrid())) {
            sum += row.stream()
                    .filter(b -> !b.isMarked())
                    .map(BingoPair::getNumber)
                    .mapToInt(Integer::intValue)
                    .sum();
        }
        return sum * drawnNumber;
    }

    private static int partTwo(Bingo bingo) {
        int i = 0;
        int drawnNumber = 0;
        int sum = 0;
        List<Integer> winningGrids = new ArrayList<>();
        while (winningGrids.size() < bingo.getGrids().size()) {
            drawnNumber = bingo.getDrawnNumbers().get(i++);
            bingo.markGridsWith(drawnNumber);
            int winningGridIndex = bingo.getWinningGridExcept(winningGrids);
            while (winningGridIndex != -1) {
                winningGrids.add(winningGridIndex);
                winningGridIndex = bingo.getWinningGridExcept(winningGrids);
            }
        }
        for (List<BingoPair> row : bingo.getGrids().get(winningGrids.get(winningGrids.size() - 1))) {
            sum += row.stream()
                    .filter(b -> !b.isMarked())
                    .map(BingoPair::getNumber)
                    .mapToInt(Integer::intValue)
                    .sum();
        }
        return sum * drawnNumber;
    }
}
