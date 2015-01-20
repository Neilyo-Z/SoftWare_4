package user_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


public class map_on_mysql {
	public static int min(int a, int b) {
		if(a>b)
			return b;
		else
			return a;
	}
	public static final String Separater = "##" ;
	static Connection con;
	static String url="jdbc:mysql://localhost:3306/software_4_db";
	static String username="root";
	static Statement statement;
	static String status_off="offline";
	static String status_on="online";
	static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//create table Nodes:n_id, n_name, n_x, n_y
	//create table Edges:e_id, e_name, n_id_1, n_id_2, distance, tag1, tag2, tag3
	//create table Comments:c_id, c_date, e_id, user_id, like_n, jubao_n, article, tag1,tag2, tag3
	//初始化与数据库的连接
	public static void init_map_db() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,"root","qazwsx");
			statement = con.createStatement();
			System.out.println("Connection Successful!");
			//create table Nodes
			String create_table_Nodes = 
					"create table if not exists "
					+ "Nodes ("
					+ "n_id INT UNSIGNED not null primary key AUTO_INCREMENT,"
					+ "n_name varchar(255) not null," 
					+ "n_x double UNSIGNED not null," 
					+ "n_y double UNSIGNED not null"
					+ ");";
			//create table Edges
			String create_table_Edges = 
					"create table if not exists "
					+ "Edges ("
					+ "e_id INT UNSIGNED not null primary key AUTO_INCREMENT,"
					+ "e_name varchar(255) not null,"
					+ "n_id_1 INT UNSIGNED not null," 
					+ "n_id_2 INT UNSIGNED not null," 
					+ "distance double UNSIGNED not null,"
					+ "tag1 INT UNSIGNED not null,"
					+ "tag2 INT UNSIGNED not null,"
					+ "tag3 INT UNSIGNED not null,"
					+ "foreign key(n_id_1) references Nodes(n_id)," 
					+ "foreign key(n_id_2) references Nodes(n_id)" 
					+ ");";
			//create table Comments
			String create_table_Comments = 
					"create table if not exists "
					+ "Comments ("
					+ "c_id INT UNSIGNED not null primary key AUTO_INCREMENT,"
					+ "c_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP ,"
					+ "e_id INT UNSIGNED not null," 
					+ "user_no INT UNSIGNED not null," 
					+ "like_n INT UNSIGNED not null,"
					+ "jubao_n INT UNSIGNED not null,"
					+ "article TINYTEXT not null,"
					+ "tag1 INT UNSIGNED not null,"
					+ "tag2 INT UNSIGNED not null,"
					+ "tag3 INT UNSIGNED not null,"
					+ "foreign key(e_id) references Edges(e_id),"
					+ "foreign key(user_no) references user_info(user_no)" 
					+ ");";
			//create table Likes (l_id,user_id,c_id,likes_n)
			String create_table_Likes =
					"create table if not exists "
					+ "Likes ("
					+ "l_id INT UNSIGNED not null primary key AUTO_INCREMENT, "
					+ "user_no INT UNSIGNED not null,"
					+ "c_id INT UNSIGNED not null,"
					+ "likes_n INT UNSIGNED not null,"
					+ "foreign key(user_no) references user_info(user_no),"
					+ "foreign key(c_id) references Comments(c_id) "
					//保证评论删除后相应的点赞记录也被删除
					+ "on delete cascade on update cascade"
					+ ");";
			
			statement.executeUpdate(create_table_Nodes);
			statement.executeUpdate(create_table_Edges);
			statement.executeUpdate(create_table_Comments);
			statement.executeUpdate(create_table_Likes);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//close the connection
	public static void close() throws SQLException{
		con.close();
	}

	//getNodeId whose n_name is n_name
	public static int getNodeId(String n_name) throws SQLException{
		synchronized (statement) {
			//SELECT
			String query =
					"select * from Nodes where "
					+ "n_name = '" + n_name
					+ "';";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(rs.next()) {
				//return the id
				return rs.getInt("n_id");
			}
			else return -1;
		}
	}

	//getNodeNumber 
	//gives the total number of table Nodes
	public static int getNodeNumber() throws SQLException{
		synchronized (statement) {
			//SELECT
			String query =
					"select COUNT(n_id) AS NumberOfNodes from Nodes "
					+ ";";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(rs.next()) {
				//return the number
				return rs.getInt("NumberOfNodes");
			}
			else return -1;
		}
	}
	
	//getNodeInfo whose n_id is n_id
	public static String getNodeInfo(int n_id) throws SQLException{
		synchronized (statement) {
			//SELECT
			String query =
					"select * from Nodes where "
					+ "n_id = " + String.valueOf(n_id)
					+ ";";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(rs.next()) {
				//return the Info
				String NodeInfo =
						rs.getString("n_name") + Separater
						+String.valueOf(rs.getDouble("n_x")) + Separater
						+String.valueOf(rs.getDouble("n_y"));
				return NodeInfo;
			}
			else return Separater;
		}
	}

	//getEdgeId whose e_name is e_name
	public static int getEdgeId(String e_name) throws SQLException{
		synchronized (statement) {
			//SELECT
			String query =
					"select * from Edges where "
					+ "e_name = '" + e_name
					+ "';";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(rs.next()) {
				//return the id
				return rs.getInt("e_id");
			}
			else return -1;
		}
	}

	//getEdgeNumber 
	//gives the total number of table Edges
	public static int getEdgeNumber() throws SQLException{
		synchronized (statement) {
			//SELECT
			String query =
					"select COUNT(e_id) AS NumberOfEdges from Edges "
					+ ";";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(rs.next()) {
				//return the number
				return rs.getInt("NumberOfEdges");
			}
			else return -1;
		}
	}

	
	//getEdgeInfo whose e_id is e_id
	public static String getEdgeInfo(int e_id) throws SQLException{
		synchronized (statement) {
			//SELECT
			String query =
					"select * from Edges where "
					+ "e_id = " + String.valueOf(e_id)
					+ ";";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(rs.next()) {
				//return the Info
				String EdgeInfo =
						rs.getString("e_name") + Separater
						+String.valueOf(rs.getInt("n_id_1")) + Separater
						+String.valueOf(rs.getInt("n_id_2")) + Separater
						+String.valueOf(rs.getDouble("distance")) + Separater
						+String.valueOf(rs.getInt("tag1")) + Separater
						+String.valueOf(rs.getInt("tag2")) + Separater
						+String.valueOf(rs.getInt("tag3"));
				return EdgeInfo;
			}
			else return Separater;
		}
	}
	
	//getCommentNumber 
	//gives the number of table Comments where e_id = e_id
	private static int getCommentNumber(int e_id) throws SQLException{
		synchronized (statement) {
			//SELECT
			String query =
					"select COUNT(c_id) AS NumberOfComments from Comments WHERE "
					+"e_id = " + String.valueOf(e_id)
					+ "';";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(rs.next()) {
				//return the number
				return rs.getInt("NumberOfComments");
			}
			else return -1;
		}
	}
	
	//getComments 获得comments_number数量的关于e_id边的评论
	public static ArrayList<String> getComments(int e_id, int comments_number)
	throws SQLException {
		synchronized (statement) {
			ArrayList<String> results = new ArrayList<String>();
			//rowCount 获取Comment中e_id的行数
			int rowCount = 
					getCommentNumber(e_id);
			rowCount = min(rowCount, comments_number);
			//SELECT
			String query =
					"SELECT * from Comments WHERE "
					+ "e_id = " + String.valueOf(e_id)
					+ " ORDER BY c_date DESC"
					+ ";";
			ResultSet rs =
					statement.executeQuery(query);
			//若没有评论信息则返回第一个字符串'0'，否则第一个字符串表示返回的评论数，该评论数为
			//min(comments_number,total_number)
			results.add(String.valueOf(rowCount));
			if(rowCount > 0) {
				while (rs.next()) {
					String CommentInfo = 
							String.valueOf(rs.getInt("c_id")) + Separater
							+ dateformat.format(rs.getTimestamp("c_date")) + Separater
							+ rs.getString("user_id") + Separater
							+ rs.getString("article") + Separater
							+ String.valueOf(rs.getInt("tag1")) + Separater
							+ String.valueOf(rs.getInt("tag2")) + Separater
							+ String.valueOf(rs.getInt("tag3"));
					results.add(CommentInfo);
				}
			}
			return  results;
		}
	}

	
	private static void updateTags(int e_id) throws SQLException {
		synchronized (statement) {
			int tag1,tag2,tag3;
			String query =
					"SELECT SUM(tag1) AS tag1Sum,SUM(tag2) AS tag2Sum,"
					+ "SUM(tag3) AS tag3Sum FROM Comments WHERE "
					+ "e_id = " + String.valueOf(e_id)
					+ ";";
			ResultSet rs =
					statement.executeQuery(query);
			if(rs.next()) {
				tag1 = rs.getInt("tag1Sum");
				tag2 = rs.getInt("tag2Sum");
				tag3 = rs.getInt("tag3Sum");
			}
			else {
				tag1 = 0;
				tag2 = 0;
				tag3 = 0;
			}
			//UPDATE
			String update_query = 
					"UPDATE Edges SET "
					+ "tag1 = " + String.valueOf(tag1) + ","
					+ "tag2 = " + String.valueOf(tag2) + ","
					+ "tag3 = " + String.valueOf(tag3) + " WHERE "
					+ "e_id = " + String.valueOf(e_id) + ";";
			statement.executeUpdate(update_query);
		}
	}
	
	
	//addComment DB会自动生成时间戳，赞数和举报数默认为0，并且保证插入新的评论后，table Edges中的
	//相应e_id的评论tag累计数会被更新，保证数据一致性
	public static boolean addComent(int e_id, String user_id, String password,
			String article, int tag1, int tag2, int tag3) throws SQLException {
		synchronized (statement) {
			//INSERT
			String insert_query =
					"INSERT INTO Comments(e_id,user_id,password,like_n,jubao_n,"
							+ "article,tag1,tag2,tag3) "
							+ "VALUES ("
							+ String.valueOf(e_id) + ",'"
							+ user_id + "','"
							+ password + "',"
							+ "0" + ","
							+ "0" + ",'"
							+ article + "',"
							+ String.valueOf(tag1) + ","
							+ String.valueOf(tag2) + ","
							+ String.valueOf(tag3)
							+ ");";
			statement.executeUpdate(insert_query);
			updateTags(e_id);
			return true;
		}
	}
	
	//clickComment 点赞或取消赞 
	public static boolean clickComment(int c_id,String u_id) throws SQLException {
		synchronized (statement) {
			//SELECT TABLE Likes
			String query = 
					"SELECT likes_n FROM Likes WHERE "
							+ "c_id = " + String.valueOf(c_id) + " AND "
							+ "user_id = '" + u_id
							+ "';";
			ResultSet rs = 
					statement.executeQuery(query);
			if(rs.next()) {
				int likes_n = 
						rs.getInt("likes_n");
				//如果是赞则取消，如果没赞过则加赞
				likes_n = likes_n ^ 1;
				//UPDATE table Likes
				String update_query = 
						"UPDATE Likes SET"
								+ "likes_n = " + String.valueOf(likes_n)
							+ "WHERE"
							+ "c_id = " + String.valueOf(c_id) + " AND "
							+ "user_id = '" + u_id
							+ "';";
				statement.executeUpdate(update_query);
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	//delComment 用户撤销该赞
	public static boolean delComment(String user_id, String password, 
			int c_id) throws SQLException {
		synchronized (statement) {
			//Judge the authority
			String query_1 = 
					"SELECT * FROM user_info WHERE "
							+ "user_id = '" + user_id + " AND '"
							+ "password = '" + password + " AND '"
							+ "status = '" + status_off
							+ "';";
			ResultSet rs_1 = 
					statement.executeQuery(query_1);
			if(rs_1.next()) {
			//SELECT TABLE Comments
				String query_2 = 
						"SELECT * FROM Comments WHERE "
								+ "c_id = " + String.valueOf(c_id) + " AND "
								+ "user_id = '" + user_id + " AND "
								+ "password = "
								+ "';";
				ResultSet rs_2 = 
						statement.executeQuery(query_2);
				if(rs_2.next()) {
					int likes_n = 
							rs_2.getInt("likes_n");
					//如果是赞则取消，如果没赞过则加赞
					likes_n = likes_n ^ 1;
					//UPDATE table Likes
					String update_query = 
							"UPDATE Likes SET"
									+ "likes_n = " + String.valueOf(likes_n)
									+ "WHERE"
									+ "c_id = " + String.valueOf(c_id) + " AND "
									+ "user_id = '" + user_id
									+ "';";
					statement.executeUpdate(update_query);
					return true;
				}
				//不存在该评论
				else {
					return false;
				}
			}
			//该用户非法
			else {
				return false;
			}
		}
	}
	
	//
}
