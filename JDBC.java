package java_work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public static Connection con;
	JDBC(){
	con = null;
	// ����������
	String driver = "com.mysql.jdbc.Driver";
	// URLָ��Ҫ���ʵ����ݿ���mydata
	String url = "jdbc:mysql://localhost:3306/test";
	// MySQL����ʱ���û���
	String user = "root";
	// MySQL����ʱ������
	String password = "xhj970829";
	// ������ѯ�����
	// ������������
	try {
		Class.forName(driver);
	} catch (ClassNotFoundException e1) {
		// TODO �Զ����ɵ� catch ��
		e1.printStackTrace();
	}
	// 1.getConnection()����������MySQL���ݿ⣡��
	try {
		con = DriverManager.getConnection(url, user, password);
		System.out.println("���ݿ����ӳɹ���");
	} catch (SQLException e1) {
		// TODO �Զ����ɵ� catch ��
		e1.printStackTrace();
	}
	
	}
}
