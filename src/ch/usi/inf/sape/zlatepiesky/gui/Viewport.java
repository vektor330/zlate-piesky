package ch.usi.inf.sape.zlatepiesky.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JComponent;

public class Viewport extends JComponent {

  private static final long serialVersionUID = 1345623525L;

  private AffineTransform transform = new AffineTransform();

  public Viewport() {
    initComponents();
    transform.setToIdentity();
  }

  @Override
  public void paint(final Graphics gr) {
    final Graphics2D g = (Graphics2D) gr;
    g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setPaint(Color.WHITE);
    g.fillRect(0, 0, getWidth(), getHeight());
    final AffineTransform at = g.getTransform();
    g.transform(transform);
    g.setPaint(Color.RED);
    g.drawLine(0, 0, 100, 0);
    g.setPaint(Color.BLUE);
    g.drawLine(0, 0, 0, 100);
    g.setTransform(at);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    setLayout(null);
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables

  public AffineTransform getTransform() {
    return transform;
  }
}
