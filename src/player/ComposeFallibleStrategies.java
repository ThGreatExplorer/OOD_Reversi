package player;

import java.util.Optional;

import model.Color;
import model.ReadOnlyReversiModel;

/**
 * Allows user to piece multiple fallible strategies together, attempting the first strategy then
 * the second strategy.
 */
public class ComposeFallibleStrategies implements FalliblePlayerStrategies {
  private final FalliblePlayerStrategies first;
  private final FalliblePlayerStrategies second;

  /**
   * Constructs a fallible strategy based on the given strategies.
   *
   * @param one the first fallible strategy to run
   * @param two the second fallible strategy to run
   */
  public ComposeFallibleStrategies(FalliblePlayerStrategies one, FalliblePlayerStrategies two) {
    this.first = one;
    this.second = two;
  }

  @Override
  public Optional<int[]> chooseMove(ReadOnlyReversiModel model, Color player) throws
      IllegalStateException {
    Optional<int[]> ans = this.first.chooseMove(model, player);
    if (ans.isPresent()) {
      return ans;
    }
    return this.second.chooseMove(model, player);
  }
}
