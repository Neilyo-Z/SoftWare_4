package client;

import javax.swing.DefaultListModel;
import javax.swing.JList;

class Comments {
	public String uID;
	public int cID;
	public String text;
	public Comments(String uID, int cID, String text){
		this.text = text;
		this.uID = uID;
		this.cID = cID;
	}
	
	public static void getComments(JList<Comments> list, int nodeID) throws Exception  {
		String str = "getComments##"+MainWindow.uID+"##"+MainWindow.pw+"##"+String.valueOf(nodeID);
		String echo = client.sendToServer(str);
		//处理回答
	    if( echo.compareTo("false") == 0 ){
			throw new Exception ("获取评论失败！");
		}
	    DefaultListModel<Comments> DLM = new DefaultListModel<Comments>();
	    if( echo.compareTo("null") != 0 ){
			String[] commentList = echo.split("##");
			if(commentList.length %3 !=0){
				throw new Exception ("获取的评论格式不正确！\n请刷新后重试");
			}
			for(int i=0 ; i<commentList.length ;i+=3){
				DLM.addElement(new Comments(commentList[i+1], Integer.parseInt(commentList[i]), commentList[i+2]) );
			}
		}
	    list.setModel(DLM);
	}
	public static void sendComments(int nodeID,String text) throws Exception  {
		String str = "addComment##"+MainWindow.uID+"##"+MainWindow.pw+"##"+String.valueOf(nodeID)+"##"+text;
		String echo = client.sendToServer(str);
		//处理回答
	    if( echo.compareTo("false") == 0 ){
			throw new Exception ("发表评论失败！");
		}
	}
	public static void delComments(int delNumber) throws Exception  {
		String str = "delComment##"+MainWindow.uID+"##"+MainWindow.pw+"##"+String.valueOf(delNumber);
		String echo = client.sendToServer(str);
		//处理回答
	    if( echo.compareTo("false") == 0 ){
			throw new Exception ("删除评论失败！\n可能评论不存在");
		}
	}
}