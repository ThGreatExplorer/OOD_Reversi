package controller;

import model.BoardTile;
import model.Color;

/**
 * Represents the actions the controller should be listening for that are coming from the View. The
 * view calls the methods in this interface as part of the addPlayerActionFeatures method in the
 * view. Then the methods mutate the model appropriately.
 */
public interface PlayerActionFeatures<T extends BoardTile> {

  /**
   * Plays a move in the model, given the coordinates of the move. If the move can't be played,
   * displays an Error Message in the view.
   *
   * @param coords, either q,r,s or row,col.
   */
  void playMove(int... coords);

  /**
   * Passes the move in the model. If the move can't be passed, displays an Error Message in the
   * view.
   */
  void passMove();

  /**
   * Returns the color that this controller is controlling.
   *
   * @return the color
   */
  Color getColor();

}
