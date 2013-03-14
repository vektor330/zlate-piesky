package ch.usi.inf.sape.zlatepiesky.gui;

import ch.usi.inf.sape.zlatepiesky.Constants;
import javax.swing.JSlider;

public class JFloatSlider extends JSlider {

  private static final long serialVersionUID = 132442413L;
  private double min = 0;
  private double max = 100;
  private double step = 1;
  private int exponent;

  public double getMin() {
    return min;
  }

  public void setMin(double min) {
    this.min = min;
    setup();
  }

  public double getMax() {
    return max;
  }

  public void setMax(double max) {
    this.max = max;
    setup();
  }

  public double getStep() {
    return step;
  }

  public void setStep(double step) {
    this.step = step;
    setup();
  }

  public double getVal() {
    setup();
    return getValue() / exponent;
  }

  public void setVal(final double value) {
    setValue((int) Math.round(value * exponent));
  }


  private void setup() {
    exponent = Math.max(getExponent(step), Math.max(getExponent(min), getExponent(max)));
    setMinimum((int) Math.round(min * exponent));
    setMaximum((int) Math.round(max * exponent));
    setMajorTickSpacing((int) Math.round(step * exponent));
  }

  private static int getExponent(final double number) {
    double current = number;
    int ret = 1;
    while (true) {
      if (Math.abs(current - (double) Math.round(current)) < Constants.EPSILON) {
        return ret;
      }
      ret *= 10;
      current *= 10;
    }
  }
}
