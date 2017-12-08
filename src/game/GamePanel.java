/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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
    private int[][] imageId;    //0=nothing / 1=player1 / 2=player2 / 3=object
    private Image[] images;

    public GamePanel() {
        super();
        columns = 0;
        rows = 0;
        imageId = new int[0][0];
    }

    public GamePanel(int columns, int rows, Image[] images) {
        super();
        this.columns = columns;
        this.rows = rows;
        this.imageId = new int[columns][rows];
        this.images = images;
    }

    @Override
    public void paint(Graphics g) {
        
        size = getSize();
        this.multC = size.width / columns;
        this.multR = size.height / rows;
        
        g.clearRect(0, 0, size.width, size.height);
        
        for (int i = 1; i < columns; i++) {
            g.drawLine(i*multC, 0, i*multC, size.height);
        }
        for (int i = 1; i < rows; i++) {
            g.drawLine(0, i*multR, size.width, i*multR);
        }
//        g.drawLine(size.width-1, 0, size.width-1, size.height-1);
//        g.drawLine(0, size.height-1, size.width-1, size.height-1);
        
        paintImages(g, size);
        
    }
    
    private void paintImages(Graphics g, Dimension size) {
        
        for (int i = 0; i < columns; i++)
            for (int i2 = 0; i2 < rows; i2++) {
                g.drawImage(images[imageId[i][i2]], i*(multC+1), i2*(multR+1), multC-i, multR-i2, null);
            }
        
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
