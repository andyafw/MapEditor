/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeditor;

import java.io.*;
import java.util.StringTokenizer;
import java.util.logging.*;

/**
 * @date 03-06-14
 * @author andrew
 */
public class InputOutput {
    public static void readMap(Tilemap map, String file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String input;
            int x = 0;
            int y = 0;
            while((input = reader.readLine()) != null) {
                StringTokenizer tokens = new StringTokenizer(input.trim(), " ");
                x = 0;
                while(tokens.hasMoreTokens()) {
                    map.map.add(y * map.width + x, Integer.parseInt(tokens.nextToken()));
                    x++;
                }
                y++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void writeMap(Tilemap map, String file) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file));
            for(int y = 0; y < map.height; y++) {
                for(int x = 0; x < map.width; x++) {
                    writer.print(map.map.get(y * map.width + x) + " ");
                }
                writer.println();
            }
        } catch (IOException ex) {
            Logger.getLogger(InputOutput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
