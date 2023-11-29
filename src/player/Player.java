package player;

import model.Color;

/**
 * Represents a player of the game.
 */
public interface Player extends PlayerActions {

  /**
   * Returns the color of the player.
   *
   * @return the color.
   */
  Color getColor();

}
