package ch.usi.inf.sape.zlatepiesky.model;

import ch.usi.inf.sape.zlatepiesky.model.interfaces.Renderable;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.vecmath.Vector2d;

public class Wall implements Renderable {

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
}
