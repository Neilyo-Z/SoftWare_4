package server;	

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import route_process.CNode;
import route_process.CityMap;
import route_process.Graph;
import user_sql.map_on_mysql;
import user_sql.user_on_mysql;

public class Server {
	ServerSocket serverSock;
	HashMap<String, String> userRecord;
	static int commentsLimit = 5;
	CityMap mapData;
	Graph graphData;
	private Server() {
	}
	public static void main(String args[])
	{
		new Server().start();
	}
	public void start()
	{
		try {
			user_on_mysql.init_user_info();
			map_on_mysql.init_map_db();
			userRecord = new HashMap<String, String>();
			int nodeSize = map_on_mysql.getNodeNumber();
			int edgeSize = map_on_mysql.getEdgeNumber();
			mapData = new CityMap(nodeSize, edgeSize);
			graphData = new Graph(mapData);
			System.out.println("initialized.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			serverSock = new ServerSocket(4201);
			while (true) {
				Socket sock = serverSock.accept();
				new Thread(new Dealer(sock)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		serverSock.close();
	}
	public class Dealer implements Runnable{
		private Socket sock;
		public Dealer(Socket socket){
			sock = socket;
			System.out.println("New connection accepted");
		}
		@Override
		public void run() {
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new InputStreamReader(sock.getInputStream(), "GBK"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			while (true) {
				StringBuilder msg = new StringBuilder();
				//Warning:current msg length limit = 2048.
				CharBuffer temp = CharBuffer.allocate(2048);
				try {
					//TODO: support long message.
					reader.read(temp);
					msg.append(temp.flip().toString());
				} catch (SocketException e) {
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("Get mesage:" + msg.toString());
				try {
					handle(msg.toString().split("##"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				reader.close();
				sock.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		private void handle(String[] content) throws Exception {
			//request without <UID, PWD> check;
			//msgType: content[0]
			switch (content[0]) {
			case "register"://userID, userPWD
				doRegister(content[1], content[2]);
				return;
			case "logIn"://userID, userPWD;
				doLogIn(content[1], content[2]);
				return;
			case "logOut"://userID, userPWD;
				doLogOut(content[1], content[2]);
				return;
			default:
				break;
			}
			
			//check <UID, PWD> pair
			if (!userRecord.containsKey(content[1])) {
				doInvalidRequest();
				return;
			} else if (!userRecord.get(content[1]).equals(content[2])) {
				doInvalidRequest();
				return;
			}
			
			//request with check.
			//msgType: content[0]
			switch (content[0]) {
			case "edit"://userID, userPWD, userSex, userEmail;
				doEditUserInfo(content[1], content[2], content[3], content[4], content[5]);
				return;
			case "getUserInfo"://userID, userPWD;
				doGetUserInfo(content[1], content[2]);
				return;
			case "getPath"://userID, userPWD, startID, endID;
				CNode startPoint = mapData.getNode(content[3]);
				CNode endPoint = mapData.getNode(content[4]);
				if ((startPoint == null) || (endPoint == null)) {
					doInvalidRequest();
				} else {
					doGetPath(startPoint.getID(), endPoint.getID());
				}
				return;
			case "getComments"://userID, userPWD, edgeID, (edgeNum);
				doGetComments(Integer.parseInt(content[3]));
				return;
			case "addComments"://userID, userPWD, edgeID, text;
				doAddComment(content[1], content[2], Integer.parseInt(content[3]), content[4]);
				return;
			default:
				doInvalidRequest();
				return;
			}
		}
		private void doInvalidRequest() {
			System.out.println("Respond Invalid Request.");
			Writer writer = null;
			try {
				writer = new OutputStreamWriter(sock.getOutputStream(), "GBK");
				writer.write("null");
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		private void doAddComment(String userID, String userPWD, int edgeID,
				String text) throws Exception {
			System.out.println("Respond addComment Request.");
			//TODO: support tags;
			map_on_mysql.addComent(edgeID, userID, userPWD, text, 0, 0, 0);
			Writer writer = new OutputStreamWriter(sock.getOutputStream(), "GBK");
			boolean ret = user_on_mysql.logIn(userID, userPWD);
			if (ret) {
				writer.write("true");
			} else {
				writer.write("false");
			}			
			writer.flush();			
		}
		private void doGetComments(int edgeID) throws Exception {
			System.out.println("Respond getComments Request.");
			ArrayList<String> ret = map_on_mysql.getComments(edgeID, commentsLimit);
			Writer writer = new OutputStreamWriter(sock.getOutputStream(), "GBK");
			for (int i = 0; i < ret.size(); ++i) {
				if (i > 0) {
					writer.write("##");
				}
				//TODO: support more complicated format.
				String[] info = ret.get(i).split("##");
				writer.write(info[0]+"##"+info[2]+"##"+info[3]);
			}
			writer.flush();
		}
		private void doGetPath(int startPoint,int endPoint) throws Exception {
			System.out.println("Respond getPath Request.");
			// TODO Auto-generated method stub
			ArrayList<Integer> path = graphData.getPath(startPoint, endPoint);
			Writer writer = new OutputStreamWriter(sock.getOutputStream(), "GBK");
			int length = path.get(0);
			for (int i = 0; i < length; ++i) {
				if (i > 0) {
					writer.write("##");
				}
				writer.write(mapData.getNode(path.get(i+1)).getName());
				writer.write("##");
				writer.write(String.valueOf((path.get(i+length))));
			}
		}
		private void doGetUserInfo(String userID, String userPWD) throws Exception {
			System.out.println("Respond getUserInfo Request.");
			String ret = user_on_mysql.getUserInfo(userID, userPWD);
			Writer writer = new OutputStreamWriter(sock.getOutputStream(), "GBK");
			writer.write(ret);
			writer.flush();
		}
		private void doEditUserInfo(String userID, String userPWD, String userNick,
				String userSex, String userEmail) throws Exception{
			System.out.println("Respond editUserInfo Request.");
			boolean ret = user_on_mysql.edit(userID, userPWD, userNick, userSex, userEmail);
			Writer writer = new OutputStreamWriter(sock.getOutputStream(), "GBK");
			if (ret) {
				writer.write("true");
			} else {
				writer.write("false");
			}						
			writer.flush();
		}
		private void doLogOut(String userID, String userPWD) throws Exception {
			System.out.println("Respond logOut Request.");
			boolean ret = user_on_mysql.logOut(userID, userPWD);
			Writer writer = new OutputStreamWriter(sock.getOutputStream(), "GBK");
			if (ret) {
				writer.write("true");
				userRecord.remove(userID);
			} else {
				writer.write("false");
			}			
			writer.flush();
		}
		private void doLogIn(String userID, String userPWD) throws Exception{
			System.out.println("Respond logIn Request.");
			Writer writer = new OutputStreamWriter(sock.getOutputStream(), "GBK");
			boolean ret = user_on_mysql.logIn(userID, userPWD);
			if (ret) {
				writer.write("true");
				userRecord.put(userID, userPWD);
			} else {
				writer.write("false");
			}			
			writer.flush();
		}
		private void doRegister(String userID, String userPWD) throws Exception{
			System.out.println("Respond register Request.");
			boolean ret = user_on_mysql.Register(userID, userPWD);
			Writer writer = new OutputStreamWriter(sock.getOutputStream(), "GBK");
			if (ret) {
				writer.write("true");
				userRecord.put(userID, userPWD);
			} else {
				writer.write("false");
			}
			writer.flush();
		}
	}
}
