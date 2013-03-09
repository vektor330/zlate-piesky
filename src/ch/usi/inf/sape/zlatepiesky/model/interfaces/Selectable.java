package ch.usi.inf.sape.zlatepiesky.model.interfaces;

import java.awt.Shape;

public interface Selectable {

  void setSelected(boolean selected);

  boolean isSelected();

  Shape getShape();

}
