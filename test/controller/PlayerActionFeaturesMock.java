package controller;

import model.Color;
import model.Hexagon;
import model.ReversiModel;
import player.Player;
import view.GUIView;

/**
 * This is a mock of the player action features class for testing purposes.
 */
public class PlayerActionFeaturesMock extends PlayerActionFeaturesImpl<Hexagon> {

  private final Color playerColor;
  private final StringBuilder ap;


  /**
   * Constructs a PlayerActionFeaturesImpl object, given a model, view, and player.
   *
   * @param model  the model to be updating
   * @param view   the view to be updating
   * @param player the player who is taking the action
   */
  public PlayerActionFeaturesMock(ReversiModel<Hexagon> model, GUIView<Hexagon> view, Player player,
                                  StringBuilder ap) {
    super(model, view, player);
    this.playerColor = player.getColor();
    this.ap = ap;
  }

  @Override
  public void playMove(int... coords) {
    this.ap.append(playerColor).append(" Attempted to move to: ").append(coords[0]).append(" ").
            append(coords[1]).append(" ").append(coords[2]).append("\n");
  }

  @Override
  public void passMove() {
    this.ap.append("Color: ").append(playerColor).append(" passed").append("\n");
  }
}
