package player;

import java.util.List;

import adapter.ReadOnlyModelAdapter;
import cs3500.reversi.provider.model.ReversiROM;
import cs3500.reversi.provider.strategy.PlaceStrategy;
import cs3500.reversi.provider.utils.HexCoords;
import model.AdaptHexCoordsToHexagon;
import model.Color;
import model.Hexagon;
import model.ReadOnlyReversiModel;


public class StrategyAdapter implements InfalliblePlayerStrategies {
  PlaceStrategy providerStrategy;

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
