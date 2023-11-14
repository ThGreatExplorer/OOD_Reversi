package player;


import java.util.Optional;

import model.Color;
import model.ReadOnlyReversiModel;

public class CompleteStrategyFromFallible implements InfallibleAIPlayerStrategies {
  FallibleAIPlayerStrategies strategyToTry;

  public CompleteStrategyFromFallible(FallibleAIPlayerStrategies strategy) {
    this.strategyToTry = strategy;
  }

  @Override
  public int[] chooseMove(ReadOnlyReversiModel model, Color player) throws IllegalStateException {
    Optional<int[]> maybeAns = this.strategyToTry.chooseMove(model, player);
    if (maybeAns.isPresent()) { return maybeAns.get(); }
    throw new IllegalStateException("There are no possible moves chosen by this strategy!");
  }
}
