/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author erik.erbsloeh
 */
public class GamePanel extends JPanel implements MouseListener, Runnable {
    
    private int columns, rows, multC, multR;
    private Dimension size;

    public GamePanel() {
        super();
        columns = 0;
        rows = 0;
    }

    public GamePanel(int columns, int rows) {
        super();
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public void paint(Graphics g) {
        
        size = getSize();
        this.multC = size.width / columns;
        this.multR = size.height / rows;
        
        g.clearRect(0, 0, size.width, size.height);
        
        for (int i = 0; i < columns; i++) {
            g.drawLine(i*multC, 0, i*multC, size.height);
        }
        for (int i = 0; i < rows; i++) {
            g.drawLine(0, i*multR, size.width, i*multR);
        }
        g.drawLine(size.width-1, 0, size.width-1, size.height-1);
        g.drawLine(0, size.height-1, size.width-1, size.height-1);
        
    }
    
    private void clicked(int column, int row) {
        System.out.println(column + ":" + row);
    }
    
    //<editor-fold desc="Getter/Setter" defaultstate="collapsed">
    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
        repaint();
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
        repaint();
    }
    
    //</editor-fold>
    
    //<editor-fold desc="MouseListenerMethods" defaultstate="collapsed">
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clicked(e.getPoint().x / multC, e.getPoint().y / multR);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    //</editor-fold>
    
    @Override
    public void run() {
        
    }
}
