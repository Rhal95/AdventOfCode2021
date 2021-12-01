import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    List<String> input = readInput();
    List<Integer> integerInput = input.stream()
            .map(Integer::parseInt)
            .toList();
    SonarSweep sonarSweep = new SonarSweep(integerInput);

    System.out.println("Input");
    integerInput.forEach(System.out::println);

    System.out.println("\nOutput");

    System.out.println("Part 1");
    System.out.println(sonarSweep.getDepthIncreases());

    System.out.println("Part 2");
    System.out.println(sonarSweep.getDepthIncreasesFlattened());
  }

  public static List<String> readInput() throws IOException {
    return Files.readAllLines(Path.of("Day1/input"));
  }
}
