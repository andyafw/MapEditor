/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeditor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author andrew
 */
public class MapEditor extends JFrame {
    Tilemap map;
    Render render;
    JPanel buttonPane, spritePane, spriteWindow, eastPane;
    JButton openButton, saveButton, addButton;
    JScrollPane scrollPane;
    File file = new File("layout_change.png");
    BufferedImage image;
    
    String filename = "res/maps/mapfile2.txt";
    
    public MapEditor() {
        map = new Tilemap();
        InputOutput.readMap(map, filename);
        try {
            image = ImageIO.read(file).getSubimage(0, 0, 64, 64);
        } catch (IOException ex) {
            Logger.getLogger(MapEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        render = new Render(map);
        add(render, BorderLayout.WEST);
        Dimension dim = new Dimension(150, 25);
        buttonPane = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPane.add(openButton = new JButton("Open"));
        openButton.setPreferredSize(dim);
        buttonPane.add(saveButton = new JButton("Save"));
        saveButton.setPreferredSize(dim);
        buttonPane.add(addButton  = new JButton("Add Sprite"));
        addButton.setPreferredSize(dim);
        Dimension dim2 = new Dimension(160, 100);
        buttonPane.setPreferredSize(dim2);
        
        spritePane = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 2));
        spritePane.setPreferredSize(new Dimension(160, 450));
        int sprites = 12;
        for(int i = 0; i < sprites; i++) {
            if(image != null) {
                ImageIcon icon = new ImageIcon(image);
                JButton button = new JButton(icon);
                button.setPreferredSize(new Dimension(64, 64));
                spritePane.add(button);
            }
        }
        scrollPane = new JScrollPane(spritePane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        eastPane = new JPanel(new BorderLayout());
        eastPane.add(buttonPane, BorderLayout.NORTH);
        eastPane.add(scrollPane, BorderLayout.CENTER);
        
        add(eastPane, BorderLayout.EAST);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        try {
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(MapEditor.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        MapEditor me = new MapEditor();
        me.setTitle("Map Editor");
        me.setSize(1012, 600);
        me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        me.setVisible(true);
    }
    
    private class Render extends JPanel {
        Tilemap map;
        
        public Render(Tilemap map) {
            this.map = map;
            this.setPreferredSize(new Dimension(812, 600));
        }
        
        @Override
        public void paintComponent(Graphics g) {
            map.render(g);
        }
    }
}
