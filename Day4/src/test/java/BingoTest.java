import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.List;

class BingoTest {
  @Test
  void main() {
    Main.main(null);
  }

  @Test
  void readInput() {
    List<String> input = InputReader.readInput("input");
    assertThat(input).containsExactly(
            "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
            "",
            "22 13 17 11  0",
            " 8  2 23  4 24",
            "21  9 14 16  7",
            " 6 10  3 18  5",
            " 1 12 20 15 19",
            "",
            " 3 15  0  2 22",
            " 9 18 13 17  5",
            "19  8  7 25 23",
            "20 11 10 24  4",
            "14 21 16 12  6",
            "",
            "14 21 17 24  4",
            "10 16 15  9 19",
            "18  8 23 26 20",
            "22 11 13  6  5",
            " 2  0 12  3  7"
    );
  }

  @Test
  void bingoBoard() {
    BingoBoard bingoBoard = new BingoBoard(List.of(
            "22 13 17 11  0",
            " 8  2 23  4 24",
            "21  9 14 16  7",
            " 6 10  3 18  5",
            " 1 12 20 15 19"));
    bingoBoard.mark(22);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(13);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(17);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(11);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(0);
    assertThat(bingoBoard.hasWon()).isTrue();
  }
  @Test
  void bingoBoard2() {
    BingoBoard bingoBoard = new BingoBoard(List.of(
            "22 13 17 11  0",
            " 8  2 23  4 24",
            "21  9 14 16  7",
            " 6 10  3 18  5",
            " 1 12 20 15 19"));
    bingoBoard.mark(22);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(8);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(21);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(6);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(1);
    assertThat(bingoBoard.hasWon()).isTrue();
  }
  @Test
  void bingoBoard3() {
    BingoBoard bingoBoard = new BingoBoard(List.of(
            "22 13 17 11  0",
            " 8  2 23  4 24",
            "21  9 14 16  7",
            " 6 10  3 18  5",
            " 1 12 20 15 19"));
    bingoBoard.mark(17);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(23);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(14);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(3);
    assertThat(bingoBoard.hasWon()).isFalse();
    bingoBoard.mark(20);
    assertThat(bingoBoard.hasWon()).isTrue();
  }
  @Test
  void bingoBoard4() {
    BingoBoard bingoBoard = new BingoBoard(List.of(
            " 1  1  1  1  1",
            " 1  1  1  1  1",
            " 1  1  1  1  1",
            " 1  1  1  1  1",
            " 1  1  1  1  1"));
    assertThat(bingoBoard.getScore()).isEqualTo(25);
    bingoBoard.mark(1);
    assertThat(bingoBoard.getScore()).isEqualTo(0);
  }
}
