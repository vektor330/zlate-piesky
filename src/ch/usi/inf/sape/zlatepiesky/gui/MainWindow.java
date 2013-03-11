package ch.usi.inf.sape.zlatepiesky.gui;

import ch.usi.inf.sape.zlatepiesky.Setup;
import ch.usi.inf.sape.zlatepiesky.Utils;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Selectable;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.vecmath.Vector2d;

public class MainWindow extends JFrame {

  private static final long serialVersionUID = 13243235L;
  private static final Logger LOG = Logger.getLogger("Viewport");
  private AffineTransform originalTransform = new AffineTransform();
  private Point2D mouseOrigin;
  private Selectable dragging;

  public MainWindow() {
    initComponents();
    setLocationRelativeTo(null);
    Setup.setup(viewport.getWorld());

    final Timer simulationTimer = new Timer();
    simulationTimer.scheduleAtFixedRate(new TimerTask() {
      private int counter = 0;

      @Override
      public void run() {
        viewport.getWorld().simulationStep();
        counter++;
        if (counter == 4) {
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
    jPanel1 = new javax.swing.JPanel();
    timeSpeed = new javax.swing.JSlider();
    jMenuBar1 = new javax.swing.JMenuBar();
    jMenu1 = new javax.swing.JMenu();
    jMenuItem1 = new javax.swing.JMenuItem();
    jMenuItem2 = new javax.swing.JMenuItem();

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
      public void mouseReleased(java.awt.event.MouseEvent evt) {
        mouseUp(evt);
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
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(12, 12, 12, 12);
    getContentPane().add(viewport, gridBagConstraints);

    timeSpeed.setMaximum(300);
    timeSpeed.setMinimum(-300);
    timeSpeed.setMinorTickSpacing(10);
    timeSpeed.setValue(100);
    timeSpeed.addChangeListener(new javax.swing.event.ChangeListener() {
      public void stateChanged(javax.swing.event.ChangeEvent evt) {
        timeSpeedStateChanged(evt);
      }
    });

    org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .add(timeSpeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(604, Short.MAX_VALUE))
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .add(timeSpeed, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(15, Short.MAX_VALUE))
    );

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    getContentPane().add(jPanel1, gridBagConstraints);

    jMenu1.setText("File");

    jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.META_MASK));
    jMenuItem1.setText("Save");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuSave(evt);
      }
    });
    jMenu1.add(jMenuItem1);

    jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.META_MASK));
    jMenuItem2.setText("Load");
    jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        menuLoad(evt);
      }
    });
    jMenu1.add(jMenuItem2);

    jMenuBar1.add(jMenu1);

    setJMenuBar(jMenuBar1);

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
        final Selectable s = viewport.getWorld().getUnder(mouseOrigin);
        if (s != null) {
          dragging = s;
        }
      }
    } catch (NoninvertibleTransformException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseDown

  private void mouseDrag(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseDrag
    try {
      if (SwingUtilities.isLeftMouseButton(evt)) {
        final Point2D current = originalTransform.inverseTransform(evt.getPoint(), null);
        if (dragging != null) {
          dragging.setPosition(new Vector2d(current.getX(), current.getY()));
        } else {
          final double tx = current.getX() - mouseOrigin.getX();
          final double ty = current.getY() - mouseOrigin.getY();
          mouseOrigin = current;
          viewport.getTransform().translate(tx, ty);
          viewport.repaint();
        }
      }
    } catch (NoninvertibleTransformException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseDrag

  private void mouseClick(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseClick
    try {
      final Point2D current = viewport.getTransform().inverseTransform(evt.getPoint(), null);
      final Selectable item = viewport.getWorld().getUnder(current);
      if (SwingUtilities.isLeftMouseButton(evt)) {
        viewport.getWorld().setSelected(item);
      } else if (SwingUtilities.isRightMouseButton(evt) && item != null) {
        item.showProperties(viewport.getWorld());
      }
    } catch (NoninvertibleTransformException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }//GEN-LAST:event_mouseClick

  private void mouseUp(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseUp
    dragging = null;
  }//GEN-LAST:event_mouseUp

  private void menuSave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSave
    final JFileChooser fc = new JFileChooser();
    int returnVal = fc.showSaveDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      Utils.save(file, viewport.getWorld());
    }
  }//GEN-LAST:event_menuSave

  private void menuLoad(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuLoad
    final JFileChooser fc = new JFileChooser();
    int returnVal = fc.showOpenDialog(this);

    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File file = fc.getSelectedFile();
      viewport.setWorld(Utils.load(file));
    }
  }//GEN-LAST:event_menuLoad

  private void timeSpeedStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_timeSpeedStateChanged
    viewport.getWorld().setTimeSpeed((double)timeSpeed.getValue() / 100);
  }//GEN-LAST:event_timeSpeedStateChanged

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenu jMenu1;
  private javax.swing.JMenuBar jMenuBar1;
  private javax.swing.JMenuItem jMenuItem1;
  private javax.swing.JMenuItem jMenuItem2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JSlider timeSpeed;
  private ch.usi.inf.sape.zlatepiesky.gui.Viewport viewport;
  // End of variables declaration//GEN-END:variables
}
