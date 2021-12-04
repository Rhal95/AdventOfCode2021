import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

public class InputReader {
  public static List<String> readInput(String path) {
    InputStream inputStream = InputReader.class
            .getClassLoader()
            .getResourceAsStream(path);
    Objects.requireNonNull(path);
    return new BufferedReader(
            new InputStreamReader(inputStream))
            .lines().toList();
  }
}
