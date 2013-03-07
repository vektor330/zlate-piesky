package ch.usi.inf.sape.zlatepiesky;

import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import ch.usi.inf.sape.zlatepiesky.model.Force;
import ch.usi.inf.sape.zlatepiesky.model.Particle;
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

  public void simulationStep() {
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
      for (long i = 0; i < emitter.getRate() / stepsPerSecond; i++) {
        final Particle p = new Particle();
        p.setPosition(emitter.getPosition());
        p.setBirthplace(emitter);
        p.setCreated(now);
        p.setSpeed(emitter.getInitialSpeed());
        p.setWeight(emitter.getParticleWeight());
        p.setSize(emitter.getParticleSize());
        getParticles().add(p);
      }
    }
    // apply all forces & move all particles
    for (final Particle particle : getParticles()) {
      final Vector2d sum = new Vector2d();
      for (final Force force : getForces()) {
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
