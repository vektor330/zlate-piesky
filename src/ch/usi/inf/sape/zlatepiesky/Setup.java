package ch.usi.inf.sape.zlatepiesky;

import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import ch.usi.inf.sape.zlatepiesky.model.Wall;
import ch.usi.inf.sape.zlatepiesky.model.forces.BlackHole;
import ch.usi.inf.sape.zlatepiesky.model.forces.Gravity;
import javax.vecmath.Vector2d;

// TODO get rid of this
public class Setup {

  public static void setup(final World world) {
    world.setSimulationStep(10);
    world.setAirResistance(0.0001);

    final Emitter e = new Emitter();
    e.setPosition(new Vector2d(10, 10));
    e.setInitialSpeed(new Vector2d(1.5, -1.5));
    e.setRate(100);
    e.setRateSpread(50);
    e.setParticleSize(3);
    e.setParticleWeight(1);
    e.setParticleScaleSpread(2);
    world.getEmitters().add(e);

    final BlackHole bh1 = new BlackHole();
    bh1.setStrength(500);
    bh1.setSchwarzschildRadius(40);
    bh1.setPosition(new Vector2d(0, 100));
    world.getForces().add(bh1);

    final BlackHole bh2 = new BlackHole();
    bh2.setStrength(400);
    bh2.setSchwarzschildRadius(20);
    bh2.setPosition(new Vector2d(100, 100));
    world.getForces().add(bh2);

    final BlackHole bh3 = new BlackHole();
    bh3.setStrength(400);
    bh3.setSchwarzschildRadius(40);
    bh3.setPosition(new Vector2d(1000, 0));
    world.getForces().add(bh3);

    final BlackHole bh4 = new BlackHole();
    bh4.setStrength(500);
    bh4.setSchwarzschildRadius(40);
    bh4.setPosition(new Vector2d(-1000, 1000));
    world.getForces().add(bh4);

    final Gravity gr = new Gravity();
    gr.setDirection(new Vector2d(0, 1));
    gr.setStrength(0.005);
    world.getForces().add(gr);

    final Wall w = new Wall();
    w.setBegin(new Vector2d(300, 50));
    w.setEnd(new Vector2d(350, -50));
    world.getWalls().add(w);

    final Wall ground = new Wall();
    ground.setBegin(new Vector2d(-100000, 1000));
    ground.setEnd(new Vector2d(100000, 1000));
    world.getWalls().add(ground);
  }
}
