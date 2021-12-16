import java.util.Objects;

public sealed class Cave implements Comparable<Cave> {
  String name;

  public Cave(String name) {
    this.name = name;
  }


  @Override
  public int compareTo(Cave o) {
    return this.name.compareTo(o.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "Cave{" + name + '}';
  }

  public static final class BigCave extends Cave {
    public BigCave(String name) {
      super(name);
    }

    @Override
    public String toString() {
      return "BigCave{" + name + '}';
    }
  }

  public static final class SmallCave extends Cave {
    public SmallCave(String name) {
      super(name);
    }

    @Override
    public String toString() {
      return "SmallCave{" + name + '}';
    }
  }

  public static final class Start extends Cave {
    public Start() {
      super("start");
    }

    @Override
    public String toString() {
      return "Start";
    }
  }

  public static final class End extends Cave {
    public End() {
      super("end");
    }

    @Override
    public String toString() {
      return "End";
    }
  }
}
