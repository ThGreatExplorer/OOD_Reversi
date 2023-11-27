package controller;

/**
 * Represents the actions the controller should be listening for that are coming from the View. The
 * view calls the methods in this interface as part of the addPlayerActionFeatures method in the
 * view. Then the methods mutate the model appropriately.
 */
public interface PlayerActionFeatures {

  /**
   * Plays a move in the model, given the coordinates of the move. If the move can't be played,
   * displays an Error Message in the view.
   *
   *
   * @param q the q coordinate
   * @param r the r coordinate
   * @param s the s coordinate
   */
  void playMove(int q, int r, int s);

  /**
   * Passes the move in the model. If the move can't be passed, displays an Error Message in the
   * view.
   */
  void passMove();

}
