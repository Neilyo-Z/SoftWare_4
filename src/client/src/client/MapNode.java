package client;

import javax.swing.DefaultListModel;
import javax.swing.JList;

class MapNode{
	public String nodeName;
	public int nodeID;
	public MapNode(String nodeName, int nodeID) {
		this.nodeName = nodeName;
		this.nodeID = nodeID;
	}
	public static void getPath(JList<MapNode> list,String startPoint,String endPoint) throws Exception  {
		System.out.printf("getPath( %s , %s )\n",startPoint,endPoint);
		DefaultListModel<MapNode> DLM = new DefaultListModel<MapNode>();
		
		DLM.addElement(new MapNode("南门", 1) );
		DLM.addElement(new MapNode("金大路", 2));
		DLM.addElement(new MapNode("远东大道", 3));

		list.setModel(DLM);
	}
	public static void setLimit()throws Exception  {
		
	}
}