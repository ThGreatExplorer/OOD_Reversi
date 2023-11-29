package Controller;

import controller.ModelObserverFeatures;
import controller.ModelObserverFeaturesImpl;
import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

public class ModelObserverFeaturesMock implements ModelObserverFeatures {
  private final ReversiModel model;
  private final GUIView view;
  private final Player player;
  private final Color playerColor;
  private final StringBuilder ap;

  /**
   * Constructs a ModelObserverFeaturesImpl object.
   *
   * @param model  the model to be updated
   * @param view   the view to be updated
   * @param player the player to make moves for
   */
  public ModelObserverFeaturesMock(ReversiModel model, GUIView view, Player player,
                                   StringBuilder ap) {
    this.model = model;
    this.view = view;
    this.player = player;
    this.playerColor = this.player.getColor();
    this.ap = ap;
  }

  @Override
  public void update() {
    this.ap.append(playerColor).append("attempted to update the view");

    try {
      //check if the Player is the current player
      if (model.getCurrentPlayer().equals(playerColor)) {
        //If an AI Player, make a move.
        int[] moveCoords = player.makeMove();

        if (moveCoords == null) {
          this.model.pass();
        } else {
          this.model.move(playerColor, moveCoords[0], moveCoords[1], moveCoords[2]);
        }
      }
    } catch (IllegalArgumentException ignored) {
      //If not an AI Player do nothing
      this.ap.append("Player is not an AI player, so no move was automatically made");
    }

    if (this.model.isGameOver()) {
      this.ap.append("Update method told the view the game is over");
    }
  }
}
