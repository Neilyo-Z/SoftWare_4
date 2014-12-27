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
		String str = "getPath##"+MainWindow.uID+"##"+MainWindow.pw+"##"+startPoint+"##"+endPoint;
		String echo = client.sendToServer(str);
		//处理回答
	    if( echo.compareTo("false") == 0 ){
			throw new Exception ("获取评论失败！");
		}			
	    DefaultListModel<MapNode> DLM = new DefaultListModel<MapNode>();
	    if( echo.compareTo("null") != 0 ){
			String[] nodeList = echo.split("##");
			if(nodeList.length %2 !=0){
				throw new Exception ("获取的查询路径结果格式不正确！\n请重试");
			}
			for(int i=0 ; i<nodeList.length ;i+=2){
				DLM.addElement(new MapNode(nodeList[i], Integer.parseInt(nodeList[i+1])));
			}
			
	    }
	    list.setModel(DLM);
	}
	public static void setLimit()throws Exception  {
		
	}
}