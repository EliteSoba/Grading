public class Item {
	int pointMax;
	int pointCur;
	String description;
	String milestone;
	String comment;
	public Item(int point, String desc, String mile) {
		pointMax = point;
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
