package ch.usi.inf.sape.zlatepiesky.model;

import ch.usi.inf.sape.zlatepiesky.model.interfaces.Position;
import javax.vecmath.Vector2d;

public abstract class PositionedItem extends Item implements Position {

  private Vector2d position;

  @Override
  public Vector2d getPosition() {
    return position;
  }

  public void setPosition(Vector2d position) {
    this.position = position;
  }
}
