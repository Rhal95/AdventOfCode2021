import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day12Test {
  List<String> input = List.of("start-A",
          "start-b",
          "A-c",
          "A-b",
          "b-d",
          "A-end",
          "b-end");
  List<Pair<Cave>> pairs = Day12.parseInput(input);

  @Test
  void inputParse() {
    assertThat(pairs).hasSize(7);
    assertThat(pairs).contains(
            new Pair<>(new Cave.Start(), new Cave.BigCave("A")),
            new Pair<>(new Cave.Start(), new Cave.SmallCave("b")),
            new Pair<>(new Cave.BigCave("A"), new Cave.SmallCave("c")),
            new Pair<>(new Cave.BigCave("A"), new Cave.SmallCave("b")),
            new Pair<>(new Cave.SmallCave("b"), new Cave.SmallCave("d")),
            new Pair<>(new Cave.BigCave("A"), new Cave.End()),
            new Pair<>(new Cave.SmallCave("b"), new Cave.End())
    );
  }

  @Test
  void generatePaths() {
    Graph graph = new Graph();
    pairs.forEach(graph::addEdge);

    assertThat(graph.getAllPaths()).hasSize(10);
  }

  @Test
  void generatePathsCheating() {
    Graph graph = new Graph();
    pairs.forEach(graph::addEdge);

    assertThat(graph.getAllPathsCheating()).hasSize(36);
  }

  @Test
  void generatePathsCheatingLargerExample() {
    Graph graph = new Graph();
    Day12.parseInput("""
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc""".lines().toList()).forEach(graph::addEdge);

    assertThat(graph.getAllPathsCheating()).hasSize(103);
  }

  @Test
  void inputParseLargerExample() {
    List<Pair<Cave>> pairs = Day12.parseInput("""
            dc-end
            HN-start
            start-kj
            dc-start
            dc-HN
            LN-dc
            HN-end
            kj-sa
            kj-HN
            kj-dc""".lines().toList());

    assertThat(pairs).contains(
            new Pair<>(new Cave.SmallCave("dc"), new Cave.End()),
            new Pair<>(new Cave.BigCave("HN"), new Cave.Start()),
            new Pair<>(new Cave.Start(), new Cave.SmallCave("kj"))
    );

    assertThat(pairs).hasSize(10);
  }

  @Test
  void generatePathsCheatingLargeExample() {
    Graph graph = new Graph();
    Day12.parseInput("""
            fs-end
            he-DX
            fs-he
            start-DX
            pj-DX
            end-zg
            zg-sl
            zg-pj
            pj-he
            RW-he
            fs-DX
            pj-RW
            zg-RW
            start-pj
            he-WI
            zg-he
            pj-fs
            start-RW""".lines().toList()).forEach(graph::addEdge);

    assertThat(graph.getAllPathsCheating()).hasSize(3509);
  }

  @Test
  void cave() {
    Cave START = new Cave.Start();
    Cave END = new Cave.End();
    Cave SMALL = new Cave.SmallCave("a");
    Cave LARGE = new Cave.BigCave("A");
    assertThat(START).isEqualTo(new Cave.Start());
    assertThat(END).isEqualTo(new Cave.End());
    assertThat(START).isNotEqualTo(END);
    assertThat(SMALL).isEqualTo(new Cave.SmallCave("a"));
    assertThat(SMALL).isNotEqualTo(new Cave.SmallCave("x"));
    assertThat(SMALL).isNotEqualTo(new Cave.BigCave("a"));
  }

  @Test
  void pair() {
    Cave START = new Cave.Start();
    Cave END = new Cave.End();
    Cave SMALL = new Cave.SmallCave("a");
    assertThat(new Pair<>(START, END)).isEqualTo(new Pair<>(START, END));
    assertThat(new Pair<>(START, END)).isEqualTo(new Pair<>(END, START));
    assertThat(new Pair<>(START, START)).isNotEqualByComparingTo(new Pair<>(END, END));
    assertThat(new Pair<>(START, START)).isNotEqualByComparingTo(new Pair<>(END, END));
    assertThat(new Pair<>(START, SMALL)).isNotEqualByComparingTo(new Pair<>(SMALL, END));
    assertThat(new Pair<>(START, SMALL)).isNotEqualByComparingTo(new Pair<>(END, SMALL));
  }
}
