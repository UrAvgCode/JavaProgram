package acm.karel;

import acm.karel.components.KarelErrorDialog;
import acm.karel.components.KarelWorld;
import acm.karel.controlls.KarelControls;
import acm.program.Program;

import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public abstract class Karel extends Program {
    public static final int INFINITE = Integer.MAX_VALUE;
    private final KarelWorld world;

    protected Karel() {
        super();
        redirectException();
        File defaultWorld = new File("src/" + getClass().getPackageName().replace('.', '/') + "/worlds/" + getClass().getSimpleName() + ".w");

        new KarelErrorDialog(this);

        world = new KarelWorld(defaultWorld);
        world.setBorder(new EmptyBorder(10, 10, 10, 10));
        world.safeWorld(getClass().getSimpleName() + ".w");
        add(world, BorderLayout.CENTER);

        KarelControls controls = new KarelControls(this, world);
        add(controls, WEST);
        setVisible(true);
    }

    private void redirectException() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) {
            }

            @Override
            public void write(byte[] b, int off, int len) {
                System.out.print(new String(b, off, len));
            }
        };
        PrintStream printStream = new PrintStream(out, true, StandardCharsets.UTF_8);
        System.setErr(printStream);
    }

    @Override
    public void start() {
    }

    protected final KarelWorld getWorld() {
        return world;
    }

    public final void move() {
        world.move();
    }

    public final void turnLeft() {
        world.turnLeft();
    }

    public final void pickBeeper() {
        world.pickBeeper();
    }

    public final void putBeeper() {
        world.putBeeper();
    }

    public final boolean frontIsClear() {
        return world.frontIsClear();
    }

    public final boolean frontIsBlocked() {
        return !frontIsClear();
    }

    public final boolean leftIsClear() {
        return world.leftIsClear();
    }

    public final boolean leftIsBlocked() {
        return !leftIsClear();
    }

    public final boolean rightIsClear() {
        return world.rightIsClear();
    }

    public final boolean rightIsBlocked() {
        return !rightIsClear();
    }

    public final boolean beepersPresent() {
        return world.beepersPresent();
    }

    public final boolean noBeepersPresent() {
        return !beepersPresent();
    }

    public final boolean beepersInBag() {
        return world.beepersInBag();
    }

    public final boolean noBeepersInBag() {
        return !beepersInBag();
    }

    public final boolean facingNorth() {
        return world.facingDirection(Direction.NORTH);
    }

    public final boolean notFacingNorth() {
        return !facingNorth();
    }

    public final boolean facingSouth() {
        return world.facingDirection(Direction.SOUTH);
    }

    public final boolean notFacingSouth() {
        return !facingSouth();
    }

    public final boolean facingEast() {
        return world.facingDirection(Direction.EAST);
    }

    public final boolean notFacingEast() {
        return !facingEast();
    }

    public final boolean facingWest() {
        return world.facingDirection(Direction.WEST);
    }

    public final boolean notFacingWest() {
        return !facingWest();
    }
}
