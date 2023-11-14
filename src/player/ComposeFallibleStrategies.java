package player;

import java.util.Optional;

import model.Color;
import model.ReadOnlyReversiModel;

/**
 * Allows you to compose fallible strategies together, attempt the first strategy then the second
 * strategy.
 */
public class ComposeFallibleStrategies implements FallibleAIPlayerStrategies {
  FallibleAIPlayerStrategies first, second;

  /**
   * Takes two strategies and performs moves by the first then the second strategy in that order.
   *
   * @param one the first fallible strategy
   * @param two the second fallible strategy
   */
  public ComposeFallibleStrategies(FallibleAIPlayerStrategies one, FallibleAIPlayerStrategies two) {
    this.first = one;
    this.second = two;
  }

  @Override
  public Optional<int[]> chooseMove(ReadOnlyReversiModel model, Color player) throws
          IllegalStateException {
    Optional<int[]> ans = this.first.chooseMove(model, player);
    if (ans.isPresent()) return ans;
    return this.second.chooseMove(model, player);
  }
}
