package ch.usi.inf.sape.zlatepiesky.model.interfaces;

import ch.usi.inf.sape.zlatepiesky.World;
import java.awt.Shape;

public interface Selectable extends Position {

  void setSelected(boolean selected);

  boolean isSelected();

  Shape getShape();

  void showProperties(World world);

}
