package ch.usi.inf.sape.zlatepiesky;

import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.Wall;
import ch.usi.inf.sape.zlatepiesky.model.forces.BlackHole;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Force;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.ForceType;
import ch.usi.inf.sape.zlatepiesky.physics.Intersection;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.vecmath.Vector2d;

// TODO add air resistance
public class World {

  private List<Force> forces = new LinkedList<>();
  private List<Emitter> emitters = new LinkedList<>();
  private List<Particle> particles = new LinkedList<>();
  private List<Wall> walls = new LinkedList<>();
  /**
   * Simulation step in milliseconds.
   */
  private long simulationStep;
  private double EPSILON = 0.00001;

  public synchronized void simulationStep() {
    final long stepsPerSecond = 1000 / getSimulationStep();
    final long now = System.currentTimeMillis();
    killOldParticles(now);
    createParticles(stepsPerSecond, now);
    moveParticles(now);
  }

  private void killOldParticles(final long now) {
    final Iterator<Particle> it = getParticles().iterator();
    while (it.hasNext()) {
      final Particle particle = it.next();
      if (particle.getCreated() + particle.getBirthplace().getLifetime() < now) {
        it.remove();
      }
    }
  }

  private void createParticles(final long stepsPerSecond, final long now) {
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
  }

  private void moveParticles(final long now) {
    final Iterator<Particle> it2 = particles.iterator();
    final List<Particle> blinks = new LinkedList<>();
    while (it2.hasNext()) {
      final Particle particle = it2.next();
      if (particle.isImmovable()) {
        continue;
      }
      final Vector2d sum = new Vector2d();
      for (final Force force : forces) {
        if (force.getType() == ForceType.BLACK_HOLE) {
          final BlackHole blackHole = (BlackHole) force;
          final Vector2d distance = new Vector2d(blackHole.getPosition());
          distance.sub(particle.getPosition());
          if (distance.length() < blackHole.getSchwarzschildRadius()) {
            it2.remove();
            final Particle blink = new Particle();
            blink.setPosition(particle.getPosition());
            blink.setSpeed(new Vector2d());
            blink.setBirthplace(particle.getBirthplace());
            blink.setCreated(now - particle.getBirthplace().getLifetime() + 20); // TODO I want a property "time left"
            blink.setColor(Color.WHITE);
            blink.setImmovable(true);
            blink.setSize(3);
            blinks.add(blink);
            break;
          }
        }
        final Vector2d effect = force.getEffect(particle);
        sum.add(effect);
      }
      particle.getSpeed().add(sum);
      final Vector2d newPosition = new Vector2d(particle.getPosition());
      newPosition.add(particle.getSpeed());
      boolean wasCollision = false;
      for (final Wall wall : getWalls()) {
        if (particleCollides(particle.getPosition(), newPosition, wall)) {
          //final Vector2d collisionPoint = null;
          //final Vector2d normal = wall.getNormal();
          //final Vector2d newSpeed = null;
          particle.getSpeed().negate();
          particle.getPosition().add(particle.getSpeed());
          wasCollision = true;
          break;
        }
      }
      if (!wasCollision) {
        particle.setPosition(newPosition);
      }
    }
    particles.addAll(blinks);
  }

  private boolean particleCollides(Vector2d position, Vector2d newPosition, Wall wall) {
    return Intersection.linesIntersect(position.x, position.y, newPosition.x, newPosition.y,
            wall.getBegin().x, wall.getBegin().y, wall.getEnd().x, wall.getEnd().y);
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

  public List<Wall> getWalls() {
    return walls;
  }

  public void setWalls(List<Wall> walls) {
    this.walls = walls;
  }
}
