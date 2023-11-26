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

  void showErrorMessage(String message);

  void update();
}
