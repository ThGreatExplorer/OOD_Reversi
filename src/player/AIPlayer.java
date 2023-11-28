package player;

import model.Color;
import model.ReversiModel;

/**
 * Represents an AI player in a game of Reversi. The AI player is capable of making moves on its own
 * depending on a strategy and doesn't interact with user input coming from the view.
 */
public class AIPlayer implements Player {
  private final Color color;
  private final ReversiModel model;
  private final InfalliblePlayerStrategies inflallibleStrategy;

  /**
   * Constructs an AIPlayer object.
   *
   * @param model the model to be updated
   * @param color the color of the player
   * @param strategy the strategy to be used
   */
  public AIPlayer(ReversiModel model, Color color, InfalliblePlayerStrategies strategy) {
    this.model = model;
    this.color = color;
    this.inflallibleStrategy = strategy;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public int[] makeMove() {
    try {
      return this.inflallibleStrategy.chooseMove(model, this.color);
    } catch (IllegalStateException e) {
      return null;
    }
  }
}
