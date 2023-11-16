package model;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    captureMostPiecesStrategy = new CaptureMostPiecesStrategy();
  }


  @Test
  public void testCaptureMostThrowWhenGameOver(){
    this.log = new StringBuilder();
    mockGameOver = new ReversiModelMockGameOver(log);
    Assert.assertThrows(IllegalStateException.class,
        () -> captureMostPiecesStrategy.chooseMove(mockGameOver, Color.BLACK));
    Assert.assertTrue(log.toString().isEmpty());
  }

  @Test
  public void testCaptureMostReturnOptionalWhenNoPossibleMove(){
    this.log = new StringBuilder();
    mockNoPossibleMoves = new ReversiModelMockNoPossibleMoves(log);
    Assert.assertEquals(this.captureMostPiecesStrategy.chooseMove(mockNoPossibleMoves, Color.WHITE),
            Optional.empty());
  }

  @Test
  public void captureMostStratPickUpperLeftWhenTie(){
    this.log = new StringBuilder();
    mockSameScore = new ReversiModelMockSameScore(log);
    Assert.assertArrayEquals(new int[]{0, -1, +1},
        captureMostPiecesStrategy.chooseMove(mockSameScore, Color.BLACK).get());

    File file = new File("test/model/strategy-transcript.txt");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(log.toString());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testCaptureMostReturnHighestScore() {
    this.log = new StringBuilder();
    mockOneHighScore = new ReversiModelMockOneHighScore(log);
    Assert.assertArrayEquals(
            this.captureMostPiecesStrategy.chooseMove(mockOneHighScore, Color.WHITE).get(),
            new int[]{0, 1, -1});
  }




}
