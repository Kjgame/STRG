/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import classes.AlreadyDoneThatException;
import java.awt.Point;
import classes.Character;
import java.util.ArrayList;

/**
 *
 * @author Erik
 */
public class GameLogic {
    
    public static final int path = 0;
    public static final int player1 = 1;
    public static final int player2 = 2;
    public static final int wall = 3;
    
    public static final int baseDamage = 20;
    public static final int damageFalloff = 2;
    public static final int movementRange = 2;
    
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
        playerTurn = player1;
        playerNotTurn = player2;
        
        ArrayList<Character> charas = new ArrayList();
        int p1chars = 0;
        int p2chars = 0;
        
        for (int c = 0; c < map.length; c++) {
            for (int i = 0; i < map[c].length; i++) {
                if (map[c][i] == 1) {
                    charas.add(new Character(player1, p1chars, 100, new Point(c, i), baseDamage, damageFalloff, movementRange));
                    p1chars++;
                }
                if (map[c][i] == 2) {
                    charas.add(new Character(player2, p2chars, 100, new Point(c, i), baseDamage, damageFalloff, movementRange));
                    p1chars++;
                }
            }
        }
        
        chars = new Character[charas.size()];
        chars = charas.toArray(chars);
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
                    else {
                        ArrayList<Point> points = new ArrayList();
                        for (Character c : chars) {
                            if (c.getPlayer()==playerNotTurn && !c.isDead() && !collision(coordClickedBefore, c.getPosition())) {
                                points.add(c.getPosition());
                            }
                        }
                        points.trimToSize();
                        if (points.isEmpty()) {
                            cp.showMessage("No target attackable", 3);
                            firstClick = true;
                        }
                        else {
                            Point[] p = new Point[points.size()];
                            p = points.toArray(p);
                            gp.showAttackTargets(p);
                        }
                    }
                        
                }
            }
            //</editor-fold>
            //<editor-fold desc="secondClick">
            else {
                if (leftClick) {
                    Point clickedNow = new Point(row, column);
                    Character actor = findChar(coordClickedBefore);
                    if (map[row][column] == playerNotTurn) {
                        if (!collision(coordClickedBefore, clickedNow)) {
                            Character victim = findChar(clickedNow);
                            try {
                                if (victim.hit(actor.attack(distance(coordClickedBefore, clickedNow)))) {map[row][column] = 0; gp.repaint();}
                                cp.updateHealth(victim);
                            } catch (AlreadyDoneThatException ex) {
                                cp.showMessage("This character has already attacked this turn", 3);
                            }
                        }
                        else {
                            cp.showMessage("View obstructed!", 3);
                        }
                    }
                    
                    gp.stopShowingTargets();
                }
                else {
                    Character actor = findChar(coordClickedBefore);
                    if (map[row][column] == GameLogic.path) {
                        try {
                            
                            if(actor.move(new Point(row, column))) {
                                map[coordClickedBefore.x][coordClickedBefore.y] = GameLogic.path;
                                map[row][column] = playerTurn;
                                ret = 1;
                            }
                            
                        } catch (AlreadyDoneThatException ex) {
                            cp.showMessage("This character has already moved this turn", 3);
                        }
                    }
                    gp.stopShowingMovementRange();
                }
                firstClick = true;
            }
            //</editor-fold>
        }
        return ret;
    }
    
    public static int distance(Point p1, Point p2) {
        return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
    }
    
    public boolean collision(Point p1, Point p2) {
        boolean ret = false;
        
        int diffX = Math.abs(p2.x-p1.x);
        int diffY = Math.abs(p2.y-p1.y);
        int direction;
        int offset;
        int step;
        
        //diff == 0!!!!!!
        System.out.println(diffX);
        System.out.println(diffY);
        
        if(diffX == diffY) {
            //<editor-fold desc="diffX = diffY">
            diffX = p2.x-p1.x;
            for (int i = diffX/Math.abs(diffX); Math.abs(i) < Math.abs(diffX); i += i/Math.abs(i)) {
                ret |= map[p1.x+i][p1.y+i] != path;
                System.out.println((p1.x+i) + "/" + (p1.y+i));
            }
            
            //</editor-fold>
        }   
        else if(diffX == 0) {
            //<editor-fold desc="diffX = 0">
            diffY = p2.y-p1.y;
            for (int i = diffY/Math.abs(diffY); Math.abs(i) < Math.abs(diffY); i += i/Math.abs(i)) {
                ret |= map[p1.x][p1.y+i] != path;
                System.out.println((p1.x) + "/" + (p1.y+i));
            }
            //</editor-fold>
        }
        else if(diffY == 0) {
            //<editor-fold desc="diffY = 0">
            diffX = p2.x-p1.x;
            for (int i = diffX/Math.abs(diffX); Math.abs(i) < Math.abs(diffX); i += i/Math.abs(i)) {
                ret |= map[p1.x+i][p1.y] != path;
                System.out.println((p1.x+i) + "/" + (p1.y));
            }
            //</editor-fold>
        }
        else if(diffX > diffY) {
            //<editor-fold desc="diffX > diffY">
            diffX = p2.x-p1.x;
            direction = (p2.y-p1.y) / diffY;
            offset = diffX%diffY;
            step = diffX/diffY;
            
            if (offset != 0)    for (int i = offset/Math.abs(offset); Math.abs(i) < Math.abs(offset)+1; i += i/Math.abs(i)) {
                ret |= map[p1.x+i][p1.y] != path;
                    System.out.println((p1.x+i) + "/" + p1.y);
            }
                
            ret |= map[p1.x+offset][p1.y+direction] != path;
                System.out.println((p1.x+offset) + "/" + (p1.y+direction));
            
            for (int c = 0; c < Math.abs(diffY); c++) {
                ret |= map[p1.x+offset+(c*step)][p1.y+((c+1)*direction)] != path;
                System.out.println((p1.x+offset+(c*step)) + "/" + (p1.y+((c+1)*direction)));
                
                for (int i = diffX/Math.abs(diffX); Math.abs(i) < Math.abs(step)+1; i += (i/Math.abs(i))) {
                    ret |= (map[p1.x+offset+(c*step)+i][p1.y+((c+1)*direction)] != path) && (map[p1.x+offset+(c*step)+i][p1.y+((c+1)*direction)] != playerNotTurn);
                    System.out.println((p1.x+offset+(c*step)+i) + "/" + (p1.y+((c+1)*direction)));
                }
                
            }
            //</editor-fold>
        }
        else if(diffX < diffY) {
            //<editor-fold desc="diffX < diffY">
            diffY = p2.y-p1.y;
            direction = (p2.x-p1.x) / diffX;
            offset = diffY%diffX;
            step = diffY/diffX;
            
            if (offset != 0)    for (int i = offset/Math.abs(offset); Math.abs(i) < Math.abs(offset)+1; i += i/Math.abs(i)) {
                ret |= map[p1.x][p1.y+i] != path;
                    System.out.println((p1.x) + "/" + (p1.y+i));
            }
                
            ret |= map[p1.x+direction][p1.y+offset] != path;
                System.out.println((p1.x+direction) + "/" + (p1.y+offset));
            
            for (int c = 0; c < Math.abs(diffX); c++) {
                ret |= map[p1.x+((c+1)*direction)][p1.y+offset+(c*step)] != path;
                System.out.println((p1.x+((c+1)*direction)) + "/" + (p1.y+offset+(c*step)));
                
                for (int i = diffY/Math.abs(diffY); Math.abs(i) < Math.abs(step)+1; i += (i/Math.abs(i))) {
                    ret |= (map[p1.x+((c+1)*direction)][p1.y+offset+(c*step)+i] != path) && (map[p1.x+((c+1)*direction)][p1.y+offset+(c*step)+i] != playerNotTurn);
                    System.out.println((p1.x+((c+1)*direction)) + "/" + (p1.y+offset+(c*step)+i));
                }
                
            }
            //</editor-fold>
        }
        
        
        return ret;
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

