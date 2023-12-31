package model;

import org.junit.Assert;
import org.junit.Test;

import view.ReversiHexagonalTextualView;

/**
 * Integration tests for the model and view, that at any game state and that any changes in the
 * model is reflected in the view accordingly.
 */

public class ModelViewIntegrationTests {
  ReversiHexagonalTextualView view;
  StandardHexagonalReversiModel model;
  StandardHexagonalBoard board;

  @Test
  public void testGeneration3RingsFilledViewCorrect() {
    this.board = ReversiModelGameStateGeneration.generate3RingsWhiteFilled();
    this.model = new StandardHexagonalReversiModel(board);
    this.view = new ReversiHexagonalTextualView(model);
    Assert.assertEquals(view.render(),
        "   O O O   \n" +
            "  O X O O \n" +
            " O O O X O \n" +
            "  O X O O \n" +
            "   O O O   ");
  }

  @Test
  public void testGeneration3RingsNoCenterViewCorrect() {
    this.board = ReversiModelGameStateGeneration.generate3RingsNoCenter();
    this.model = new StandardHexagonalReversiModel(board);
    this.view = new ReversiHexagonalTextualView(model);
    Assert.assertEquals(view.render(),
        "   O O O   \n" +
            "  O X O O \n" +
            " O O _ X O \n" +
            "  O X O O \n" +
            "   O O O   ");
  }

  @Test
  public void testGeneration3RingsBlackWhiteCantMoveViewCorrect() {
    this.board = ReversiModelGameStateGeneration.generate3RingsBlackAndWhiteCantMove();
    this.model = new StandardHexagonalReversiModel(board);
    this.view = new ReversiHexagonalTextualView(model);
    Assert.assertEquals(view.render(),
        "   _ X _   \n" +
            "  X X O X \n" +
            " _ O _ X _ \n" +
            "  X X O X \n" +
            "   _ X _   ");
  }

  @Test
  public void testPlay3RingGameFromStartToFinish() {
    this.model = new StandardHexagonalReversiModel(2);
    this.view = new ReversiHexagonalTextualView(model);
    Assert.assertEquals(this.view.render(),
        "   _ _ _   \n" +
            "  _ X O _ \n" +
            " _ O _ X _ \n" +
            "  _ X O _ \n" +
            "   _ _ _   ");
    this.model.move(this.model.getCurrentPlayer(), 1, -2, 1);
    Assert.assertEquals(this.view.render(),
        "   _ O _   \n" +
            "  _ O O _ \n" +
            " _ O _ X _ \n" +
            "  _ X O _ \n" +
            "   _ _ _   ");
    this.model.move(this.model.getCurrentPlayer(), 1, 1, -2);
    Assert.assertEquals(this.view.render(),
        "   _ O _   \n" +
            "  _ O O _ \n" +
            " _ O _ X _ \n" +
            "  _ X X X \n" +
            "   _ _ _   ");
    this.model.move(this.model.getCurrentPlayer(), -1, 2, -1);
    Assert.assertEquals(this.view.render(),
        "   _ O _   \n" +
            "  _ O O _ \n" +
            " _ O _ X _ \n" +
            "  _ O X X \n" +
            "   _ O _   ");
    this.model.move(this.model.getCurrentPlayer(), -2, 1, 1);
    Assert.assertEquals(this.view.render(),
        "   _ O _   \n" +
            "  _ O O _ \n" +
            " _ O _ X _ \n" +
            "  X X X X \n" +
            "   _ O _   ");
    this.model.move(this.model.getCurrentPlayer(), 2, -1, -1);
    Assert.assertEquals(this.view.render(),
        "   _ O _   \n" +
            "  _ O O O \n" +
            " _ O _ O _ \n" +
            "  X X O X \n" +
            "   _ O _   ");
    this.model.move(this.model.getCurrentPlayer(), -1, -1, 2);
    Assert.assertEquals(this.view.render(),
        "   _ O _   \n" +
            "  X O O O \n" +
            " _ X _ O _ \n" +
            "  X X O X \n" +
            "   _ O _   ");
    Assert.assertEquals(this.model.getScore(Color.WHITE), 7);
    Assert.assertEquals(this.model.getScore(Color.BLACK), 5);
    Assert.assertEquals(this.model.getCurrentPlayer(), Color.BLACK);
    Assert.assertFalse(this.model.canMakeAnyMove(Color.WHITE));
    Assert.assertFalse(this.model.canMakeAnyMove(Color.BLACK));
    Assert.assertTrue(this.model.isGameOver());
  }
}

