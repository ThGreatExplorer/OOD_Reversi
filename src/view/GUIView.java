package view;

import controller.PlayerActionFeatures;
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
}
