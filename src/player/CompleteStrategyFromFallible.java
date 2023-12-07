package player;

import java.util.Optional;

import model.Color;
import model.ReadOnlyReversiModel;

/**
 * Runs a fallible strategy as an infallible strategy to guarantee an output move or else throw.
 */
public class CompleteStrategyFromFallible implements InfalliblePlayerStrategies {
  private final FalliblePlayerStrategies strategyToTry;

  /**
   * Constructs in infallible strategy based on the given fallible strategy.
   *
   * @param strategy the strategy to run
   */
  public CompleteStrategyFromFallible(FalliblePlayerStrategies strategy) {
    this.strategyToTry = strategy;
  }

  @Override
  public int[] chooseMove(ReadOnlyReversiModel model, Color player) throws IllegalStateException {
    Optional<int[]> maybeAns = this.strategyToTry.chooseMove(model, player);
    if (maybeAns.isPresent()) {
      return maybeAns.get();
    }
    throw new IllegalStateException("There are no possible moves chosen by this strategy!");
  }
}
