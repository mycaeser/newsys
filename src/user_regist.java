import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class user_regist extends JFrame implements ActionListener {
	JLabel jlArray[] = { new JLabel("����"), new JLabel("����"), new JLabel("רҵ"), new JLabel("ѧ��") };
	JLabel j2lArr[] = { new JLabel("�Ա�"), new JLabel("����"), new JLabel("����"), new JLabel("ע��ʱ��") };
	JTextField jtArray[] = { new JTextField(), new JTextField(), new JTextField(), new JTextField() };
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// �������ڸ�ʽ
	String datestr=df.format(new Date());
	JTextField fine = new JTextField("0"), date = new JTextField(datestr);
	ButtonGroup group = new ButtonGroup();
	JRadioButton male = new JRadioButton("��", true), female = new JRadioButton("Ů");
	JComboBox comBox = new JComboBox();
	JButton submit = new JButton("�ύ");
	String[] pattern = { "[\u4e00-\u9fa5]{2,6}", "[0-9a-zA-Z]{6,12}", "[\u4e00-\u9fa5]{4,12}", "[0-9]{12}" };
	// String namepattern = "[\u4e00-\u9fa5]{2,6}";//2��6λ���ֵ�������ʽ
	// String codepatterm="[0-9a-zA-Z]{6,12}";//6��12λ���ֻ���ĸ��������ʽ
	// String majorpattern = "[\u4e00-\u9fa5]{4,12}";//4��12λ���ֵ�������ʽ
	// String stuidpattern="[0-9]{12}";//12λ���ֵ�������ʽ
	JTextArea helpArea = new JTextArea("��ʾ:������ʽΪ2��6λ����\n        ����Ϊ6��12λ���ֻ���ĸ\n        רҵΪ4��12λ����\n        ѧ��Ϊ12λ����");
	connectdt jdbc = new connectdt();// �������ݿ���
	Statement stmt = jdbc.stmt;

	public user_regist() {
		init();
		setLocation(400, 150);
		setVisible(true);// ���ô��ڿɼ�
		setSize(500, 350);
		setTitle("ע���û�");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// ���ùرյ�ǰ����
		setResizable(false);// ���ɵ������ڳߴ�
	}

	public void init() {
		setLayout(null);
		for (int i = 0; i < jlArray.length; i++) {// ���õ�һ�еı�ͷ���ı���
			jlArray[i].setBounds(50, 20 + i * 50, 50, 50);
			jtArray[i].setBounds(100, 30 + i * 50, 150, 30);
			add(jlArray[i]);
			add(jtArray[i]);
		}
		for (int i = 0; i < j2lArr.length; i++) {// ���õڶ��еı�ͷ
			j2lArr[i].setBounds(290, 20 + i * 50, 80, 50);
			add(j2lArr[i]);
		}
		male.setBounds(350, 20, 50, 50);
		female.setBounds(400, 20, 50, 50);
		group.add(male);// �Ա�ѡ����뵽
		group.add(female);
		for (int i = 1990; i < 2016; i++) {
			comBox.addItem(i);
		}
		comBox.setBounds(350, 80, 100, 30);
		fine.setBounds(350, 130, 50, 30);
		fine.setEditable(false);
		date.setBounds(350, 180, 80, 30);
		date.setEditable(false);
		submit.setBounds(350, 240, 60, 30);
		helpArea.setBounds(50, 230, 200, 70);
		helpArea.setEditable(false);
		add(comBox);
		add(male);
		add(female);
		add(fine);
		add(date);
		add(submit);
		add(helpArea);
		addListener();
	}

	public void addListener() {
		for (int i = 0; i < jtArray.length; i++) {//
			jtArray[i].addActionListener(this);
		}
		submit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == submit) {
				String[] str = new String[4];
				String sql;
				for (int i = 0; i < str.length; i++) {
					str[i] = jtArray[i].getText();
					if (str[i].length() == 0) {
						JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
						return;
					}
					if (!str[i].matches(pattern[i])) {
						JOptionPane.showMessageDialog(null, "���벻�Ϸ�");
						return;
					}
				}
				
				int sexbg = male.isSelected() ? 0 : 1;
				String sech = "��";
				if (sexbg != 0) {
					sech = "Ů";
				}
				int born = (int) comBox.getSelectedItem();
				
				sql = "insert into readers (name,code,major,stu_id,sex,born,fine,date) values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"','"+sech+"',"+born+",0,NOW());";
				stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "ע��ɹ�");
				dispose();
				stmt.close();
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}