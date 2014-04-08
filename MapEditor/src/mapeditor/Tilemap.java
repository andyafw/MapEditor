/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeditor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author andrew
 */
public class Tilemap {
    public ArrayList<Integer> map = new ArrayList<Integer>(20);
    public int tilewidth = 32;
    public int tileheight = 32;
    public int width = 100;
    public int height = 100;
    
    public Tilemap() {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                map.add(y * width + x, (int)(Math.random() * 4));
            }
        }
    }
    
    public void render(Graphics g) {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int i = map.get(y * width + x);
                g.setColor(changeColor(i));
                g.fillRect(x * tilewidth, y * tileheight, tilewidth, tileheight);
            }
        }  
    }
    
    public Color changeColor(int i) {
        switch(i) {
            case 0:
            case 1:
                return Color.green;
            default:
                return Color.black;
        }
    }
}
