import java.util.List;

public class Submarine {
  int horizontal = 0;
  int depth = 0;

  public Submarine(List<String> input) {
    input.forEach(this::move);
  }

  private void move(String command) {
    Direction direction = parseDirection(command);
    int distance = parseDistance(command);

    switch (direction){
      case Forward -> horizontal += distance;
      case Down -> depth += distance;
      case Up -> depth -= distance;
    }
  }

  private int parseDistance(String command) {
    String length = command.split(" ")[1];
    return Integer.parseInt(length);
  }

  private Direction parseDirection(String command) {
    if (command.startsWith("forward")) return Direction.Forward;
    if (command.startsWith("down")) return Direction.Down;
    if (command.startsWith("up")) return Direction.Up;
    throw new RuntimeException("invalid direction");
  }

  public int getHorizontal() {
    return horizontal;
  }

  public int getDepth() {
    return depth;
  }
}
