import model.ReadOnlyReversiModel;
import model.StandardHexagonalReversiModel;
import view.GUIView;
import view.ReversiGraphicsView;

public final class Reversi {
  public static void main(String[] args) {
    ReadOnlyReversiModel model = new StandardHexagonalReversiModel(5);
    GUIView view = new ReversiGraphicsView(model);
    view.setVisible();
  }
}