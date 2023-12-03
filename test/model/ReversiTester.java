package model;

import controller.Controller;
import player.HumanPlayer;
import player.Player;
import view.GUIView;
import view.ReversiGraphicsView;

/**
 * Test runner for pre-configured board states.
 */
public class ReversiTester {

  /**
   * Tests if the view and model work together and can display a pre-set condition.
   */
  public static void main(String[] args) {
    ReversiModel model =
            new StandardHexagonalReversiModel(
                    ReversiModelGameStateGeneration.generate3RingsWhiteFilled());

    GUIView view1 = new ReversiGraphicsView(model);
    GUIView view2 = new ReversiGraphicsView(model);


    Player  player1 = new HumanPlayer(Color.WHITE);
    Player  player2 = new HumanPlayer(Color.BLACK);

    Controller controller1 = new Controller(model, view1, player1);
    Controller controller2 = new Controller(model, view2, player2);
    view1.setVisible();
    view2.setVisible();
    model.startGame();
  }
}
