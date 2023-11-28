package view;

import controller.PlayerActionFeatures;
import model.Color;
import player.Player;

/**
 * The view representing the GUI of a game of Reversi.
 */
public interface GUIView {

  /**
   * Sets the JFrame of this GUI to visible, allowing the contents to be displayed.
   */
  void setVisible();

  /**
   * Adds the given PlayerActionFeatures to the view. This allows the controller, which has the
   * PlayerActionFeatures as a delegate, to listen to the view whenever a move is mode in
   * the view,
   *
   * @param playerActionFeatures the PlayerActionFeatures to be added
   */
  void addPlayerActionFeatures(PlayerActionFeatures playerActionFeatures);

  /**
   * Shows an error message on the JFrame, disables any moves until after the message is closed.
   * Used to communicate to the user that an invalid move was attempted.
   *
   * @param message the message to be displayed
   */
  void showErrorMessage(String message);

  /**
   * Updates the view based on the given Player.
   * @param player the player to update the view with.
   */
  void update(Player player);

  /**
   * Displays the end game scene on the JFrame, disabling any moves and printing the scores of the
   * players with a tie, win, or lose message for that player.
   *
   * @param winner the color of the winner of the game or null if there is a tie.
   * @param playerColor the color of the player that is viewing the end game scene.
   * @throws IllegalArgumentException if the game is not over.
   */
  void gameOver(Color winner, Color playerColor);
}
