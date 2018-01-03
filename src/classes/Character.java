/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import game.GameLogic;
import java.awt.Point;

/**
 *
 * @author Erik
 */
public class Character {
    
    private int player;
    private int charNr;
    private int health;
    private Point position;
    private boolean attacked, moved;
    private int baseDamage;
    private int damageFalloff;
    private int movementRange;
    
    public Character(int alliance, int charNr, int health, Point position, int baseDamage, int damageFalloff, int movementRange) {
        this.player = alliance;
        this.charNr = charNr;
        this.health = health;
        this.position = position;
        this.baseDamage = baseDamage;
        this.damageFalloff = damageFalloff;
        this.movementRange = movementRange;
        attacked = false;
        moved = false;
    }
    
    public boolean hit(int damage) {
        health -= damage;
        return health <= 0;
    }
    
    public int attack(int distance) throws AlreadyDoneThatException {
        if (attacked) throw new AlreadyDoneThatException(AlreadyDoneThatException.doneAttack);
        attacked = true;
        return baseDamage - ((distance/2)-1)*damageFalloff;
    }
    
    public boolean move(Point p) throws AlreadyDoneThatException {
        if (moved) throw new AlreadyDoneThatException(AlreadyDoneThatException.doneMove);
        boolean ret = false;
        if (GameLogic.distance(position, p) <= movementRange) {
            moved = true;
            this.position = p;
            ret = true;
        }
        return ret;
    }

    public void reset() {
        this.attacked = false;
        this.moved = false;
    }
    
    public int getPlayer() {
        return player;
    }

    public int getCharNr() {
        return charNr;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getDamageFalloff() {
        return damageFalloff;
    }

    public void setDamageFalloff(int damageFalloff) {
        this.damageFalloff = damageFalloff;
    }

    public int getMovementRange() {
        return movementRange;
    }

    public void setMovementRange(int movementRange) {
        this.movementRange = movementRange;
    }
    
    
    
}
