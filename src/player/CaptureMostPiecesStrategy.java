package player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import model.Hexagon;
import model.ReadOnlyReversiModel;
import model.Color;

/**
 * Strategy to make the move (if possible) to capture as many pieces as possible, breaks ties
 * by choosing the move that is in the top left corner.
 */
public class CaptureMostPiecesStrategy implements FallibleAIPlayerStrategies {

  @Override
  public Optional<int[]> chooseMove(ReadOnlyReversiModel model, Color player)
          throws IllegalStateException {
    if (!String.valueOf(model.getCurrentPlayer()).equals(String.valueOf(player))) {
      return Optional.empty();
    }
    HashMap<Hexagon, Integer> possibleMoveScores = model.getValidMoveScores(player);
    //accumulates max score so far.
    int maxScore = 0;
    //stores the hexagons that are all currently at the same score in case of ties.
    List<Hexagon> maxHex = new ArrayList<>();
    for (Hexagon hex : possibleMoveScores.keySet()) {
      int score = possibleMoveScores.get(hex);
      //implements logic of updating the current maxScore, clearing the old list of hexagons
      //storing the previous iteration's list of hexagons of that score
      //then setting maxHex to this score.
      if (score > maxScore) {
        maxHex.clear();
        maxScore = score;
        maxHex.add(hex);
      }
      else if (score == maxScore) {
        maxHex.add(hex);
      }
    }
    if (maxScore == 0) {
      return Optional.empty();
    }
    else {
      //process the list of hexagon valid moves to get topleftmost
      if (maxHex.size() == 1) {
        //if singleton, return that hexagon's coordinates
        Hexagon hex = maxHex.get(0);
        return Optional.of(new int[]{hex.getQ(), hex.getR(), hex.getS()});
      }
      else {
        //check topmost first, by finding max -r coordinate, since -2 is two rings up and +2 is
        //two rings down
        //TODO
        return null;
        //check the leftmost by then finding max s coordinate if needed
      }
    }
  }
}
