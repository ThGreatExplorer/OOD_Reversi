package Controller;

import controller.PlayerActionFeatures;
import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

public class PlayerActionFeaturesMock implements PlayerActionFeatures {

  private final ReversiModel model;
  private final GUIView view;
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
    this.model = model;
    this.view = view;
    this.playerColor = player.getColor();
    this.ap = ap;
  }

  @Override
  public void playMove(int q, int r, int s) {
    Color currentPlayer = model.getCurrentPlayer();
    if (playerColor != currentPlayer) {
      this.ap.append("It is not your turn!");
    }
    this.ap.append(playerColor).append(" Attempted to move to: ").append(q).append(" ").append(r)
            .append(" ").append(s);
  }

  @Override
  public void passMove() {
    Color currentPlayer = model.getCurrentPlayer();
    if (playerColor != currentPlayer) {
      this.ap.append("It is not your turn!");
    } else {
      this.ap.append("Color: ").append(playerColor).append(" passed");
    }
  }
}
