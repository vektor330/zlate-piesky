package ch.usi.inf.sape.zlatepiesky.model.forces;

import ch.usi.inf.sape.zlatepiesky.model.Force;
import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.Position;
import javax.vecmath.Vector2d;

public class BlackHole implements Force, Position {

  private Vector2d position;
  private double strength;
  private double schwarzschildRadius;

  @Override
  public Vector2d getPosition() {
    return position;
  }

  public void setPosition(Vector2d position) {
    this.position = position;
  }

  @Override
  public double getStrength() {
    return strength;
  }

  public void setStrength(double strength) {
    this.strength = strength;
  }

  public double getSchwarzschildRadius() {
    return schwarzschildRadius;
  }

  public void setSchwarzschildRadius(double schwarzschildRadius) {
    this.schwarzschildRadius = schwarzschildRadius;
  }

  @Override
  public Vector2d getEffect(Particle particle) {
    final Vector2d ret = new Vector2d(position);
    ret.sub(particle.getPosition());
    final double distanceSq = ret.lengthSquared();
    ret.normalize();
    ret.scale(strength * particle.getWeight() / distanceSq);
    return ret;
  }
}
