/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Erik
 */
public class AlreadyDoneThatException extends Exception {
    
    public static final int doneAttack = 0;
    public static final int doneMove = 1;
    
    int doneWhat;
    
    public AlreadyDoneThatException(int doneWhat) {
        super();
        this.doneWhat = doneWhat;
    }
    
}
