import java.sql.*;
public class connectdt {//此类为连接数据库的类
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/emp";
	static final String USER = "root";
	static final String PASS = "iop0A9m43";
	String struser = "username", strpass = "password", strtable1 = "admindt", strtable2 = "books",ID="id";
	Connection conn = null;
	Statement stmt = null;

	public connectdt() {
		try {
			Class.forName(JDBC_DRIVER);// 工程要连接额外的jar包
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}
