import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day5 implements Day {

    @Override
    public String partOne(List<String> input) {
        int[][] grid = parseGrid(input, l -> l.line.contains(l.x, l.y));

        return "" + countIntersections(grid);
    }

    @Override
    public String partTwo(List<String> input) {
        int[][] grid = parseGrid(input, l -> l.line.part2Contains(l.x, l.y));

        return "" + countIntersections(grid);
    }

    private void printGrid(int[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                System.out.print(" "+ (grid[y][x] == 0 ? "." : grid[y][x]));
            }

            System.out.println();
        }
    }

    private int countIntersections(int[][] grid) {
        int result = 0;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] > 1) {
                    result++;
                }
            }
        }

        return result;
    }



    public int[][] parseGrid(List<String> input, Predicate<LineCheckInput> countCondition) {
        List<Line> lines = parseLines(input);

        int gridSide = findGridSide(lines);

        int[][] grid = new int[gridSide][];
        for (int i = 0; i < gridSide; i++) {
            grid[i] = new int[gridSide];
        }

        for (Line line : lines) {
            for (int y = 0; y < grid.length; y++) {
                for (int x = 0; x < grid[y].length; x++) {
                    if (countCondition.test(new LineCheckInput(line, x, y))) {
                        grid[y][x]++;
                    }
                }
            }
        }

        return grid;
    }

    private int findGridSide(List<Line> lines) {
        int maxX = lines.stream().map(l -> Math.max(l.start.x, l.end.x)).max(Comparator.naturalOrder()).get();
        int maxY = lines.stream().map(l -> Math.max(l.start.y, l.end.y)).max(Comparator.naturalOrder()).get();

        return Math.max(maxX, maxY) + 1;
    }

    private List<Line> parseLines(List<String> input) {
        return input.stream().map(Line::new).collect(Collectors.toList());
    }

    class Point {
        private final int x, y;

        public Point(String pointsDefinition) {
            String[] points = pointsDefinition.split(",");

            x = Integer.parseInt(points[0]);
            y = Integer.parseInt(points[1]);
        }
    }

    class Line {
        private final Point start, end;

        public Line(String lineDefinition) {
            String[] pointsDefinition = lineDefinition.split(" -> ");

            Point a = new Point(pointsDefinition[0]);
            Point b = new Point(pointsDefinition[1]);

            if (a.y == b.y) {
                if (a.x < b.x) {
                    start = a;
                    end = b;
                } else {
                    start = b;
                    end = a;
                }
            } else if (a.x == b.x){
                if (a.y < b.y) {
                    start = a;
                    end = b;
                } else {
                    start = b;
                    end = a;
                }
            } else {
                start = a;
                end = b;
            }
        }

        public boolean contains(int x, int y) {
            if (start.x == end.x) {
                return x == start.x && start.y <= y && end.y >= y;
            } else if (start.y == end.y) {
                return y == start.y && start.x <= x && end.x >= x;
            }
            return false;
        }

        public boolean part2Contains(int x, int y) {
            if (contains(x, y)) {
                return true;
            }

            float dx = (x - start.x) / (float) (end.x - start.x);
            float dy = (y - start.y) / (float) (end.y - start.y);

            return dx == dy && (0 <= dx && dx <= 1) && (0 <= dy && dy <= 1);
        }
    }

    private class LineCheckInput {
        private Line line;
        private int x, y;

        private LineCheckInput(Line line, int x, int y) {
            this.line = line;
            this.x = x;
            this.y = y;
        }
    }
}