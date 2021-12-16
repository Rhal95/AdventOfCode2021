import java.util.Objects;
import java.util.stream.Stream;

public record Pair<A extends Comparable<A>>(A a, A b) implements Comparable<Pair<A>> {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pair<?> pair = (Pair<?>) o;
    return (Objects.equals(a, pair.a) && Objects.equals(b, pair.b)) ||
            Objects.equals(a, pair.b) && Objects.equals(b, pair.a);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(a) + Objects.hashCode(b);
  }

  public boolean contains(A elem) {
    return a.equals(elem) || b.equals(elem);
  }

  @Override
  public int compareTo(Pair<A> o) {
    return Stream.of(
                    this.a().compareTo(o.a()),
                    this.b().compareTo(o.b()),
                    this.a().compareTo(o.b()),
                    this.b().compareTo(o.a())
            )
            .filter(i -> i != 0)
            .reduce((i1, i2) -> i1 * i2)
            .orElse(0);
  }
}
