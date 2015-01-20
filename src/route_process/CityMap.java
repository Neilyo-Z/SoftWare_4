package route_process;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import user_sql.map_on_mysql;

public class CityMap{
	private CNode[] nodes;
	private CEdge[] edges;
	private int nodeSize;
	private HashMap<String, Integer> nodeIndex;
	public CityMap(int nodeNum, int edgeNum) throws SQLException{
		nodeIndex = new HashMap<>();
		nodeSize = nodeNum;
		nodes = new CNode[nodeNum];
		//get nodes from DB.
		for (int i = 0; i < nodeNum; ++i) {
			String info = map_on_mysql.getNodeInfo(i);
			String content[] = info.split("##");
			int nid = Integer.parseInt(content[0]);
			nodes[i] = new CNode(nid, content[1]);
			nodeIndex.put(content[1], nid);
		}
		edges = new CEdge[edgeNum];
		//get edge from DB.
		for (int i = 0; i < edgeNum; ++i) {
			String info = map_on_mysql.getEdgeInfo(i);
			String content[] = info.split("##");
			int eid = Integer.parseInt(content[0]);
			int startPoint = Integer.parseInt(content[2]);
			int endPoint = Integer.parseInt(content[3]);
			edges[i] = new CEdge(eid, content[1], startPoint, endPoint);
		}
	}
	public int getNodeSize() {
		return nodeSize;
	}
	public CNode getNode(int nID) {
		return nodes[nID];
	}
	public CNode getNode(String nName) {
		if (nodeIndex.containsKey(nName)) {
			return nodes[nodeIndex.get(nName)];
		} else {
			return null;
		}
	}
	public ArrayList<CNode> getNodes(){
		return (ArrayList<CNode>) Arrays.asList(nodes);
	}
	public CEdge getEdge(int eID) {
		return edges[eID];
	}
	public ArrayList<CEdge> getEdges() {
		ArrayList<CEdge> ret = new ArrayList<>();
		Collections.addAll(ret, edges);
		return ret;
	}
}

