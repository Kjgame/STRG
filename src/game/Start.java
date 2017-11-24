/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import login.LoginFrame;

/**
 *
 * @author erik.erbsloeh
 */
public class Start {
    
    public static void main(String[] args) {
//        LoginFrame.loginWindow();
        start("Test");
    }
    
    public static void start(String username) {
        GameFrame.start();
    }
    
}
