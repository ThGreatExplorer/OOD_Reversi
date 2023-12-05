package cs3500.reversi.provider.strategy;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.provider.utils.HexCoords;
import cs3500.reversi.provider.utils.HexVectors;
import cs3500.reversi.provider.model.ReversiROM;

/**
 * Represents the strategy that avoids the cells next to a corner.
 */
public class AvoidNextToCorner implements PlaceStrategy {

  @Override
  public List<HexCoords> getValidMoves(ReversiROM model, List<HexCoords> validMoves) {
    List<HexCoords> allCorners = new ArrayList<>();
    int cornerIndex = model.getSideLength() - 1;
    for (HexVectors vec : HexVectors.values()) {
      allCorners.add(new HexCoords(vec.qChange * cornerIndex, vec.rChange * cornerIndex));
    }
    List<HexCoords> badMoves = new ArrayList<>();
    for (HexCoords hc : allCorners) {
      for (HexVectors vec : HexVectors.values()) {
        HexCoords nextMove = new HexCoords(hc.q + vec.qChange, hc.r + vec.rChange);
        if (model.isMoveLegal(nextMove)) {
          badMoves.add(nextMove);
        }
      }
    }
    List<HexCoords> stratMoves = new ArrayList<>();
    for (HexCoords hc : validMoves) {
      if (!badMoves.contains(hc)) {
        stratMoves.add(hc);
      }
    }
    return stratMoves;
  }
}
