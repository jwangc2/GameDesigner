import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ListPane extends JPanel{
	protected DefaultListModel listModel;
	protected JList list;
	protected JPanel sidePane, optionPane, displayPane;
	protected JFileChooser fc;
	
	
	public ListPane(Dimension size, DefaultListModel listModel, JList list){		
		setLayout(new BorderLayout());
		setOpaque(false);
        
        sidePane = new JPanel();
        sidePane.setLayout(new BoxLayout(sidePane, BoxLayout.PAGE_AXIS));
        sidePane.setOpaque(false);
        
        //buttons
        JButton[] buttonList = createButtonList();
		optionPane = new JPanel(new FlowLayout(FlowLayout.LEADING, 2, 2));
		optionPane.setOpaque(false);
		for(JButton button : buttonList){
			optionPane.add(button);
		}
		
		//create the list
		this.listModel = listModel;
		this.list = list;
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(createSelectionListener());
        
		JScrollPane sidebar = new JScrollPane(list);
        sidebar.setPreferredSize(size);
        sidebar.setOpaque(false);
        
        //display
        displayPane = createContentPane();
        
        sidePane.add(optionPane);
        sidePane.add(sidebar);
        
        add(sidePane, BorderLayout.LINE_START);
        add(displayPane, BorderLayout.CENTER);
	}
	
	public ListSelectionListener createSelectionListener(){
		ListSelectionListener listener = null;
		return listener;
	}
	
	public JButton[] createButtonList(){	
		JButton addButton = new JButton( new AbstractAction("Add"){
			public void actionPerformed(ActionEvent e){
				JFileChooser fc = new JFileChooser();
				FileNameExtensionFilter filter = getFilter();
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					File file = fc.getSelectedFile();
					//deal with adding to the list
					Object element = createItem(file);
					listModel.addElement(element);
					int index = listModel.getSize() - 1;
					list.setSelectedIndex(index);
					list.ensureIndexIsVisible(index);
					
					addItem(element);
				}
			}
		});
	    addButton.setVerticalTextPosition(AbstractButton.CENTER);
        addButton.setHorizontalTextPosition(AbstractButton.LEADING);
        
        //remove sprite
        JButton removeButton = new JButton( new AbstractAction("Remove"){
			public void actionPerformed(ActionEvent e){
				if (listModel.getSize() > 0){
					int index = list.getSelectedIndex();
					Object element = list.getSelectedValue();
		    		listModel.remove(index);
		    		
		    		//since it was the last index, gotta pull back one
		    		if (index == listModel.getSize()){
		    			index --;
		    		}
		    		
		    		//select what takes it's place
		    		list.setSelectedIndex(index);
		    		list.ensureIndexIsVisible(index);
		    		removeItem(element);
				}
			}
		});
        removeButton.setVerticalTextPosition(AbstractButton.CENTER);
        removeButton.setHorizontalTextPosition(AbstractButton.LEADING);
        
        JButton[] buttonList = {addButton, removeButton};
        
		return buttonList;
	}
	
	public JPanel createContentPane(){
		JPanel pane = new JPanel();
		pane.setOpaque(false);
		return pane;
	}
	
	public Object createItem(File f){
		return new ObjectData(f);
	}
	
	public FileNameExtensionFilter getFilter(){
		return new FileNameExtensionFilter("Java file", "java");
	}

	public void addItem(Object obj){
	}
	
	public void removeItem(Object obj){
	}
}
