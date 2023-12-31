import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.StandardSquareReversiModel;
import view.ReversiSquareTextualView;

public class ReversiSquareTextualViewTest {

  ReversiSquareTextualView view;

  @Test
  public void testSize2() {
    this.view = new ReversiSquareTextualView(new StandardSquareReversiModel(2));
    Assert.assertEquals(this.view.render(), "X  O  \n" +
            "O  X");
  }

  @Test
  public void testSize4() {
    this.view = new ReversiSquareTextualView(new StandardSquareReversiModel(4));
    Assert.assertEquals(this.view.render(),
            "_  _  _  _  \n" +
                    "_  X  O  _  \n" +
                    "_  O  X  _  \n" +
                    "_  _  _  _");
  }

  @Test
  public void testSize4Move() {
    StandardSquareReversiModel square = new StandardSquareReversiModel(4);
    square.move(Color.WHITE, 1,0);
    this.view = new ReversiSquareTextualView(square);
    Assert.assertEquals(this.view.render(),
            "_  _  _  _  \n" +
                    "O  O  O  _  \n" +
                    "_  O  X  _  \n" +
                    "_  _  _  _");
  }

  @Test
  public void testSize8() {
    StandardSquareReversiModel square = new StandardSquareReversiModel(8);
    this.view = new ReversiSquareTextualView(square);
    Assert.assertEquals(this.view.render(),
            "_  _  _  _  _  _  _  _  \n" +
                    "_  _  _  _  _  _  _  _  \n" +
                    "_  _  _  _  _  _  _  _  \n" +
                    "_  _  _  X  O  _  _  _  \n" +
                    "_  _  _  O  X  _  _  _  \n" +
                    "_  _  _  _  _  _  _  _  \n" +
                    "_  _  _  _  _  _  _  _  \n" +
                    "_  _  _  _  _  _  _  _");
  }

}
