package client;

import java.util.Vector;

class MapNode{
	public String nodeName;
	public int nodeID;
	public MapNode(String nodeName, int nodeID) {
		this.nodeName = nodeName;
		this.nodeID = nodeID;
	}
	public static void getPath(Vector<MapNode> vector) throws Exception  {
		
		vector.add(new MapNode("南门", 1) );
		vector.add(new MapNode("金大路", 2));
		vector.add(new MapNode("远东大道", 3));
		
	}
	public static void setLimit()throws Exception  {
		
	}
}