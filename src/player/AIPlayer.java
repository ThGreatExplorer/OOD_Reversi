package player;

import model.Color;
import model.ReversiModel;

public class AIPlayer implements Player {
  private final Color color;
  private final ReversiModel model;
  private final InfalliblePlayerStrategies inflallibleStrategy;

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
    } catch (IllegalArgumentException e) {
      return null;
    }
  }
}
