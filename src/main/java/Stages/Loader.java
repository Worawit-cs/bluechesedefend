package Stages;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Loader {

    public BufferedImage loadMap(String mapPath) {
        try {
            // ใช้ ClassLoader ในการโหลด resource
            InputStream is = getClass().getResourceAsStream(mapPath);
            if (is == null) {
                System.err.println("Error: Images not found: " + mapPath);
                return null;
            }
            return ImageIO.read(is);
        } catch (IOException e) {
            System.err.println("Error loading map: " + e.getMessage());
            return null;
        }
    }    
}
