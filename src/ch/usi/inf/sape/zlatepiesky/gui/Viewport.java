package ch.usi.inf.sape.zlatepiesky.gui;

import ch.usi.inf.sape.zlatepiesky.Constants;
import ch.usi.inf.sape.zlatepiesky.World;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

// TODO make axes hidable
public class Viewport extends JComponent {

  private static final long serialVersionUID = 1345623525L;
  private AffineTransform transform = new AffineTransform();
  private volatile World world = new World();

  public Viewport() {
    initComponents();
    transform.setToIdentity();
    getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "doSomething");
    getActionMap().put("doSomething", new AbstractAction() {
      private static final long serialVersionUID = 425431L;

      @Override
      public void actionPerformed(ActionEvent e) {
        if (world.getSelected() != null) {
          world.delete(world.getSelected());
        }
      }
    });
  }

  @Override
  public void paint(final Graphics gr) {
    final Graphics2D g = (Graphics2D) gr;
    if (Constants.DUMB) {
      g.setPaint(Color.WHITE);
    } else {
      g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g.setPaint(Color.BLACK);
    }
    g.fillRect(0, 0, getWidth(), getHeight());
    final AffineTransform at = g.getTransform();
    g.transform(transform);
    g.setPaint(Color.RED);
    g.drawLine(0, 0, 100, 0);
    g.setPaint(Color.BLUE);
    g.drawLine(0, 0, 0, 100);
    if (world != null) {
      Renderer.render(world, g);
    }
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

  public World getWorld() {
    return world;
  }

  public void setWorld(World world) {
    this.world = world;
  }
}
