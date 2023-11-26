package view;

import controller.PlayerActionFeatures;

/**
 * The view representing the GUI of a game of Reversi.
 */
public interface GUIView {

  /**
   * Sets the JFrame of this GUI to visible, allowing the contents to be displayed.
   */
  void setVisible();

  /*
  For next part of assignment:
  Implement logic for the view to handle being updated by the controller
   */

  void addPlayerActionFeatures(PlayerActionFeatures playerActionFeatures);

  /**
   * Shows an error message on the JFrame, disables any moves until after the message is closed.
   * @param message the message to be displayed
   */
  void showErrorMessage(String message);

  /**
   * Updates the view.
   */
  void update();
}
