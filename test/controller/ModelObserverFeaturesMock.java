package controller;

import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

/**
 * This is a mock of the model observer features class for testing purposes.
 */
public class ModelObserverFeaturesMock extends ModelObserverFeaturesImpl {
  private final Color playerColor;
  private final StringBuilder ap;

  /**
   * Constructs a mock of the ModelObserverFeaturesImpl object.
   *
   * @param model  the model to be updated
   * @param view   the view to be updated
   * @param player the player to make moves for
   */
  public ModelObserverFeaturesMock(ReversiModel model, GUIView view, Player player,
                                   StringBuilder ap) {
    super(model, view, player);
    this.playerColor = player.getColor();
    this.ap = ap;
  }

  @Override
  public void update() {
    ap.append(playerColor).append(" notified of move made").append("\n");
  }
}
