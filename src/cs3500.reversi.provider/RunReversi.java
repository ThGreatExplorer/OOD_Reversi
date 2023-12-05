package cs3500.reversi.provider;

import java.util.HashMap;
import java.util.Map;

import cs3500.reversi.provider.controller.IController;
//import cs3500.reversi.provider.controller.RevController;
//import cs3500.reversi.provider.model.Reversi;
import cs3500.reversi.provider.model.ReversiModel;
//import cs3500.reversi.provider.player.AI;
import cs3500.reversi.provider.player.IPlayer;
//import cs3500.reversi.provider.player.Player;
import cs3500.reversi.provider.strategy.AvoidNextToCorner;
import cs3500.reversi.provider.strategy.GetCorner;
import cs3500.reversi.provider.strategy.GetHighestScore;
import cs3500.reversi.provider.strategy.MinMax;
import cs3500.reversi.provider.view.RevGUI;
import cs3500.reversi.provider.view.ReversiGraphicsView;

/**
 * The actual runnable class of reversi.
 */

//public final class RunReversi {
  /**
   * The method that allows you to run Reversi.
   * @param args does not do anything at the moment.
   */
  /*
  public static void main(String[] args) {
    ReversiModel model = new Reversi(4);
    if (args.length < 2) {
      System.out.println("Need Player Types");
      return;
    }
    IPlayer p1 = getPlayerType(args[0]);
    IPlayer p2 = getPlayerType(args[1]);
    if (p1 == null || p2 == null) {
      System.out.println("Invalid Player Type");
      return;
    }
    RevGUI view1 = new ReversiGraphicsView(1000, 1000, model);
    RevGUI view2 = new ReversiGraphicsView(1000, 1000, model);
    IController cont1 = new RevController(model, p1, view1);
    IController cont2 = new RevController(model, p2, view2);
    model.startGame();
  }


  private static IPlayer getPlayerType(String s) {
    Map<String, IPlayer> knownPlayers = new HashMap<>();
    knownPlayers.put("human", new Player());
    knownPlayers.put("strategy1", new AI(new GetHighestScore()));
    knownPlayers.put("strategy2", new AI(new GetCorner()));
    knownPlayers.put("strategy3", new AI(new AvoidNextToCorner()));
    knownPlayers.put("strategy4", new AI(new MinMax(new GetHighestScore())));

    return knownPlayers.get(s);
  }
}
*/