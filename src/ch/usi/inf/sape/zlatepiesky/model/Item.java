package ch.usi.inf.sape.zlatepiesky.model;

import ch.usi.inf.sape.zlatepiesky.model.interfaces.Renderable;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Selectable;

public abstract class Item implements Renderable, Selectable {

  private boolean selected;

  @Override
  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public boolean isSelected() {
    return selected;
  }
}
