package view;

import model.Color;
import model.ReadOnlyReversiModel;
import player.Player;

/**
 * This is a mock of the view for testing purposes.
 */
public class ReversiGraphicsViewMock extends ReversiGraphicsView {

  StringBuilder ap;
  ReadOnlyReversiModel model;

  /**
   * Constructs the mock of the Graphics view of a Game of Reversi.
   *
   * @param model the model being passed in
   */
  public ReversiGraphicsViewMock(ReadOnlyReversiModel model, StringBuilder ap) {
    super(model);
    this.model = model;
    this.ap = ap;
  }

  @Override
  public void showErrorMessage(String message) {
    ap.append("Error Message:" + message
        + " Current Player=" + this.model.getCurrentPlayer().toString() + "\n");
  }

  @Override
  public void update(Color color) {
    ap.append("Request to update:"
        + " Current Player=" + this.model.getCurrentPlayer().toString()
        + " This Player=" + color.toString() + "\n");
  }

  @Override
  public void gameOver(Color winner, Color playerColor) {
    if (winner == null) {
      ap.append("Tie!");
    } else {
      ap.append("Winner!:" + winner.toString());
    }
    ap.append(" Current Player=" + this.model.getCurrentPlayer().toString()
        + " This Player=" + playerColor.toString() + "\n");
  }

}
