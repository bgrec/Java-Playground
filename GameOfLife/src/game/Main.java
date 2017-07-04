package game;

import gameoflife.Simulator;

public class Main {

    public static void main(String[] args) {
 PersonalBoard board = new PersonalBoard(4, 5);
        board.initiateRandomCells(1.0);

        GameOfLifeTester tester = new GameOfLifeTester(board);
        tester.play();;
     }
}/*00 01 
   10 11 
   20 21*/