import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day11 {
  public static void main(String[] args) throws IOException {
    int[][] input = Files.lines(Path.of(args.length > 0 ? args[args.length - 1] : "input"))
            .map(s -> s.chars().map(c -> c - '0').toArray())
            .toArray(int[][]::new);

    Grid g = new Grid(input);

    int flashes = 0;
    for (int i = 0; i < 100; i++) {
      flashes += g.step();
    }
    System.out.println(flashes);

    // reset grid because we may have already synchronized
    int i;
    for (i = 101; g.step() < 100; i++);

    System.out.println(i);
  }
}
