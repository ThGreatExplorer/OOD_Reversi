package model;

import java.util.Map;

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
  public void pass() {}

  @Override
  public void move(Color color, int q, int r, int s) throws IllegalArgumentException {}
}
