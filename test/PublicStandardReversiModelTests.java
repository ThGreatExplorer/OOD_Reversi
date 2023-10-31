import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import Model.Color;
import Model.Hexagon;
import Model.StandardHexagonalBoard;
import Model.StandardHexagonalReversiModel;
import View.ReversiTextualView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
    Hexagon valid = new Hexagon(-1,0,+1);
    assertEquals(this.model.getCurrentBoardState().isOccupiedTile(valid), true);
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(valid), Color.WHITE);
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
            new Hexagon(0, -1, 1)), Color.BLACK);
    this.model.move(1, -2, 1);
    //this should flip the black piece inbetween
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
            new Hexagon(0, -1, 1)), Color.WHITE);
    //check player is now switched
    assertEquals(this.model.getCurrentPlayer(), Color.BLACK);
    //check the piece is now placed at the given position
    assertEquals(this.model.getCurrentBoardState().
            whoOccupiesThisTile(new Hexagon(1,-2,1)), Color.WHITE);
    assertEquals(this.model.getScore(Color.WHITE), 5);
  }

  @Test
  public void testMoveFlipMultipleTiles() {
    this.model = new StandardHexagonalReversiModel(3);
    this.model.move(1, -2, 1);

    //now see if when black moves, the two white pieces get flipped.
    Hexagon valid = new Hexagon(1,0,-1);
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(valid), Color.BLACK);
    this.model.move(1, -3, 2);

    //check the pieces in between are all flipped to Black
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
            new Hexagon(1, -1, 0)), Color.BLACK);
    assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
            new Hexagon(1, -2, 1)), Color.BLACK);
    //check the piece is now placed at the given position
    assertEquals(this.model.getCurrentBoardState().
            whoOccupiesThisTile(new Hexagon(1,-3,2)), Color.BLACK);

    //check player now switched
    assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
  }

  @Test
  public void testMoveOnOccupiedTile() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      this.model.move(1,-1,0);
    });
    assertEquals("Tile is already occupied WHITE", exception.getMessage());
  }

  @Test
  public void testInvalidLogicalMove() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      this.model.move(0,0,0);
    });
    assertEquals("Invalid logical move!", exception.getMessage());
  }

  @Test
  public void testOutOfBoundsCoordinatesMoveThrowsException() {
    Assert.assertThrows(IllegalArgumentException.class,
            () -> this.model.move(5,5,5));
  }

  @Test
  public void testCanMakeMovesValid() {
    Assert.assertEquals(this.model.canMakeMove(Color.WHITE), true);
    Assert.assertEquals(this.model.canMakeMove(Color.BLACK), true);
  }

  @Test
  public void testCanTMakeMoves() {
    this.model = new StandardHexagonalReversiModel(2);
    this.model.move(1, -2, 1);
    this.model.move(1, 1, -2);
    this.model.move(-1, 2, -1);
    this.model.move(-2, 1, 1);
    this.model.move(2, -1, -1);
    this.model.move(-1, -1, 2);
    Assert.assertEquals(this.model.getScore(Color.WHITE), 7);
    Assert.assertEquals(this.model.getScore(Color.BLACK), 5);
    //TODO Black not white
    Assert.assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
    Assert.assertEquals(this.model.canMakeMove(Color.WHITE), false);
    Assert.assertEquals(this.model.canMakeMove(Color.BLACK), false);
    Assert.assertEquals(this.model.isGameOver(), true);
  }
}
