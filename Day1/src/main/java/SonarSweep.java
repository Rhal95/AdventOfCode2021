import java.util.List;

public class SonarSweep {
  private final int[] depths;

  public SonarSweep(List<Integer> depths) {
    this.depths = depths.stream().mapToInt(Integer::intValue).toArray();
  }

  private int countIncreases(int[] depths){
    int depthIncreases = 0;
    for (int i = 0; i < depths.length-1; i++) {
      int reference = depths[i];
      int depth = depths[i+1];
      if(reference < depth) depthIncreases++;
    }
    return depthIncreases;
  }

  public int getDepthIncreases() {
    return countIncreases(this.depths);
  }

  public int getDepthIncreasesFlattened(){
    int[] numberGroups = new int[depths.length-2];
    for (int i = 0; i < depths.length - 2; i++) {
      int a = depths[i], b = depths[i+1], c = depths[i+2];
      numberGroups[i] = a+b+c;
    }
    return countIncreases(numberGroups);
  }
}
