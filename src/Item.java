/**
 *The following class is a data structure used to encapsulate all the necessary data associated with a requirement
 * @author Tobias
 *
 */
public class Item {
	/**The maximum amount of points*/
	int pointMax;
	/**The current amount of points*/
	int pointCur;
	/**What they see*/
	String description;
	/**Internal descriptor*/
	String milestone;
	/**Comment associated with deduction*/
	String comment;
	/**
	 * Constructor.
	 * @param point The maximum amount of points for this requirement
	 * @param desc A description of the requirement for students to see
	 * @param mile A misnamed internal descriptor used for evaluating other things
	 */
	public Item(int point, String desc, String mile) {
		pointMax = point;
		if (mile.equalsIgnoreCase("EC"))
			pointCur = 0;
		else
			pointCur = pointMax > 0 ? pointMax : 0;
		description = desc;
		milestone = mile;
		comment = "";
	}
	public String getComment() {
		return comment;
	}
	public int getPointMax() {
		return pointMax;
	}
	public void setPointMax(int pointMax) {
		this.pointMax = pointMax;
	}
	public int getPointCur() {
		if (milestone.equalsIgnoreCase("alterations")) {
			if (pointCur == 0)
				return 0;
			else if (pointCur < 3)
				return -10;
			else if (pointCur < 6)
				return -15;
			else
				return -25;
		}
		return pointCur;
	}
	public void setPointCur(int pointCur) {
		this.pointCur = pointCur;
	}
	public String getDescription() {
		return description;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMilestone() {
		return milestone;
	}
	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}
	public String toString() { 
		return milestone + " - " + description + " (" + pointMax + " points)";
	}
}
