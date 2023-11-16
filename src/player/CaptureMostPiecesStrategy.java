package player;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.Hexagon;
import model.ReadOnlyReversiModel;
import model.Color;

/**
 * Strategy to make the move (if possible) to capture as many pieces as possible in the next move.
 * Breaks tie by choosing the move that is in the top left corner.
 */
public class CaptureMostPiecesStrategy implements FalliblePlayerStrategies {

  @Override
  public Optional<int[]> chooseMove(ReadOnlyReversiModel model, Color player)
      throws IllegalStateException{

    if(model.isGameOver()){
      throw new IllegalStateException("Game is over! No moves to make!");
    }

    Map<Hexagon, Integer> possibleMoveScores = model.getValidMoveScores(player);

    //if there are no moves to make, return empty
    if(possibleMoveScores.isEmpty()){
      return Optional.empty();
    }

    //determines which possible move(s) has the highest potential score

    //gets max score
    int maxScore = possibleMoveScores.values().stream().mapToInt(v -> v).max()
        .orElseThrow();

    List<Hexagon> maxHex = new ArrayList<>();
    for (Map.Entry<Hexagon, Integer> entry : possibleMoveScores.entrySet()) {
      if (entry.getValue().equals(maxScore)) {
        maxHex.add(entry.getKey());
      }
    }

    if (maxHex.size() == 1) {
      //if singleton, return that hexagon's coordinates
      Hexagon hexMove = maxHex.get(0);
      return Optional.of(new int[]{hexMove.getQ(), hexMove.getR(), hexMove.getS()});
    }

    //filter by uppermost
    int upperMostCoor = maxHex.stream().mapToInt(hex -> hex.getR())
        .min().orElseThrow();
    List<Hexagon> uppermostHex = maxHex.stream()
        .filter(hexagon -> hexagon.getR() == upperMostCoor).collect(Collectors.toList());

    if (uppermostHex.size() == 1){
      Hexagon hexMove = uppermostHex.get(0);
      return Optional.of(new int[]{hexMove.getQ(), hexMove.getR(), hexMove.getS()});
    }

    //filter by leftmost
    int leftMostCoor = uppermostHex.stream().mapToInt(hex -> hex.getS())
        .max().orElseThrow();
    List<Hexagon> leftMostHex = uppermostHex.stream()
        .filter(hexagon -> hexagon.getS() == leftMostCoor).collect(Collectors.toList());

    if (leftMostHex.size() == 1){
      Hexagon hexMove = leftMostHex.get(0);
      return Optional.of(new int[]{hexMove.getQ(), hexMove.getR(), hexMove.getS()});
    }

    //if none of the operations have narrowed the possible moves yet, something went wrong.
    return Optional.empty();
  }
}
