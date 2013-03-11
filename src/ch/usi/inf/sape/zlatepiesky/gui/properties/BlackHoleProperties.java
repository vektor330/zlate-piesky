package ch.usi.inf.sape.zlatepiesky.gui.properties;

import ch.usi.inf.sape.zlatepiesky.World;
import ch.usi.inf.sape.zlatepiesky.model.forces.BlackHole;

public class BlackHoleProperties extends javax.swing.JFrame {

  private static final long serialVersionUID = 13425325L;
  private final BlackHole blackhole;
  private final World world;

  public BlackHoleProperties() {
    this(null, null);
  }

  public BlackHoleProperties(final BlackHole blackhole, final World world) {
    this.blackhole = blackhole;
    this.world = world;
    initComponents();
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 400, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 300, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents
  // Variables declaration - do not modify//GEN-BEGIN:variables
  // End of variables declaration//GEN-END:variables
}
