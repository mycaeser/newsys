import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class register extends JFrame implements ActionListener {
	JLabel jlArray[] = { new JLabel("����Ա���"), new JLabel("�û���"), new JLabel("����"), new JLabel("�ٴ�����") };
	JButton button1 = new JButton("ȷ��");
	JTextField jtArray[] = { new JTextField(), new JTextField(), new JTextField(), new JTextField() };
	String patternStr = "[0-9a-zA-Z]{6,10}",id_target="[0-9]{1,6}";
	//6��12λ���ֺ���ĸ��������ʽ��1��6λ���ֵ�������ʽ
	connectdt jdbc = new connectdt();//�������ݿ���
	Connection conn = jdbc.conn;
	Statement stmt = jdbc.stmt;
	String tbname=jdbc.strtable1,username=jdbc.struser,id=jdbc.ID;
	public register() {
		init();
		setLocation(400, 150);
		setVisible(true);//���ô��ڿɼ�
		setSize(500, 400);
		setTitle("ע�����Ա");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùرյ�ǰ����
		setResizable(false);//���ɵ������ڳߴ�
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
				JOptionPane.showMessageDialog(null, "��������");
				return;
			}
			if(!st1.matches(id_target)){
				JOptionPane.showMessageDialog(null, "���ֻ����1��6λ����");
				return;
			}
			int n = Integer.parseInt(st1);
			String st2 = jtArray[1].getText();
			if (st2.length() == 0) {
				JOptionPane.showMessageDialog(null, "�������û���");
				return;
			}
			if (!st2.matches(patternStr)) {
				JOptionPane.showMessageDialog(null, "�û���ֻ����6��12λ����ĸ������");
				return;
			}
			String st3 = jtArray[2].getText();
			if (st3.length() == 0) {
				JOptionPane.showMessageDialog(null, "����������");
				return;
			}
			if (!st3.matches(patternStr)) {
				JOptionPane.showMessageDialog(null, "����ֻ����6��12λ����ĸ������");
				return;
			}
			String st4 = jtArray[3].getText();
			if (!st4.equals(st3)) {
				JOptionPane.showMessageDialog(null, "��ȷ������һ��");
				return;
			}
			String sql;
			sql = "select "+id+" from "+tbname+" where "+id+"=" + n;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "��Ŵ���");
				return;
			}
			sql = "select "+username+" from "+tbname+" where "+username+"='" + st1 + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				JOptionPane.showMessageDialog(null, "�û�������");
				return;
			}
			sql = "insert into "+tbname+" values(" + n + ",'" + st1 + "','" + st2 + "','" + st3 + "');";
			stmt.executeUpdate(sql);
			rs.close();
			stmt.close();
			conn.close();
			JOptionPane.showMessageDialog(null, "ע��ɹ�");
			dispose();
		} catch (Exception ee) {
			System.out.println("����������");
		}
	}
}