import javax.swing.*;
import java.sql.*;
import java.util.*;

public class gaintb {
	//获得表格类
	String tb1;
	connectdt jdbc = new connectdt();
	Connection conn = jdbc.conn;
	Statement stmt = jdbc.stmt;
	JTable jtable;
	JScrollPane JSP = null;
	Vector<Object> coldata = new Vector<Object>(1);
	Vector<Object> rowdata = new Vector<Object>(1);
	public gaintb(String p1){
		this.tb1=p1;
	}
	
	//为读取表格，
	public JScrollPane rjsp() {// 返回值为JSP容器就是获取的表格
		try {
			String sql = "select * from " + tb1;
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rs.getMetaData().getColumnCount();//表格列数
			Vector<Object> columnNames = new Vector<Object>(1);
			while (rs.next()) {
				for (int i = 0; i < column; i++) {//获取除表头的每一行
					coldata.addElement(rs.getString(i + 1));
					//System.out.println(coldata.get(i));
					//输出获取内容，用来调试
				}
				rowdata.addElement(coldata);//将获取的每一行添加到另一个数组里实现二维数组
				coldata = new Vector<Object>(1);//清空原来的数组
			}
			

			for (int i = 0; i < column; i++) {//获取表头
				columnNames.add(rsmd.getColumnName(i + 1));
				//System.out.println(columnNames.get(i));
			}
			jtable = new JTable() {//创建表格
				public boolean isCellEditable(int row, int column) {
					return false;/* 表格不允许被编辑 */}
			};
			JSP = new JScrollPane(jtable);
			return JSP;
		} catch (Exception ee) {
			ee.printStackTrace();
			return JSP;
		}
	}
}
