import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Day13Test {

  String testInput = """
          6,10
          0,14
          9,10
          0,3
          10,4
          4,11
          6,0
          6,12
          4,1
          0,13
          10,12
          3,4
          3,0
          8,4
          1,10
          2,14
          8,10
          9,0
                            
          fold along y=7
          fold along x=5
          """;

  @Test
  void getFolds() {
    List<TransparentPaper.Fold> folds = Day13.getFolds(testInput);

    assertThat(folds)
            .hasSize(2)
            .contains(
                    new TransparentPaper.Fold.Horizontal(7),
                    new TransparentPaper.Fold.Vertical(5)
            );
  }

  @Test
  void getPoints() {
    List<Point> points = Day13.getPoints(testInput);

    assertThat(points)
            .hasSize(18)
            .contains(
                    new Point(6, 10),
                    new Point(0, 14),
                    new Point(9, 10),
                    new Point(0, 3),
                    new Point(10, 4),
                    new Point(4, 11),
                    new Point(6, 0),
                    new Point(6, 12),
                    new Point(4, 1),
                    new Point(0, 13),
                    new Point(10, 12),
                    new Point(3, 4),
                    new Point(3, 0),
                    new Point(8, 4),
                    new Point(1, 10),
                    new Point(2, 14),
                    new Point(8, 10),
                    new Point(9, 0)
            );
  }

  @Test
  void input() {
    TransparentPaper paper = Day13.parseInput(testInput);

    assertThat(paper.width()).isEqualTo(11);
    assertThat(paper.height()).isEqualTo(15);

    assertThat(paper.marked(6, 10)).isTrue();
    assertThat(paper.marked(0, 14)).isTrue();
    assertThat(paper.marked(9, 10)).isTrue();
    assertThat(paper.marked(0, 3)).isTrue();
    assertThat(paper.marked(10, 4)).isTrue();
    assertThat(paper.marked(4, 11)).isTrue();
    assertThat(paper.marked(6, 0)).isTrue();
    assertThat(paper.marked(6, 12)).isTrue();
    assertThat(paper.marked(4, 1)).isTrue();
    assertThat(paper.marked(0, 13)).isTrue();
    assertThat(paper.marked(10, 12)).isTrue();
    assertThat(paper.marked(3, 4)).isTrue();
    assertThat(paper.marked(3, 0)).isTrue();
    assertThat(paper.marked(8, 4)).isTrue();
    assertThat(paper.marked(1, 10)).isTrue();
    assertThat(paper.marked(2, 14)).isTrue();
    assertThat(paper.marked(8, 10)).isTrue();
    assertThat(paper.marked(9, 0)).isTrue();

    assertThat(paper.getPlannedFolds()).contains(
            new TransparentPaper.Fold.Horizontal(7),
            new TransparentPaper.Fold.Vertical(5)
    );
  }
}
