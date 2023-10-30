package View;

import java.util.ArrayList;

import Model.PlayingBoard;

public class ReversiTextualView extends AbstractHexagonRepresentation implements TextualView {


  public String toString(PlayingBoard boardState) {
    int[][] boardRepresentation = super.boardByNumber(boardState);
    int height = boardRepresentation.length;
    int boardSize = boardState.getSize();

    ArrayList<ArrayList<String>> boardString = new ArrayList<>(height);

    //create the rows and add to array
    for (int rowNum = 0; rowNum < height; rowNum++) {
      ArrayList<String> row = new ArrayList<>(height);
      for (int col = 0; col <= height; col++) {

      }
    }
    return " ";
  }
}
