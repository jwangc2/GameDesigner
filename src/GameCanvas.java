import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class GameCanvas extends Canvas{
	boolean drawGrid;
	int gridWidth, gridHeight;
	int width, height;
	
	public GameCanvas(int w, int h){
		width = w;
		height = h;
		setPreferredSize(new Dimension(w, h));
		drawGrid = true;
		gridWidth = 16;
		gridHeight = 16;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.lightGray);
		g.fillRect(0, 0, getWidth(), getHeight());
		drawGrid(g);
	}
	
	public void drawGrid(Graphics g){
		if (drawGrid){
			g.setColor(Color.black);
			for(int i = 0; i < (width / gridWidth) + 1; i ++){
				g.drawLine(i * gridWidth, 0, i * gridWidth, height);
			}
			for(int i = 0; i < (height / gridHeight) + 1; i ++){
				g.drawLine(0, i * gridHeight, width, i * gridHeight);
			}
		}
	}
}
