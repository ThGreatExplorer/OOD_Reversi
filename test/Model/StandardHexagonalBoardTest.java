package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class StandardHexagonalBoardTest {
  @Test
  public void testConstructorGeneratesHexagonsCorrectly() {
    StandardHexagonalBoard standardBoard = new StandardHexagonalBoard(1);

    Assert.assertEquals(standardBoard.getBoard(), new ArrayList<>(
            Arrays.asList(new Hexagon(0,0,0), new Hexagon(-1, 0, +1),
                    new Hexagon(0, -1, +1),
                    new Hexagon(+1, -1, 0), new Hexagon(+1, 0, -1),
                    new Hexagon(0, +1, -1), new Hexagon(-1, +1, 0))
    ));
  }

  @Test
  public void testConstructorGeneratesMultipleRingHexagons() {
    StandardHexagonalBoard standardBoard = new StandardHexagonalBoard(3);
    Assert.assertEquals(standardBoard.getBoard().size(), 37);
  }

  @Test
  public void testConstructorGeneratesMultipleRingsII() {
    StandardHexagonalBoard standardBoard = new StandardHexagonalBoard(4);
    Assert.assertEquals(standardBoard.getBoard().size(), 61);
  }

  /*@Test
  public void testConstructorIntializes{}*/
}
