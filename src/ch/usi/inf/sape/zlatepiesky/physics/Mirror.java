package ch.usi.inf.sape.zlatepiesky.physics;

import javax.vecmath.Vector2d;
import javax.vecmath.Vector4d;

public class Mirror {

  /**
   * Mirrors the line given by begin and end around the axis given by axisBegin
   * and axisEnd.
   *
   * @param axisBegin
   * @param axisEnd
   * @param begin
   * @param end
   * @return
   */
  public static Vector4d mirror(final Vector2d axisBegin, final Vector2d axisEnd,
          final Vector2d begin, final Vector2d end) {
    final Vector2d n = new Vector2d(axisEnd);
    n.sub(axisBegin);
    n.normalize();
    final double A = 2 * (1 - n.x * n.x);
    final double B = 2 * (-n.x * n.y);
    final double C = B;
    final double D = 2 * (1 - n.y * n.y);

    final Vector2d differenceBegin = new Vector2d(axisEnd);
    differenceBegin.sub(begin); // (P - Q)
    final double aBegin = differenceBegin.x;
    final double bBegin = differenceBegin.y;

    final Vector2d beginM = new Vector2d(begin);
    beginM.add(new Vector2d(A * aBegin + B * bBegin, C * aBegin + D * bBegin));


    final Vector2d differenceEnd = new Vector2d(axisEnd);
    differenceEnd.sub(end); // (P - Q)
    final double aEnd = differenceEnd.x;
    final double bEnd = differenceEnd.y;

    final Vector2d endM = new Vector2d(end);
    endM.add(new Vector2d(A * aEnd + B * bEnd, C * aEnd + D * bEnd));

    return new Vector4d(beginM.x, beginM.y, endM.x, endM.y);
  }
}
