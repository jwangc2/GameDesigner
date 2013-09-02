import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;


public class SpritePane extends ListPane {
	private JPanel imagePane;
	
	public SpritePane(Dimension size, DefaultListModel listModel, JList list) {
		super(size, listModel, list);
	}
	                                  
	public JPanel createContentPane(){
		JPanel pane = new JPanel(new FlowLayout(FlowLayout.LEADING, 2, 2));
		imagePane = new ImagePane(null);
		pane.add(imagePane);
		return pane;
	}
	
	public FileNameExtensionFilter getFilter(){
		return new FileNameExtensionFilter("JPG & PNG", "jpg", "png");
	}
	
	public Object createItem(File f){
		return new SpriteData(f);
	}
	
	public ListSelectionListener createSelectionListener(){
		ListSelectionListener listener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				displayPane.remove(imagePane);
				imagePane = new ImagePane((SpriteData)(list.getSelectedValue()));
				displayPane.add(imagePane);
				revalidate();
				repaint();
			}
			
		};
		return listener;
	}
	
	public void removeItem(Object obj){
		if (listModel.size() <= 0){
			displayPane.remove(imagePane);
			imagePane = new ImagePane(null);
			displayPane.add(imagePane);
			revalidate();
			repaint();
		}
	}
}
