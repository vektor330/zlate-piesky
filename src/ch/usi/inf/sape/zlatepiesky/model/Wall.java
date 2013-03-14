package ch.usi.inf.sape.zlatepiesky.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import javax.vecmath.Vector2d;

// TODO make it possible to move ends
public class Wall extends Item implements Serializable {

  private static final long serialVersionUID = 142525252L;
  private Vector2d begin = new Vector2d(0, 0);
  private Vector2d end = new Vector2d(100, 100);
  private double elasticity = 1;

  public Vector2d getBegin() {
    return begin;
  }

  public void setBegin(Vector2d begin) {
    this.begin = new Vector2d(begin);
  }

  public Vector2d getEnd() {
    return end;
  }

  public void setEnd(Vector2d end) {
    this.end = new Vector2d(end);
  }

  public double getElasticity() {
    return elasticity;
  }

  public void setElasticity(double elasticity) {
    this.elasticity = elasticity;
  }

  @Override
  public Vector2d getPosition() {
    return begin;
  }

  @Override
  public void setPosition(Vector2d position) {
    final Vector2d d = new Vector2d(end);
    d.sub(begin);
    setBegin(position);
    position.add(d);
    setEnd(position);
  }

  @Override
  public void render(Graphics2D g) {
    if (isSelected()) {
      g.setPaint(new Color(255, 204, 52));
      g.setStroke(new BasicStroke(3f));
      g.drawLine((int) Math.round(begin.x), (int) Math.round(begin.y), (int) Math.round(end.x), (int) Math.round(end.y));
    } else {
      g.setPaint(Color.ORANGE);
      g.setStroke(new BasicStroke(1f));
      g.drawLine((int) Math.round(begin.x), (int) Math.round(begin.y), (int) Math.round(end.x), (int) Math.round(end.y));
    }
  }

  @Override
  public Shape getShape() {
    final double x = Math.min(begin.x, end.x);
    final double y = Math.min(begin.y, end.y);
    final double w = Math.abs(begin.x - end.x);
    final double h = Math.abs(begin.y - end.y);
    return new Rectangle2D.Double(x, y, w, h);
  }

  @Override
  public void showProperties(final Point screenPosition) {
    throw new UnsupportedOperationException("Not supported yet.");
  }
}
