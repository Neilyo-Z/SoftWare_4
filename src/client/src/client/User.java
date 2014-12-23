package client;

class User{
	public static void Login(String id,char[] pw) throws Exception{
		System.out.println("登录:");
		System.out.println(id);
		System.out.println(pw);
		//将id和pw发送是否成功的标志
		int socketSendFlag;
		
		socketSendFlag = 1;
		
		//用户名和密码是否正确的标志
		boolean pwIsRight;
		
		pwIsRight = true;
		
		if( socketSendFlag < 0)
			throw new Exception ("无法建立连接！");
		if( pwIsRight == false )
			throw new Exception ("用户名或密码不正确！");
		
		MainWindow.id = id;
		MainWindow.pw = String.valueOf(pw);
		MainWindow.main(null);
	}
	
	public static void Register(String id,char[] pw) throws Exception{
		System.out.println("注册:");
		System.out.println(id);
		System.out.println(pw);
		//将id和pw发送的标志
		int socketSendFlag;
		
		socketSendFlag = 1;
		
		if( socketSendFlag < 0)
			throw new Exception ("无法建立连接！");
	}
	
	public static void Logout(String id,String pw) throws Exception{
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