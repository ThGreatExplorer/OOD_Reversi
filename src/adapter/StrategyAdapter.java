package adapter;

import cs3500.reversi.provider.model.ReversiROM;
import cs3500.reversi.provider.strategy.PlaceStrategy;
import cs3500.reversi.provider.utils.HexCoords;
import model.AdaptHexCoordsToHexagon;
import model.Color;
import model.Hexagon;
import model.ReadOnlyReversiModel;
import player.InfalliblePlayerStrategies;

/**
 * This class is used to convert the provider's PlaceStrategy to our InfalliblePlayerStrategy.
 */
public class StrategyAdapter implements InfalliblePlayerStrategies {
  PlaceStrategy providerStrategy;

  /**
   * Creates a strategy adapter to convert from the provider strategy to our strategy.
   * @param providerStrategy the PlaceStrategy which has the logic to use.
   */
  public StrategyAdapter(PlaceStrategy providerStrategy) {
    this.providerStrategy = providerStrategy;
  }

  @Override
  public int[] chooseMove(ReadOnlyReversiModel model, Color player) throws IllegalStateException {
    ReversiROM providerModel = new ReadOnlyModelAdapter(model);
    HexCoords firstValidMoveAxial =
            providerStrategy.getValidMoves(providerModel, providerModel.getPossibleMoves())
                    .get(0);
    Hexagon firstValidMoveCubic = new AdaptHexCoordsToHexagon(firstValidMoveAxial)
            .convertHexCoordsToHexagon();

    return new int[]{firstValidMoveCubic.getQ(), firstValidMoveCubic.getR(),
            firstValidMoveCubic.getS()};
  }

}
