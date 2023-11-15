package model;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.Optional;

import player.CaptureMostPiecesStrategy;

public class testStrategyWithMock {

  StringBuilder log;
  ReversiModelMockSameScore sameScore;
  ReversiModelMockNoPossibleMoves noPossibleMoves;
  ReversiModelMockOneHighScore oneHighScore;
  ReversiModelMockGameOver gameOver;
  CaptureMostPiecesStrategy captureMostPiecesStrategy;

  @Before
  public void init() {
    this.log = new StringBuilder();
    sameScore = new ReversiModelMockSameScore(log);
    noPossibleMoves = new ReversiModelMockNoPossibleMoves(log);
    oneHighScore = new ReversiModelMockOneHighScore(log);
    gameOver = new ReversiModelMockGameOver(log);
    captureMostPiecesStrategy = new CaptureMostPiecesStrategy();
  }


  @Test
  public void testCaptureMostthrowWhenGameOver(){
    Assert.assertArrayEquals(this.captureMostPiecesStrategy.chooseMove(sameScore, Color.WHITE).get(),
            new int[]{0,-1,1});
    Assert.assertEquals(this.log.toString(), "asked for moves\n");
  }

  @Test
  public void testCaptureMostReturnOptionalWhenNoPossibleMove(){
    Assert.assertEquals(this.captureMostPiecesStrategy.chooseMove(noPossibleMoves, Color.WHITE),
            Optional.empty());
  }

  @Test
  public void testCaptureMostReturnOptionalWhenGameOverWithMoves() {
    Assert.assertEquals(this.captureMostPiecesStrategy.chooseMove(gameOver, Color.WHITE),
            Optional.empty());
  }

  @Test
  public void testCaptureMostRetunHighestScore() {
    Assert.assertArrayEquals(
            this.captureMostPiecesStrategy.chooseMove(oneHighScore, Color.WHITE).get(),
            new int[]{0, 1, -1});
  }




}
