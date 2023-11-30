package controller;

import org.junit.Assert;
import org.junit.Test;

import model.Color;
import model.ReversiModel;
import model.StandardHexagonalReversiModel;
import player.HumanPlayer;
import player.Player;
import view.GUIView;
import view.ReversiGraphicsView;

/**
 * This class tests the overall control flow by testing that the player action features and model
 * observer features within the controller umbrella are called at appropriate times.
 */
public class ControllerMockTests {

  private String getLineInLog(int line, StringBuilder log) {
    String[] lines = log.toString().split("\n");
    if (lines.length < line) {
      return "";
    }
    return lines[line - 1];
  }


  @Test
  public void controllerFeaturesCalledToUpdate() {
    StringBuilder player1ControllerLog = new StringBuilder();
    StringBuilder player2ControllerLog = new StringBuilder();

    ReversiModel model = new StandardHexagonalReversiModel(3);
    GUIView view1 = new ReversiGraphicsView(model);
    GUIView view2 = new ReversiGraphicsView(model);

    Player player1 = new HumanPlayer(Color.WHITE);
    Player player2 = new HumanPlayer(Color.BLACK);

    ControllerMock controller1 = new ControllerMock(model, view1, player1, player1ControllerLog);
    ControllerMock controller2 = new ControllerMock(model, view2, player2, player2ControllerLog);

    model.startGame();

    Assert.assertTrue(getLineInLog(1, player1ControllerLog).contains("notified of move made"));
    Assert.assertTrue(getLineInLog(1, player2ControllerLog).contains("notified of move made"));

    controller1.playerActionFeatures.playMove(-1, -1, 2);
    Assert.assertTrue(getLineInLog(2, player1ControllerLog)
        .contains("WHITE Attempted to move to:"));

    controller2.playerActionFeatures.passMove();
    Assert.assertTrue(getLineInLog(2, player2ControllerLog)
        .contains("BLACK passed"));

    //System.out.println(player1ControllerLog);
    //System.out.println(player2ControllerLog);
  }

}
