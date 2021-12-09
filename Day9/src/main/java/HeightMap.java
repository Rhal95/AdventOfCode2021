import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;

public class HeightMap {
  List<List<Integer>> heightMap;

  public HeightMap(List<List<Integer>> heightMap) {
    this.heightMap = heightMap;
  }

  private boolean outOfBounds(Point p) {
    return p.y < 0 ||
            p.x < 0 ||
            heightMap.size() <= p.y ||
            heightMap.get(p.y).size() <= p.x;
  }

  private int get(Point p) {
    return heightMap.get(p.y).get(p.x);
  }

  private int getRisk(Point p) {
    // check the position is inside the board
    if (outOfBounds(p)) return 0;
    int height = get(p);
    if (!outOfBounds(p.left()) && get(p.left()) <= height) return 0;
    if (!outOfBounds(p.right()) && get(p.right()) <= height) return 0;
    if (!outOfBounds(p.above()) && get(p.above()) <= height) return 0;
    if (!outOfBounds(p.below()) && get(p.below()) <= height) return 0;
    return height + 1;
  }

  public int getRiskSum() {
    int riskSum = 0;
    for (int y = 0; y < heightMap.size(); y++) {
      for (int x = 0; x < heightMap.get(y).size(); x++) {
        int risk= getRisk(new Point(x,y));
        if(risk > 0){
          riskSum += risk;
        }
      }
    }
    return riskSum;
  }

  private void findBasin(Set<Point> basin, Point p){
    int depth = get(p);
    basin.add(p);

    Consumer<Point> checkPosition = (point)->{
      if (!outOfBounds(point) &&
              get(point) >= depth &&
              get(point) < 9 &&
              !basin.contains(point)
      ) findBasin(basin, point);
    };

    checkPosition.accept(p.left());
    checkPosition.accept(p.right());
    checkPosition.accept(p.above());
    checkPosition.accept(p.below());
  }

  private int getBasinSize(Point p){
    Set<Point> basin = new TreeSet<>();
    findBasin(basin, p);
    return basin.size();
  }

  public int basinSizes(){
    Map<Point, Integer> basins = new TreeMap<>();
    for (int y = 0; y < heightMap.size(); y++) {
      for (int x = 0; x < heightMap.get(y).size(); x++) {
        Point p = new Point(x, y);
        if(getRisk(p) > 0) basins.put(p, getBasinSize(p));
      }
    }
    return basins.values()
            .stream()
            .sorted(Comparator.reverseOrder())
            .limit(3)
            .reduce((a, b)->a*b)
            .orElse(0);
  }

  public static void main(String[] args) throws IOException {
    List<List<Integer>> input = Files.lines(Path.of(args.length > 0 ? args[args.length-1] : "input"))
            .map(s -> s.chars()
                    .map(c -> c - '0')
                    .boxed()
                    .toList())
            .toList();
    HeightMap heightMap = new HeightMap(input);
    System.out.println(heightMap.getRiskSum());
    System.out.println(heightMap.basinSizes());
  }

  record Point(int x, int y) implements Comparable<Point>{
    @Override
    public int compareTo(Point o) {
      if(this.equals(o)) return 0;
      return Integer.compare(this.x,o.x) + Integer.compare(this.y, o.y) * 2;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof Point p) return x == p.x && y == p.y;
      return false;
    }

    public Point above(){
      return new Point(x, y-1);
    }
    public Point below(){
      return new Point(x, y+1);
    }
    public Point left(){
      return new Point(x-1, y);
    }
    public Point right(){
      return new Point(x+1, y);
    }
  }
}
