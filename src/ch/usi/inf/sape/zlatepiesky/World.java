package ch.usi.inf.sape.zlatepiesky;

import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import ch.usi.inf.sape.zlatepiesky.model.Force;
import ch.usi.inf.sape.zlatepiesky.model.ForceType;
import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.forces.BlackHole;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.vecmath.Vector2d;

public class World {

  private List<Force> forces = new LinkedList<>();
  private List<Emitter> emitters = new LinkedList<>();
  private List<Particle> particles = new LinkedList<>();
  /**
   * Simulation step in milliseconds.
   */
  private long simulationStep;
  private double EPSILON = 0.00001;

  public synchronized void simulationStep() {
    final long stepsPerSecond = 1000 / getSimulationStep();
    final long now = System.currentTimeMillis();
    // kill old particles
    final Iterator<Particle> it = getParticles().iterator();
    while (it.hasNext()) {
      final Particle particle = it.next();
      if (particle.getCreated() + particle.getBirthplace().getLifetime() < now) {
        it.remove();
      }
    }
    // create new particles
    for (final Emitter emitter : getEmitters()) {
      final double create = (double) emitter.getRate() / (double) stepsPerSecond;
      int atLeast = 0;
      if (create < 1) {
        if (Math.random() < create) {
          atLeast = 1;
        }
      }
      for (long i = 0; i < atLeast + emitter.getRate() / stepsPerSecond; i++) {
        final Particle p = new Particle();
        p.setPosition(emitter.getPosition());
        p.setBirthplace(emitter);
        p.setCreated(now);
        final Vector2d speed;
        if (emitter.getSpeedSpread() > EPSILON) {
          speed = new Vector2d(emitter.getInitialSpeed());
          speed.scale(speed.length() + (Math.random() - 0.5) * emitter.getSpeedSpread());
        } else {
          speed = emitter.getInitialSpeed();
        }
        p.setSpeed(speed);
        p.setWeight(emitter.getParticleWeight());
        p.setSize(emitter.getParticleSize());
        particles.add(p);
      }
    }
    // apply all forces & move all particles
    final Iterator<Particle> it2 = particles.iterator();
    while (it2.hasNext()) {
      final Particle particle = it2.next();
      final Vector2d sum = new Vector2d();
      for (final Force force : getForces()) {
        if (force.getType() == ForceType.BLACK_HOLE) {
          final BlackHole blackHole = (BlackHole)force;
          final Vector2d distance = new Vector2d(blackHole.getPosition());
          distance.sub(particle.getPosition());
          if (distance.lengthSquared() < blackHole.getSchwarzschildRadius() * blackHole.getSchwarzschildRadius()) {
            it2.remove();
            break;
          }
        }
        final Vector2d effect = force.getEffect(particle);
        sum.add(effect);
      }
      particle.getSpeed().add(sum);
      particle.getPosition().add(particle.getSpeed());
    }
  }

  public List<Force> getForces() {
    return forces;
  }

  public List<Emitter> getEmitters() {
    return emitters;
  }

  public List<Particle> getParticles() {
    return particles;
  }

  public long getSimulationStep() {
    return simulationStep;
  }

  public void setSimulationStep(long simulationStep) {
    this.simulationStep = simulationStep;
  }
}
