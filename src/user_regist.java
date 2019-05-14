import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class user_regist extends JFrame implements ActionListener {
	JLabel jlArray[] = { new JLabel("姓名"), new JLabel("密码"), new JLabel("专业"), new JLabel("学号") };
	JLabel j2lArr[] = { new JLabel("性别"), new JLabel("出生"), new JLabel("罚款"), new JLabel("注册时间") };
	JTextField jtArray[] = { new JTextField(), new JTextField(), new JTextField(), new JTextField() };
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
	String datestr=df.format(new Date());
	JTextField fine = new JTextField("0"), date = new JTextField(datestr);
	ButtonGroup group = new ButtonGroup();
	JRadioButton male = new JRadioButton("男", true), female = new JRadioButton("女");
	JComboBox comBox = new JComboBox();
	JButton submit = new JButton("提交");
	String[] pattern = { "[\u4e00-\u9fa5]{2,6}", "[0-9a-zA-Z]{6,12}", "[\u4e00-\u9fa5]{4,12}", "[0-9]{12}" };
	// String namepattern = "[\u4e00-\u9fa5]{2,6}";//2到6位汉字的正则表达式
	// String codepatterm="[0-9a-zA-Z]{6,12}";//6到12位数字或字母的正则表达式
	// String majorpattern = "[\u4e00-\u9fa5]{4,12}";//4到12位汉字的正则表达式
	// String stuidpattern="[0-9]{12}";//12位数字的正则表达式
	JTextArea helpArea = new JTextArea("提示:姓名格式为2到6位汉字\n        密码为6到12位数字或字母\n        专业为4到12位汉字\n        学号为12位数字");
	connectdt jdbc = new connectdt();// 连接数据库类
	Statement stmt = jdbc.stmt;

	public user_regist() {
		init();
		setLocation(400, 150);
		setVisible(true);// 设置窗口可见
		setSize(500, 350);
		setTitle("注册用户");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 设置关闭当前窗口
		setResizable(false);// 不可调整窗口尺寸
	}

	public void init() {
		setLayout(null);
		for (int i = 0; i < jlArray.length; i++) {// 设置第一列的表头和文本框
			jlArray[i].setBounds(50, 20 + i * 50, 50, 50);
			jtArray[i].setBounds(100, 30 + i * 50, 150, 30);
			add(jlArray[i]);
			add(jtArray[i]);
		}
		for (int i = 0; i < j2lArr.length; i++) {// 设置第二列的表头
			j2lArr[i].setBounds(290, 20 + i * 50, 80, 50);
			add(j2lArr[i]);
		}
		male.setBounds(350, 20, 50, 50);
		female.setBounds(400, 20, 50, 50);
		group.add(male);// 性别选择加入到
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
						JOptionPane.showMessageDialog(null, "输入不能为空");
						return;
					}
					if (!str[i].matches(pattern[i])) {
						JOptionPane.showMessageDialog(null, "输入不合法");
						return;
					}
				}
				
				int sexbg = male.isSelected() ? 0 : 1;
				String sech = "男";
				if (sexbg != 0) {
					sech = "女";
				}
				int born = (int) comBox.getSelectedItem();
				
				sql = "insert into readers (name,code,major,stu_id,sex,born,fine,date) values('"+str[0]+"','"+str[1]+"','"+str[2]+"','"+str[3]+"','"+sech+"',"+born+",0,NOW());";
				stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "注册成功");
				dispose();
				stmt.close();
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}