package model;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Optional;

import player.CaptureMostPiecesStrategy;

public class TestStrategyWithMock {

  StringBuilder log;
  ReversiModelMockSameScore mockSameScore;
  ReversiModelMockNoPossibleMoves mockNoPossibleMoves;
  ReversiModelMockOneHighScore mockOneHighScore;
  ReversiModelMockGameOver mockGameOver;
  CaptureMostPiecesStrategy captureMostPiecesStrategy;

  @Before
  public void init() {
    this.log = new StringBuilder();
    mockSameScore = new ReversiModelMockSameScore(log);
    mockNoPossibleMoves = new ReversiModelMockNoPossibleMoves(log);
    mockOneHighScore = new ReversiModelMockOneHighScore(log);
    mockGameOver = new ReversiModelMockGameOver(log);
    captureMostPiecesStrategy = new CaptureMostPiecesStrategy();
  }


  @Test
  public void testCaptureMostThrowWhenGameOver(){
    Assert.assertThrows(IllegalStateException.class,
        () -> captureMostPiecesStrategy.chooseMove(mockGameOver, Color.BLACK));
    Assert.assertTrue(log.toString().isEmpty());
  }

  @Test
  public void testCaptureMostReturnOptionalWhenNoPossibleMove(){
    Assert.assertEquals(this.captureMostPiecesStrategy.chooseMove(mockNoPossibleMoves, Color.WHITE),
            Optional.empty());
  }

  @Test
  public void captureMostStratPickUpperLeftWhenTie(){
    Assert.assertArrayEquals(new int[]{0, -1, +1},
        captureMostPiecesStrategy.chooseMove(mockSameScore, Color.BLACK).get());
  }

  @Test
  public void testCaptureMostReturnHighestScore() {
    Assert.assertArrayEquals(
            this.captureMostPiecesStrategy.chooseMove(mockOneHighScore, Color.WHITE).get(),
            new int[]{0, 1, -1});
  }




}
