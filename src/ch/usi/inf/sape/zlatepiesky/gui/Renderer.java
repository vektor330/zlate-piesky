package ch.usi.inf.sape.zlatepiesky.gui;

import ch.usi.inf.sape.zlatepiesky.World;
import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import ch.usi.inf.sape.zlatepiesky.model.Force;
import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.forces.BlackHole;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Renderer {

  // TODO matej Move rendering to classes themselves
  public static void render(World world, Graphics2D g) {
    synchronized (world) {
      for (final Emitter e : world.getEmitters()) {
        final int x = (int) Math.round(e.getPosition().x);
        final int y = (int) Math.round(e.getPosition().y);
        g.setPaint(Color.RED);
        g.fillRect(x - 5, y - 5, x + 5, y + 5);
      }
      for (final Particle particle : world.getParticles()) {
        final int x = (int) Math.round(particle.getPosition().x);
        final int y = (int) Math.round(particle.getPosition().y);
        final int x2 = (int) Math.round(particle.getPosition().x + particle.getSpeed().x);
        final int y2 = (int) Math.round(particle.getPosition().y + particle.getSpeed().y);
        final Color color;
        if (particle.getColor() != null) {
          color = particle.getColor();
        } else {
          float blue = Math.max(0, Math.min(1, (float) Math.log(particle.getSpeed().lengthSquared())));
          color = new Color(1 - blue, 0.2f, blue);
        }
        g.setPaint(color);
        g.setStroke(new BasicStroke((int) Math.round(particle.getSize())));
        g.drawLine(x, y, x2, y2);
      }
      for (final Force force : world.getForces()) {
        switch (force.getType()) {
          case BLACK_HOLE:
            final BlackHole bh = (BlackHole) force;
            final int r = (int) Math.round(bh.getSchwarzschildRadius());
            final int d = r * 2;
            final int x = (int) Math.round(bh.getPosition().x);
            final int y = (int) Math.round(bh.getPosition().y);

            g.setPaint(Color.BLACK);
            g.fillOval(x - r, y - r, d, d);
            g.setPaint(new Color(1f, 0f, 0f));
            g.setStroke(new BasicStroke(0.1f));
            g.drawOval(x - r, y - r, d, d);
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
