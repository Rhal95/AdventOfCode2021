import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Main {
  public static void main(String[] args) {
    List<String> input = new ArrayList<>(InputReader.readInput("input"));
    String inputString = input.get(0);
    input.remove(0);

    List<BingoBoard> bingoBoards = Arrays.stream(input.stream()
            .reduce((s1, s2) -> s1 + "\n" + s2)
            .get()
            .split("\n\n"))
            .filter(Predicate.not(String::isEmpty))
            .map(s1 -> Arrays.stream(s1.split("\n"))
                    .filter(Predicate.not(String::isEmpty))
                    .toList())
            .map(BingoBoard::new).toList();

    List<Integer> inputNumbers = Arrays.stream(inputString.split(",")).map(Integer::parseInt).toList();

    boolean winnerFound = false, loserFound = false;
    for (int number: inputNumbers){
      List<BingoBoard> potentialLosers = bingoBoards.stream().filter(Predicate.not(BingoBoard::hasWon)).toList();
      bingoBoards.forEach(bingoBoard -> bingoBoard.mark(number));
      if(potentialLosers.size() == 1 && !loserFound && potentialLosers.get(0).hasWon()){
        System.out.println("Last to win");
        System.out.println(potentialLosers.get(0).getScore());
        System.out.println(number);
        System.out.println(potentialLosers.get(0).getScore() * number);
        loserFound = true;
      }
      Optional<BingoBoard> potentialWinner = bingoBoards.stream().filter(BingoBoard::hasWon).findFirst();
      if (potentialWinner.isPresent() && !winnerFound){
        System.out.println("First to win");
        System.out.println(potentialWinner.get().getScore());
        System.out.println(number);
        System.out.println(potentialWinner.get().getScore() * number);
        winnerFound = true;
      }

    }
  }
}
