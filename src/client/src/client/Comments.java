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
		
		 DefaultListModel<Comments> DLM = new DefaultListModel<Comments>();
		if(nodeID==1){
			DLM.addElement(new Comments("朱维希", 1, "这道太jb堵了！") );
			DLM.addElement(new Comments("朱维希", 2, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈！"));
		}
		else if(nodeID==2){
			DLM.addElement(new Comments("猪未洗", 3, "前面俩傻逼撞了，\n哈\n哈\n哈\n哈\n哈\n哈\n哈\n哈\n哈\n哈\n哈\n哈！"));
			DLM.addElement(new Comments("猪未洗", 4, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 5, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 6, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 7, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 8, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 9, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 10, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 11, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 12, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 13, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
			DLM.addElement(new Comments("猪未洗", 14, "前面俩傻逼撞了，哈哈哈哈哈哈哈哈哈哈哈哈！"));
		}
		list.setModel(DLM);
	}
	public static void sendComments(int nodeID,String text) throws Exception  {
		System.out.printf("sendComments( %d , %s )\n",nodeID,text);
	}
	public static void delComments(JList<Comments> list, int delNumber) throws Exception  {
		
	}
}