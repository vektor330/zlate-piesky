package ch.usi.inf.sape.zlatepiesky.model.interfaces;

import java.awt.Point;
import java.awt.Shape;

public interface Selectable extends Position {

  void setSelected(boolean selected);

  boolean isSelected();

  Shape getShape();

  void showProperties(Point screenPosition);

}
