package client;

class Logout{
	public static void main(String id,String pw) throws Exception{
		System.out.println("登出:");
		System.out.println(id);
		System.out.println(pw);
		//将id和pw发送是否成功的标志
		int socketSendFlag;
		
		socketSendFlag = 1;
		

		
		if( socketSendFlag < 0)
			throw new Exception ("无法建立连接！");
	}
}