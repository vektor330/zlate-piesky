package ch.usi.inf.sape.zlatepiesky.model.forces;

import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Force;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.ForceType;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Position;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Renderable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.vecmath.Vector2d;

public class BlackHole implements Force, Position, Renderable {

  private Vector2d position;
  private double strength;
  private double schwarzschildRadius = 10;

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

  @Override
  public ForceType getType() {
    return ForceType.BLACK_HOLE;
  }

  @Override
  public void render(Graphics2D g) {
    final int r = (int) Math.round(schwarzschildRadius);
    final int d = r * 2;
    final int x = (int) Math.round(position.x);
    final int y = (int) Math.round(position.y);

    g.setPaint(Color.BLACK);
    g.fillOval(x - r, y - r, d, d);
    g.setPaint(new Color(1f, 0f, 0f));
    g.setStroke(new BasicStroke(0.1f));
    g.drawOval(x - r, y - r, d, d);
  }
}
