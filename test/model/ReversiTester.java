package model;

import view.GUIView;
import view.ReversiGraphicsView;

/**
 * Test runner for pre-configured board states.
 */
public class ReversiTester {
  public static void main(String[] args) {
    ReadOnlyReversiModel model =
            new StandardHexagonalReversiModel(
                    ReversiModelGameStateGeneration.generate3RingsWhiteFilled());
    GUIView view = new ReversiGraphicsView(model, 40);
    view.setVisible();
  }
}
