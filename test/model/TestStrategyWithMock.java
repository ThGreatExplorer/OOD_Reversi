package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import player.CaptureMostPiecesStrategy;
import player.FalliblePlayerStrategies;

public class TestStrategyWithMock {

  @Test
  public void captureMostStratThrowWhenGameOver(){
    StringBuilder log = new StringBuilder();
    ReadOnlyReversiModel model = new ReversiModelMockGameOver(log, 3);
    FalliblePlayerStrategies strategy = new CaptureMostPiecesStrategy();
    Assert.assertThrows(IllegalStateException.class, () -> strategy.chooseMove(model, Color.BLACK));
    Assert.assertTrue(log.toString().isEmpty());
  }

  @Test
  public void captureMostStratReturnOptionalWhenNoPossibleMove(){
    ReadOnlyReversiModel model = new ReversiModelMockNoPossibleMoves(3);
    FalliblePlayerStrategies strategy = new CaptureMostPiecesStrategy();
    Assert.assertEquals(Optional.empty(), strategy.chooseMove(model, Color.BLACK));
  }

  @Test
  public void captureMostStratPickUperLeftWhenTie(){
    ReadOnlyReversiModel model = new ReversiModelMockSameScore(3);
    FalliblePlayerStrategies strategy = new CaptureMostPiecesStrategy();
    Assert.assertArrayEquals(new int[]{0, -1, +1}, strategy.chooseMove(model, Color.BLACK).get());
  }

  @Test
  public void captureMostStratPickHighestScore(){
    ReadOnlyReversiModel model = new ReversiModelMockOneHighScore(3);
    FalliblePlayerStrategies strategy = new CaptureMostPiecesStrategy();
    Assert.assertArrayEquals(new int[]{0, 1, -1}, strategy.chooseMove(model, Color.BLACK).get());
  }

}
