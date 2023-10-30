package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    Assert.assertEquals(this.model.getScore(Color.WHITE), 3);
    Assert.assertEquals(this.model.getScore(Color.BLACK), 3);
    Assert.assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
    //Assert.assertEquals(this.model.getCurrentBoardState(), this.board);
  }

  @Test
  public void testPass() {
    this.model.pass();
    Assert.assertEquals(this.model.getCurrentPlayer(), Color.BLACK);
    this.model.pass();
    Assert.assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
  }

  @Test
  public void testValidMove() {
    Hexagon valid = new Hexagon(-1,0,+1);
    Assert.assertEquals(this.model.getCurrentBoardState().isOccupiedTile(valid), true);
    Assert.assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(valid), Color.WHITE);
    Assert.assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
            new Hexagon(0, -1, 1)), Color.BLACK);
    this.model.move(1, -2, 1);
    //this should flip the black piece inbetween
    Assert.assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
            new Hexagon(0, -1, 1)), Color.WHITE);
    //check player is now switched
    Assert.assertEquals(this.model.getCurrentPlayer(), Color.BLACK);
    //check the piece is now placed at the given position
    Assert.assertEquals(this.model.getCurrentBoardState().
            whoOccupiesThisTile(new Hexagon(1,-2,1)), Color.WHITE);
  }

  @Test
  public void testMoveFlipMultipleTiles() {
    this.model = new StandardHexagonalReversiModel(3);
    this.model.move(1, -2, 1);

    //now see if when black moves, the two white pieces get flipped.
    Hexagon valid = new Hexagon(1,0,-1);
    Assert.assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(valid), Color.BLACK);
    this.model.move(1, -3, 2);

    //check the pieces in between are all flipped to Black
    Assert.assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
            new Hexagon(1, -1, 0)), Color.BLACK);
    Assert.assertEquals(this.model.getCurrentBoardState().whoOccupiesThisTile(
            new Hexagon(1, -2, 1)), Color.BLACK);
    //check the piece is now placed at the given position
    Assert.assertEquals(this.model.getCurrentBoardState().
            whoOccupiesThisTile(new Hexagon(1,-3,2)), Color.BLACK);

    //check player now switched
    Assert.assertEquals(this.model.getCurrentPlayer(), Color.WHITE);
  }

}
