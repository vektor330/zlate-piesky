package ch.usi.inf.sape.zlatepiesky.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.vecmath.Vector2d;

public class Wall extends Item {

  private Vector2d begin;
  private Vector2d end;
  private double elasticity;

  public Vector2d getBegin() {
    return begin;
  }

  public void setBegin(Vector2d begin) {
    this.begin = begin;
  }

  public Vector2d getEnd() {
    return end;
  }

  public void setEnd(Vector2d end) {
    this.end = end;
  }

  public double getElasticity() {
    return elasticity;
  }

  public void setElasticity(double elasticity) {
    this.elasticity = elasticity;
  }

  @Override
  public void render(Graphics2D g) {
    g.setPaint(Color.ORANGE);
    g.drawLine((int) Math.round(begin.x), (int) Math.round(begin.y), (int) Math.round(end.x), (int) Math.round(end.y));
  }

  @Override
  public Shape getShape() {
    final double x = Math.min(begin.x, end.x);
    final double y = Math.min(begin.y, end.y);
    final double w = Math.abs(begin.x - end.x);
    final double h = Math.abs(begin.y - end.y);
    return new Rectangle2D.Double(x, y, w, h);
  }
}
