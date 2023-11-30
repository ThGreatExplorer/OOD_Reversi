package controller;

import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

/**
 * This is a mock of the player action features class for testing purposes.
 */
public class PlayerActionFeaturesMock extends PlayerActionFeaturesImpl {

  private final Color playerColor;
  private final StringBuilder ap;


  /**
   * Constructs a PlayerActionFeaturesImpl object, given a model, view, and player.
   *
   * @param model  the model to be updating
   * @param view   the view to be updating
   * @param player the player who is taking the action
   */
  public PlayerActionFeaturesMock(ReversiModel model, GUIView view, Player player,
                                  StringBuilder ap) {
    super(model, view, player);
    this.playerColor = player.getColor();
    this.ap = ap;
  }

  @Override
  public void playMove(int q, int r, int s) {
    this.ap.append(playerColor).append(" Attempted to move to: ").append(q).append(" ").append(r)
        .append(" ").append(s).append("\n");
  }

  @Override
  public void passMove() {
    this.ap.append("Color: ").append(playerColor).append(" passed").append("\n");
  }
}
