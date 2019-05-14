import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class register extends JFrame implements ActionListener {
	JLabel jlArray[] = { new JLabel("管理员编号"), new JLabel("用户名"), new JLabel("密码"), new JLabel("再次输入") };
	JButton button1 = new JButton("确认");
	JTextField jtArray[] = { new JTextField(), new JTextField(), new JTextField(), new JTextField() };
	String patternStr = "[0-9a-zA-Z]{6,10}",id_target="[0-9]{1,6}";
	//6到12位数字和字母的正则表达式，1到6位数字的正则表达式
	connectdt jdbc = new connectdt();//连接数据库类
	Connection conn = jdbc.conn;
	Statement stmt = jdbc.stmt;
	String tbname=jdbc.strtable1,username=jdbc.struser,id=jdbc.ID;
	public register() {
		init();
		setLocation(400, 150);
		setVisible(true);//设置窗口可见
		setSize(500, 400);
		setTitle("注册管理员");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//设置关闭当前窗口
		setResizable(false);//不可调整窗口尺寸
	}

	public void init() {
		setLayout(null);
		for (int i = 0; i < jtArray.length; i++) {
			jtArray[i].setBounds(180, 30 + 50 * i, 150, 30);
			add(jtArray[i]);
		}
		for (int i = 0; i < jlArray.length; i++) {
			jlArray[i].setBounds(110, 30 + 50 * i, 150, 30);
			add(jlArray[i]);
		}
		button1.setBounds(180, 250, 150, 30);
		add(button1);
		addListener();
	}

	public void addListener() {
		jtArray[0].addActionListener(this);
		jtArray[1].addActionListener(this);
		jtArray[2].addActionListener(this);
		button1.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			String st1 = jtArray[0].getText();
			if (st1.length() == 0) {
				JOptionPane.showMessageDialog(null, "请输入编号");
				return;
			}
			if(!st1.matches(id_target)){
				JOptionPane.showMessageDialog(null, "编号只能是1到6位数字");
				return;
			}
			int n = Integer.parseInt(st1);
			String st2 = jtArray[1].getText();
			if (st2.length() == 0) {
				JOptionPane.showMessageDialog(null, "请输入用户名");
				return;
			}
			if (!st2.matches(patternStr)) {
				JOptionPane.showMessageDialog(null, "用户名只能是6到12位的字母或数字");
				return;
			}
			String st3 = jtArray[2].getText();
			if (st3.length() == 0) {
				JOptionPane.showMessageDialog(null, "请输入密码");
				return;
			}
			if (!st3.matches(patternStr)) {
				JOptionPane.showMessageDialog(null, "密码只能是6到12位的字母或数字");
				return;
			}
			String st4 = jtArray[3].getText();
			if (!st4.equals(st3)) {
				JOptionPane.showMessageDialog(null, "请确认密码一致");
				return;
			}
			String sql;
			sql = "select "+id+" from "+tbname+" where "+id+"=" + n;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "编号存在");
				return;
			}
			sql = "select "+username+" from "+tbname+" where "+username+"='" + st1 + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "用户名存在");
				return;
			}
			sql = "insert into "+tbname+" values(" + n + ",'" + st1 + "','" + st2 + "','" + st3 + "');";
			stmt.executeUpdate(sql);
			rs.close();
			stmt.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "注册成功");
			dispose();
		} catch (Exception ee) {
			System.out.println("请输入数字");
		}
	}
}