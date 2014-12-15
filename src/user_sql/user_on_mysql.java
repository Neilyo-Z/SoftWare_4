package user_sql;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class user_on_mysql  {
	static Connection con;
	static String url="jdbc:mysql://localhost:3306/user_db";
	static String username="root";
	static Statement statement;
	static String status_off="offline";
	static String status_on="online";
	//初始化与数据库的连接
	public static void init() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,"root","38203136");
			statement=con.createStatement();
			System.out.println("Connection Successful!");
			//建立tables user_info,status标识是否在线
			String create_table_user_info = "create table if not exists user_info (user_id varchar(255) not null primary key,"
					+ "password varchar(255) not null," + "nickname varchar(255) not null," 
					+ "sex varchar(255) not null,"
					+ "e_mail varchar(255) not null," + "status varchar(255) not null );";
			statement.executeUpdate(create_table_user_info);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//关闭与数据库的连接
	public static void close() throws SQLException{
		con.close();
	}
	public static boolean Login(String user_id,String password) throws SQLException{
		synchronized (statement) {
			//选出处于离线状态的符合用户名密码的用户
			String query="select * from user_info where user_id='"+user_id
					+"' and password='"+password+"' and status='"+status_off+"';";
			ResultSet rs=statement.executeQuery(query);
			//若不存在符合条件的用户则登录失败
			if(!rs.next())
				return false;
			else {
				//更新该用户的status为status_on
				String update_query="update user_info set status='"+status_on+
						"' where user_id='"+user_id+"' and password='"+password+"';";
				statement.executeUpdate(update_query);
				return true;
			}
		}
	}
}
