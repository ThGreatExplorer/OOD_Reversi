package view;

import org.junit.Assert;
import org.junit.Test;

import controller.Controller;
import model.Color;
import model.ReversiModel;
import model.StandardHexagonalReversiModel;
import player.AIPlayer;
import player.CaptureMostPiecesStrategy;
import player.CompleteStrategyFromFallible;
import player.HumanPlayer;
import player.Player;

/**
 * These are tests to check that the view is being notified when it needs to update
 * and when the game is over.
 */
public class viewMockTests {

  private String getLineInLog(int line, StringBuilder log){
    String[] lines = log.toString().split("\n");
    if (lines.length < line) {
      return "";
    }
    return lines[line-1];
  }


  @Test
  public void viewCalledToUpdate() {
    StringBuilder player1ViewLog = new StringBuilder();
    StringBuilder player2ViewLog = new StringBuilder();

    ReversiModel model = new StandardHexagonalReversiModel(3);
    GUIView view1 = new ReversiGraphicsViewMock(model, player1ViewLog);
    GUIView view2 = new ReversiGraphicsViewMock(model, player2ViewLog);

    Player player1 = new HumanPlayer(Color.WHITE);
    Player player2 = new HumanPlayer(Color.BLACK);

    Controller controller1 = new Controller(model, view1, player1);
    Controller controller2 = new Controller(model, view2, player2);

    model.startGame();

    Assert.assertTrue(getLineInLog(1, player1ViewLog).contains("Request to update"));
    Assert.assertTrue(getLineInLog(1, player2ViewLog).contains("Request to update"));

    model.move(Color.WHITE, -1, -1, 2);
    Assert.assertTrue(getLineInLog(2, player1ViewLog).contains("Request to update"));
    Assert.assertTrue(getLineInLog(2, player2ViewLog).contains("Request to update"));

    model.move(Color.BLACK, 1, 1, -2);
    Assert.assertTrue(getLineInLog(3, player1ViewLog).contains("Request to update"));
    Assert.assertTrue(getLineInLog(3, player2ViewLog).contains("Request to update"));

    //System.out.println(player1ViewLog);
    //System.out.println(player2ViewLog);
  }

  @Test
  public void viewCalledWhenGameOver() {
    StringBuilder player1ViewLog = new StringBuilder();
    StringBuilder player2ViewLog = new StringBuilder();

    ReversiModel model = new StandardHexagonalReversiModel(3);
    GUIView view1 = new ReversiGraphicsViewMock(model, player1ViewLog);
    GUIView view2 = new ReversiGraphicsViewMock(model, player2ViewLog);

    Player player1 = new AIPlayer(model, Color.WHITE,
        new CompleteStrategyFromFallible(new CaptureMostPiecesStrategy()));
    Player player2 = new AIPlayer(model ,Color.BLACK,
        new CompleteStrategyFromFallible(new CaptureMostPiecesStrategy()));

    Controller controller1 = new Controller(model, view1, player1);
    Controller controller2 = new Controller(model, view2, player2);

    model.startGame();

    //System.out.println(player1ViewLog);
    //System.out.println(player2ViewLog);

    Assert.assertTrue(getLineInLog(24, player1ViewLog).contains("Request to update"));
    Assert.assertTrue(getLineInLog(11, player2ViewLog).contains("Request to update"));

    Assert.assertTrue(getLineInLog(25, player1ViewLog).contains("Winner!"));
    Assert.assertTrue(getLineInLog(12, player2ViewLog).contains("Winner!"));

  }

  @Test
  public void gameOverWithTwoPass() {
    StringBuilder player1ViewLog = new StringBuilder();
    StringBuilder player2ViewLog = new StringBuilder();

    ReversiModel model = new StandardHexagonalReversiModel(3);
    GUIView view1 = new ReversiGraphicsViewMock(model, player1ViewLog);
    GUIView view2 = new ReversiGraphicsViewMock(model, player2ViewLog);

    Player player1 = new HumanPlayer(Color.WHITE);
    Player player2 = new HumanPlayer(Color.BLACK);

    Controller controller1 = new Controller(model, view1, player1);
    Controller controller2 = new Controller(model, view2, player2);

    model.startGame();

    model.pass();
    model.pass();

    //System.out.println(player1ViewLog);
    //System.out.println(player2ViewLog);

    Assert.assertTrue(getLineInLog(4, player1ViewLog).contains("Tie!"));
    Assert.assertTrue(getLineInLog(4, player2ViewLog).contains("Tie!"));

  }

}
