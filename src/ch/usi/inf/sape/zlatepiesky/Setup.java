package ch.usi.inf.sape.zlatepiesky;

import ch.usi.inf.sape.zlatepiesky.model.Emitter;
import ch.usi.inf.sape.zlatepiesky.model.Wall;
import ch.usi.inf.sape.zlatepiesky.model.forces.BlackHole;
import ch.usi.inf.sape.zlatepiesky.model.forces.Gravity;
import javax.vecmath.Vector2d;

public class Setup {

  public static void setup(final World world) {
    final Emitter e = new Emitter();
    e.setPosition(new Vector2d(10, 10));
    e.setInitialSpeed(new Vector2d(1.5, -1.5));
    e.setRate(100);
    e.setParticleSize(3);
    world.getEmitters().add(e);

    final BlackHole bh1 = new BlackHole();
    bh1.setStrength(500);
    bh1.setSchwarzschildRadius(30);
    bh1.setPosition(new Vector2d(0, 100));
    world.getForces().add(bh1);

    final BlackHole bh2 = new BlackHole();
    bh2.setStrength(400);
    bh2.setSchwarzschildRadius(20);
    bh2.setPosition(new Vector2d(100, 100));
    world.getForces().add(bh2);

    final Gravity gr = new Gravity();
    gr.setDirection(new Vector2d(0, 1));
    gr.setStrength(0.001);
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
