package ch.usi.inf.sape.zlatepiesky.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import javax.vecmath.Vector2d;

public class Emitter extends Item {

  private static final long serialVersionUID = 1245235L;
  /**
   * Number of particles emitted per second.
   */
  private double rate = 1;
  private double rateSpread = 0.5;
  /**
   * How many milliseconds do particles survive.
   */
  private long lifetime = 10000;
  private long lifetimeSpread = 3000;
  private Vector2d initialSpeed = new Vector2d(1, 1);
  /**
   * Spread in radians.
   */
  private double angleSpread = 0; // TODO implement
  private double speedSpread = 1;
  private double particleWeight = 1;
  private double particleScaleSpread = 0.9;
  private double particleSize = 1;

  @Override
  public void render(Graphics2D g) {
    g.setPaint(Color.BLUE);
    final Shape shape = getShape();
    g.fill(shape);
    if (isSelected()) {
      g.setPaint(new Color(255, 204, 52));
      g.setStroke(new BasicStroke(3f));
      g.draw(shape);
    }
  }

  @Override
  public Shape getShape() {
    return new Rectangle2D.Double(getPosition().x - 5, getPosition().y - 5, 10, 10);
  }

  // --- GET/SET ---------------------------------------------------------------
  public double getRate() {
    return rate;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public double getRateSpread() {
    return rateSpread;
  }

  public void setRateSpread(final double rateSpread) {
    this.rateSpread = rateSpread;
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
    this.initialSpeed = new Vector2d(initialSpeed);
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

  public double getAngleSpread() {
    return angleSpread;
  }

  public void setAngleSpread(double angleSpread) {
    this.angleSpread = angleSpread;
  }

  public double getSpeedSpread() {
    return speedSpread;
  }

  public void setSpeedSpread(double speedSpread) {
    this.speedSpread = speedSpread;
  }

  public long getLifetimeSpread() {
    return lifetimeSpread;
  }

  public void setLifetimeSpread(long lifetimeSpread) {
    this.lifetimeSpread = lifetimeSpread;
  }

  public double getParticleScaleSpread() {
    return particleScaleSpread;
  }

  public void setParticleScaleSpread(double particleScaleSpread) {
    this.particleScaleSpread = particleScaleSpread;
  }

}
