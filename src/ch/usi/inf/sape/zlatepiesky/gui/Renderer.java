package ch.usi.inf.sape.zlatepiesky.gui;

import ch.usi.inf.sape.zlatepiesky.World;
import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.Wall;
import ch.usi.inf.sape.zlatepiesky.model.forces.BlackHole;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Force;
import java.awt.Graphics2D;

public class Renderer {

  public static void render(World world, Graphics2D g) {
    synchronized (world) {
      for (final Emitter e : world.getEmitters()) {
        e.render(g);
      }
      for (final Wall w : world.getWalls()) {
        w.render(g);
      }
      for (final Particle particle : world.getParticles()) {
        particle.render(g);
      }
      for (final Force force : world.getForces()) {
        switch (force.getType()) {
          case BLACK_HOLE:
            final BlackHole bh = (BlackHole) force;
            bh.render(g);
            break;
          case GRAVITY:
            break;
          case WIND:
            break;
        }
      }
    }
  }
}
