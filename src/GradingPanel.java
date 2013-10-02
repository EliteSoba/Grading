import javax.swing.*;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;
import java.util.*;

public class GradingPanel extends JPanel {
	String ID;
	ArrayList<Item> items;
	JScrollPane content;
	JPanel main;
	
	private class GradingButton implements ActionListener{
		JButton button;
		Item item;
		public GradingButton(Item i) {
			item = i;
			button = new JButton(item.getDescription());
			button.addActionListener(this);
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource().equals(button)) {
				if (button.getForeground().equals(Color.red)) {
					item.setPointCur(0 > item.getPointMax() ? 0 : item.getPointMax());
					button.setForeground(Color.black);
				}
				else {
					item.setPointCur(0 < item.getPointMax() ? 0 : item.getPointMax());
					button.setForeground(Color.red);
				}
			}
		}
	}
	
	public GradingPanel(String I) {
		ID = I;
		main = new JPanel();
		main.setLayout(new GridLayout(0,1));
		content = new JScrollPane(main);
		add(content);
		
		items = new ArrayList<Item>();
		
//		content.setPreferredSize(new Dimension(200, 200));
	}
	
	public void addItem(Item i) {
		Item item = new Item(i.getPointMax(), i.getDescription(), i.getMilestone());
		GradingButton button = new GradingButton(item);
		items.add(item);
		main.add(button.button);
	}
	
//	public void update() {
//		//remove(content);
//
//		main.revalidate();
//		main.repaint();
//		//add(content);
//	}
	
	public int addItems() {
		int total = 0;
		for (int i = 0; i < items.size(); i++)
			total += items.get(i).getPointCur();
		return total;
	}
	
	public ArrayList<Item> getItems() {
		return items;
	}
	
	public Item get(int index) {
		return items.get(index);
	}
}
