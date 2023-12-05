package cs3500.reversi.provider.strategy;

import java.util.List;

import cs3500.reversi.provider.utils.HexCoords;
import cs3500.reversi.provider.model.ReversiROM;

/**
 * Represents the strategy for placing a token. Various function objects implement this interface
 * to provide unique strategies for placement.
 */
public interface PlaceStrategy {

  /**
   * Gets all possible coordinates that creates Valid moves on the given ReversiModel
   * in accordance to the strategy. Listed in order from top left to bottom right.
   * @param model the model portion of Reversi
   * @return the list of all possible moves given a specific strategy
   */
  List<HexCoords> getValidMoves(ReversiROM model, List<HexCoords> validMoves);
}
