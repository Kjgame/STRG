/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import game.ControlPanel;
import javax.swing.JLabel;

/**
 *
 * @author Erik
 */
public class PauseThread extends TimerThread {

    protected int remainingTime;
    protected int player;
    protected ControlPanel cp;
    
    public PauseThread(JLabel lbl, String text, int seconds, int player, ControlPanel cp) {
        super(lbl, seconds+"", seconds);
        this.cp = cp;
        this.player = player;
        remainingTime = duration;
    }
    
    @Override
    public void run() {
        for (int i = 0; i <= remainingTime; remainingTime--) {
            try {
                lbl.setText(remainingTime+"");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                i = duration;
            }
        }
        if (remainingTime <= 0) {
            end();
        }
    }
    
    protected void end() {
        cp.endPause(player);
    }
    
    public int getRemainingTime() {
        return remainingTime;
    }
    
}
