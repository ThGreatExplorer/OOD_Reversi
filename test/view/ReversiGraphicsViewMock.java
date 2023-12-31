package view;

import model.Color;
import model.Hexagon;
import model.ReadOnlyReversiModel;
import model.StandardHexagonalReversiModel;

/**
 * This is a mock of the view for testing purposes.
 */
public class ReversiGraphicsViewMock extends ReversiGraphicsView<Hexagon, Path2DHexagon,
        ReversiHexagonalPanel> {

  StringBuilder ap;
  ReadOnlyReversiModel<Hexagon> model;

  /**
   * Constructs the mock of the Graphics view of a Game of Reversi.
   *
   * @param model the model being passed in
   */
  public ReversiGraphicsViewMock(ReadOnlyReversiModel<Hexagon> model, StringBuilder ap) {
    super(model, new ReversiHexagonalPanel(model));
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void showErrorMessage(String message) {
    ap.append("Error Message:").append(message).append(" Current Player=")
            .append(this.model.getCurrentPlayer().toString()).append("\n");
  }

  @Override
  public void update(Color color) {
    ap.append("Request to update:" + " Current Player=").append(this.model.getCurrentPlayer().
            toString()).append(" This Player=").append(color.toString()).append("\n");
  }

  @Override
  public void gameOver(Color winner, Color playerColor) {
    if (winner == null) {
      ap.append("Tie!");
    } else {
      ap.append("Winner!:").append(winner.toString());
    }
    ap.append(" Current Player=").append(this.model.getCurrentPlayer().toString()).
            append(" This Player=").append(playerColor.toString()).append("\n");
  }

}
