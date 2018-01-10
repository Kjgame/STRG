/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erik
 */
public class FileManager {
    
    public static final String path = "maps//";
    
    private static FileFilter filter = new FileFilter() {

        @Override
        public boolean accept(File pathname) {
            return pathname.getName().contains(".map") || pathname.isDirectory();
        }
    };
    private static BufferedReader reader;
    
    public static String[] getFiles() {
        File f = new File(path);
        File[] files = f.listFiles(filter);
        String[] ret = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            ret[i] = files[i].getName().replace(".map", "");
        }
        return ret;
    }
    
    public static int[][] getMap(String name) {
        int[][] ret = new int[8][8];
        try {
            
            reader = new BufferedReader(new FileReader(path + name + ".map"));
            for (int i = 0; i < 8; i++) {
                String s = reader.readLine();
                String[] strings = s.split(":");
                for (int c = 0; c < 8; c++) {
                    ret[i][c] = Integer.parseInt(strings[c]);
                }
            }
            reader.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
    }   catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    return ret;
    }
}
