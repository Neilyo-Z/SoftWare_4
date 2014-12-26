package client;

class User{
	public static void Login(String id,char[] pw) throws Exception{
		String str = "logIn##"+id+"##"+String.valueOf(pw);
		String echo = client.sendToServer(str);
		//处理回答
	    if( echo.compareTo("false") == 0 ){
			throw new Exception ("用户名或密码不正确！");
		}
		else{
			MainWindow.uID = id;
			MainWindow.pw = String.valueOf(pw);
			MainWindow.main(null);
		}
	}
	
	public static void Register(String id,char[] pw) throws Exception{
		String str = "register##"+id+"##"+String.valueOf(pw);
		String echo = client.sendToServer(str);
		//处理回答
	    if( echo.compareTo("false") == 0 ){
			throw new Exception ("注册不成功！\n可能由于用户名与他人重复");
		}
	}
	
	public static void Logout(String id,String pw) throws Exception{
		String str = "logOut##"+id+"##"+String.valueOf(pw);
		String echo = client.sendToServer(str);
		//处理回答
	    if( echo.compareTo("false") == 0 ){
			throw new Exception ("注销失败！");
		}
	}
}