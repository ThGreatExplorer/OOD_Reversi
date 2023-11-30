package Controller;

import org.junit.Assert;
import org.junit.Test;

import controller.Controller;
import model.Color;
import model.ReversiModel;
import model.StandardHexagonalReversiModel;
import player.HumanPlayer;
import player.Player;
import view.GUIView;
import view.ReversiGraphicsView;

public class controllerMockTests {

  private String getLineInLog(int line, StringBuilder log){
    String[] lines = log.toString().split("\n");
    if (lines.length < line) {
      return "";
    }
    return lines[line-1];
  }


  @Test
  public void viewCalledToUpdate() {
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



    System.out.println(player1ControllerLog);
    System.out.println(player2ControllerLog);
  }

}
