package ch.usi.inf.sape.zlatepiesky.gui.properties;

import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import java.awt.Point;
import javax.swing.JFrame;

// TODO need the handle for initial speed
public class EmitterProperties extends JFrame {

  private static final long serialVersionUID = 15432525L;
  private final Emitter emitter;

  public EmitterProperties() {
    this(null, null);
  }

  public EmitterProperties(final Emitter emitter, final Point screenPosition) {
    this.emitter = emitter;
    initComponents();
    size.setVal(emitter.getParticleSize());
    sizeSpread.setVal(emitter.getParticleScaleSpread());
    rate.setVal(emitter.getRate());
    rateSpread.setVal(emitter.getRateSpread());
    lifetime.setValue(emitter.getLifetime());
    lifetimeSpread.setValue(emitter.getLifetimeSpread());
    setLocation(screenPosition);
  }

  private void setValues() {
    emitter.setRate(rate.getVal());
    emitter.setRateSpread(rateSpread.getVal());
    emitter.setParticleSize(size.getVal());
    emitter.setParticleScaleSpread(sizeSpread.getVal());
    emitter.setLifetime(lifetime.getValue());
    emitter.setLifetimeSpread(lifetimeSpread.getValue());
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    size = new ch.usi.inf.sape.zlatepiesky.gui.JFloatSlider();
    sizeSpread = new ch.usi.inf.sape.zlatepiesky.gui.JFloatSlider();
    sizeLabel = new javax.swing.JLabel();
    fillH = new javax.swing.JPanel();
    fillV = new javax.swing.JPanel();
    rateLabel = new javax.swing.JLabel();
    lifetimeLabel = new javax.swing.JLabel();
    rate = new ch.usi.inf.sape.zlatepiesky.gui.JFloatSlider();
    rateSpread = new ch.usi.inf.sape.zlatepiesky.gui.JFloatSlider();
    lifetime = new javax.swing.JSlider();
    lifetimeSpread = new javax.swing.JSlider();

    setAlwaysOnTop(true);
    getContentPane().setLayout(new java.awt.GridBagLayout());

    size.setMax(10.0);
    size.setMin(1.0E-4);
    size.setMinimumSize(new java.awt.Dimension(200, 20));
    size.setStep(0.1);
    size.setVal(1.0);
    size.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        mouseDrag(evt);
      }
    });
    size.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        EmitterProperties.this.keyTyped(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    getContentPane().add(size, gridBagConstraints);

    sizeSpread.setMax(10.0);
    sizeSpread.setMinimumSize(new java.awt.Dimension(100, 20));
    sizeSpread.setStep(0.1);
    sizeSpread.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        mouseDrag(evt);
      }
    });
    sizeSpread.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        EmitterProperties.this.keyTyped(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    getContentPane().add(sizeSpread, gridBagConstraints);

    sizeLabel.setText("Particle size:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 12, 2, 12);
    getContentPane().add(sizeLabel, gridBagConstraints);

    org.jdesktop.layout.GroupLayout fillHLayout = new org.jdesktop.layout.GroupLayout(fillH);
    fillH.setLayout(fillHLayout);
    fillHLayout.setHorizontalGroup(
      fillHLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 19, Short.MAX_VALUE)
    );
    fillHLayout.setVerticalGroup(
      fillHLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 72, Short.MAX_VALUE)
    );

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 98;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(fillH, gridBagConstraints);

    org.jdesktop.layout.GroupLayout fillVLayout = new org.jdesktop.layout.GroupLayout(fillV);
    fillV.setLayout(fillVLayout);
    fillVLayout.setHorizontalGroup(
      fillVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 430, Short.MAX_VALUE)
    );
    fillVLayout.setVerticalGroup(
      fillVLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(0, 20, Short.MAX_VALUE)
    );

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 99;
    gridBagConstraints.gridwidth = 4;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weighty = 1.0;
    getContentPane().add(fillV, gridBagConstraints);

    rateLabel.setText("Rate:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 12, 2, 12);
    getContentPane().add(rateLabel, gridBagConstraints);

    lifetimeLabel.setText("Lifetime:");
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    gridBagConstraints.insets = new java.awt.Insets(2, 12, 2, 12);
    getContentPane().add(lifetimeLabel, gridBagConstraints);

    rate.setMax(1000.0);
    rate.setMinimumSize(new java.awt.Dimension(200, 20));
    rate.setStep(0.5);
    rate.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        mouseDrag(evt);
      }
    });
    rate.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        EmitterProperties.this.keyTyped(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    getContentPane().add(rate, gridBagConstraints);

    rateSpread.setMax(500.0);
    rateSpread.setMinimumSize(new java.awt.Dimension(100, 20));
    rateSpread.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        mouseDrag(evt);
      }
    });
    rateSpread.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        EmitterProperties.this.keyTyped(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    getContentPane().add(rateSpread, gridBagConstraints);

    lifetime.setMaximum(100000);
    lifetime.setMinimumSize(new java.awt.Dimension(200, 20));
    lifetime.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        mouseDrag(evt);
      }
    });
    lifetime.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        EmitterProperties.this.keyTyped(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    getContentPane().add(lifetime, gridBagConstraints);

    lifetimeSpread.setMaximum(50000);
    lifetimeSpread.setMinimumSize(new java.awt.Dimension(100, 20));
    lifetimeSpread.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
      public void mouseDragged(java.awt.event.MouseEvent evt) {
        mouseDrag(evt);
      }
    });
    lifetimeSpread.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyTyped(java.awt.event.KeyEvent evt) {
        EmitterProperties.this.keyTyped(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
    getContentPane().add(lifetimeSpread, gridBagConstraints);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void mouseDrag(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mouseDrag
    setValues();
  }//GEN-LAST:event_mouseDrag

  private void keyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_keyTyped
    setValues();
  }//GEN-LAST:event_keyTyped
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel fillH;
  private javax.swing.JPanel fillV;
  private javax.swing.JSlider lifetime;
  private javax.swing.JLabel lifetimeLabel;
  private javax.swing.JSlider lifetimeSpread;
  private ch.usi.inf.sape.zlatepiesky.gui.JFloatSlider rate;
  private javax.swing.JLabel rateLabel;
  private ch.usi.inf.sape.zlatepiesky.gui.JFloatSlider rateSpread;
  private ch.usi.inf.sape.zlatepiesky.gui.JFloatSlider size;
  private javax.swing.JLabel sizeLabel;
  private ch.usi.inf.sape.zlatepiesky.gui.JFloatSlider sizeSpread;
  // End of variables declaration//GEN-END:variables
}
