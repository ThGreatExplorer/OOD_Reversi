import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.StandardSquareBoard;
import model.StandardSquareReversiModel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class StandardSquareReversiModelTests {
  StandardSquareReversiModel squareReversiModel;
  StandardSquareBoard squareBoard;

  @Before
  public void init() {
    this.squareReversiModel = new StandardSquareReversiModel(4);
    this.squareBoard = new StandardSquareBoard(4);
  }

  @Test
  public void testInitialization() {
    System.out.println(this.squareReversiModel.getCurrentBoardState().getBoard());
    Assert.assertEquals(this.squareReversiModel.getScore(Color.WHITE), 2);
    Assert.assertEquals(this.squareReversiModel.getScore(Color.BLACK), 2);
    Assert.assertEquals(this.squareReversiModel.getCurrentPlayer(), Color.WHITE);
    Assert.assertEquals(this.squareReversiModel.getColorAt(1,1), Color.BLACK);
    Assert.assertEquals(this.squareReversiModel.getColorAt(2,1), Color.WHITE);
    Assert.assertEquals(this.squareReversiModel.getColorAt(1,2), Color.WHITE);
    Assert.assertEquals(this.squareReversiModel.getColorAt(2,2), Color.BLACK);
  }

  @Test
  public void testPass() {
    this.squareReversiModel.pass();
    assertEquals(this.squareReversiModel.getCurrentPlayer(), Color.BLACK);
    this.squareReversiModel.pass();
    assertEquals(this.squareReversiModel.getCurrentPlayer(), Color.WHITE);
  }

  @Test
  public void testPassTwiceGameOverAndCanTPassAgain() {
    this.squareReversiModel.pass();
    this.squareReversiModel.pass();
    Assert.assertTrue(this.squareReversiModel.isGameOver());
    Assert.assertThrows(IllegalArgumentException.class, () -> this.squareReversiModel.pass());
  }

  @Test
  public void testMoveOnOccupiedTile() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () ->
            this.squareReversiModel.move(Color.WHITE, 1,1));
    assertEquals("Tile is already occupied BLACK", exception.getMessage());
  }

  @Test
  public void testInvalidLogicalMove() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () ->
            this.squareReversiModel.move(Color.WHITE, 0, 0));
    assertEquals("Invalid logical move!", exception.getMessage());
  }

  @Test
  public void testOutOfBoundsCoordinatesMoveThrowsException() {
    Assert.assertThrows(IllegalArgumentException.class,
            () -> this.squareReversiModel.move(Color.WHITE, 5, 5, 5));
  }

  @Test
  public void testCanMakeAnyMoveValid() {
    Assert.assertTrue(this.squareReversiModel.canMakeAnyMove(Color.WHITE));
    Assert.assertTrue(this.squareReversiModel.canMakeAnyMove(Color.BLACK));
  }

  @Test
  public void testValidMove() {
    Assert.assertEquals(this.squareReversiModel.getColorAt(2,1), Color.WHITE);
    Assert.assertEquals(this.squareReversiModel.getColorAt(1,1), Color.BLACK);
    Assert.assertEquals(this.squareReversiModel.getColorAt(0,1), null);

    //perform move
    this.squareReversiModel.move(Color.WHITE, 0, 1);

    Assert.assertEquals(this.squareReversiModel.getScore(Color.WHITE), 4);
    Assert.assertEquals(this.squareReversiModel.getScore(Color.BLACK), 1);
    Assert.assertEquals(this.squareReversiModel.getColorAt(0,1), Color.WHITE);
    Assert.assertEquals(this.squareReversiModel.getColorAt(1,1), Color.WHITE);
    Assert.assertEquals(this.squareReversiModel.getCurrentPlayer(), Color.BLACK);
  }

}

