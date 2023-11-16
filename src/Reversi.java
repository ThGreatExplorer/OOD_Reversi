import model.ReadOnlyReversiModel;
import model.StandardHexagonalReversiModel;
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
    ReadOnlyReversiModel model =
            new StandardHexagonalReversiModel(8);
    GUIView view = new ReversiGraphicsView(model);
    view.setVisible();
  }
}