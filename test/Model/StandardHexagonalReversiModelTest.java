package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/**
 * Test class for the StandardHexagonalReversiModel.
 */
public class StandardHexagonalReversiModelTest {
  StandardHexagonalReversiModel model;
  StandardHexagonalBoard board;


  @Before
  public void init() {
    this.model = new StandardHexagonalReversiModel(2);
    this.board = new StandardHexagonalBoard(2);
  }

  @Test
  public void testIntialization() {
    assertEquals(this.model.getScore(Color.WHITE), 3);
    assertEquals(this.model.getScore(Color.BLACK), 3);
    assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
    //Assert.assertEquals(this.model.getCurrentBoardState(), this.board);
  }

  @Test
  public void testPass() {
    this.model.pass();
    assertEquals(this.model.getCurrentPlayer(), Color.BLACK);
    this.model.pass();
    assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
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
  public void testNoMoveMoresToMakeThrowsException() {
    this.board =
            new StandardHexagonalBoard(ReversiModelGameStateGeneration.generate3RingsNoCenter());
    this.model = new StandardHexagonalReversiModel(this.board);
    this.model.pass();
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      this.model.move(0,0,0);
    });
    assertEquals("Can't make any moves, must pass!", exception.getMessage());
  }

  @Test
  public void testFilledBoardMoveThrowsException() {
    this.board = new StandardHexagonalBoard(ReversiModelGameStateGeneration.
            generate3RingsWhiteFilled());
    this.model = new StandardHexagonalReversiModel(this.board);
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      this.model.move(1,-1,0);
    });
    assertEquals("Can't make any moves, must pass!", exception.getMessage());
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
  public void testInvalidMove() {
    Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
      this.model.move(0,0,0);
    });
    assertEquals("Invalid logical move!", exception.getMessage());
  }

  @Test
  public void testCanMakeMoveForFiledBoard() {
    this.board = new StandardHexagonalBoard(ReversiModelGameStateGeneration.
            generate3RingsWhiteFilled());
    this.model = new StandardHexagonalReversiModel(this.board);
    assertEquals(this.model.canMakeMove(Color.WHITE), false);
    assertEquals(this.model.canMakeMove(Color.BLACK), false);
    assertEquals(this.model.getScore(Color.WHITE), 16);
    assertEquals(this.model.getScore(Color.BLACK), 3);
  }

  @Test
  public void testCanMakeMoveForNotFilledONLYWhiteMove() {
    this.board =
            new StandardHexagonalBoard(ReversiModelGameStateGeneration.generate3RingsNoCenter());

    this.model = new StandardHexagonalReversiModel(this.board);
    assertEquals(this.model.canMakeMove(Color.WHITE), true);
    assertEquals(this.model.canMakeMove(Color.BLACK), false);
    assertEquals(this.model.getScore(Color.BLACK), 3);
    assertEquals(this.model.getScore(Color.WHITE), 15);
  }

  @Test
  public void testCanMakeMoveForNotFilledNeitherWhiteOrBlack() {
    this.board =
            new StandardHexagonalBoard(ReversiModelGameStateGeneration.
                    generate3RingsBlackAndWhiteCantMove());
    this.model = new StandardHexagonalReversiModel(this.board);
    assertEquals(this.model.canMakeMove(Color.WHITE), false);
    assertEquals(this.model.canMakeMove(Color.BLACK), false);
    assertEquals(this.model.getScore(Color.WHITE), 3);
    assertEquals(this.model.getScore(Color.BLACK), 9);
  }

  @Test
  public void testGameIsOverFilledBoard() {
    this.board = new StandardHexagonalBoard(ReversiModelGameStateGeneration.
            generate3RingsWhiteFilled());
    this.model = new StandardHexagonalReversiModel(this.board);
    assertEquals(this.model.isGameOver(), true);
  }

  @Test
  public void testGameIsNOTOverOnlyWhiteCanMove() {
    this.board =
            new StandardHexagonalBoard(ReversiModelGameStateGeneration.generate3RingsNoCenter());

    this.model = new StandardHexagonalReversiModel(this.board);
    Assert.assertFalse(this.model.isGameOver());
    this.model.move(0,0,0);

    //now game is over
    Assert.assertTrue(this.model.isGameOver());
  }

  @Test
  public void testGameIsOverWhenBothPlayersHaveToPass() {
    this.board =
            new StandardHexagonalBoard(ReversiModelGameStateGeneration.
                    generate3RingsBlackAndWhiteCantMove());
    this.model = new StandardHexagonalReversiModel(this.board);
    Assert.assertTrue(this.model.isGameOver());
  }


}
