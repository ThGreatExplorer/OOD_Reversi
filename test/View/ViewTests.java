package View;

import org.junit.Assert;
import org.junit.Test;

import Model.PlayingBoard;
import Model.StandardHexagonalBoard;

public class ViewTests {

  @Test
  public void abstractRepresentationArrayCorrectShapeWithBoardSize2(){
    PlayingBoard boardState = new StandardHexagonalBoard(2);
    int[][] boardRepresentation = AbstractHexagonRepresentation.boardByNumber(boardState);
    Assert.assertEquals(5, boardRepresentation.length);

    Assert.assertEquals(3, boardRepresentation[0].length);
    Assert.assertEquals(4, boardRepresentation[1].length);
    Assert.assertEquals(5, boardRepresentation[2].length);
    Assert.assertEquals(4, boardRepresentation[3].length);
    Assert.assertEquals(3, boardRepresentation[4].length);
  }

  @Test
  public void abstractRepresentationArrayCorrectShapeWithBoardSize5(){
    PlayingBoard boardState = new StandardHexagonalBoard(5);
    int[][] boardRepresentation = AbstractHexagonRepresentation.boardByNumber(boardState);
    Assert.assertEquals(11, boardRepresentation.length);

    Assert.assertEquals(6, boardRepresentation[0].length);
    Assert.assertEquals(8, boardRepresentation[2].length);
    Assert.assertEquals(11, boardRepresentation[5].length);
    Assert.assertEquals(10, boardRepresentation[6].length);
    Assert.assertEquals(9, boardRepresentation[7].length);
    Assert.assertEquals(7, boardRepresentation[9].length);
    Assert.assertEquals(6, boardRepresentation[10].length);
  }

}