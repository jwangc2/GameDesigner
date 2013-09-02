import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;


public class ImagePane extends JPanel {
	private SpriteData spriteData;
	
	public ImagePane(SpriteData data) {
		spriteData = data;
		if (spriteData != null){
			Image image = spriteData.getImage();
			setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
		}
	}

	public void paint(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (spriteData != null){
			g.drawImage(spriteData.getImage(), 0, 0, null);
		}
	}
}
