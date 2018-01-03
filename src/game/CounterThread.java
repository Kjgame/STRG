/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Erik
 */
public class CounterThread extends TimerThread {

    private int remainingTime;
    
    public CounterThread(JLabel lbl, String text, int seconds) {
        super(lbl, seconds+"", seconds);
        remainingTime = duration;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < remainingTime; remainingTime--) {
            try {
                lbl.setText(remainingTime+"");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                i = duration;
            }
        }
    }
    
    public int getRemainingTime() {
        return remainingTime;
    }
    
}
