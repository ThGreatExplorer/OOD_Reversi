package player;

import model.Color;
import model.ReversiModel;

public class HumanPlayer implements Player {
  private final Color color;
  private final ReversiModel model;

  public HumanPlayer(Color color, ReversiModel model) {
    this.color = color;
    this.model = model;
  }

  @Override
  public Color getColor() {
    return this.color;
  }


  @Override
  public int[] makeMove() {
    throw new IllegalArgumentException("Not AI Player!");
  }
}
