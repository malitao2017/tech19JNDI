package j1use;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JndiTest {

	public static void main(String[] args) {
		//没用jndi之前 ，只能用jdbc
		jdbc();
		//使用之后，有容器来提供
		jndi();
	}
	public static void jndi(){
		Connection conn=null;  
		try {  
		  Context ctx=new InitialContext();  
		  Object datasourceRef=ctx.lookup("java:MySqlDS"); //引用数据源  
		  DataSource ds=(DataSource)datasourceRef;  
		  conn=ds.getConnection();  
//		  ......  
		  conn.close();  
		} catch(Exception e) {  
		  e.printStackTrace();  
		} finally {  
		  if(conn!=null) {  
		    try {  
		      conn.close();  
		    } catch(SQLException e) { }  
		  }  
		}  
	}
	public static void jdbc(){
		Connection conn=null;  
		try {  
		  Class.forName("com.mysql.jdbc.Driver",  
		                true, Thread.currentThread().getContextClassLoader());  
		  conn=DriverManager.  
		    getConnection("jdbc:mysql://MyDBServer?user=qingfeng&password=mingyue");  
//		  ......  
		  conn.close();  
		} catch(Exception e) {  
		  e.printStackTrace();  
		} finally {  
		  if(conn!=null) {  
		    try {  
		      conn.close();  
		    } catch(SQLException e) {}  
		  }  
		} 
	}
}
