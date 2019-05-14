import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class addook extends JFrame implements ActionListener{
	JLabel label[]={new JLabel("ISBN"),new JLabel("书名"),new JLabel("出版社"),new JLabel("库存")};
	JTextField field[]={new JTextField(),new JTextField(),new JTextField(),new JTextField()};
	JButton submit=new JButton("添加");
	String namepattern = "[\u4e00-\u9fa5]{4,15}";//4到15位汉字
	String sql="";
	connectdt jdbc = new connectdt();// 连接数据库类
	Statement stmt = jdbc.stmt;
	public addook(){
		init();
		setLocation(370, 230);
		setVisible(true);
		setSize(650, 150);
		setTitle("添加图书");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	public void init(){
		setLayout(null);
		int i;
		for(i=0;i<label.length;i++){
			label[i].setBounds(30+150*i, 10, 50, 30);
			add(label[i]);
		}
		for(i=0;i<field.length-1;i++){
			field[i].setBounds(10+150*i, 40, 120, 30);
			add(field[i]);
			field[i].addActionListener(this);
		}
		field[i].setBounds(470, 40, 50, 30);
		add(field[i]);
		field[i].addActionListener(this);
		submit.setBounds(550, 40, 70, 30);
		add(submit);
		submit.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource()==submit){
				String strT[]=new String[4];
				for(int j=0;j<field.length;j++){
					strT[j]=field[j].getText();
					if(strT[j].equals("")){//注意:不要写成strT[j]==""
						JOptionPane.showMessageDialog(null, "输入不能为空");
						return;
					}
				}
				sql="select * from books where ISBN='"+strT[0]+"';";
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					JOptionPane.showMessageDialog(null, "请勿重复添加");
					return;
				}
				sql="insert into books (ISBN,TITLE,PUBLISH,STOCK) values('"+strT[0]+"','"+strT[1]+"','"+strT[2]+"',"+strT[3]+");";
				stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "添加成功");
				int i = JOptionPane.showConfirmDialog(null, "您要继续添加吗？", "提示:", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.OK_OPTION) {
					sql="";
					field[0].setText("");
					field[1].setText("");
					field[2].setText("");
					field[3].setText("");
					return;
				}else{
					rs.close();
					stmt.close();
					dispose();
				}
			}
			
		}catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}
