package ch.usi.inf.sape.zlatepiesky.model;

import javax.vecmath.Vector2d;

public class Emitter implements Position {

  private Vector2d position;
  /**
   * Number of particles emitted per second.
   */
  private long rate;
  /**
   * How many seconds do particles survive.
   */
  private long lifetime;
  private Vector2d initialSpeed;

  public Vector2d getPosition() {
    return position;
  }

  public void setPosition(Vector2d position) {
    this.position = position;
  }

  public long getRate() {
    return rate;
  }

  public void setRate(long rate) {
    this.rate = rate;
  }

  public long getLifetime() {
    return lifetime;
  }

  public void setLifetime(long lifetime) {
    this.lifetime = lifetime;
  }

  public Vector2d getInitialSpeed() {
    return initialSpeed;
  }

  public void setInitialSpeed(Vector2d initialSpeed) {
    this.initialSpeed = initialSpeed;
  }
}
