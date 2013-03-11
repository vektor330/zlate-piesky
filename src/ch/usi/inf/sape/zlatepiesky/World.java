package ch.usi.inf.sape.zlatepiesky;

import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import ch.usi.inf.sape.zlatepiesky.model.Particle;
import ch.usi.inf.sape.zlatepiesky.model.Wall;
import ch.usi.inf.sape.zlatepiesky.model.forces.BlackHole;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Force;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.ForceType;
import ch.usi.inf.sape.zlatepiesky.model.interfaces.Selectable;
import ch.usi.inf.sape.zlatepiesky.physics.Intersection;
import ch.usi.inf.sape.zlatepiesky.physics.Mirror;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.vecmath.Vector2d;
import javax.vecmath.Vector4d;

// TODO reimplement physics from scrach
public class World implements Serializable {

  private static final long serialVersionUID = 14245L;
  private List<Force> forces = new LinkedList<>();
  private List<Emitter> emitters = new LinkedList<>();
  private List<Particle> particles = new LinkedList<>();
  private List<Wall> walls = new LinkedList<>();
  /**
   * Simulation step in milliseconds.
   */
  private long simulationStep;
  private double timeSpeed = 1;
  private long now = 0;
  private double airResistance = 0;
  private Selectable selected = null;

  public synchronized void simulationStep() {
    final long stepsPerSecond = 1000 / getSimulationStep();
    setNow(getNow() + getSimulationStep());
    killOldParticles();
    createParticles(stepsPerSecond);
    moveParticles();
  }

  private void killOldParticles() {
    final Iterator<Particle> it = getParticles().iterator();
    while (it.hasNext()) {
      final Particle particle = it.next();
      if (Math.abs(particle.getPosition().x) > Constants.SIZE
              || Math.abs(particle.getPosition().y) > Constants.SIZE) {
        it.remove();
        continue;
      }
      if (particle.getDeath() < getNow()) {
        it.remove();
        continue;
      }
    }
  }

  private void createParticles(final long stepsPerSecond) {
    for (final Emitter emitter : getEmitters()) {
      final double create = (emitter.getRate() + (Math.random() - 0.5) * emitter.getRateSpread()) / (double) stepsPerSecond;
      int atLeast = 0;
      if (create < 1) {
        if (Math.random() < create) {
          atLeast = 1;
        }
      }
      for (int i = 0; i < atLeast + create; i++) {
        final Particle p = new Particle();
        p.setPosition(emitter.getPosition());
        p.setDeath(getNow() + (long) (emitter.getLifetime() + (Math.random() - 0.5) * emitter.getLifetimeSpread()));
        final Vector2d speed;
        if (emitter.getSpeedSpread() > Constants.EPSILON) {
          speed = new Vector2d(emitter.getInitialSpeed());
          speed.scale(speed.length() + (Math.random() - 0.5) * emitter.getSpeedSpread());
        } else {
          speed = emitter.getInitialSpeed();
        }
        p.setSpeed(speed);
        final double scale = Math.random() - 0.5;
        p.setWeight(Math.max(Constants.EPSILON, emitter.getParticleWeight() + scale * emitter.getParticleWeight() * emitter.getParticleScaleSpread()));
        p.setSize(Math.max(Constants.EPSILON, emitter.getParticleSize() + scale * emitter.getParticleSize() * emitter.getParticleScaleSpread()));
        particles.add(p);
      }
    }
  }

  private void moveParticles() {
    final Iterator<Particle> it2 = particles.iterator();
    final List<Particle> blinks = new LinkedList<>();
    while (it2.hasNext()) {
      final Particle particle = it2.next();
      if (particle.isImmovable()) {
        continue;
      }
      // sum up all the forces acting on the particle
      final Vector2d forceSum = new Vector2d();
      for (final Force force : forces) {
        if (force.getType() == ForceType.BLACK_HOLE) {
          final BlackHole blackHole = (BlackHole) force;
          final Vector2d distance = new Vector2d(blackHole.getPosition());
          distance.sub(particle.getPosition());
          if (distance.length() < blackHole.getSchwarzschildRadius()) {
            it2.remove();
            blinks.add(createBlink(particle));
            break;
          }
        }
        final Vector2d effect = force.getEffect(particle);
        forceSum.add(effect);
      }
      // air resistance
      final double drag = particle.getSpeed().lengthSquared() * getAirResistance() * particle.getSize() * particle.getSize();
      final Vector2d dragForce = new Vector2d(particle.getSpeed());
      dragForce.negate();
      dragForce.normalize();
      dragForce.scale(drag);
      forceSum.add(dragForce);

      // a = F/m
      forceSum.scale(1 / (particle.getWeight() * timeSpeed));

      // v += a
      particle.getSpeed().add(forceSum);

      final Vector2d newPosition = new Vector2d(particle.getPosition());
      final Vector2d speed = particle.getSpeed();
      speed.scale(timeSpeed);
      newPosition.add(speed);

      boolean wasCollision = false;
      for (final Wall wall : getWalls()) {
        if (particleCollides(particle.getPosition(), newPosition, wall)) {
          final Vector4d mirrored = Mirror.mirror(wall.getBegin(), wall.getEnd(), particle.getPosition(), newPosition);
          Vector2d newPosM = new Vector2d(mirrored.z, mirrored.w);
          particle.setPosition(newPosM);
          newPosM.sub(new Vector2d(mirrored.x, mirrored.y));
          particle.setSpeed(newPosM);
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

  public Selectable getUnder(final Point2D current) {
    for (final Force f : forces) {
      if (f instanceof Selectable) {
        final Selectable item = (Selectable) f;
        if (item.getShape().contains(current)) {
          return item;
        }
      }
    }
    for (final Emitter e : emitters) {
      if (e instanceof Selectable) {
        final Selectable item = (Selectable) e;
        if (item.getShape().contains(current)) {
          return item;
        }
      }
    }
    for (final Wall w : walls) {
      if (w instanceof Selectable) {
        final Selectable item = (Selectable) w;
        if (item.getShape().contains(current)) {
          return item;
        }
      }
    }
    return null;
  }

  public void delete(Selectable selected) {
    final Iterator<Force> itf = forces.iterator();
    while (itf.hasNext()) {
      final Force f = itf.next();
      if (f == selected) {
        itf.remove();
        return;
      }
    }
    final Iterator<Emitter> ite = emitters.iterator();
    while (ite.hasNext()) {
      final Emitter e = ite.next();
      if (e == selected) {
        ite.remove();
        return;
      }
    }
    final Iterator<Wall> itw = walls.iterator();
    while (itw.hasNext()) {
      final Wall w = itw.next();
      if (w == selected) {
        itw.remove();
        return;
      }
    }
  }

  // --- GET/SET ---------------------------------------------------------------
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

  public double getAirResistance() {
    return airResistance;
  }

  public void setAirResistance(double airResistance) {
    this.airResistance = airResistance;
  }

  public Selectable getSelected() {
    return selected;
  }

  public void setSelected(Selectable selected) {
    final Selectable old = this.selected;

    if (this.selected == selected) {
      this.selected = null;
    } else {
      this.selected = selected;
      if (this.selected != null) {
        this.selected.setSelected(true);
      }
    }

    if (old != null) {
      old.setSelected(false);
    }
  }

  public double getTimeSpeed() {
    return timeSpeed;
  }

  public void setTimeSpeed(double timeSpeed) {
    this.timeSpeed = timeSpeed;
  }

  public long getNow() {
    return now;
  }

  public void setNow(long now) {
    this.now = now;
  }

  private Particle createBlink(final Particle particle) {
    final Particle ret = new Particle();
    ret.setPosition(particle.getPosition());
    ret.setSpeed(new Vector2d());
    ret.setDeath(getNow() + 20);
    ret.setColor(Color.WHITE);
    ret.setImmovable(true);
    ret.setSize(3);
    return ret;
  }
}
