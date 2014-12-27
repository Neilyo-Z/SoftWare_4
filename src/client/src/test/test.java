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
			    	  writer.write("¶þÊ¦ÐÖ##male##zwxnju@gmail.com");
			      }
			      else if(text[0].compareTo("getPath")==0){
			    	  writer.write("ÄÏ´óÄÏÃÅ##101##½ð´óÂ·##102##Ô¶¶«´óµÀ##103##´óÑ§Éú»î¶¯ÖÐÐÄ£¨´ó»î£©##104");
			      }
			      else if(text[0].compareTo("getComments")==0){
			    	  if(text[3].compareTo("102")==0 ){
			    		  writer.write(""
			    		  		+ "1##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "2##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "3##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "4##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "5##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "6##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "7##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "9##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "10##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "11##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "12##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    		  		+ "13##dwt##Ç°ÃæÁ©Éµ±Æ×²ÁË£¬\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ\n¹þ£¡##"
			    				  );
			    	  }
			    	  else if(text[3].compareTo("103")==0 ){
			    		  writer.write("1222##zwx##ÕâµÀÌ«jb¶ÂÁË£¡##1322##zwx##²»ÈÃÈË»îÀ²£¡##3##zwx"
					    	  		+ "##²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý"
					    	  		+ "²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý²Ý£¡");
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