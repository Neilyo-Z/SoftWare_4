package route_process;

public class CEdge{
	private int id;
	private String name;
	private int[] linkedNode;
	private double length;
	private int likes;
	public CEdge(int newID, String newName, int newNodeA, int newNodeB){
		linkedNode = new int[2];
		name = newName;
		id = newID;
		linkedNode[0] = newNodeA;
		linkedNode[1] = newNodeB;
	}
	public int getID(){
		return id;
	}
	public String getName(){
		return name;
	}
	public int[] getLinkedNode(){
		int[] ret = new int[2];
		ret[0] = linkedNode[0];
		ret[1] = linkedNode[1];
		return ret;
	}
	public double getLength() {
		return length;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int newLikes) {
		likes = newLikes;
	}
}