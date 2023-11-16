import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.Hexagon;
import model.StandardHexagonalBoard;
import model.StandardHexagonalReversiModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Tests the public game play of the reversi model.
 */
public class PublicStandardReversiModelTests {

  StandardHexagonalReversiModel model;
  StandardHexagonalBoard board;


  @Before
  public void init() {
    this.model = new StandardHexagonalReversiModel(2);
    this.board = new StandardHexagonalBoard(2);
  }

  @Test
  public void testInitialization() {
    assertEquals(this.model.getScore(Color.WHITE), 3);
    assertEquals(this.model.getScore(Color.BLACK), 3);
    assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
  }

  @Test
  public void testPass() {
    this.model.pass();
    assertEquals(this.model.getCurrentPlayer(), Color.BLACK);
    this.model.pass();
    assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
  }

  @Test
  public void testPassTwiceGameOverAndCanTPassAgain() {
    this.model.pass();
    this.model.pass();
    Assert.assertTrue(this.model.isGameOver());
    Assert.assertThrows(IllegalArgumentException.class, () -> this.model.pass());
  }

  @Test
  public void testValidMove() {
    Hexagon valid = new Hexagon(-1, 0, +1);
    assertTrue(this.model.getCurrentBoardState().isOccupiedTile(valid));
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(valid), Color.WHITE);
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
        new Hexagon(0, -1, 1)), Color.BLACK);
    this.model.move(Color.WHITE, 1, -2, 1);
    //this should flip the black piece inbetween
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
        new Hexagon(0, -1, 1)), Color.WHITE);
    //check player is now switched
    assertEquals(this.model.getCurrentPlayer(), Color.BLACK);
    //check the piece is now placed at the given position
    assertEquals(this.model.getCurrentBoardState().
        whoOccupiesThisTile(new Hexagon(1, -2, 1)), Color.WHITE);
    assertEquals(this.model.getScore(Color.WHITE), 5);
  }

  @Test
  public void testMoveFlipMultipleTiles() {
    this.model = new StandardHexagonalReversiModel(3);
    this.model.move(Color.WHITE, 1, -2, 1);

    //now see if when black moves, the two white pieces get flipped.
    Hexagon valid = new Hexagon(1, 0, -1);
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(valid), Color.BLACK);
    this.model.move(Color.BLACK, 1, -3, 2);

    //check the pieces in between are all flipped to Black
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
        new Hexagon(1, -1, 0)), Color.BLACK);
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
        new Hexagon(1, -2, 1)), Color.BLACK);
    //check the piece is now placed at the given position
    assertEquals(this.model.getCurrentBoardState().
        whoOccupiesThisTile(new Hexagon(1, -3, 2)), Color.BLACK);

    //check player now switched
    assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
  }

  @Test
  public void testMoveOnOccupiedTile() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () ->
        this.model.move(Color.WHITE, 1, -1, 0));
    assertEquals("Tile is already occupied WHITE", exception.getMessage());
  }

  @Test
  public void testInvalidLogicalMove() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () ->
        this.model.move(Color.WHITE, 0, 0, 0));
    assertEquals("Invalid logical move!", exception.getMessage());
  }

  @Test
  public void testOutOfBoundsCoordinatesMoveThrowsException() {
    Assert.assertThrows(IllegalArgumentException.class,
        () -> this.model.move(Color.WHITE, 5, 5, 5));
  }

  @Test
  public void testCanMakeAnyMoveValid() {
    Assert.assertTrue(this.model.canMakeAnyMove(Color.WHITE));
    Assert.assertTrue(this.model.canMakeAnyMove(Color.BLACK));
  }

  @Test
  public void testCantMakeMoves() {
    this.model = new StandardHexagonalReversiModel(2);
    this.model.move(Color.WHITE, 1, -2, 1);
    this.model.move(Color.BLACK, 1, 1, -2);
    this.model.move(Color.WHITE, -1, 2, -1);
    this.model.move(Color.BLACK, -2, 1, 1);
    this.model.move(Color.WHITE, 2, -1, -1);
    this.model.move(Color.BLACK, -1, -1, 2);
    Assert.assertEquals(this.model.getScore(Color.WHITE), 7);
    Assert.assertEquals(this.model.getScore(Color.BLACK), 5);
    Assert.assertEquals(this.model.getCurrentPlayer(), Color.BLACK);
    Assert.assertFalse(this.model.canMakeAnyMove(Color.WHITE));
    Assert.assertFalse(this.model.canMakeAnyMove(Color.BLACK));
    Assert.assertTrue(this.model.isGameOver());
  }

  @Test
  public void testMoveThrowsExceptionWithSpaceInBetween() {
    this.model = new StandardHexagonalReversiModel(3);
    Throwable exception = assertThrows(IllegalArgumentException.class, () ->
        this.model.move(Color.WHITE, 3, 0, -3));
    assertEquals("Invalid logical move!", exception.getMessage());
  }

  @Test
  public void testMoveThrowsExceptionWNoEndingTileOfSameColor() {
    this.model = new StandardHexagonalReversiModel(2);
    Throwable exception = assertThrows(IllegalArgumentException.class, () ->
        this.model.move(Color.WHITE, 2, 0, -2));
    assertEquals("Invalid logical move!", exception.getMessage());
  }
}
