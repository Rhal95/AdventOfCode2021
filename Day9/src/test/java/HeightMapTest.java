import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class HeightMapTest {

  @Test
  void readInput() throws IOException {
    List<List<Integer>> input = Files.lines(Path.of("testInput"))
            .map(s -> s.chars()
                    .map(c -> c - '0')
                    .boxed()
                    .toList())
            .toList();
    assertThat(input).containsExactly(
            List.of(2, 1, 9, 9, 9, 4, 3, 2, 1, 0),
            List.of(3, 9, 8, 7, 8, 9, 4, 9, 2, 1),
            List.of(9, 8, 5, 6, 7, 8, 9, 8, 9, 2),
            List.of(8, 7, 6, 7, 8, 9, 6, 7, 8, 9),
            List.of(9, 8, 9, 9, 9, 6, 5, 6, 7, 8)
    );
  }

  @Test
  void getRiskSum() {
    List<List<Integer>> input = List.of(
            List.of(2, 1, 9, 9, 9, 4, 3, 2, 1, 0),
            List.of(3, 9, 8, 7, 8, 9, 4, 9, 2, 1),
            List.of(9, 8, 5, 6, 7, 8, 9, 8, 9, 2),
            List.of(8, 7, 6, 7, 8, 9, 6, 7, 8, 9),
            List.of(9, 8, 9, 9, 9, 6, 5, 6, 7, 8)
    );

    HeightMap heightMap = new HeightMap(input);
    Assertions.assertEquals(15, heightMap.getRiskSum());
  }
}
