import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.awt.*;
public class login extends JFrame implements ActionListener {
	JLabel label1 = new JLabel("用户名");
	JLabel label2 = new JLabel("密码");
	JButton button1 = new JButton("确认");
	JTextField textField1 = new JTextField();
	JPasswordField password = new JPasswordField();
	connectdt jdbc = new connectdt();
	String user , pass , table1,title;
	Connection conn = jdbc.conn;
	Statement stmt = jdbc.stmt;
	String st1=null;
	JPanel jpl;
	public login(String p1,String p2,String p3,String p4) {
		this.table1=p1;
		this.user=p2;
		this.pass=p3;
		this.title=p4;
		init();
		setVisible(true);
		setLocation(500, 150);
		setSize(300, 300);
		setTitle(title);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 当写EXIT_ON_CLOSE时，点击关闭，会关闭所有窗口
		setResizable(false);

	}

	public void init() {
		setLayout(null);
		label1.setBounds(40, 30, 50, 30);
		add(label1);
		textField1.setBounds(90, 30, 150, 30);
		add(textField1);
		label2.setBounds(40, 130, 50, 30);
		add(label2);
		password.setBounds(90, 130, 150, 30);
		add(password);
		button1.setBounds(90, 200, 150, 30);
		add(button1);
		textField1.addActionListener(this);
		password.addActionListener(this);
		button1.addActionListener(this);
		addWindowListener(new WindowAdapter() {  
			  
			  
			public void windowClosing(WindowEvent e) {  
			super.windowClosing(e);  
			//加入动作  
			new menu(st1);
			//  
			 }  
			  
			});   
	}
	public void actionPerformed(ActionEvent e) {
		try {
			st1 = textField1.getText();
			String st2 = new String(password.getPassword());
			if (st1.length() == 0) {
				JOptionPane.showMessageDialog(null, "账号不能为空");
				return;
			}
			if (st2.length() == 0) {
				JOptionPane.showMessageDialog(null, "密码不能为空");
				return;
			}
			String sql;
			sql = "select * from " + table1 + " where " + pass + "='" + st2 + "' and "+user+" ='"+st1+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				JOptionPane.showMessageDialog(null, "账号或密码不正确");
				return;
			}
			dispose();// 隐藏登录窗口
			if(title=="管理员登录"){
				new manager();
			}else{
				new menu(st1);
			    
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}