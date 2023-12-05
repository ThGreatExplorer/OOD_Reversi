package cs3500.reversi.provider.strategy;

import java.util.List;

import cs3500.reversi.provider.utils.HexCoords;
import cs3500.reversi.provider.model.ReversiROM;

/**
 * Represents a strategy that allows the chaining of strategies. This is beneficial as we can
 * have the logic of picking a move from a list of strategies in the strategy directory instead
 * of the AI class, keeping similar implementations in the same place.
 */
public class ChainStrat implements PlaceStrategy {
  private PlaceStrategy[] strats;

  /**
   * Default constructor for ChainStrat.
   * @param strats variable length list of PlaceStrategies
   */
  public ChainStrat(PlaceStrategy... strats) {
    this.strats = strats;
  }

  @Override
  public List<HexCoords> getValidMoves(ReversiROM model, List<HexCoords> validMoves) {
    List<HexCoords> currentMoves = validMoves;
    for (PlaceStrategy strat : strats) {
      List<HexCoords> lohc = strat.getValidMoves(model, currentMoves);
      if (lohc.size() != 0) {
        currentMoves = lohc;
      }
    }
    return currentMoves;
  }
}
