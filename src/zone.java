import javax.swing.*;
import java.sql.*;
import java.util.*;
public class zone extends JFrame{
	JPanel myp=new JPanel();
	JLabel info[]={new JLabel("姓名"),new JLabel("专业"),new JLabel("学号"),new JLabel("性别"),new JLabel("出生日期"),new JLabel("注册日期"),new JLabel("罚款")};
	JTextField field[]={new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField(),new JTextField()};
	JTable jtable;
	JScrollPane JSP = null;
	connectdt jdbc = new connectdt();
	Connection conn = jdbc.conn;
	Statement stmt = jdbc.stmt;
	Vector<Object> coldata = new Vector<Object>(1);
	Vector<Object> rowdata = new Vector<Object>(1);
	String sql,nm;
	int fine=0;
	public zone(String p1){
		nm=p1;
	}
	public void init(){
		for(int i=0;i<info.length;i++){
			info[i].setBounds(50, 20+50*i, 80, 30);
			myp.add(info[i]);
		}
		sql ="select * from readers where name='"+nm+"'";
		myp.setLayout(null);
		myp.setVisible(true);
		try {
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				String unm=rs.getString("name"),major=rs.getString("major"),stid=rs.getString("stu_id"),sex=rs.getString("sex"),date=rs.getString("date");
				int born=rs.getInt("born");
				fine=rs.getInt("fine");
				field[0].setText(unm);
				field[0].setEditable(false);
				field[1].setText(major);
				field[1].setEditable(false);
				field[2].setText(stid);
				field[2].setEditable(false);
				field[3].setText(sex);
				field[3].setEditable(false);
				field[4].setText(""+born);
				field[4].setEditable(false);
				field[5].setText(date);
				field[5].setEditable(false);
				field[6].setText(""+fine);
				field[6].setEditable(false);
				for(int i=0;i<field.length;i++){
					field[i].setBounds(110, 20+50*i, 90, 30);
					myp.add(field[i]);
				}
			}
			sql="select * from borrow where reader='"+nm+"'";
			rs = stmt.executeQuery(sql);
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
			jtable = new JTable();
			JSP = new JScrollPane(jtable);
			JSP.setBounds(210, 20, 500, 400);
			myp.add(JSP);
		}catch (Exception ee) {
			ee.printStackTrace();
		}
	}
	public JPanel gainpl(){
		init();
		return myp;
	}
	public int gainfine(){
		return fine;
	}
	
}
