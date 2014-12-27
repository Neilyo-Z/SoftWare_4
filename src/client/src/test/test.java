import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;


class test{
	public static void main(String arg[]) {
		try{
			      int port = 4201;  
			      ServerSocket server = new ServerSocket(port);  
			      Socket socket = server.accept();  
			      Reader reader = new InputStreamReader(socket.getInputStream(),"GBK");  
			      while(true){
			      char chars[] = new char[64];  
			      int len;  
			      StringBuilder sb = new StringBuilder();  
			      len=reader.read(chars);  
			      sb.append(new String(chars, 0, len));  
			      System.out.println("from client: " + sb);  
			      Writer writer = new OutputStreamWriter(socket.getOutputStream(),"GBK");  
			      String[] text = sb.toString().split("##");  
			      if (text[0].compareTo("logIn")==0){
			    	  if(text[1].compareTo("zwx")==0 && text[2].compareTo("zwx")==0){
			    		  writer.write("true");
			    	  }
			    	  else {
			    		  writer.write("false");
			    	  }
			      }
			      else if(text[0].compareTo("register")==0){
			    	  if(text[1].compareTo("dwt")==0 ){
			    		  writer.write("true");
			    	  }
			    	  else {
			    		  writer.write("false");
			    	  }
			      }
			      else if(text[0].compareTo("logOut")==0){
			    	  writer.write("true");
			      }
			      else if(text[0].compareTo("edit")==0){
			    	  writer.write("true");
			      }
			      else if(text[0].compareTo("getUserInfo")==0){
			    	  writer.write("��ʦ��##male##zwxnju@gmail.com");
			      }
			      else if(text[0].compareTo("getPath")==0){
			    	  writer.write("�ϴ�����##101##���·##102##Զ�����##103##��ѧ������ģ���##104");
			      }
			      else if(text[0].compareTo("getComments")==0){
			    	  if(text[3].compareTo("102")==0 ){
			    		  writer.write(""
			    		  		+ "1##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "2##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "3##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "4##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "5##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "6##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "7##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "9##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "10##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "11##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "12##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    		  		+ "13##dwt##ǰ����ɵ��ײ�ˣ�\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n��\n����##"
			    				  );
			    	  }
			    	  else if(text[3].compareTo("103")==0 ){
			    		  writer.write("1222##zwx##���̫jb���ˣ�##1322##zwx##�����˻�����##3##zwx"
					    	  		+ "##�ݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲ�"
					    	  		+ "�ݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݲݣ�");
			    	  }
			    	  else {
			    		  writer.write("null");
			    	  }
			      }
			      else if(text[0].compareTo("addComment")==0){
			    	  writer.write("true");
			      }
			      else if(text[0].compareTo("delComment")==0){
			    	  writer.write("true");
			      }			  
			      else{
			    	  writer.write("false");
			      }
			      writer.flush();
		      }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}