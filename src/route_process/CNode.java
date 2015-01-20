package route_process;

public class CNode {
	private int id;
	private String name;
	public CNode(int newID, String newName){
		id = newID;
		name = newName;
	}
	public int getID(){
		return id;
	}
	public String getName(){
		return name;
	}
}