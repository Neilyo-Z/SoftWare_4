package client;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

class client{
	public static String host = "127.0.0.1";
	public static int port = 4201;
	public static Writer writer;
	public static Reader reader;
	
	public static void main(String[] args){
		WelcomeWindow.main(null);
	}
	
	public static void connectToServer() throws Exception{
		try{
			@SuppressWarnings("resource")
			Socket socket = new Socket(host, port);
			//设置超时间为5秒  
		    socket.setSoTimeout(3000);  
			client.writer = new OutputStreamWriter(socket.getOutputStream(),"GBK");
			client.reader = new InputStreamReader(socket.getInputStream(),"GBK");
		}
		catch(Exception socketException){
			throw new Exception("无法与服务器建立连接！请检查网络设置");
		}
	}
	
	public static String sendToServer(String str)throws Exception{
		//向输出流写入发出的内容
		client.writer.write(str);
		client.writer.flush();
		//接收一个服务器回答
		char chars[] = new char[2000];  
	    int len;  
	    StringBuilder sb = new StringBuilder();  
	    len=client.reader.read(chars);  
	    sb.append(new String(chars, 0, len));
	    return sb.toString();
	}
}