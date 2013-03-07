package ch.usi.inf.sape.zlatepiesky.model;

import javax.vecmath.Vector2d;

public class Particle implements Position {

  private Vector2d position;
  private Vector2d speed;
  private double weight;
  /**
   * Timestamp when this particle was created.
   */
  private long created;
  private double size;
  private Emitter birthplace;

  @Override
  public Vector2d getPosition() {
    return position;
  }

  public void setPosition(Vector2d position) {
    this.position = position;
  }

  public Vector2d getSpeed() {
    return speed;
  }

  public void setSpeed(Vector2d speed) {
    this.speed = speed;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  public long getCreated() {
    return created;
  }

  public void setCreated(long created) {
    this.created = created;
  }

  public double getSize() {
    return size;
  }

  public void setSize(double size) {
    this.size = size;
  }

  public Emitter getBirthplace() {
    return birthplace;
  }

  public void setBirthplace(Emitter birthplace) {
    this.birthplace = birthplace;
  }
}
