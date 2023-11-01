package view;

import org.junit.Assert;
import org.junit.Test;

import model.PlayingBoard;
import model.StandardHexagonalBoard;

/**
 * These tests check the board is being represented correctly by the abstract class.
 */
public class AbstractViewTests {

  @Test
  public void abstractRepresentationArrayCorrectShapeWithBoardSize2() {
    PlayingBoard boardState = new StandardHexagonalBoard(2);
    int[][] boardRepresentation = HexagonRepresentation.boardByNumber(boardState);
    Assert.assertEquals(5, boardRepresentation.length);

    Assert.assertEquals(3, boardRepresentation[0].length);
    Assert.assertEquals(4, boardRepresentation[1].length);
    Assert.assertEquals(5, boardRepresentation[2].length);
    Assert.assertEquals(4, boardRepresentation[3].length);
    Assert.assertEquals(3, boardRepresentation[4].length);
  }

  @Test
  public void abstractRepresentationArrayCorrectShapeWithBoardSize5() {
    PlayingBoard boardState = new StandardHexagonalBoard(5);
    int[][] boardRepresentation = HexagonRepresentation.boardByNumber(boardState);
    Assert.assertEquals(11, boardRepresentation.length);

    Assert.assertEquals(6, boardRepresentation[0].length);
    Assert.assertEquals(8, boardRepresentation[2].length);
    Assert.assertEquals(11, boardRepresentation[5].length);
    Assert.assertEquals(10, boardRepresentation[6].length);
    Assert.assertEquals(9, boardRepresentation[7].length);
    Assert.assertEquals(7, boardRepresentation[9].length);
    Assert.assertEquals(6, boardRepresentation[10].length);
  }


  @Test
  public void abstractRepresentationArrayCorrectInitialRepresentationWithBoardSize5() {
    PlayingBoard boardState = new StandardHexagonalBoard(5);
    int[][] boardRepresentation = HexagonRepresentation.boardByNumber(boardState);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, boardRepresentation[0]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0}, boardRepresentation[1]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, boardRepresentation[2]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, boardRepresentation[3]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 2, 1, 0, 0, 0, 0}, boardRepresentation[4]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 1, 0, 2, 0, 0, 0, 0}, boardRepresentation[5]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 2, 1, 0, 0, 0, 0}, boardRepresentation[6]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0}, boardRepresentation[7]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0, 0}, boardRepresentation[8]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0}, boardRepresentation[9]);
    Assert.assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0}, boardRepresentation[10]);
  }

}