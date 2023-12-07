package model;

import java.util.Map;
import controller.ModelObserverFeatures;
import cs3500.reversi.provider.controller.ModelFeatures;

/**
 * A base template for mocks of the model which strips the model down to just signatures and
 * includes a log.
 */

public class AbstractModelMock implements ReversiModel {

  StringBuilder log;

  protected AbstractModelMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public boolean canMakeAnyMove(Color color) {
    return false;
  }

  @Override
  public boolean isValidMove(Color color, int q, int r, int s) {
    return false;
  }

  @Override
  public Map<Hexagon, Integer> getValidMoveScores(Color color) {
    return null;
  }

  @Override
  public int getScore(Color color) {
    return 0;
  }

  @Override
  public Color getWinner() {
    return null;
  }

  @Override
  public boolean isGameOver() throws IllegalArgumentException {
    log.append("checked game over \n");
    return false;
  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public Color getCurrentPlayer() {
    return Color.WHITE;
  }

  @Override
  public PlayingBoard getCurrentBoardState() {
    return null;
  }

  @Override
  public Color getColorAt(int q, int r, int s) throws IllegalArgumentException {
    return null;
  }

  @Override
  public boolean getFlagPass() {
    return false;
  }

  //TODO
  @Override
  public void addModelFeatures(ModelObserverFeatures modelFeatures) {
    //does not do anything as of now
  }

  @Override
  public void addMoveListener(ModelFeatures modelFeatures) {

  }

  @Override
  public void notifyMoveMade() {
    //does not do anything as of now
  }


  //TODO
  @Override
  public void startGame() {
    //does not do anything as of now
  }

  @Override
  public void pass() {
    //pass method does not need to affect anything
  }

  @Override
  public void move(Color color, int q, int r, int s) throws IllegalArgumentException {
    //move method does not need to affect anything
  }
}
