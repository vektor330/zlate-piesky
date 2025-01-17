package ch.usi.inf.sape.zlatepiesky.model.forces;

import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Force;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.ForceType;
import java.io.Serializable;
import javax.vecmath.Vector2d;

public class Gravity implements Force, Serializable {

  private static final long serialVersionUID = 1346436L;
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
    ret.scale(strength * particle.getWeight());
    return ret;
  }

  @Override
  public ForceType getType() {
    return ForceType.GRAVITY;
  }
}
