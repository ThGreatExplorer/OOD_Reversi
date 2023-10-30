package Model;

import org.junit.Assert;
import org.junit.Test;

public class HexagonTests {
  Hexagon a;
  Hexagon b;

  @Test
  public void testSameDirectionDistanceVectorCalculatesCorrectly() {
    this.a = new Hexagon(1,-1,0);
    this.b = new Hexagon(2, -2, 0);
    Assert.assertArrayEquals(a.distanceVector(b), new int[]{1,-1,0});
  }

  @Test
  public void testDifferentDirectionDistanceVector() {
    this.a = new Hexagon(1,2,-3);
    this.b = new Hexagon(2, -2, 0);
    Assert.assertArrayEquals(a.distanceVector(b), new int[]{1,-4,3});
  }

  @Test
  public void testGetDistance() {
    this.a = new Hexagon(1, -5, 4);
    this.b = new Hexagon(1,1, -2);
    Assert.assertEquals(a.getDistance(), 5);
    Assert.assertEquals(b.getDistance(), 2);
  }

  @Test
  public void testNormalizedDistanceVectorSameDirection() {
    this.a = new Hexagon(1,-1,0);
    this.b = new Hexagon(4, -4, 0);
    Assert.assertArrayEquals(a.normalizedDistanceVector(b), new int[]{1,-1,0});
  }

  @Test
  public void testNormalizedDistanceVectorNotSameDirection() {
    this.a = new Hexagon(1,2,-3);
    this.b = new Hexagon(2, -2, 0);
    Assert.assertThrows(IllegalArgumentException.class, () -> a.normalizedDistanceVector(b));
  }
}
