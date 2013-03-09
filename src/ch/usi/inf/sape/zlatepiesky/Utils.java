package ch.usi.inf.sape.zlatepiesky;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Utils {

  private Utils() {
  }
  private static final Logger LOG = Logger.getLogger("utils");

  public static void save(final File file, final World world) {
    try {
      OutputStream stream = new FileOutputStream(file);
      OutputStream buffer = new BufferedOutputStream(stream);
      try (ObjectOutput output = new ObjectOutputStream(buffer)) {
        output.writeObject(world);
      }
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, "", ex);
    }
  }

  public static World load(final File file) {
    try {
      InputStream stream = new FileInputStream(file);
      InputStream buffer = new BufferedInputStream(stream);
      try (ObjectInput input = new ObjectInputStream(buffer)) {
        final World ret = (World) input.readObject();
        return ret;
      }
    } catch (ClassNotFoundException | IOException ex) {
      LOG.log(Level.SEVERE, "", ex);
    }
    return null;
  }
}
