package ch.usi.inf.sape.zlatepiesky.model;

import ch.usi.inf.sape.zlatepiesky.model.interfaces.Position;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Renderable;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import javax.vecmath.Vector2d;

public class Particle implements Position, Renderable, Serializable {

  private static final long serialVersionUID = 5425251L;
  private Vector2d position = new Vector2d();
  private Vector2d speed = new Vector2d();
  private double weight = 1;
  /**
   * Timestamp when this particle was created.
   */
  private long created = 0;
  private double size = 1;
  private Emitter birthplace = null;
  private Color color = null;
  private boolean immovable = false;

  @Override
  public Vector2d getPosition() {
    return position;
  }

  @Override
  public void setPosition(Vector2d position) {
    this.position = new Vector2d(position);
  }

  public Vector2d getSpeed() {
    return speed;
  }

  public void setSpeed(Vector2d speed) {
    this.speed = new Vector2d(speed);
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

  public Color getColor() {
    return color;
  }

  public void setColor(final Color color) {
    this.color = color;
  }

  public boolean isImmovable() {
    return immovable;
  }

  public void setImmovable(boolean immovable) {
    this.immovable = immovable;
  }

  @Override
  public void render(Graphics2D g) {
    final int x = (int) Math.round(position.x);
    final int y = (int) Math.round(position.y);
    final int x2 = (int) Math.round(position.x + speed.x);
    final int y2 = (int) Math.round(position.y + speed.y);
    final Color c;
    if (color != null) {
      c = color;
    } else {
      final float speedLog = (float) Math.log(speed.lengthSquared());
      if (speedLog < 0) {
        c = new Color(1 / (-speedLog + 1), 0.2f / (-speedLog + 1), 0);
      } else if (speedLog > 1) {
        c = new Color(1 - 1 / (speedLog), 1f - 0.8f / speedLog, 1f);
      } else {
        c = new Color(1 - speedLog, 0.2f, speedLog);
      }
    }
    g.setPaint(c);
    g.setStroke(new BasicStroke((int) Math.round(size)));
    g.drawLine(x, y, x2, y2);
  }
}
