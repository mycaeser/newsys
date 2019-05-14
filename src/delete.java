import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class delete extends JFrame implements ActionListener {
	// 删除管理员的类 继承了窗口 和 监听
	JLabel tip[] = { new JLabel("编号"), new JLabel("从右侧列表删除→→→") };
	JTextField idField = new JTextField();
	JButton submit = new JButton("删除");
	connectdt jdbc = new connectdt();
	Connection conn = jdbc.conn;
	Statement stmt = jdbc.stmt;
	gaintb jsp;
	JScrollPane JSP;
	String sql = "",tbname="";

	public delete(String p1) {
		tbname=p1;
		init();
		setLocation(450, 200);
		setVisible(true);
		setSize(400, 270);
		setTitle("删除操作");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	public void init() {
		setLayout(null);
		tip[0].setBounds(10, 10, 50, 30);
		add(tip[0]);
		idField.setBounds(10, 50, 80, 30);
		add(idField);
		submit.setBounds(140, 50, 70, 30);
		add(submit);
		tip[1].setBounds(10, 100, 200, 30);
		add(tip[1]);
		jsp = new gaintb(tbname);
		JSP = jsp.rjsp();
		JSP.setBounds(245, 20, 300, 200);
		add(JSP);
		submit.addActionListener(this);
		idField.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == submit) {
				String st1 = idField.getText();
				if (st1.equals("")) {
					JOptionPane.showMessageDialog(null, "不能为空");
					return;
				}
				int n = Integer.parseInt(st1);
				sql = "select * from "+tbname+" where id =" + n;
				ResultSet rs = stmt.executeQuery(sql);
				if (!rs.next()) {
					JOptionPane.showMessageDialog(null, "输入不存在");
					return;
				}
				int i = JOptionPane.showConfirmDialog(null, "您确认要删除吗？", "提示:", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.OK_OPTION) {
					sql = "delete from "+tbname+" where id=" + n;
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "操作成功");
					rs.close();
					stmt.close();
					conn.close();
					dispose();
				}
			}
		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}
