package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import player.CaptureMostPiecesStrategy;
import player.FalliblePlayerStrategies;

/**
 * Tests the strategies using pre-set game conditions.
 */
public class StrategyWithModelTests {
  ReadOnlyReversiModel model;
  FalliblePlayerStrategies fallibleStrategy;

  @Before
  public void init() {
    fallibleStrategy = new CaptureMostPiecesStrategy();
  }

  @Test
  public void captureMostPiecesStrategyGetsMostPiecesInDifferentDirections() {
    model = new StandardHexagonalReversiModel(
        new StandardHexagonalBoard(ReversiModelGameStateGeneration
            .optimalWhiteMoveHasTwoDirections()));
    Assert.assertArrayEquals(new int[]{-2, 1, 1},
        fallibleStrategy.chooseMove(model, Color.WHITE).get());
  }

  @Test
  public void captureMostPiecesStrategyThrowsWithGameOver() {
    model = new StandardHexagonalReversiModel(
        new StandardHexagonalBoard(ReversiModelGameStateGeneration
            .generate3RingsBlackAndWhiteCantMove()));
    Assert.assertThrows(IllegalStateException.class,
        () -> fallibleStrategy.chooseMove(model, Color.BLACK));
  }
}
