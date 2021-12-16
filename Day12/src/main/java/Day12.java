import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day12 {

  public static List<Pair<Cave>> parseInput(List<String> inputString){
    return inputString.stream()

            .map(s -> Arrays.stream(s.split("-"))
                    .map(String::trim)
                    .map(ss -> ss.equals("start") ? new Cave.Start()
                            : ss.equals("end") ? new Cave.End()
                            : ss.charAt(0) >= 'a' ? new Cave.SmallCave(ss)
                            : new Cave.BigCave(ss)).collect(Collectors.toList()))
            .map(caves -> new Pair<>(caves.get(0), caves.get(1)))
            .toList();
  }

  public static void main(String[] args) throws IOException {

    List<String> inputString = Files.lines(Path.of(args.length > 0 ? args[args.length - 1] : "input"))
            .toList();
    List<Pair<Cave>> input = parseInput(inputString);

    Graph graph = new Graph();

    input.forEach(graph::addEdge);

    System.out.println(graph.getAllPaths().size());
    System.out.println(graph.getAllPathsCheating().size());
  }
}
