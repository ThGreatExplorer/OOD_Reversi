import adapter.AIPlayerAdapter;
import adapter.HumanPlayerAdapter;
import adapter.ModelAdapter;
import controller.Controller;
import cs3500.reversi.provider.player.IPlayer;
import cs3500.reversi.provider.strategy.AvoidNextToCorner;
import cs3500.reversi.provider.strategy.GetCorner;
import cs3500.reversi.provider.strategy.GetHighestScore;
import cs3500.reversi.provider.strategy.MinMax;
import cs3500.reversi.provider.strategy.PlaceStrategy;
import cs3500.reversi.provider.utils.TokenStatus;
import cs3500.reversi.provider.view.RevGUI;
import model.Color;
import model.ReversiModel;
import model.StandardHexagonalReversiModel;
import player.AIPlayer;
import player.CaptureMostPiecesStrategy;
import player.CompleteStrategyFromFallible;
import player.HumanPlayer;
import player.InfalliblePlayerStrategies;
import player.Player;
import view.GUIView;
import view.ReversiGraphicsView;

/**
 * The main class that executes a game of Reversi.
 */
public final class Reversi {
  /**
   * currentIndex guides which command in the arguments is making the next customization of the
   * players.
   */
  private static int currentIndex;

  /**
   * Creates a model and view to run the game with the selected players.
   *
   * @param args String commands, with spaces in between, to pick the player and, if computer
   *             player, strategy.
   *             There must be two players picked.
   *             First word is the first player, either "human" or "computer".
   *             If picked "computer" as player 1, then second word must be the strategy.
   *             Supported strategies for player 1: "CaptureMostPieces"
   *             The next word will be the second player, and if the computer player is picked,
   *             there must be a next word for the strategy.
   *             Supported strategies for player 2: "CaptureMostPieces",
   *             "Strategy1": GetHighestScore, "Strategy2": AvoidNextToCorner,
   *             "Strategy3": GetCorner, "Strategy4": MinMax
   */

  public static void main(String[] args) {
    currentIndex = 0;

    ReversiModel model = new StandardHexagonalReversiModel(8);
    GUIView view1 = new ReversiGraphicsView(model);

    //their model and view implementations
    cs3500.reversi.provider.model.ReversiModel providerModel =
        new ModelAdapter(model);
    //used their width and height numbers
    RevGUI view2 = new cs3500.reversi.provider.view.ReversiGraphicsView(1000, 1000,
        providerModel);

    Player player1;
    IPlayer player2;

    //If no input, set default as human-human
    if (args.length == 0) {
      player1 = new HumanPlayer(Color.WHITE);
      player2 = new HumanPlayerAdapter();
    } else {
      //Player human1 = new HumanPlayer(Color.WHITE, model);
      player1 = getPlayer(Color.WHITE, model, args);
      player2 = getIPlayer(args);
    }

    //our controller for our Reversi Implementation
    Controller controller1 = new Controller(model, view1, player1);

    //Controller for their Reversi Implementation
    Controller controller2 = new Controller(TokenStatus.BLACK, providerModel, view2, player2);
    view1.setVisible();
    providerModel.startGame();

  }

  private static String getCommand(String[] args) {
    String next = args[currentIndex];
    currentIndex++;
    return next;
  }

  private static IPlayer getIPlayer(String[] args) {
    String player = getCommand(args);
    switch (player) {
      case "human":
        return new HumanPlayerAdapter();
      case "computer":
        return new AIPlayerAdapter(getPlaceStrategy(args));
      default:
        throw new IllegalArgumentException("Must pick 'computer' or 'human' as next player");
    }
  }

  private static PlaceStrategy getPlaceStrategy(String[] args) {
    String strategy = getCommand(args);
    switch (strategy) {
      case "Strategy1":
        return new GetHighestScore();
      case "Strategy2":
        return new AvoidNextToCorner();
      case "Strategy3":
        return new GetCorner();
      case "Strategy4":
        return new MinMax(new GetHighestScore());
    }
    throw new IllegalArgumentException("Must pick supported strategy");
  }

  private static Player getPlayer(Color color, ReversiModel model, String[] args) {
    String player = getCommand(args);
    switch (player) {
      case "human":
        return new HumanPlayer(color);
      case "computer":
        return new AIPlayer(model, color, getStrategy(args, color));
      default:
        throw new IllegalArgumentException("Must pick 'computer' or 'human' as next player");
    }
  }

  private static InfalliblePlayerStrategies getStrategy(String[] args, Color color) {
    String strategy = getCommand(args);
    if (strategy.equals("CaptureMostPieces")) {
      return new CompleteStrategyFromFallible(new CaptureMostPiecesStrategy());
    }

    throw new IllegalArgumentException("Must pick supported strategy");
  }
}