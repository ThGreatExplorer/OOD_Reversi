import controller.Controller;
import model.Color;
import model.ReadOnlyReversiModel;
import model.ReversiModel;
import model.StandardHexagonalReversiModel;
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
    GUIView view2 = new ReversiGraphicsView(model);
    Player human1 = new HumanPlayer(Color.WHITE, model);
    Player human2 = new HumanPlayer(Color.BLACK, model);
    Controller controller1 = new Controller(model, view1, human1);
    Controller controller2 = new Controller(model, view2, human2);
    view1.setVisible();
    view2.setVisible();
    model.startGame();
  }
}