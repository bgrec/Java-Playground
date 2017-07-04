/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.domain;

import java.util.ArrayList;
import java.util.List;
import wormgame.Direction;

/**
 *
 * @author bgrec
 */
public class Worm {

    private int x;
    private int y;
    private Direction direction;
    private List<Piece> worm;
    private boolean grow;

    public Worm(int originalX, int originalY, Direction originalDirection) {
        this.x = originalX;
        this.y = originalY;
        this.direction = originalDirection;
        this.worm = new ArrayList<Piece>();
        this.worm.add(new Piece(originalX, originalY));
        this.grow = false;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public int getLength() {
        return this.worm.size();
    }
    public void updateCoord(){
        this.x = worm.get(worm.size() - 1).getX();
        this.y = worm.get(worm.size() - 1).getY();
    }

    public void move() {
        if (direction == direction.UP) {
            worm.add(new Piece(x, y - 1));
            updateCoord();
        } else if (direction == direction.DOWN) {
            worm.add(new Piece(x, y + 1));
            updateCoord();
        } else if (direction == direction.LEFT) {
            worm.add(new Piece(x - 1, y));
            updateCoord();
        } else if (direction == direction.RIGHT) {
            worm.add(new Piece(x + 1, y));
            updateCoord();
        }
        if (worm.size() > 3 && grow == false) {
            worm.remove(0);
        }
        this.grow = false;
    }

    public void grow() {
        this.grow = true;
     }

    public boolean runsInto(Piece piece) {
        return worm.get(worm.size() - 1).runsInto(piece) || worm.contains(piece);
    }

    public boolean runsIntoItself() {
        Piece head = worm.get(worm.size() - 1);
        boolean itself = false;
        for (Piece p : worm) {
            if (head == p) {
                continue;
            }
            if (head.runsInto(p)) {
                itself = true;
            }
        }
        return itself;
    }

    public List<Piece> getPieces() {
        return this.worm;
    }
}
