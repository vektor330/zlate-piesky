package ch.usi.inf.sape.zlatepiesky.gui;

import java.awt.EventQueue;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;

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

  public MainWindow() {
    initComponents();
    setLocationRelativeTo(null);
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    jLabel1 = new javax.swing.JLabel();
    translation = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    zoom = new javax.swing.JTextField();
    viewport = new ch.usi.inf.sape.zlatepiesky.gui.Viewport();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setPreferredSize(new java.awt.Dimension(800, 600));
    getContentPane().setLayout(new java.awt.GridBagLayout());

    jLabel1.setText("Translation:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 0);
    getContentPane().add(jLabel1, gridBagConstraints);

    translation.setEditable(false);
    translation.setText("0, 0");
    translation.setName(""); // NOI18N
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 186;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(6, 18, 6, 0);
    getContentPane().add(translation, gridBagConstraints);

    jLabel2.setText("Zoom:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 0);
    getContentPane().add(jLabel2, gridBagConstraints);

    zoom.setEditable(false);
    zoom.setText("1");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 186;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(6, 18, 6, 0);
    getContentPane().add(zoom, gridBagConstraints);

    viewport.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
      public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
        mouseWheel(evt);
      }
    });
    viewport.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mousePressed(java.awt.event.MouseEvent evt) {
        mouseDown(evt);
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
      zoom.setText(DF.format(viewport.getTransform().getScaleX()));
    } catch (NoninvertibleTransformException ex) {
      Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseWheel

  private void mouseDown(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseDown
    try {
      mouseOrigin = viewport.getTransform().inverseTransform(evt.getPoint(), null);
      originalTransform = (AffineTransform) viewport.getTransform().clone();
    } catch (NoninvertibleTransformException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseDown

  private void mouseDrag(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseDrag
    try {
      final Point2D current = originalTransform.inverseTransform(evt.getPoint(), null);
      final double tx = current.getX() - mouseOrigin.getX();
      final double ty = current.getY() - mouseOrigin.getY();
      mouseOrigin = current;
      viewport.getTransform().translate(tx, ty);
      viewport.repaint();
      translation.setText(DF.format(viewport.getTransform().getTranslateX()) + ", " + DF.format(viewport.getTransform().getTranslateY()));
    } catch (NoninvertibleTransformException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseDrag

  private static double boundZoom(final double zoom) {
    if (zoom > 1000) {
      return 1000;
    }
    if (zoom < 0.01) {
      return 0.01;
    }
    return zoom;
  }

  private static double boundDimension(final double dimension) {
    if (dimension > 10000) {
      return 1000;
    }
    if (dimension < -10000) {
      return -10000;
    }
    return dimension;
  }

  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new MainWindow().setVisible(true);
      }
    });
  }
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JTextField translation;
  private ch.usi.inf.sape.zlatepiesky.gui.Viewport viewport;
  private javax.swing.JTextField zoom;
  // End of variables declaration//GEN-END:variables
}
