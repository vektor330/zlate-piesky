package ch.usi.inf.sape.zlatepiesky.physics;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector4d;
import org.junit.Assert;
import org.junit.Test;

public class MirrorTest {

  private static final double DELTA = 0.000001;

  @Test
  public void testMirror1() {
    Vector2d axisBegin = new Vector2d(0, 0);
    Vector2d axisEnd = new Vector2d(0, 2);
    Vector2d begin = new Vector2d(1, 1);
    Vector2d end = new Vector2d(2, 1);
    Vector4d result = Mirror.mirror(axisBegin, axisEnd, begin, end);
    Assert.assertEquals(-1d, result.x, DELTA);
    Assert.assertEquals(1d, result.y, DELTA);
    Assert.assertEquals(-2d, result.z, DELTA);
    Assert.assertEquals(1d, result.w, DELTA);
  }

  @Test
  public void testMirror2() {
    Vector2d axisBegin = new Vector2d(0, 0);
    Vector2d axisEnd = new Vector2d(2, 2);
    Vector2d begin = new Vector2d(1, 0);
    Vector2d end = new Vector2d(2, 0);
    Vector4d result = Mirror.mirror(axisBegin, axisEnd, begin, end);
    Assert.assertEquals(0d, result.x, DELTA);
    Assert.assertEquals(1d, result.y, DELTA);
    Assert.assertEquals(0d, result.z, DELTA);
    Assert.assertEquals(2d, result.w, DELTA);
  }

  @Test
  public void testMirror3() {
    Vector2d axisBegin = new Vector2d(0, 0);
    Vector2d axisEnd = new Vector2d(2, 2);
    Vector2d begin = new Vector2d(0, 1);
    Vector2d end = new Vector2d(1, 0);
    Vector4d result = Mirror.mirror(axisBegin, axisEnd, begin, end);
    Assert.assertEquals(1d, result.x, DELTA);
    Assert.assertEquals(0d, result.y, DELTA);
    Assert.assertEquals(0d, result.z, DELTA);
    Assert.assertEquals(1d, result.w, DELTA);
  }
}