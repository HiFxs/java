package java_work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public static Connection con;
	JDBC(){
	con = null;
	// 驱动程序名
	String driver = "com.mysql.jdbc.Driver";
	// URL指向要访问的数据库名mydata
	String url = "jdbc:mysql://localhost:3306/test";
	// MySQL配置时的用户名
	String user = "root";
	// MySQL配置时的密码
	String password = "xhj970829";
	// 遍历查询结果集
	// 加载驱动程序
	try {
		Class.forName(driver);
	} catch (ClassNotFoundException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
	// 1.getConnection()方法，连接MySQL数据库！！
	try {
		con = DriverManager.getConnection(url, user, password);
		System.out.println("数据库连接成功！");
	} catch (SQLException e1) {
		// TODO 自动生成的 catch 块
		e1.printStackTrace();
	}
	
	}
}
