import javax.naming.ldap.Control;

import controller.Controller;
import model.Color;
import model.ReadOnlyReversiModel;
import model.ReversiModel;
import model.StandardHexagonalReversiModel;
import player.AIPlayer;
import player.CaptureMostPiecesStrategy;
import player.CompleteStrategyFromFallible;
import player.HumanPlayer;
import player.Player;
import view.GUIView;
import view.ReversiGraphicsView;

/**
 * The main class that executes a game of Reversi.
 */
public final class Reversi {

  /**
   * Creates a model and view to run the game.
   */
  public static void main(String[] args) {
    ReversiModel model =
            new StandardHexagonalReversiModel(8);
    GUIView view1 = new ReversiGraphicsView(model);
    Player human1 = new HumanPlayer(Color.WHITE, model);
    Controller controller1 = new Controller(model, view1, human1);
    view1.setVisible();
    GUIView view2 = new ReversiGraphicsView(model);
    Player human2 = new HumanPlayer(Color.BLACK, model);
    Controller controller2 = new Controller(model, view2, human2);
    view2.setVisible();
    //Player ai = new AIPlayer(model, Color.BLACK,
    //        new CompleteStrategyFromFallible(new CaptureMostPiecesStrategy()));
    //Controller controller3 = new Controller(model, view1, ai);
    model.startGame();
  }
}