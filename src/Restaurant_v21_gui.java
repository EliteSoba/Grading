import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Restaurant_v21_gui extends JFrame implements ActionListener {

	JTabbedPane content;
	JButton gen;
	JTextArea text;
	GradingPanel Dsign, host, customer, wait, cook, ani, sc, git, deduc;
	GradingPanel v2;
	public Restaurant_v21_gui() {
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		content = new JTabbedPane();
		
		setUpV2();
		setUpDesign();
		
		setUpHost();
		setUpCust();
		setUpWait();
		setUpCook();
		setUpAnim();
		setUpScen();
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
	
	private void setUpV2() {
		
		Item V2[] = new Item[5];
		V2[0] = new Item(2, "One Customer, One Waiter", "V2 Requirements");
		V2[1] = new Item(2, "Multiple Customer, One Waiter", "V2 Requirements");
		V2[2] = new Item(2, "Load the Tables, One Waiter", "V2 Requirements");
		V2[3] = new Item(2, "Multiple Customer, Multiple Waiter", "V2 Requirements");
		V2[4] = new Item(2, "Funcitonal Pause Button", "V2 Requirements");
		
		v2 = new GradingPanel("V2 Requirements");
		populateTab(V2, v2);
	}
	
	private void setUpDesign() {
		
		Item Design[] = new Item[14];
		Design[0] = new Item(1, "Normative Interaction Diagram", "Design");
		Design[1] = new Item(1, "Interaction Diagram includes stuff", "Design");
		Design[2] = new Item(2, "Host - Full Design of Host.", "Host Design");
		Design[3] = new Item(1, "Host - Good Pseudocode.", "Host Design");
		Design[4] = new Item(2, "Cook - Full Design of Host.", "Cook Design");
		Design[5] = new Item(1, "Cook - Good Pseudocode.", "Cook Design");
		Design[6] = new Item(2, "Customer - Full Design of Host.", "Customer Design");
		Design[7] = new Item(1, "Customer - Good Pseudocode.", "Customer Design");
		Design[8] = new Item(2, "Waiter - Full Design of Host.", "Waiter Design");
		Design[9] = new Item(1, "Waiter - Good Pseudocode.", "Waiter Design");
		Design[10] = new Item(2, "Cashier - Full Design of Host.", "Cashier Design");
		Design[11] = new Item(1, "Cashier - Good Pseudocode.", "Cashier Design");
		Design[12] = new Item(2, "Market - Full Design of Host.", "Market Design");
		Design[13] = new Item(1, "Market - Good Pseudocode.", "Market Design");

		Dsign = new GradingPanel("Design");
		populateTab(Design, Dsign);
	}
	
	private void setUpHost() {
		Item Host[] = new Item[5];
		Host[0] = new Item(1, "Handles new Customer and puts it onto waiting list.", "Host");
		Host[1] = new Item(1, "Properly assigns Customers when a table becomes free and a waiter is known.", "Host");
		Host[2] = new Item(2, "Implements a mechanism to load balance the waiters.", "Host");
		Host[3] = new Item(1, "Properly deals with assigning multiple customers to multiple waiters to multiple tables", "Host");
		Host[4] = new Item(1, "Frees table when Customer leaves.", "Host");
		
		
		host = new GradingPanel("Host");
		
		for (Item i:Host)
			host.addItem(i);
		
		content.addTab("Host", host);
	}
	
	private void setUpCust() {
		Item Cust[] = new Item[9];
		Cust[0] = new Item(1, "Handles hungry message. Eventually messages the Host about wanting food.", "Cust");
		Cust[1] = new Item(1, "Handles Waiter follow me message and eventually sits down at the correct table.", "Cust");
		Cust[2] = new Item(1, "Messages the Waiter when ready to order.", "Cust");
		Cust[3] = new Item(1, "Handles Waiter message about what does he want.", "Cust");
		Cust[4] = new Item(1, "Implements a mechanism to choose what to order.", "Cust");
		Cust[5] = new Item(1, "Messages the Waiter with order choice.", "Cust");
		Cust[6] = new Item(1, "Handles cooked food delivery and eventually eats food which is managed by a timer.", "Cust");
		Cust[7] = new Item(1, "Leaves the restaurant and when done eating, informing his Waiter,<br>goes into a state able to be hungry again and start the entire process over.", "Cust");
		Cust[8] = new Item(4, "Proper implementation of FSM with proper states and events", "Cust");
		
		customer = new GradingPanel("Customer");
		
		for (Item i:Cust)
			customer.addItem(i);
		
		content.addTab("Customer", customer);
	}
	
	private void setUpWait() {
		Item Wait[] = new Item[12];
		Wait[0] = new Item(1, "Correctly gets message from Host to seat Customer", "Wait");
		Wait[1] = new Item(2, "Correctly seats Customer at the correct table and gives menu", "Wait");
		Wait[2] = new Item(1, "Responds when Customer is ready to order.", "Wait");
		Wait[3] = new Item(1, "Tells Customer to give order", "Wait");
		Wait[4] = new Item(1, "Handles Customer food order.", "Wait");
		Wait[5] = new Item(1, "Sends correct Customer order to Cook.", "Wait");
		Wait[6] = new Item(1, "Handles CookÅfs order is ready message.", "Wait");
		Wait[7] = new Item(1, "Delivers finished order to correct Customer.", "Wait");
		Wait[8] = new Item(1, "Handles Customer message when it's finished eating.", "Wait");
		Wait[9] = new Item(1, "Informs Host that a table is free once the customer has finished eating", "Wait");
		Wait[10] = new Item(3, "Can handle all of the above with multiple customers at the same time", "Wait");
		Wait[11] = new Item(2, "Correctly waits for animation to finish movements before executing new actions", "Wait");
		
		wait = new GradingPanel("Waiter");
		
		for (Item i:Wait)
			wait.addItem(i);
		
		content.addTab("Waiter", wait);
	}
	
	private void setUpCook() {
		Item Cook[] = new Item[5];
		Cook[0] = new Item(1, "Food and Order classes properly created and used.", "Cook");
		Cook[1] = new Item(1, "Cook receives an order from the waiter and stores it into a list.", "Cook");
		Cook[2] = new Item(2, "Food order is cooked which is managed by a Timer.", "Cook");
		Cook[3] = new Item(1, "Waiter is notified of the cooked order (but only after the Timer runs).", "Cook");
		Cook[4] = new Item(1, "The correct cooked food order is given to the correct Waiter.", "Cook");
		
		cook = new GradingPanel("Cook");
		
		for (Item i:Cook)
			cook.addItem(i);
		
		content.addTab("Cook", cook);
	}
	
	private void setUpAnim() {
		Item Anim[] = new Item[11];
		Anim[0] = new Item(2, "Animation supports at least 3 tables (can be hard-coded).", "Animation");
		Anim[1] = new Item(3, "Animation supports and shows simultaneous movement for multiple Waiters.", "Animation");
		Anim[2] = new Item(3, "Ordering: Waiter returns to table to take order. Then waiter either moves offscreen to deliver orders to Cook<br>OR simply sends the cook a message, i.e., doesn't move to the cook.", "Animation");
		Anim[3] = new Item(3, "Delivery: Waiter gets order from cook offscreen and delivers food to correct table.", "Animation");
		Anim[4] = new Item(3, "Implementation of a Waiter creation panel. Please note that if you have hardcoded the creation of waiters,<br>i.e., you don't have a Waiter creation panel, then you can't run several of the required scenarios and will lose additional points.", "Animation");
		Anim[5] = new Item(2, "Food is represented with an icon or a text abbreviation (i.e. ST for steak).", "Animation");
		Anim[6] = new Item(2, "Customer hungry status can be set during Customer creation.", "Animation");
		Anim[7] = new Item(2, "No \"Magic Numbers\" in code.", "Animation");
		Anim[8] = new Item(2, "Animation and control panels are in the same window.", "Animation");
		Anim[9] = new Item(2, "Pause button that sends a pause message to base agent for all agent instances.", "Animation");
		Anim[10] = new Item(1, "Customers can be made hungry again.", "Animation");
		
		ani = new GradingPanel("Animation");
		
		for (Item i:Anim)
			ani.addItem(i);
		
		content.addTab("Animation", ani);
	}
	
	private void setUpScen() {
		Item Scen[] = new Item[5];
		Scen[0] = new Item(2, "One customer, one waiter", "Scenario");
		Scen[1] = new Item(2, "Multiple customers, one waiter", "Scenario");
		Scen[2] = new Item(2, "Load the tables, one waiter.", "Scenario");
		Scen[3] = new Item(2, "Multiple waiters, multiple customers", "Scenario");
		Scen[4] = new Item(2, "Pause/Restart button should work in any of the above scenarios", "Scenario");
		
		sc = new GradingPanel("Scenarios");
		
		for (Item i:Scen)
			sc.addItem(i);
		
		content.addTab("Scenarios", sc);
	}
	
	private void setUpGit() {
		Item Git[] = new Item[4];
		Git[0] = new Item(1, "Intermediate commits for the assignment showing progress (at least 5 commits)", "Git");
		Git[1] = new Item(1, "Final marked commit for submission/grading.", "Git");
		Git[2] = new Item(1, "Commits - in general - have appropriate, descriptive names", "Git");
		Git[3] = new Item(2, "README.md contains stuff", "Git");
		
		git = new GradingPanel("Git");
		
		for (Item i:Git)
			git.addItem(i);
		
		content.addTab("Git", git);
	}
	
	private void setUpDedu() {
		Item Dedu[] = new Item[3];
		Dedu[0] = new Item(-5, "Not using the agent methodology correctly:", "Deductions");
		Dedu[1] = new Item(-2, "Runtime errors other than concurrent modification errors, which we ignore for now.", "Deductions");
		Dedu[2] = new Item(-10, "Missing a lab deadline (for each lab deadline missed)", "Deductions");
		
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
		return Dsign.addItems() + host.addItems() + customer.addItems() + wait.addItems() + cook.addItems() + ani.addItems() + sc.addItems() + git.addItems() + deduc.addItems();
	}
	
	public static void main(String[] args) {
		Restaurant_v21_gui test = new Restaurant_v21_gui();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(gen)) {
			String output = "Your Assignment v2 Submission Received " + getTotal() + " points. The following error(s) were encountered:\n";
			output += " 1. Milestone.v2.A - " + (20-Dsign.addItems()) + " points were deducted\n";
			output += Dsign.getErrors();
			output += "\n";
			//		+ (20-Dsign.addItems() == 0 ? "\n" : ("  + error1\n" + "  + error2\n"));
			output += " 2. Milestone.v2.B - " + (40-host.addItems()-customer.addItems()-wait.addItems()-cook.addItems()) + " points were deducted\n";
			output += host.getErrors() + customer.getErrors() + wait.getErrors() + cook.getErrors();
			//		+ (40-host.addItems()-customer.addItems()-wait.addItems()-cook.addItems() == 0 ? "\n" : ("  + error3\n" + "  + error4\n"));;
			output += "\n";
			output += " 3. Milestone.v2.C - " + (35-ani.addItems()-sc.addItems()) + " points were deducted\n";
			output += ani.getErrors() + sc.getErrors();
			//		+ (35-ani.addItems()-sc.addItems() == 0 ? "\n" : ("  + error5\n" + "  + error6\n"));
			output += "\n";
			output += " 4. Git Usage - " + (5-git.addItems()) + " points were deducted\n";
			//		+ (5-git.addItems() == 0 ? "\n" : ("  + error7\n" + "  + error8\n"));
			output += git.getErrors();
			output += "\n";
			if (deduc.addItems() != 0) {
				output += "Also, the following deductions were applied\n";
//				for (int i = 0; i < deduc.getItems().size(); i++)
//					if (deduc.get(i).pointCur != 0)
//						output += "  + (" + deduc.get(i).pointCur + " points) " + deduc.get(i).getDescription() + "\n";
				output += deduc.getErrors();
				output += "\n";
			}
			output += "Other Notes:\n\n";
			output += ">Although you may be getting this message via email, it is an issue on issue on your private GitHub repository and you should interact with it on GitHub\n\n"
					+ ">Any and all discussions pertaining to this deduction **must** be done via comments on this issue. If you are statisfied with the grading, you **must** close the issue. Note that a closed issue implicitly signals that you do not want to discuss this any further. No emails are accepted.\n\n"
					+ ">You have until Oct 9th, 2013 to dispute this deduction. Please allow up to 72 hours for a response to a regrade request. You are expected to follow the _Grading Disputes_ policies outlined in the [course syllabus](http://www-scf.usc.edu/~csci201/syllabus.html).";
			System.out.println(output);
			output.replaceAll("\n","<br />");
			text.setText(output);
			
		}
	}

}
