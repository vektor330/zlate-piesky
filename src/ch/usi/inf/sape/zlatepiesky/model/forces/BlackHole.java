package ch.usi.inf.sape.zlatepiesky.model.forces;

import ch.usi.inf.sape.zlatepiesky.model.Item;
import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Force;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.ForceType;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import javax.vecmath.Vector2d;

public class BlackHole extends Item implements Force, Serializable {

  private static final long serialVersionUID = 12345426L;
  private double strength;
  private double schwarzschildRadius = 10;

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
    final Vector2d ret = new Vector2d(getPosition());
    ret.sub(particle.getPosition());
    final double distanceSq = ret.lengthSquared();
    ret.normalize();
    ret.scale(strength * particle.getWeight() / distanceSq);
    return ret;
  }

  @Override
  public ForceType getType() {
    return ForceType.BLACK_HOLE;
  }

  @Override
  public void render(Graphics2D g) {
    g.setPaint(Color.BLACK);
    final Shape shape = getShape();
    g.fill(shape);
    if (isSelected()) {
      g.setPaint(new Color(255, 204, 52));
      g.setStroke(new BasicStroke(3f));
    } else {
      g.setPaint(new Color(1f, 0f, 0f));
      g.setStroke(new BasicStroke(0.1f));
    }
    g.draw(shape);
  }

  @Override
  public Shape getShape() {
    return new Ellipse2D.Double(
            getPosition().x - schwarzschildRadius,
            getPosition().y - schwarzschildRadius,
            schwarzschildRadius * 2,
            schwarzschildRadius * 2);
  }
}
