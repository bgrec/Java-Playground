/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wormgame.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import wormgame.domain.Piece;
import wormgame.game.WormGame;

/**
 *
 * @author bgrec
 */
public class DrawingBoard extends JPanel implements Updatable{

    private WormGame wormGame;
    private int pieceLenght;

    public DrawingBoard(WormGame wormGame, int pieceLenght) {
        super.setBackground(Color.GRAY);
        this.wormGame = wormGame;
        this.pieceLenght = pieceLenght;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Piece p : wormGame.getWorm().getPieces()) {
            graphics.setColor(Color.BLACK);
            graphics.fill3DRect(p.getX() * pieceLenght, p.getY() * pieceLenght, pieceLenght, pieceLenght, false);
        }
        graphics.setColor(Color.RED);
        graphics.fillOval(wormGame.getApple().getX() * pieceLenght,
                wormGame.getApple().getY() * pieceLenght,
                pieceLenght, pieceLenght);
    }

    @Override
    public void update() {
        super.repaint();
     }

}
