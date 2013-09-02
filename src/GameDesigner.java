import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GameDesigner extends JFrame{
	private DefaultListModel objListModel, spriteListModel;
	private JList objList, spriteList;
	private JFileChooser fc;
	private FileNameExtensionFilter filter;
	
	public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	GameDesigner GD = new GameDesigner("Game Designer");
            	GD.setVisible(true);

            }
        });
    }
	
	public GameDesigner(String label){
		super(label);
		initComponents();
	}
	
	private void initComponents() {
        //Create and set up the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.LEADING, 4, 4));
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        //Create the menu bar.  Make it have a green background.
        JMenuBar greenMenuBar = new JMenuBar();
        greenMenuBar.setOpaque(true);
        greenMenuBar.setBackground(new Color(154, 165, 127));
        greenMenuBar.setPreferredSize(new Dimension(200, 20));
        
        
        fc = new JFileChooser();
        
        spriteListModel = new DefaultListModel();
        spriteList = new JList(spriteListModel);
        
        objListModel = new DefaultListModel();
        objList = new JList(objListModel);
        
        //room / object tabs
        JTabbedPane controlPane = new JTabbedPane();
        ListPane listPane = null;
        ListPane spritePane = null;
        
        listPane = new ListPane(new Dimension(0, 600), objListModel, objList);
        spritePane = new SpritePane(new Dimension(0, 600), spriteListModel, spriteList);
        
        controlPane.addTab("Room Editor", new GameCanvas(800, 600));
        controlPane.addTab("Object Editor", listPane);
        controlPane.addTab("Sprite Editor", spritePane);
        
        //Set the menu bar and add the label to the content pane.
        setJMenuBar(greenMenuBar);
        container.add(controlPane);

        //Display the window.
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
}
