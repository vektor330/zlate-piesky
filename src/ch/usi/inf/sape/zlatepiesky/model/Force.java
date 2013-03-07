package ch.usi.inf.sape.zlatepiesky.model;

import javax.vecmath.Vector2d;

public interface Force {

  double getStrength();

  Vector2d getEffect(Particle particle);
}
