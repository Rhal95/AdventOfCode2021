public class Grid {
  private final int[][] data;

  public Grid(int[][] data) {
    this.data = data;
  }

  public int[][] getBoard() {
    return data;
  }

  private void flash(int x, int y) {
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        int xx = x + i;
        int yy = y + j;
        if (0 <= xx && xx < 10 && 0 <= yy && yy < 10) data[xx][yy] += 1;
      }
    }
  }

  private void incrementAll() {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        data[i][j]++;
      }
    }
  }

  private boolean tryFlash(boolean[][] flashMap) {
    boolean flashed = false;
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (data[i][j] >= 10 && !flashMap[i][j]) {
          flashMap[i][j] = true;
          flash(i,j);
          flashed = true;
        }
      }
    }
    return flashed;
  }

  public int step() {
    incrementAll();

    boolean[][] flashMap = new boolean[10][10];

    while (tryFlash(flashMap)) ;

    resetValues();

    return countFlashes(flashMap);
  }

  private void resetValues() {
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (data[i][j] >= 10)data[i][j] = 0;
      }
    }
  }

  private int countFlashes(boolean[][] flashMap) {
    int flashes = 0;
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 10; j++) {
        if (flashMap[i][j])flashes++;
      }
    }
    return flashes;
  }
}
