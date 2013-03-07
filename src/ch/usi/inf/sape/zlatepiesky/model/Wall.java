package ch.usi.inf.sape.zlatepiesky.model;

import javax.vecmath.Vector2d;

public class Wall {

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
}
