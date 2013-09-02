import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteData {
	private File spriteFile;
	private String fname, path;
	private BufferedImage image;
	
	public SpriteData(File file){
		spriteFile = file;
		path = spriteFile.getPath();
		fname = "Null";
		
		if (spriteFile != null){
			fname = spriteFile.getName();
			fname = fname.substring(0, fname.lastIndexOf("."));
		}
		
		try {
			image = ImageIO.read(spriteFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getPath(){
		return path;
	}
	
	public Image getImage(){
		return image;
	}
	
	public String toString(){
		return fname;
	}
}
