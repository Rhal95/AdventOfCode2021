import java.util.List;
import java.util.Optional;

public class TransparentPaper {
  List<Point> markedPoints;
  List<Fold> folds;

  private int width;
  private int height;

  public TransparentPaper(List<Point> markedPoints, List<Fold> folds) {
    this.markedPoints = markedPoints;
    this.folds = folds;
    this.width  = calculateWidth(markedPoints);
    this.height  = calculateHeight(markedPoints);
  }

  private int calculateHeight(List<Point> markedPoints) {
    return markedPoints.stream().map(Point::y).reduce(0, (i, i2) -> i < i2 ? i2 : i) + 1;
  }

  private int calculateWidth(List<Point> points) {
    return points.stream().map(Point::x).reduce(0, (i, i2) -> i < i2 ? i2 : i) + 1;
  }


  public int width() {
    return this.width;
  }

  public int height() {
    return this.height;
  }

  public boolean marked(int x, int y) {
    return markedPoints.contains(new Point(x, y));
  }

  public List<Fold> getPlannedFolds() {
    return folds;
  }

  public List<Point> getMarkedPoints() {
    return markedPoints;
  }


  public void fold(){
    Optional<Fold> fold = folds.stream().findFirst();
    folds = folds.stream().skip(1).toList();
    if (fold.isPresent() && fold.get() instanceof Fold.Horizontal y){
      markedPoints = markedPoints.stream().map(point -> y.y < point.y() ? new Point(point.x(), point.y() - (point.y() - y.y)*2) : point).distinct().toList();
    }else if(fold.isPresent() && fold.get() instanceof Fold.Vertical x){
      markedPoints = markedPoints.stream().map(point -> x.x < point.x() ? new Point(point.x() - (point.x() - x.x)*2, point.y()) : point).distinct().toList();
    }
    width = calculateWidth(markedPoints);
    height = calculateHeight(markedPoints);
  }

  public void print(){
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        System.out.print(marked(x,y) ? "#" : ".");
      }
      System.out.println();
    }
  }

  public abstract sealed static class Fold {
    static final class Horizontal extends Fold {
      private final int y;

      public Horizontal(int y) {
        this.y = y;
      }

      public int y() {
        return y;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Horizontal that = (Horizontal) o;

        return y == that.y;
      }

      @Override
      public int hashCode() {
        return y;
      }
    }

    static final class Vertical extends Fold {
      private final int x;

      public Vertical(int x) {
        this.x = x;
      }

      public int x() {
        return x;
      }

      @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertical vertical = (Vertical) o;

        return x == vertical.x;
      }

      @Override
      public int hashCode() {
        return x;
      }
    }
  }
}
