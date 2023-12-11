package controller;

import model.BoardTile;
import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

/**
 * Represents the actions that a given player has/could take. The view tells this class which method
 * the player has taken, and this class mutates the model appropriately.
 */
public class PlayerActionFeaturesImpl<T extends BoardTile> implements PlayerActionFeatures<T> {
  private final ReversiModel<T> model;
  private final GUIView view;
  private final Color playerColor;

  /**
   * Constructs a PlayerActionFeaturesImpl object, given a model, view, and player.
   *
   * @param model  the model to be updating
   * @param view   the view to be updating
   * @param player the player who is taking the action
   */
  public PlayerActionFeaturesImpl(ReversiModel<T> model, GUIView<T> view, Player player) {
    this.model = model;
    this.view = view;
    this.playerColor = player.getColor();
  }

  @Override
  public void playMove(int... coords) {
    Color currentPlayer = model.getCurrentPlayer();
    if (playerColor != currentPlayer) {
      view.showErrorMessage("It is not your turn!");
      return;
    }

    try {
      if (coords.length == 2) {
        System.out.print(this.playerColor + "Move played: " + coords[0] + " " + coords[1] + "\n");
        model.move(playerColor, coords[0], coords[1]);
      }
      else if (coords.length == 3) {
        System.out.print(this.playerColor + "Move played: " + coords[0] + " " + coords[1] + " "
                        + coords[2] + "\n");
        model.move(playerColor, coords[0], coords[1], coords[2]);
      }

    } catch (IllegalArgumentException e) {
      view.showErrorMessage(e.getMessage());
    }

  }

  @Override
  public void passMove() {
    Color currentPlayer = model.getCurrentPlayer();
    if (playerColor != currentPlayer) {
      view.showErrorMessage("It is not your turn!");
    } else {
      model.pass();
    }
  }

  @Override
  public Color getColor() {
    return this.playerColor;
  }

}
