package cs3500.reversi.provider.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cs3500.reversi.provider.utils.HexCoords;
import cs3500.reversi.provider.utils.HexVectors;
import cs3500.reversi.provider.model.ReversiROM;

/**
 * Represents the strategy that picks the cell if it is a corner.
 */
public class GetCorner implements PlaceStrategy {
  @Override
  public List<HexCoords> getValidMoves(ReversiROM model, List<HexCoords> validMoves) {
    List<HexCoords> allCorners = new ArrayList<>();
    int cornerIndex = model.getSideLength() - 1;
    for (HexVectors vec : HexVectors.values()) {
      allCorners.add(new HexCoords(vec.qChange * cornerIndex, vec.rChange * cornerIndex));
    }
    List<HexCoords> stratMoves = validMoves.stream().filter((HexCoords hc) ->
            allCorners.contains(hc)).collect(Collectors.toList());
    return stratMoves;
  }
}