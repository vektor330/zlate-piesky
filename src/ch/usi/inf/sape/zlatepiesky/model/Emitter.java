package ch.usi.inf.sape.zlatepiesky.model;

import javax.vecmath.Vector2d;

public class Emitter implements Position {

  private Vector2d position = new Vector2d();
  /**
   * Number of particles emitted per second.
   */
  private long rate = 1;
  /**
   * How many milliseconds do particles survive.
   */
  private long lifetime = 1000;
  private Vector2d initialSpeed = new Vector2d();
  private double particleWeight = 1;
  private double particleSize = 1;

  @Override
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

  public double getParticleWeight() {
    return particleWeight;
  }

  public void setParticleWeight(double particleWeight) {
    this.particleWeight = particleWeight;
  }

  public double getParticleSize() {
    return particleSize;
  }

  public void setParticleSize(double particleSize) {
    this.particleSize = particleSize;
  }
}
