package ch.usi.inf.sape.zlatepiesky;

import ch.usi.inf.sape.zlatepiesky.gui.MainWindow;
import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

// TODO add handles:
// - edit line ends
// - edit schwarzschild
// TODO gravity and wind should be visible
// TODO add editor windows
// TODO how do I get the right slider event?
public class ZlatePiesky {

  public static void main(String args[]) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
      Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
    }

    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        new MainWindow().setVisible(true);
      }
    });
  }
}
