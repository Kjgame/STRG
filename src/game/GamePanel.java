/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
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
    private int[][] help;

    private ContentPanel parent;
    
    public GamePanel() {
        super();
        columns = 0;
        rows = 0;
        imageId = new int[0][0];
    }

    public GamePanel(int columns, int rows, Image[] images, int[][] map, ContentPanel parent) {
        super();
        this.columns = columns;
        this.rows = rows;
        this.images = images;
        this.imageId = new int[0][0];
        this.imageId = map;
        this.parent = parent;
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
        
        paintImages(g, size, imageId);
        
    }
    
    public void showMovementRange(Point p, int range) {
        help = new int[8][8];
        for (int i = 0; i < imageId.length; i++) 
            help[i] = Arrays.copyOf(imageId[i], 8);
        
        for (int i = 0; i < help.length; i++) 
            for (int c = 0; c < help[i].length; c++) {
                if (help[i][c] == GameLogic.path) 
                    if (GameLogic.distance(p, new Point(i, c)) <= range)
                        help[i][c] = 4;
            }
        this.paintImages(getGraphics(), size, help);
    }
    
    public void stopShowingMovementRange() {
        this.setMap(imageId);
    }
    
    private void paintImages(Graphics g, Dimension size, int[][] imageId) {
        
        for (int i = 0; i < columns; i++)
            for (int i2 = 0; i2 < rows; i2++) {
                g.drawImage(images[imageId[i2][i]], i*(multC)+1, i2*(multR)+1, multC-1, multR-1, null);
            }
        
    }
    
    public void setMap(int[][] map) {
        this.imageId = map;
        repaint();
    }
    
    private void clicked(int column, int row, int button) {
        System.out.println(column + ":" + row + " " + button);
        if (parent.clicked(column, row, button) == 1) repaint();
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
        clicked(e.getPoint().x / multC, e.getPoint().y / multR, e.getButton());
        
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
