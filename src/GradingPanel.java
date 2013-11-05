import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;
import java.util.*;

/**
 * A panel to separate relevant milestones to aide the grader in organizational matters
 * @author Tobias
 *
 */
public class GradingPanel extends JPanel {
	/**The name of the panel*/
	String ID;
	/**The list of requirements managed by this panel*/
	ArrayList<Item> items;
	/**A nonfunctional scrollpane where all content is added*/
	JScrollPane content;
	/**The main panel upon which stuff is added*/
	JPanel main;

	/**
	 * A private class holding relevant implementation information about an Item
	 * @author Tobias
	 *
	 */
	private class GradingButton implements ActionListener, DocumentListener{
		/**Button to allow editing of project and easy deduction*/
		JButton button;
		/**The actual requirement associated with the GradingButton*/
		Item item;
		/**An editable score field to allow partial credit*/
		JTextField score;
		/**A label to help alert the grader what the maximum is*/
		JLabel total;
		/**A button to allow adding a comment to a particular deduction*/
		JButton comment;
		/**The comment in question*/
		String com;

		/**
		 * Constructor
		 * @param i The Item being managed
		 */
		public GradingButton(Item i) {
			item = i;
			button = new JButton("<html><center>"+item.getDescription()+"</center></html>");
			button.setForeground(Color.black);
			button.addActionListener(this);
			score = new JTextField(2);
			score.setText(""+item.getPointCur());
			score.setEditable(false);
			score.getDocument().addDocumentListener(this);
			total = new JLabel("/" + i.getPointMax());

			com = "";
			comment = new JButton("Comment");
			comment.setForeground(Color.gray);
			comment.addActionListener(this);
		}

		/**Manages button presses*/
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource().equals(button)) {
				if (button.getForeground().equals(Color.red)) {
					if (item.getMilestone().equalsIgnoreCase("EC"))
						item.setPointCur(0);
					else
						item.setPointCur(0 > item.getPointMax() ? 0 : item.getPointMax());
					button.setForeground(Color.black);
					score.setText(""+item.getPointCur());
					score.setEditable(false);
				}
				else {
					if (item.getMilestone().equalsIgnoreCase("EC"))
						item.setPointCur(item.getPointMax());
					else
						item.setPointCur(0 < item.getPointMax() ? 0 : item.getPointMax());
					button.setForeground(Color.red);
					score.setText(""+item.getPointCur());
					score.setEditable(true);
				}
			}
			else if (arg0.getSource().equals(comment)) {
				String temp = JOptionPane.showInputDialog("Comment?:", com);
				if (temp != null)
					com = temp;
				if (com != null && !com.isEmpty()) {
					comment.setForeground(Color.black);
					comment.setToolTipText(com);
					item.setComment(com);
				}
				else {
					comment.setForeground(Color.gray);
					comment.setToolTipText("");
					item.setComment("");
				}
			}
		}

		public void changedUpdate(DocumentEvent arg0) {

			item.setPointCur(getScore());
		}

		/**Returns the score based on the text field*/
		public int getScore() {
			int sc = 0;
			try {
				sc = Integer.parseInt(score.getText());
			}
			catch (Exception e) {
				return 0;
			}
			if (item.getPointMax() <= 0) {
				//				if (sc > 0)
				//					sc = 0;
				if (sc < item.getPointMax())
					sc = item.getPointMax();
				return sc;
			}
			if (sc < 0)
				sc = 0;
			if (sc > item.getPointMax())
				sc = item.getPointMax();
			return sc;
		}

		public void insertUpdate(DocumentEvent arg0) {
			item.setPointCur(getScore());
		}

		public void removeUpdate(DocumentEvent arg0) {

		}
	}

	GridBagConstraints c;

	/**
	 * Constructor
	 * @param I The ID of the panel
	 */
	public GradingPanel(String I) {
		ID = I;
		main = new JPanel();
		main.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.gridy = 1;
		content = new JScrollPane(main);
		add(content);

		items = new ArrayList<Item>();

		//		content.setPreferredSize(new Dimension(200, 200));
	}

	/**
	 * Adds an Item and its GradingButton to the panel
	 * @param i The Item to be added
	 */
	public void addItem(Item i) {
		Item item = new Item(i.getPointMax(), i.getDescription(), i.getMilestone());
		GradingButton button = new GradingButton(item);
		items.add(item);
		c.gridx = 1;
		c.weightx = .85;
		c.fill = GridBagConstraints.HORIZONTAL;
		main.add(button.button, c);
		c.gridx = 2;
		c.weightx = .05;
		c.fill = GridBagConstraints.NONE;
		main.add(button.score, c);
		c.gridx = 3;
		c.weightx = .05;
		main.add(button.total, c);
		c.gridx = 4;
		c.weightx = .05;
		main.add(button.comment, c);
		c.gridy++;
	}

	//	public void update() {
	//		//remove(content);
	//
	//		main.revalidate();
	//		main.repaint();
	//		//add(content);
	//	}

	/**
	 * Sums up all the points in the panel
	 * @return The total points awarded in the panel
	 */
	public int addItems() {
		int total = 0;
		for (int i = 0; i < items.size(); i++)
			total += items.get(i).getPointCur();
		return total;
	}

	/**
	 * Returns all the comments for the grading report
	 * @return A multi-line string containing all of the comments associated with each deduction
	 */
	public String getErrors() {
		String errors = "";
		for (Item i:items) {
			if (i.getMilestone().equalsIgnoreCase("EC")) {
				if (i.getPointCur() != 0) {
					if (i.getComment() != null && !i.getComment().isEmpty())
						errors += ("  + (+" + (i.getPointCur()) + ") - " + i.getDescription() + " - " + i.getComment()) + "\n";
					else
						errors += ("  + (+" + (i.getPointCur()) + ") - " + i.getDescription()) + "\n";
				}
			}

			else if (i.getPointMax() > 0 && i.getPointCur() < i.getPointMax()) {
				if (i.getComment() != null && !i.getComment().isEmpty())
					errors += ("  + (-" + (i.getPointMax()-i.getPointCur()) + ") - " + i.getDescription() + " - " + i.getComment()) + "\n";
				else
					errors += ("  + (-" + (i.getPointMax()-i.getPointCur()) + ") - " + i.getDescription()) + "\n";
			}
			else if (i.getPointMax() <= 0 && i.getPointCur() < 0) {
				if (i.getComment() != null && !i.getComment().isEmpty())
					errors += ("  + (" + i.getPointCur() + " points) - "  + i.getDescription() + " - " + i.getComment() + "\n");
				else
					errors += ("  + (" + i.getPointCur() + " points) - " + i.getDescription() + "\n");
			}
			else if (i.getComment() != null && !i.getComment().isEmpty())
				errors += "  + " + i.getDescription() + " - " + i.getComment() + "\n";
		}
		return errors;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public Item get(int index) {
		return items.get(index);
	}
}
