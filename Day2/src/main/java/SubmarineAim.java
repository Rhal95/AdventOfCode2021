import java.util.List;

public class SubmarineAim {
  int horizontal = 0;
  int depth = 0;
  int aim = 0;

  public SubmarineAim(List<String> input) {
    input.forEach(this::move);
  }

  private void move(String command) {
    Direction direction = parseDirection(command);
    int distance = parseDistance(command);

    switch (direction){
      case Forward -> {
        horizontal += distance;
        depth += aim * distance;
      }
      case Down -> aim += distance;
      case Up -> aim -= distance;
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
    throw new RuntimeException("illegal Direction");
  }

  public int getHorizontal() {
    return horizontal;
  }

  public int getDepth() {
    return depth;
  }
}
