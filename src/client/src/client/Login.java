package client;

class Login{
	public static void main(String id,char[] pw) throws Exception{
		System.out.println("登录:");
		System.out.println(id);
		System.out.println(pw);
		//将id和pw发送的标志
		int socketSendFlag;
		
		socketSendFlag = 1;
		
		//用户名和密码是否正确的标志
		boolean pwIsRight;
		
		pwIsRight = true;
		
		if( socketSendFlag < 0)
			throw new Exception ("无法建立连接！");
		if( pwIsRight == false )
			throw new Exception ("用户名或密码不正确！");
		
		MainWindow mainWindow = new MainWindow(null);
		mainWindow.main(null, id);
	}
}