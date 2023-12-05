package cs3500.reversi.provider.strategy;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.provider.utils.HexCoords;
// import cs3500.reversi.provider.model.Reversi;
import cs3500.reversi.provider.model.ReversiModel;
import cs3500.reversi.provider.model.ReversiROM;

/**
 * Represents an updated version of MinMax that bases decisions of the opponents known strategies.
 */
public class MinMax implements PlaceStrategy {
  PlaceStrategy strat;

  public MinMax(PlaceStrategy strat) {
    this.strat = strat;
  }

  @Override
  public List<HexCoords> getValidMoves(ReversiROM model, List<HexCoords> validMoves) {
    int bestScore = model.getSideLength() * model.getSideLength();
    List<HexCoords> bestCoords = new ArrayList<>();
    for (HexCoords coord : validMoves) {
      ReversiModel copy = new Reversi(model);
      copy.placeToken(coord);
      List<HexCoords> currMoves = copy.getPossibleMoves();
      currMoves = strat.getValidMoves(copy, currMoves);
      if (currMoves.size() < bestScore) {
        bestScore = currMoves.size();
        bestCoords.clear();
        bestCoords.add(coord);
      }
      if (currMoves.size() == bestScore) {
        bestCoords.add(coord);
      }
    }
    return bestCoords;
  }
}