import javax.swing.*;
import java.sql.*;
import java.util.*;

public class gaintb {
	//��ñ����
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
	
	//Ϊ��ȡ���
	public JScrollPane rjsp() {// ����ֵΪJSP�������ǻ�ȡ�ı��
		try {
			String sql = "select * from " + tb1;
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int column = rs.getMetaData().getColumnCount();//�������
			Vector<Object> columnNames = new Vector<Object>(1);
			while (rs.next()) {
				for (int i = 0; i < column; i++) {//��ȡ����ͷ��ÿһ��
					coldata.addElement(rs.getString(i + 1));
					//System.out.println(coldata.get(i));
					//�����ȡ���ݣ���������
				}
				rowdata.addElement(coldata);//����ȡ��ÿһ����ӵ���һ��������ʵ�ֶ�ά����
				coldata = new Vector<Object>(1);//���ԭ��������
			}
			

			for (int i = 0; i < column; i++) {//��ȡ��ͷ
				columnNames.add(rsmd.getColumnName(i + 1));
				//System.out.println(columnNames.get(i));
			}
			jtable = new JTable() {//�������
				public boolean isCellEditable(int row, int column) {
					return false;/* ��������༭ */}
			};
			JSP = new JScrollPane(jtable);
			return JSP;
		} catch (Exception ee) {
			ee.printStackTrace();
			return JSP;
		}
	}
}
