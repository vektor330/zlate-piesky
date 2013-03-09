package ch.usi.inf.sape.zlatepiesky.gui;

import ch.usi.inf.sape.zlatepiesky.Setup;
import ch.usi.inf.sape.zlatepiesky.World;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

// TODO save/load
public class MainWindow extends JFrame {

  private static final long serialVersionUID = 13243235L;
  private static final Logger LOG = Logger.getLogger("Viewport");
  private static final DecimalFormat DF = (DecimalFormat) DecimalFormat.getInstance();

  static {
    DF.setRoundingMode(RoundingMode.HALF_UP);
    DF.setMaximumFractionDigits(3);
  }
  private AffineTransform originalTransform;
  private Point2D mouseOrigin;
  private final World world = new World();

  public MainWindow() {
    initComponents();
    setLocationRelativeTo(null);
    Setup.setup(world);
    viewport.setWorld(world);

    final Timer simulationTimer = new Timer();
    simulationTimer.scheduleAtFixedRate(new TimerTask() {
      private int counter = 0;

      @Override
      public void run() {
        world.simulationStep();
        counter++;
        if (counter == 2) {
          viewport.repaint();
          counter = 0;
        }
      }
    }, 0, 5);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    viewport = new ch.usi.inf.sape.zlatepiesky.gui.Viewport();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setPreferredSize(new java.awt.Dimension(800, 600));
    getContentPane().setLayout(new java.awt.GridBagLayout());

    viewport.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
      public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
        mouseWheel(evt);
      }
    });
    viewport.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        mouseDown(evt);
      }
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        mouseClick(evt);
      }
    });
    viewport.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        mouseDrag(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 12);
    getContentPane().add(viewport, gridBagConstraints);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void mouseWheel(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_mouseWheel
    try {
      final double factor = Math.pow(2, -evt.getPreciseWheelRotation() / 10);
      final Point2D mouse = viewport.getTransform().inverseTransform(evt.getPoint(), null);
      viewport.getTransform().translate(mouse.getX(), mouse.getY());
      viewport.getTransform().scale(factor, factor);
      viewport.getTransform().translate(-mouse.getX(), -mouse.getY());
      viewport.repaint();
    } catch (NoninvertibleTransformException ex) {
      Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseWheel

  private void mouseDown(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseDown
    try {
      if (evt.getButton() == MouseEvent.BUTTON1) {
        mouseOrigin = viewport.getTransform().inverseTransform(evt.getPoint(), null);
        originalTransform = (AffineTransform) viewport.getTransform().clone();
      }
    } catch (NoninvertibleTransformException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseDown

  private void mouseDrag(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseDrag
    try {
      if (evt.getButton() == MouseEvent.BUTTON1) {
        final Point2D current = originalTransform.inverseTransform(evt.getPoint(), null);
        final double tx = current.getX() - mouseOrigin.getX();
        final double ty = current.getY() - mouseOrigin.getY();
        mouseOrigin = current;
        viewport.getTransform().translate(tx, ty);
        viewport.repaint();
      }
    } catch (NoninvertibleTransformException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseDrag

  private void mouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClick
    try {
      if (evt.getButton() == MouseEvent.BUTTON1) {
        final Point2D current = originalTransform.inverseTransform(evt.getPoint(), null);
        world.setSelected(world.getUnder(current));
      }
    } catch (NoninvertibleTransformException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseClick
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private ch.usi.inf.sape.zlatepiesky.gui.Viewport viewport;
  // End of variables declaration//GEN-END:variables
}
