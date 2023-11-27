package controller;

import model.Color;

/**
 * Represents an observer that is subscribed to updates from the model. Functions as a delegate in
 * the controller to the model, and is responsible for updating the view when the model changes.
 */
public interface ModelObserverFeatures {

  /**
   * Gets this player's color.
   *
   * @return this player's color
   */
  Color getcurrentPlayerColor();

  /**
   * Tells the view to update once the model state has changed.
   */
  void update();

  /**
   * Makes a move in the model for this player.
   */
  void makeMove();

}
