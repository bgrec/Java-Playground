package wormgame.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;
import wormgame.Direction;
import wormgame.domain.Apple;
import wormgame.domain.Worm;
import wormgame.gui.Updatable;

public class WormGame extends Timer implements ActionListener {

    private int width;
    private int height;
    private boolean continues;
    private Updatable updatable;
    private Worm worm;
    private Apple apple;

    public WormGame(int width, int height) {
        super(1000, null);

        this.width = width;
        this.height = height;
        this.continues = true;

        addActionListener(this);
        setInitialDelay(2000);

        this.worm = new Worm(width / 2, height / 2, Direction.DOWN);

        createApple();

    }

    private void createApple() {
        Random random = new Random();
        this.apple = new Apple(random.nextInt(width), random.nextInt(height));
        while (worm.runsInto(apple)) {
            this.apple = new Apple(random.nextInt(width), random.nextInt(height));
        }
    }

    public boolean continues() {
        return continues;
    }

    public void setUpdatable(Updatable updatable) {
        this.updatable = updatable;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (!continues) {
            return;
        }
        this.worm.move();
        if (worm.runsInto(apple)) {
            worm.grow();
            createApple();
        }
        if (worm.runsIntoItself() || wormHitsBorder()) {
            continues = false;
        }
        updatable.update();
        super.setDelay(1000 / worm.getLength());
    }

    public Worm getWorm() {
        return this.worm;
    }

    public void setWorm(Worm worm) {
        this.worm = worm;
    }

    public Apple getApple() {
        return apple;
    }

    public void setApple(Apple apple) {
        this.apple = apple;
    }
    public boolean wormHitsBorder() {
        if (worm.getPieces().get(worm.getLength() - 1).getX() == 0
                || worm.getPieces().get(worm.getLength() - 1).getX() == width
                || worm.getPieces().get(worm.getLength() - 1).getY() == 0
                || worm.getPieces().get(worm.getLength() - 1).getY() == height)
            return true;
        return false;
    }
}
