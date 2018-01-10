/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import javax.swing.JLabel;

/**
 *
 * @author Erik
 */
public class TimerThread extends Thread {
        
        protected JLabel lbl;
        private String text;
        protected int duration;
        
        public TimerThread(JLabel lbl, String text, int seconds) {
            super();
            this.lbl = lbl;
            this.text = text;
            this.duration = seconds;
        }
        
        @Override
        public void run() {
            try {
                lbl.setText(text);
                Thread.sleep(duration *1000);
                lbl.setText("");
            } catch (InterruptedException ex) {
                lbl.setText("");
                System.out.println("Thread stopped");
            }
            
        }
        
    }
