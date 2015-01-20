package user_sql;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class user_on_mysql  {
	public static final String Separater = "##" ;
	static Connection con;
	static String url="jdbc:mysql://localhost:3306/software_4_db";
	static String username="root";
	static Statement statement;
	static String status_off="offline";
	static String status_on="online";
	
	//初始化与数据库的连接
	//初始化表user_info
	//user_info: user_id, password, nickname, sex, e_mail, status
	public static void init_user_info() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,"root","qazwsx");
			statement = con.createStatement();
			System.out.println("Connection Successful!");
			//建立tables user_info,status标识是否在线
			String create_table_user_info = 
					"create table if not exists "
					+ "user_info ("
					+ "user_no INT UNSIGNED not null primary key,"
					+ "user_id varchar(255) not null,"
					+ "password varchar(255) not null," 
					+ "nickname varchar(255)," 
					+ "sex varchar(255),"
					+ "e_mail varchar(255)," 
					+ "status varchar(255) not null );";
			statement.executeUpdate(create_table_user_info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//关闭与数据库的连接
	public static void close() throws SQLException{
		con.close();
	}
	//注册
	public static boolean Register(String user_id,String password) throws SQLException{
		synchronized (statement) {
			//检查user_id是否重复
			String query =
					"select * from user_info where "
					+ "user_id = '" + user_id
					+ "';";
			ResultSet rs =
					statement.executeQuery(query);
			//若不重复则注册成功
			if(!rs.next()) {
				//插入该用户信息
				String insert_query =
						"insert into user_info(user_id,password,status) "
						+ "VALUES ('"
						+ user_id + "','"
						+ password + "','" 
						+ status_off 
						+ "');";
				statement.executeUpdate(insert_query);
				return true;
			}
			else return false;
		}
	}
	//登录
	public static boolean logIn(String user_id,String password) throws SQLException{
		synchronized (statement) {
			//选出处于离线状态的符合用户名密码的用户
			String query =
					"select * from user_info where "
					+ "user_id = '" + user_id 
					+ "' and password = '" + password
					+ "' and status = '" + status_off 
					+ "';";
			ResultSet rs = statement.executeQuery(query);
			//若不存在符合条件的用户则登录失败
			if(!rs.next())
				return false;
			else {
				//更新该用户的status为status_on
				String update_query =
						"update user_info set "
						+ "status='" + status_on
						+ "' where user_id='" + user_id
						+ "' and password='" + password
						+ "';";
				statement.executeUpdate(update_query);
				return true;
			}
		}
	}
	//登出
	public static boolean logOut(String user_id,String password) throws SQLException{
		synchronized (statement) {
			//选出处于在线状态的符合用户名密码的用户
			String query =
					"select * from user_info where "
					+ "user_id='" + user_id
					+ "' and password='" + password
					+ "' and status='" + status_on 
					+ "';";
			ResultSet rs = statement.executeQuery(query);
			//若不存在符合条件的用户则登出失败
			if(!rs.next())
				return false;
			else {
				//更新该用户的status为status_off
				String update_query =
						"update user_info set "
						+ "status='" + status_off
						+ "' where "
						+ "user_id='" + user_id + "' and "						
						+ "password='" + password
						+ "';";
				statement.executeUpdate(update_query);
				return true;
			}
		}
	}
	//编辑个人信息
	public static boolean edit(String user_id,String password,String nickname,
			String sex,String e_mail) throws SQLException{
		synchronized (statement) {
			//选出合法用户，注：必须在线才允许修改
			String query =
					"SELECT * FROM user_info WHERE "
					+ "user_id = '" + user_id
					+ "' and password = '" + password
					+ "' and status = '" + status_on
					+ "';";
			ResultSet rs = statement.executeQuery(query);
			//合法用户必须存在
			if(!rs.next())
				return false;
			else {
				//更新该用户个人信息
				String update_query=
					"UPDATE user_info SET "
					+ "password = '" + password 
					+ "',nickname = '" + nickname
					+ "',sex = '" + sex
					+ "',e_mail = '" + e_mail
					+ "' WHERE user_id = '" + user_id
					+ "';";
				statement.executeUpdate(update_query);
				return true;
			}
		}
	}
	//获得用户信息
	public static String getUserInfo (String user_id, String password)
	throws SQLException{
		synchronized (statement) {
			String UserInfo;
			String query =
					"SELECT * FROM user_info WHERE " + 
					"user_id = '" + user_id +
					"' AND " +
					"password = '" + password +
					"';";
			ResultSet rs = statement.executeQuery(query);
			//用户非法则返回一个分隔符
			if(!rs.next())
				return Separater;
			//否则返回他们的相加
			else {
				UserInfo = 
						rs.getString("user_id") + Separater +
						rs.getString("password") + Separater +
						rs.getString("nickname") + Separater +
						rs.getString("sex") + Separater +
						rs.getString("e_mail") + Separater +
						rs.getString("status");
				return UserInfo;
			}
		}
	}
}
