package cs3500.reversi.provider.controller;

import cs3500.reversi.provider.utils.TokenStatus;

/**
 * Represents the various features the game can use.
 */
public interface ModelFeatures {

  /**
   * Ends the game. Displays the message for the end of the game, either a tie or a black or
   * white win.
   */
  void gameOver();

  /**
   * Set's the player to be the current turn.
   */
  void yourTurn();

  /**
   * Updates all objects that are listening through updates to this model through the controller.
   */
  void refreshAll();

  /**
   * The color of the player.
   * @return the color
   */
  TokenStatus getColor();

  /**
   * Assigns the given color to this Controller.
   * @param color the color you wish to give this controller.
   * @throws IllegalArgumentException if the given color is EMPTY.
   * @throws IllegalStateException if the color has already been assigned.
   */
  void assignColor(TokenStatus color) throws IllegalArgumentException, IllegalStateException;
}
