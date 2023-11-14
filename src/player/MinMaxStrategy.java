package player;

import java.util.Optional;

import model.Color;
import model.ReadOnlyReversiModel;

public class MinMaxStrategy implements FallibleAIPlayerStrategies {
  @Override
  public Optional<int[]> chooseMove(ReadOnlyReversiModel model, Color player)
          throws IllegalStateException {
    return Optional.empty();
  }
}
