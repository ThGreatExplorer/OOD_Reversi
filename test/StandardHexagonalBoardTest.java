import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.Color;
import model.Hexagon;
import model.StandardHexagonalBoard;

/**
 * Tests the public methods of the standard hexagonal board.
 */

public class StandardHexagonalBoardTest {

  StandardHexagonalBoard standardBoard;
  Map<Hexagon, Color> occupied;

  @Before
  public void init() {
    this.standardBoard = new StandardHexagonalBoard(4);
    this.occupied = standardBoard.getOccupiedTiles();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorExceptions() {
    new StandardHexagonalBoard(1);
  }

  @Test
  public void testConstructorGeneratesHexagonsCorrectly() {
    this.standardBoard = new StandardHexagonalBoard(2);

    Assert.assertTrue(standardBoard.getBoard().containsAll(new ArrayList<>(
        Arrays.asList(new Hexagon(0, 0, 0), new Hexagon(-1, 0, +1),
            new Hexagon(0, -1, +1),
            new Hexagon(+1, -1, 0), new Hexagon(+1, 0, -1),
            new Hexagon(0, +1, -1), new Hexagon(-1, +1, 0)))));
  }

  @Test
  public void testConstructorGeneratesMultipleRingHexagons() {
    this.standardBoard = new StandardHexagonalBoard(3);
    Assert.assertEquals(standardBoard.getBoard().size(), 37);
  }

  @Test
  public void testConstructorGeneratesMultipleRingsII() {
    Assert.assertEquals(standardBoard.getBoard().size(), 61);
  }

  @Test
  public void testConstructorGeneratesStartingColors() {
    Map<Hexagon, Color> occupied = standardBoard.getOccupiedTiles();
    Assert.assertEquals(occupied.size(), 6);
    Assert.assertEquals(occupied.get(new Hexagon(-1, 0, +1)), Color.WHITE);
    Assert.assertEquals(occupied.get(new Hexagon(0, -1, +1)), Color.BLACK);
    Assert.assertEquals(occupied.get(new Hexagon(+1, -1, 0)), Color.WHITE);
    Assert.assertEquals(occupied.get(new Hexagon(+1, 0, -1)), Color.BLACK);
    Assert.assertEquals(occupied.get(new Hexagon(0, +1, -1)), Color.WHITE);
    Assert.assertEquals(occupied.get(new Hexagon(-1, +1, 0)), Color.BLACK);
  }

  @Test
  public void testGetSize() {
    Assert.assertEquals(this.standardBoard.getSize(), 4);
  }

  @Test
  public void testIsOccupiedTile() {
    Assert.assertTrue(this.standardBoard.isOccupiedTile(new Hexagon(1, 0, -1)));
    Assert.assertFalse(this.standardBoard.isOccupiedTile(new Hexagon(-2, 0, 2)));
  }

  @Test
  public void testWhoOccupiesTiles() {
    Assert.assertTrue(this.standardBoard.isOccupiedTile(new Hexagon(1, 0, -1)));
    Assert.assertEquals(this.standardBoard.whoOccupiesThisTile(new Hexagon(1, 0, -1)),
        Color.BLACK);

    Assert.assertThrows(IllegalArgumentException.class,
        () -> this.standardBoard.whoOccupiesThisTile(new Hexagon(-2, 0, 2)));
  }

  @Test
  public void testBoardConstructionOrder() {
    List<Hexagon> tiles = this.standardBoard.getBoard();
    Assert.assertEquals(tiles.get(0), new Hexagon(0,0,0));
    Assert.assertEquals(tiles.get(1), new Hexagon(-1,0,1));
    Assert.assertEquals(tiles.get(2), new Hexagon(0,-1,1));
    Assert.assertEquals(tiles.get(6), new Hexagon(-1,1,0));
    Assert.assertEquals(tiles.get(7), new Hexagon(-2,0,2));
    Assert.assertEquals(tiles.get(8), new Hexagon(-1, -1, 2));
    Assert.assertEquals(tiles.get(17).getQ(), -1);
    Assert.assertEquals(tiles.get(17).getR(), 2);
    Assert.assertEquals(tiles.get(17).getS(), -1);
    Assert.assertEquals(tiles.get(18).getQ(), -2);
    Assert.assertEquals(tiles.get(18).getR(), 2);
    Assert.assertEquals(tiles.get(18).getS(), 0);
    Assert.assertEquals(tiles.get(19).getQ(), -3);
    Assert.assertEquals(tiles.get(19).getR(), 0);
    Assert.assertEquals(tiles.get(19).getS(), 3);

    Assert.assertEquals(tiles.contains(new Hexagon(-2,1,1)),true);
    String s = tiles.stream().map(h -> "(" + h.getQ() + "," + h.getR() + "," + h.getS() + "), ")
                    .collect(Collectors.joining());
    System.out.println(s);
  }
}
