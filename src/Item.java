public class Item {
	int pointMax;
	int pointCur;
	String description;
	String milestone;
	public Item(int point, String desc, String mile) {
		pointMax = point;
		pointCur = pointMax > 0 ? pointMax : 0;
		description = desc;
		milestone = mile;
	}
	public int getPointMax() {
		return pointMax;
	}
	public void setPointMax(int pointMax) {
		this.pointMax = pointMax;
	}
	public int getPointCur() {
		return pointCur;
	}
	public void setPointCur(int pointCur) {
		this.pointCur = pointCur;
	}
	public String getDescription() {
		return description;
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
