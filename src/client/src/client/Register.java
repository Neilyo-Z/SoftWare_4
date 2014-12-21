package client;

class Register{
	public static void main(String id,char[] pw) throws Exception{
		System.out.println("注册:");
		System.out.println(id);
		System.out.println(pw);
		//将id和pw发送的标志
		int socketSendFlag;
		
		socketSendFlag = 1;
		
		if( socketSendFlag < 0)
			throw new Exception ("无法建立连接！");
	}
}