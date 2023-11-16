package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the StandardHexagonalReversiModel.
 */
public class StandardHexagonalReversiModelTest {
  StandardHexagonalReversiModel model;
  StandardHexagonalBoard board;

  @Test
  public void testNoMoveMoresToMakeThrowsException() {
    this.board =
        new StandardHexagonalBoard(ReversiModelGameStateGeneration.generate3RingsNoCenter());
    this.model = new StandardHexagonalReversiModel(this.board);
    this.model.pass();
    Throwable exception = assertThrows(IllegalArgumentException.class, () ->
        this.model.move(Color.BLACK,0, 0, 0));
    assertEquals("Can't make any moves, must pass!", exception.getMessage());
  }

  @Test
  public void testFilledBoardMoveThrowsException() {
    this.board = new StandardHexagonalBoard(ReversiModelGameStateGeneration.
        generate3RingsWhiteFilled());
    this.model = new StandardHexagonalReversiModel(this.board);
    Throwable exception = assertThrows(IllegalArgumentException.class, () ->
        this.model.move(Color.WHITE, 1, -1, 0));
    assertEquals("Can't make any moves, must pass!", exception.getMessage());
  }


  @Test
  public void testcanMakeAnyMoveForFiledBoard() {
    this.board = new StandardHexagonalBoard(ReversiModelGameStateGeneration
        .generate3RingsWhiteFilled());
    this.model = new StandardHexagonalReversiModel(this.board);
    assertFalse(this.model.canMakeAnyMove(Color.WHITE));
    assertFalse(this.model.canMakeAnyMove(Color.BLACK));
    assertEquals(this.model.getScore(Color.WHITE), 16);
    assertEquals(this.model.getScore(Color.BLACK), 3);
  }

  @Test
  public void testCanMakeAnyMoveForNotFilledONLYWhiteMove() {
    this.board =
        new StandardHexagonalBoard(ReversiModelGameStateGeneration.generate3RingsNoCenter());

    this.model = new StandardHexagonalReversiModel(this.board);
    assertTrue(this.model.canMakeAnyMove(Color.WHITE));
    assertFalse(this.model.canMakeAnyMove(Color.BLACK));
    assertEquals(this.model.getScore(Color.BLACK), 3);
    assertEquals(this.model.getScore(Color.WHITE), 15);
  }

  @Test
  public void testCanMakeAnyMoveForNotFilledNeitherWhiteOrBlack() {
    this.board =
        new StandardHexagonalBoard(ReversiModelGameStateGeneration.
            generate3RingsBlackAndWhiteCantMove());
    this.model = new StandardHexagonalReversiModel(this.board);
    assertFalse(this.model.canMakeAnyMove(Color.WHITE));
    assertFalse(this.model.canMakeAnyMove(Color.BLACK));
    assertEquals(this.model.getScore(Color.WHITE), 3);
    assertEquals(this.model.getScore(Color.BLACK), 9);
  }

  @Test
  public void testGameIsOverFilledBoard() {
    this.board = new StandardHexagonalBoard(ReversiModelGameStateGeneration.
        generate3RingsWhiteFilled());
    this.model = new StandardHexagonalReversiModel(this.board);
    assertTrue(this.model.isGameOver());
  }

  @Test
  public void testGameIsNOTOverOnlyWhiteCanMove() {
    this.board =
        new StandardHexagonalBoard(ReversiModelGameStateGeneration.generate3RingsNoCenter());

    this.model = new StandardHexagonalReversiModel(this.board);
    Assert.assertFalse(this.model.isGameOver());
    HashMap<Hexagon, Integer> hexagonIntegerHashMap = new HashMap<>();
    hexagonIntegerHashMap.put(new Hexagon(0,0,0), 3);
    Assert.assertEquals(this.model.getValidMoveScores(Color.WHITE), hexagonIntegerHashMap);
    Assert.assertEquals(this.model.getValidMoveScores(Color.BLACK), new HashMap<>());
    this.model.move(Color.WHITE, 0, 0, 0);

    //now game is over
    Assert.assertTrue(this.model.isGameOver());
  }

  @Test
  public void testGameIsOverWhenBothPlayersHaveToPass() {
    this.board =
        new StandardHexagonalBoard(ReversiModelGameStateGeneration.
            generate3RingsBlackAndWhiteCantMove());
    this.model = new StandardHexagonalReversiModel(this.board);
    Assert.assertTrue(this.model.getValidMoveScores(model.getCurrentPlayer()).isEmpty());
    Assert.assertTrue(this.model.isGameOver());
  }



}
