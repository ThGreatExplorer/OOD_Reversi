import model.ReadOnlyReversiModel;
import model.StandardHexagonalReversiModel;
import view.GUIView;
import view.ReversiGraphicsView;

/**
 * The main class that executes a game of Reversi.
 */
public final class Reversi {
  public static void main(String[] args) {
    ReadOnlyReversiModel model =
            new StandardHexagonalReversiModel(5);
    GUIView view = new ReversiGraphicsView(model, 40);
    view.setVisible();
  }
}