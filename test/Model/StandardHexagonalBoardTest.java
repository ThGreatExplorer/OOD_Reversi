package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StandardHexagonalBoardTest {

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions() {
    StandardHexagonalBoard standardHexagonalBoard = new StandardHexagonalBoard(1);
  }

  @Test
  public void testConstructorGeneratesHexagonsCorrectly() {
    StandardHexagonalBoard standardBoard = new StandardHexagonalBoard(2);

    Assert.assertTrue(standardBoard.getBoard().containsAll(new ArrayList<>(
            Arrays.asList(new Hexagon(0,0,0), new Hexagon(-1, 0, +1),
                    new Hexagon(0, -1, +1),
                    new Hexagon(+1, -1, 0), new Hexagon(+1, 0, -1),
                    new Hexagon(0, +1, -1), new Hexagon(-1, +1, 0)))));
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

  @Test
  public void testConstructorGeneratesStartingColors() {
    StandardHexagonalBoard standardBoard = new StandardHexagonalBoard(4);
    Map<Hexagon, Color> occupied = standardBoard.getOccupiedTiles();
    Assert.assertEquals(occupied.size(), 6);
    Assert.assertEquals(occupied.get(new Hexagon(-1, 0, +1)), Color.WHITE);
    Assert.assertEquals(occupied.get(new Hexagon(0, -1, +1)), Color.BLACK);
    Assert.assertEquals(occupied.get( new Hexagon(+1, -1, 0)), Color.WHITE);
    Assert.assertEquals(occupied.get(new Hexagon(+1, 0, -1)), Color.BLACK);
    Assert.assertEquals(occupied.get(new Hexagon(0, +1, -1)), Color.WHITE);
    Assert.assertEquals(occupied.get(new Hexagon(-1, +1, 0)), Color.BLACK);
  }


}
