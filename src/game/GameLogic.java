/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import classes.AlreadyDoneThatException;
import java.awt.Point;
import classes.Character;

/**
 *
 * @author Erik
 */
public class GameLogic {
    
    public static final int path = 0;
    public static final int player1 = 1;
    public static final int player2 = 2;
    public static final int wall = 3;
    
    private int playerTurn;
    private int playerNotTurn;
    private int[][] map;
    private boolean firstClick = true;
    private boolean leftClick = false;
    private int clickedBefore;
    private Point coordClickedBefore;
    
    private Character[] chars;
    
    private GamePanel gp;
    private ControlPanel cp;
    
    public GameLogic(int[][] map, GamePanel gp, ControlPanel cp) {
        this.map = map;
        this.gp = gp;
        this.cp = cp;
        playerTurn = 1;
        playerNotTurn = 2;
        
        chars = new Character[4];
        chars[0] = new Character(2, 0, 100, new Point(0, 0), 20, 2, 2);
        chars[1] = new Character(2, 1, 100, new Point(0, 1), 20, 2, 2);
        chars[2] = new Character(1, 0, 100, new Point(7, 6), 20, 2, 2);
        chars[3] = new Character(1, 1, 100, new Point(7, 7), 20, 2, 2);
    }
    
    //leftClick to attack, rightClick to move
    public int click(int column, int row, int button) {
        int ret = 0;
        if (button == 1 || button == 3) {
            
            //<editor-fold desc="firstClick">
            if (firstClick) {
                leftClick = button==1;
                
                clickedBefore = map[row][column];
                if (clickedBefore != playerTurn) {
                    cp.showMessage("You can not do that!", 3);
                }
                else {
                    firstClick = false;
                    coordClickedBefore = new Point(row, column);
                    if(!leftClick)
                        gp.showMovementRange(coordClickedBefore, findChar(coordClickedBefore).getMovementRange());
                }
            }
            //</editor-fold>
            //<editor-fold desc="secondClick">
            else {
                if (leftClick) {
                    Point clickedNow = new Point(row, column);
                    Character actor = findChar(coordClickedBefore);
                    if (map[row][column] == playerNotTurn) {
                        Character victim = findChar(clickedNow);
                        try {
                            if (victim.hit(actor.attack(distance(coordClickedBefore, clickedNow)))) {map[row][column] = 0; gp.repaint();}
                            cp.updateHealth(victim);
                            firstClick = true;
                        } catch (AlreadyDoneThatException ex) {
                            cp.showMessage("This character has already attacked this turn", 3);
                            firstClick = true;
                        }
                    }
                    
                    
                }
                else {
                    Character actor = findChar(coordClickedBefore);
                    if (map[row][column] == GameLogic.path) {
                        try {
                            
                            if(actor.move(new Point(row, column))) {
                                map[coordClickedBefore.x][coordClickedBefore.y] = GameLogic.path;
                                map[row][column] = playerTurn;
                                firstClick = true;
                                ret = 1;
                                gp.stopShowingMovementRange();
                            }
                            
                        } catch (AlreadyDoneThatException ex) {
                            gp.stopShowingMovementRange();
                            cp.showMessage("This character has already moved this turn", 3);
                            firstClick = true;
                        }
                    }
                }
            }
            //</editor-fold>
        }
        return ret;
    }
    
    public static int distance(Point p1, Point p2) {
        return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
    }
    
    public Character findChar(Point p) {
        Character ret = null;
        for (Character c : this.chars) {
            if (c.getPosition().equals(p)) {
                ret = c;
            }
        }
        return ret;
    }
    
    public void nextTurn() {
        int help = playerTurn;
        playerTurn = playerNotTurn;
        playerNotTurn = help;
        
        for (Character c : chars) {
            c.reset();
        }
    }
    
}

