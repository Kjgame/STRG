/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import game.FileManager;
import game.GameFrame;
import login.LoginFrame;

/**
 *
 * @author erik.erbsloeh
 */
public class Start {
    
    public static void main(String[] args) {
        LoginFrame.loginWindow();
//        start("Erik", "Samu", "maps//graveyard.map");
    }
    
    public static void start(String username1, String username2, String map) {
        GameFrame.start(username1, username2, map);
    }
    
}
