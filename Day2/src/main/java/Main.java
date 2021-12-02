import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
  public static void main(String[] args) throws IOException {
    System.out.println("Input");
    List<String> input = readInput();
    input.forEach(System.out::println);

    System.out.println("Output");

    System.out.println("Part 1");
    Submarine submarine = new Submarine(input);
    System.out.println(submarine.getHorizontal() * submarine.getDepth());

    System.out.println("Part 2");
    SubmarineAim submarineAim = new SubmarineAim(input);
    System.out.println(submarineAim.getHorizontal() * submarineAim.getDepth());
  }

  public static List<String> readInput() throws IOException {
    return Files.readAllLines(Path.of("Day2/input"));
  }
}
