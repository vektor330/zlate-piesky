package ch.usi.inf.sape.zlatepiesky.model;

import ch.usi.inf.sape.zlatepiesky.model.interfaces.Renderable;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Selectable;
import javax.vecmath.Vector2d;

public abstract class Item implements Renderable, Selectable {

  private Vector2d position;
  private boolean selected;

  @Override
  public Vector2d getPosition() {
    return position;
  }

  public void setPosition(Vector2d position) {
    this.position = position;
  }

  @Override
  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public boolean isSelected() {
    return selected;
  }
}
