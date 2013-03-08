package ch.usi.inf.sape.zlatepiesky.model.interfaces;

import ch.usi.inf.sape.zlatepiesky.model.Particle;
import javax.vecmath.Vector2d;

public interface Force {

  double getStrength();

  /**
   * Returns the acceleration this force exerts to the particle.
   */
  Vector2d getEffect(Particle particle);

  ForceType getType();
}
