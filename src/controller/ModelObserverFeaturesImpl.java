package controller;

import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

/**
 * Represents the features of a ModelObserver. This class is used to update the view and make moves
 * for AI players.
 */
public class ModelObserverFeaturesImpl implements ModelObserverFeatures {
  private final ReversiModel model;
  private final GUIView view;
  private final Player player;
  private final Color playerColor;

  /**
   * Constructs a ModelObserverFeaturesImpl object.
   *
   * @param model  the model to be updated
   * @param view   the view to be updated
   * @param player the player to make moves for
   */
  public ModelObserverFeaturesImpl(ReversiModel model, GUIView view, Player player) {
    this.model = model;
    this.view = view;
    this.player = player;
    this.playerColor = this.player.getColor();
  }

  @Override
  public void update() {
    //Update the view
    this.view.update(playerColor);

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
    }

    //checks if the game is over
    if (this.model.isGameOver()) {
      this.view.gameOver(this.model.getWinner(), playerColor);
    }
  }

}
