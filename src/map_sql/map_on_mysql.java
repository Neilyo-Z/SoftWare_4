package map_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class map_on_mysql {
	
	public static final String Separater = "##" ;
	static Connection con;
	static String url="jdbc:mysql://localhost:3306/map_db";
	static String username="root";
	static Statement statement;
	
	//create table Nodes:n_id, n_name, n_x, n_y
	//create table Edges:e_id, e_name, n_id_1, n_id_2, distance, tag1, tag2, tag3
	//create table Comments:c_id, c_date, e_id, user_id, like_n, jubao_n, article, tag1,tag2, tag3
	//初始化与数据库的连接
	public static void init_map_db() throws ClassNotFoundException{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,"root","38203136");
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
					+ "tag3 INT UNSIGNED not null"
					+ ");";
			//create table Comments
			String create_table_Comments = 
					"create table if not exists "
					+ "Comments ("
					+ "c_id INT UNSIGNED not null primary key AUTO_INCREMENT,"
					+ "c_date TIMESTAMP not null," 
					+ "e_id INT UNSIGNED not null," 
					+ "user_id INT UNSIGNED not null," 
					+ "like_n INT UNSIGNED not null,"
					+ "jubao_n INT UNSIGNED not null,"
					+ "article TINYTEXT not null,"
					+ "tag1 INT UNSIGNED not null,"
					+ "tag2 INT UNSIGNED not null,"
					+ "tag3 INT UNSIGNED not null"
					+ ");";
			statement.executeUpdate(create_table_Nodes);
			statement.executeUpdate(create_table_Edges);
			statement.executeUpdate(create_table_Comments);
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
			if(!rs.next()) {
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
					+ "';";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(!rs.next()) {
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
					+ "n_id = '" + n_id
					+ "';";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(!rs.next()) {
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
			if(!rs.next()) {
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
					+ "';";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(!rs.next()) {
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
					+ "e_id = '" + e_id
					+ "';";
			ResultSet rs =
					statement.executeQuery(query);
			//Exist and return
			if(!rs.next()) {
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
	
}
