/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.JLabel;

/**
 *
 * @author Erik
 */
public class TurnThread extends PauseThread {

    public TurnThread(JLabel lbl, String text, int seconds, int player, ControlPanel cp) {
        super(lbl, text, seconds, player, cp);
    }
    
    @Override
    public void end() {
        cp.endTurn(player);
    }
    
}
