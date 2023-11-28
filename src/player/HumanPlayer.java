package player;

import model.Color;
import model.ReversiModel;

/**
 * Represents a human player in a game of Reversi. The human player is essentially a stub since all
 * player input would be coming from the view and handled by the PlayerAction Interface.
 */
public class HumanPlayer implements Player {
  private final Color color;
  private final ReversiModel model;

  /**
   * Constructs a HumanPlayer object.
   *
   * @param model the model to be updated
   * @param color the color of the player
   */
  public HumanPlayer(ReversiModel model, Color color) {
    this.color = color;
    this.model = model;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * No move should be made by a human player. This method is a stub.
   * @throws IllegalArgumentException if it is ever called.
   */
  @Override
  public int[] makeMove() {
    throw new IllegalArgumentException("Not AI Player!");
  }
}
