/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import gameoflife.GameOfLifeBoard;
import java.util.Random;

/**
 *
 * @author bgrec
 */
public class PersonalBoard extends GameOfLifeBoard {

    public PersonalBoard(int width, int height) {
        super(width, height);
    }

    @Override
    public void initiateRandomCells(double d) {
        Random random = new Random();
        for (int i = 0; i < super.getWidth(); i++) {
            for (int j = 0; j < super.getHeight(); j++) {
                double live;
                live = d + random.nextDouble();
                if (live < 1) {
                    turnToDead(i, j);
                } else {
                    turnToLiving(i, j);
                }
                //System.out.println(i + " " + j);
            }
        }
    }

    @Override
    public boolean isAlive(int x, int y) {
        if (x >= 0 && x < super.getWidth() && y >= 0 && y < super.getHeight()) {
            return getBoard()[x][y];
        }
        return false;
    }

    @Override
    public void turnToLiving(int x, int y) {
        if (x >= 0 && x < super.getWidth() && y >= 0 && y < super.getHeight()) {
            getBoard()[x][y] = true;
        }
    }

    @Override
    public void turnToDead(int x, int y) {
        if (x >= 0 && x < super.getWidth() && y >= 0 && y < super.getHeight()) {
            getBoard()[x][y] = false;
        }
    }

    @Override
    public int getNumberOfLivingNeighbours(int x, int y) {
        int livingNeighbours;
        livingNeighbours = 0;

        for (int i = y - 1; i <= y + 2; i++) {
            for (int j = x - 1; j <= x + 2; j++) {
                if ((i >= 0 && i  <= super.getHeight())
                        && (j >= 0 && j <= super.getWidth())){
                    if (isAlive(i, j)) {
                        livingNeighbours++;
                    }
                    //System.out.println(i + " " + j + isAlive(i, j));
                    
                }
            }
        }
        if (isAlive(x,y)) {
            livingNeighbours--;
        }
        return livingNeighbours;
    }

    @Override
    public void manageCell(int x, int y, int livingNeighbours
    ) {
        if (livingNeighbours < 2 || livingNeighbours > 3) {
            turnToDead(x, y);
        } else if (livingNeighbours == 3) {
            turnToLiving(x, y);
        }
    }

}
