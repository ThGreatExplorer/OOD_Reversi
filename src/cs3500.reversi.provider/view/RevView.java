package cs3500.reversi.provider.view;

import cs3500.reversi.provider.controller.PlayerFeatures;

/**
 * Represents the view for a game of Reversi.
 */
public interface RevView {

  /**
   * Displays the board with the most up-to-date information.
   * Should be called each time the model updates. Called through both controllers.
   */
  void render();

  /**
   * Sets the given PlayerFeatures as a listener for this view's low level activities.
   * Should be set in the controller's constructor.
   * @param feat a view feature.
   */
  void setCommandListener(PlayerFeatures feat);

  /**
   * Displays the given message to provide the player with information.
   * @param message the message you wish to display.
   *      Should be called when displaying:
   *                error messages (catching exceptions)
   *                game over
   *                starting info
   */
  void showMessage(String message);

}
