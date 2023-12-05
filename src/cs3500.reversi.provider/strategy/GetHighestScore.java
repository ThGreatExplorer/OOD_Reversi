package cs3500.reversi.provider.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cs3500.reversi.provider.utils.HexCoords;
import cs3500.reversi.provider.model.ReversiROM;

/**
 * Represents the strategy that picks the cell that results in the most tiles flipped.
 */
public class GetHighestScore implements PlaceStrategy {
  @Override
  public List<HexCoords> getValidMoves(ReversiROM model, List<HexCoords> validMoves) {
    List<Integer> listOfInt = List.copyOf(validMoves).stream().map((HexCoords hc) ->
            model.valueOfMove(hc)).collect(Collectors.toList());
    List<HexCoords> stratMoves;
    if (listOfInt.size() > 0) {
      Integer max = listOfInt.get(0);
      for (Integer i : listOfInt) {
        if (i > max) {
          max = i;
        }
      }
      Integer finalMax = max;
      stratMoves = validMoves.stream().filter((HexCoords hc) ->
                      model.valueOfMove(hc) == finalMax.intValue()).collect(Collectors.toList());
    } else {
      stratMoves = new ArrayList<>();
    }
    return stratMoves;
  }
}