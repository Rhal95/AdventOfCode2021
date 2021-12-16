import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Graph {
  Set<Cave> nodes = new TreeSet<>();
  Set<Pair<Cave>> edges = new TreeSet<>();

  public void addNode(Cave cave) {
    nodes.add(cave);
  }

  public void addEdge(Pair<Cave> edge) {
    nodes.add(edge.a());
    nodes.add(edge.b());
    edges.add(edge);
  }

  public List<List<Cave>> getAllPaths() {
    return getAllPaths(new LinkedList<>(), new Cave.Start());
  }

  public List<List<Cave>> getAllPaths(List<Cave> currentPath, Cave current) {
    currentPath.add(current);
    if (current instanceof Cave.End) return List.of(currentPath); // success

    return edges.stream()
            .filter(pair -> pair.contains(current))
            .map(pair -> pair.a().equals(current) ? pair.b() : pair.a())
            .filter(Predicate.not(cave -> cave instanceof Cave.SmallCave && currentPath.contains(cave)))
            .filter(Predicate.not(Cave.Start.class::isInstance))
            .map(cave -> getAllPaths(new LinkedList<>(currentPath), cave))
            .filter(Predicate.not(List::isEmpty))
            .flatMap(Collection::stream)
            .filter(Predicate.not(List::isEmpty))
            .toList();
  }

  public List<List<Cave>> getAllPathsCheating() {
    return getAllPathsCheating(new LinkedList<>(), new Cave.Start());
  }

  boolean canAddToList(List<Cave> list, Cave c) {
    // is not start
    if (c instanceof Cave.Start) return false;
    // is small cave
    if (!(c instanceof Cave.SmallCave)) return true;

    List<Cave> smallCaves = list.stream().filter(Cave.SmallCave.class::isInstance).toList();
    Set<Cave> set = Set.copyOf(smallCaves);
    // if diff > 1 then there are at least two same elements in the list
    if(set.size() < smallCaves.size()) return !set.contains(c);
    return true;
  }

  private boolean isLegal(List<Cave> currentPath){
    if (currentPath.indexOf(new Cave.Start()) != currentPath.lastIndexOf(new Cave.Start())) return false;
    List<Cave> smallCaves = currentPath.stream().filter(Cave.SmallCave.class::isInstance).toList();
    return true;
  }

  public List<List<Cave>> getAllPathsCheating(List<Cave> currentPath, Cave current) {
    currentPath.add(current);
    if (current instanceof Cave.End) return List.of(currentPath); // success
    if(!isLegal(currentPath))return List.of();

    return edges.stream()
            .filter(pair -> pair.contains(current))
            .map(pair -> pair.a().equals(current) ? pair.b() : pair.a())
            .filter(cave -> canAddToList(currentPath, cave))
            .map(cave -> getAllPathsCheating(new LinkedList<>(currentPath), cave))
            .flatMap(Collection::stream)
            .toList();
  }

}
