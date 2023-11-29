package player;

import model.Color;

/**
 * Represents a Player. A player could be human or AI.
 */
public interface Player extends PlayerActions {

  /**
   * Returns the color of the player.
   * @return the color.
   */
  Color getColor();

}
