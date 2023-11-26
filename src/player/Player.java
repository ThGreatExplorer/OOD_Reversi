package player;

import model.Color;

public interface Player extends PlayerActions {

  /**
   * Returns the color of the player.
   * @return the color.
   */
  Color getColor();

}
