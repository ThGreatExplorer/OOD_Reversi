package player;

import model.Color;

/**
 * Represents a human player in a game of Reversi. The human player is essentially a stub since all
 * player input would be coming from the view and handled by the PlayerAction Interface.
 */
public class HumanPlayer implements Player {
  private final Color color;

  /**
   * Constructs a HumanPlayer object.
   *
   * @param color the color of the player
   */
  public HumanPlayer(Color color) {
    this.color = color;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * No move should be made by a human player. This method is a stub.
   *
   * @throws IllegalArgumentException if it is ever called.
   */
  @Override
  public int[] makeMove() {
    throw new IllegalArgumentException("Not AI Player!");
  }
}
