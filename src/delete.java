import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class delete extends JFrame implements ActionListener {
	// ɾ������Ա���� �̳��˴��� �� ����
	JLabel tip[] = { new JLabel("���"), new JLabel("���Ҳ��б�ɾ��������") };
	JTextField idField = new JTextField();
	JButton submit = new JButton("ɾ��");
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
		setTitle("ɾ������");
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
					JOptionPane.showMessageDialog(null, "����Ϊ��");
					return;
				}
				int n = Integer.parseInt(st1);
				sql = "select * from "+tbname+" where id =" + n;
				ResultSet rs = stmt.executeQuery(sql);
				if (!rs.next()) {
					JOptionPane.showMessageDialog(null, "���벻����");
					return;
				}
				int i = JOptionPane.showConfirmDialog(null, "��ȷ��Ҫɾ����", "��ʾ:", JOptionPane.YES_NO_OPTION);
				if (i == JOptionPane.OK_OPTION) {
					sql = "delete from "+tbname+" where id=" + n;
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "�����ɹ�");
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
