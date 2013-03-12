package ch.usi.inf.sape.zlatepiesky.gui.properties;

import ch.usi.inf.sape.zlatepiesky.World;
import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import javax.swing.JFrame;

public class EmitterProperties extends JFrame {

  private static final long serialVersionUID = 15432525L;
  private final Emitter emitter;
  private final World world;

  public EmitterProperties() {
    this(null, null);
  }

  public EmitterProperties(final Emitter emitter, final World world) {
    this.emitter = emitter;
    this.world = world;
    initComponents();
    rate.setValue((int) Math.round(emitter.getRate()));
    // TODO show up next to the emitter
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    rate = new javax.swing.JSlider();

    setAlwaysOnTop(true);

    jLabel1.setText("TODO: create a slider that will work with double values");

    rate.setMaximum(500);
    rate.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        rateChanged(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(47, 47, 47)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(rate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jLabel1))
        .addContainerGap(7, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(26, 26, 26)
        .add(jLabel1)
        .add(57, 57, 57)
        .add(rate, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(172, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void rateChanged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rateChanged
    synchronized (world) {
      emitter.setRate(rate.getValue());
    }
  }//GEN-LAST:event_rateChanged

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JSlider rate;
  // End of variables declaration//GEN-END:variables
}
