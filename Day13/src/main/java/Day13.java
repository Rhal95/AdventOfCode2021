import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day13 {


  public static TransparentPaper parseInput(String input) {
    List<Point> points = getPoints(input);

    List<TransparentPaper.Fold> folds = getFolds(input);

    return new TransparentPaper(points, folds);
  }

  static List<TransparentPaper.Fold> getFolds(String input) {
    return input.lines()
            .filter(Predicate.not(String::isEmpty))
            .filter(s->s.contains("="))
            .map(s -> s.split("="))
            .map(s -> s[0].endsWith("x") ? new TransparentPaper.Fold.Vertical(Integer.parseInt(s[1])) : new TransparentPaper.Fold.Horizontal(Integer.parseInt(s[1])))
            .toList();
  }

  static List<Point> getPoints(String input) {
    return Arrays.stream(input.trim()
                    .split("\\R"))
            .takeWhile(Predicate.not(String::isEmpty))
            .filter(Predicate.not(String::isEmpty))
            .map(s -> Arrays.stream(s.split(",")).map(Integer::parseInt).collect(Collectors.toList()))
            .map(integers -> new Point(integers.get(0), integers.get(1))).toList();
  }

  public static void main(String[] args) throws IOException {
    String input = Files.readString(Path.of(args.length > 0 ? args[args.length - 1] : "input"));

    TransparentPaper transparentPaper = parseInput(input);
    transparentPaper.fold();
    System.out.println(transparentPaper.getMarkedPoints().size());


    while(!transparentPaper.getPlannedFolds().isEmpty()) transparentPaper.fold();
    transparentPaper.print();
  }
}
