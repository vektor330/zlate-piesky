package ch.usi.inf.sape.zlatepiesky.model;

import javax.vecmath.Vector2d;

public class Gravity implements Force {

  private Vector2d direction;
  private double strength;

  public Gravity() {
  }

  public Vector2d getDirection() {
    return direction;
  }

  public void setDirection(Vector2d direction) {
    this.direction = direction;
  }

  @Override
  public double getStrength() {
    return strength;
  }

  public void setStrength(double strength) {
    this.strength = strength;
  }

  @Override
  public Vector2d getEffect(Particle particle) {
    final Vector2d ret = new Vector2d(direction);
    ret.normalize();
    ret.scale(particle.getWeight());
    return ret;
  }
}
