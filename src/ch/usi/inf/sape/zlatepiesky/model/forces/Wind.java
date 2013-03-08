package ch.usi.inf.sape.zlatepiesky.model.forces;

import ch.usi.inf.sape.zlatepiesky.model.interfaces.Force;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.ForceType;
import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Position;
import javax.vecmath.Vector2d;

public class Wind implements Force, Position {

  private Vector2d position;
  private Vector2d direction;
  private double strength;
  // TODO add perturbation

  @Override
  public Vector2d getPosition() {
    return position;
  }

  public void setPosition(Vector2d position) {
    this.position = position;
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
    final Vector2d ret = new Vector2d(particle.getPosition());
    ret.sub(position);
    final double distanceSq = ret.lengthSquared();
    ret.normalize();
    ret.scale(strength * particle.getSize() / distanceSq);
    return ret;
  }


  @Override
  public ForceType getType() {
    return ForceType.WIND;
  }
}
