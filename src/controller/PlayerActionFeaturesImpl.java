package controller;

import model.Color;
import model.ReversiModel;
import player.Player;
import view.GUIView;

/**
 * Represents the actions that a given player has/could take. The view tells this class which method
 * the player has taken, and this class mutates the model appropriately.
 */
public class PlayerActionFeaturesImpl implements PlayerActionFeatures {
  private final ReversiModel model;
  private final GUIView view;
  private final Color playerColor;

  /**
   * Constructs a PlayerActionFeaturesImpl object, given a model, view, and player.
   *
   * @param model  the model to be updating
   * @param view   the view to be updating
   * @param player the player who is taking the action
   */
  public PlayerActionFeaturesImpl(ReversiModel model, GUIView view, Player player) {
    this.model = model;
    this.view = view;
    this.playerColor = player.getColor();
  }

  @Override
  public void playMove(int q, int r, int s) {
    Color currentPlayer = model.getCurrentPlayer();
    if (playerColor != currentPlayer) {
      view.showErrorMessage("It is not your turn!");
      return;
    }

    try {
      System.out.print(this.playerColor + "Move played: " + q + " " + r + " " + s + "\n");
      model.move(playerColor, q, r, s);
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

}
