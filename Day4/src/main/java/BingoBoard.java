import java.util.List;

public class BingoBoard {
  private final int boardSize = 5;
  private boolean[][] marked = new boolean[boardSize][boardSize];
  private int[][] numbers = new int[boardSize][boardSize];

  public BingoBoard(List<String> board) {
    for (int i = 0; i < boardSize; i++) {
      String row = board.get(i);
      String[] split = row.trim().split("\s+");
      for (int j = 0; j < boardSize; j++) {
        numbers[i][j] = Integer.parseInt(split[j]);
      }
    }
  }

  public boolean hasWon() {
    boolean won = true;
    for (int i = 0; i < marked.length; i++) {
      won = true;
      for (int j = 0; j < marked[0].length; j++) {
        if (!marked[i][j]) {
          won = false;
          break;
        }
      }
      if (won) return true;
    }
    for (int i = 0; i < marked[0].length; i++) {
      won = true;
      for (int j = 0; j < marked.length; j++) {
        if (!marked[j][i]) {
          won = false;
          break;
        }
      }
      if (won) return true;
    }
    return won;
  }

  public void mark(int number) {
    for (int i = 0; i < numbers.length; i++) {
      for (int j = 0; j < numbers[i].length; j++) {
        if (numbers[i][j] == number) {
          marked[i][j] = true;
        }
      }
    }
  }

  public int getScore() {
    int score = 0;
    for (int i = 0; i < boardSize; i++) {
      for (int j = 0; j < boardSize; j++) {
        if (!marked[i][j]) score += numbers[i][j];
      }
    }
    return score;
  }
}
