package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import Model.Hexagon;
import Model.StandardHexagonalBoard;

public class StandardHexagonalBoardTest {
  StandardHexagonalBoard board;
  int boardSize;

  @Before
  public void init() {
    this.boardSize = 1;
    this.board = new StandardHexagonalBoard(1);
  }

  @Test
  public void testConstructorGeneratesHexagonsCorrectly() {
    Assert.assertEquals(this.board.getBoard(), new ArrayList<>(
            Arrays.asList(new Hexagon(0,0,0), new Hexagon(-1, 0, +1),
                    new Hexagon(0, -1, +1),
                    new Hexagon(+1, -1, 0), new Hexagon(+1, 0, -1),
                    new Hexagon(0, +1, -1), new Hexagon(-1, +1, 0))
    ));
  }

  @Test
  public void testConstructorGeneratesMultipleRingHexagons() {
    this.board = new StandardHexagonalBoard(3);
    Assert.assertEquals(this.board.getBoard().size(), 37);
  }

  @Test
  public void testConstructorGeneratesMultipleRingsII() {
    this.board = new StandardHexagonalBoard(4);
    Assert.assertEquals(this.board.getBoard().size(), 61);
  }

  /*@Test
  public void testConstructorIntializes{}*/
}
