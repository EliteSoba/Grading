import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Restaurant_v22_gui extends JFrame implements ActionListener {

	JTabbedPane content;
	JButton gen;
	JTextArea text;
	GradingPanel git, deduc;
	GradingPanel A2, B2, C2, D2;
	public Restaurant_v22_gui() {
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		content = new JTabbedPane();

		
		setUp22A();
		setUp22B();
		setUp22C();
		setUp22D();
		
		setUpGit();
		setUpDedu();

		setUpFinal();

		add(content);
		pack();
	}

	private void populateTab(Item[] items, GradingPanel panel) {
		for (Item i:items)
			panel.addItem(i);

		content.addTab(panel.ID, panel);
	}


	
	private void setUp22A() {
		Item A[] = new Item[3];
		A[0] = new Item(10, "One order, fulfilled by the market, bill paid in full", "Market/Cashier");
		A[1] = new Item(10, "One order, fulfilled by TWO markets, 2 bills paid in full", "Market/Cashier");
		A[2] = new Item(5, "(EC) One order, fulfilled by the market, bill unable to be paid.<br>You end the scenario by telling us how your cashier makes good on the payment.", "MarketCashier");
	
		A2 = new GradingPanel("v2.2A");
		
		populateTab(A, A2);
	}
	
	private void setUp22B() {
		Item B[] = new Item[6];
		B[0] = new Item(10, "Unit Test - One order, fulfilled by the market, bill paid in full", "Unit Test");
		B[1] = new Item(10, "Unit Test - One order, fulfilled by TWO markets, 2 bills paid in full", "Unit Test");
		B[2] = new Item(5, "Unit Test - Cashier Unit Test 1", "Unit Test");
		B[3] = new Item(5, "Unit Test - Cashier Unit Test 2", "Unit Test");
		B[4] = new Item(5, "Unit Test - Cashier Unit Test 3", "Unit Test");
		B[5] = new Item(5, "Unit Test - Cashier Unit Test 4", "Unit Test");
		
		B2 = new GradingPanel("v2.2B");
		
		populateTab(B, B2);
	}
	
	private void setUp22C() {
		Item C[] = new Item[1];
		C[0] = new Item(10, "No ConcurrentModificationErrors", "Threadsafe");
		
		C2 = new GradingPanel("v2.2C");
		
		populateTab(C, C2);
	}
	
	private void setUp22D() {
		Item D[] = new Item[5];
		D[0] = new Item(10, "Waiting area for new customers", "Animation");
		D[1] = new Item(5, "Unique waiter home positions", "Animation");
		D[2] = new Item(5, "Cooking area", "Animation");
		D[3] = new Item(5, "Plating area", "Animation");
		D[4] = new Item(5, "(EC) Refrigerator", "Animation");
		
		D2 = new GradingPanel("v2.2D");
		
		populateTab(D, D2);
		
	}

	private void setUpGit() {
		Item Git[] = new Item[5];
		Git[0] = new Item(1, "Intermediate commits for the assignment showing progress (at least 5 commits)", "Git");
		Git[1] = new Item(1, "Final marked commit for submission/grading WITH TAG", "Git");
		Git[2] = new Item(1, "Commits - in general - have appropriate, descriptive names", "Git");
		Git[3] = new Item(2, "README.md contains stuff", "Git");
		Git[4] = new Item(-5, "Violations of what to do & what not to do in your repository", "Git");

		git = new GradingPanel("Git");

		for (Item i:Git)
			git.addItem(i);

		content.addTab("Git", git);
	}

	private void setUpDedu() {
		Item Dedu[] = new Item[6];
		Dedu[0] = new Item(-10, "Not using the agent methodology correctly", "Deductions");
		Dedu[1] = new Item(-2, "Runtime errors other than concurrent modification errors, which we ignore for now", "Deductions");
		Dedu[2] = new Item(-10, "Missing a lab deadline (for each lab deadline missed)", "Deductions");
		Dedu[3] = new Item(-5, "GUI is not reasonably sized (components cut off, text unreadable, etc.)", "Deductions");
		Dedu[4] = new Item(-10, "Program only runs on student's machine", "Deductions");
		Dedu[5] = new Item(0, "Needing to alter student's code to make it work (Enter # lines)", "Alterations");

		deduc = new GradingPanel("Deductions");

		for (Item i:Dedu)
			deduc.addItem(i);

		content.addTab("Deductions", deduc);
	}

	private void setUpFinal() {
		JPanel fin = new JPanel();
		fin.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		gen = new JButton("Generate Report");
		gen.addActionListener(this);
		text = new JTextArea();

		c.weightx = 1;
		fin.add(gen,c);
		c.gridy = 2;
		c.weighty = 1;
		fin.add(text,c);
		content.addTab("Finish", fin);
	}

	public int getTotal() {
		return A2.addItems() + B2.addItems() + C2.addItems() + D2.addItems()
				+ git.addItems() + deduc.addItems();
	}

	public static void main(String[] args) {
		Restaurant_v22_gui test = new Restaurant_v22_gui();
		test.setSize(850, test.getHeight());
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(gen)) {
			String output = "Your Assignment v2.2 Submission Received " + getTotal() + " points. The following error(s) were encountered:\n";
			output += " 1. Milestone.v2.1A - " + (20-A2.addItems()) + " points were deducted\n";
			output += A2.getErrors();
			output += "\n";
			output += " 2. Milestone.v2.1B - " + (40-B2.addItems()) + " points were deducted\n";
			output += B2.getErrors();
			output += "\n";
			output += " 3. Milestone.v2.1C - " + (10-C2.addItems()) + " points were deducted\n";
			output += C2.getErrors();
			output += "\n";
			output += " 4. Milestone.v2.1D - " + (25-D2.addItems()) + " points were deducted\n";
			output += D2.getErrors();
			output += "\n";
			output += " 5. Git Usage - " + (5-git.addItems()) + " points were deducted\n";
			output += git.getErrors();
			output += "\n";
			if (deduc.addItems() != 0) {
				output += "Also, the following deductions were applied\n";
				output += deduc.getErrors();
				output += "\n";
			}
			output += "Other Notes:\n\n";
			output += ">Although you may be getting this message via email, it is an issue on issue on your private GitHub repository and you should interact with it on GitHub\n\n"
					+ ">Any and all discussions pertaining to this deduction **must** be done via comments on this issue. If you are statisfied with the grading, you **must** close the issue. Note that a closed issue implicitly signals that you do not want to discuss this any further. No emails are accepted.\n\n"
					+ ">You have until Oct 29th, 2013 to dispute this deduction. Please allow up to 72 hours for a response to a regrade request. You are expected to follow the _Grading Disputes_ policies outlined in the [course syllabus](http://www-scf.usc.edu/~csci201/syllabus.html).\n\n";
			System.out.println(output);
			output.replaceAll("\n","<br />");
			text.setText(output);

		}
	}

}
