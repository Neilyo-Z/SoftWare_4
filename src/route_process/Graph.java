package route_process;

import java.beans.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph{
	private double[] distance;
	private ArrayList<DirectedEdge>[] edgeList;
	int nodeSize;
	Statement state;
	@SuppressWarnings("unchecked")
	public Graph(CityMap source){
		nodeSize = source.getNodeSize();
		distance = new double[nodeSize];
		edgeList = (ArrayList<Graph.DirectedEdge>[]) new ArrayList[nodeSize];
		for (CEdge ce: source.getEdges()){
			int[] linked = ce.getLinkedNode();
			edgeList[linked[0]].add(new DirectedEdge(
					ce.getID(), linked[1], ce.getLength(), ce.getLikes()));
			edgeList[linked[1]].add(new DirectedEdge(
					ce.getID(), linked[0], ce.getLength(), ce.getLikes()));
		}
	}
	public class DirectedEdge{
		double cost;
		int endPoint, id;
		public DirectedEdge(int edgeID, int endID, double length, int likes) {
			id = edgeID;
			endPoint = endID;
			//TODO: more complicated cost function;
			cost = length;
		}
	}
	//run Dijkstra algorithm.
	public ArrayList<Integer> getPath(int sID, int tID) {
		//initialize.
		Arrays.fill(distance, -1.0);
		distance[sID] = 0.0;
		boolean[] visit = new boolean[nodeSize];
		//for record.
		int[] lastEdgeID = new int[nodeSize];
		int[] lastNodeID = new int[nodeSize];
		int u = sID;
		for (int k = 1; k < nodeSize; ++k) {
			//update current node.
			visit[u] = true;
			if (u == tID) {
				break;
			}
			//update linked nodes.
			for (DirectedEdge i: edgeList[u]) {
				int v = i.endPoint;
				if (visit[v]) {
					continue;
				}
				if ((distance[v] < 0) || (distance[v] > distance[u] + i.cost)) {
					distance[v] = distance[u] + i.cost;
					lastEdgeID[v] = i.id; 
					lastNodeID[v] = u; 
				}
			}
			//find next node.
			double top = 1e20;
			for (int v = 0; v < nodeSize; ++v) {
				if (visit[v]) {
					continue;
				}
				if ((distance[v] >= 0.0) && (distance[v] < top)) {
					top = distance[v];
					u = v;
				}
			}
		}
		//get path from record.
		ArrayList<Integer> retNodes = new ArrayList<>(), retEdges = new ArrayList<>();
		u = tID;
		while (u != sID) {
			retEdges.add(lastEdgeID[u]);
			retNodes.add(lastNodeID[u]);
			u = lastNodeID[u];
		}
		//return format: (length,nodeList,edgeList);
		ArrayList<Integer> ret = new ArrayList<>();
		ret.add(retNodes.size());
		ret.addAll(retNodes);
		ret.addAll(retEdges);
		return ret;
	}
}
