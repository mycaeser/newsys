import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class menu extends JFrame implements ActionListener {
	JMenuBar menubar = new JMenuBar();
	JMenu meArray[] = { new JMenu("常用功能"), new JMenu("个人中心"), new JMenu("系统维护"), new JMenu("帮助") };
	JMenuItem item[] = { new JMenuItem("浏览图书",new ImageIcon("image/browse.jpg")), new JMenuItem("查找图书",new ImageIcon("image/query.jpg")), new JMenuItem("我的空间",new ImageIcon("image/user.jpg")),new JMenuItem("登录",new ImageIcon("image/login.jpg")), new JMenuItem("注册",new ImageIcon("image/key.jpg")),
			new JMenuItem("后台管理",new ImageIcon("image/admin.jpg")), new JMenuItem("帮助信息",new ImageIcon("image/help.jpg")) };
	gaintb jsp;
	JScrollPane JSP;
	JPanel jpl;
	JLabel bgimg=new JLabel(new ImageIcon("image\\MAIN.jpg"));//开始界面
	String namep=null;
	 CardLayout card=new CardLayout();
	 JPanel cardPanel=new JPanel();
	 JLabel titback=new JLabel("选择还书名称");
	 JButton suback=new JButton("还书");
	 JTextField bid=new JTextField();
	 
	 String sql="";
	 connectdt jdbc = new connectdt();//连接数据库类
		Connection conn = jdbc.conn;
		Statement stmt = jdbc.stmt;
	public menu(String p1) {
		namep=p1;
		init();
		cardPanel.add(bgimg);
		add(cardPanel);
		jsp = new gaintb("books");
		JSP = jsp.rjsp();
		JSP.setBounds(0, 0, 800, 550);
		cardPanel.add(JSP,"browse");
		if(namep!=null){
			zone cp6=new zone(namep);
			jpl=cp6.gainpl();
			suback.setBounds(580, 440, 80, 30);
			titback.setBounds(400, 440, 100, 30);
			bid.setBounds(500, 440, 50, 30);
			jpl.add(suback);
			jpl.add(titback);
			jpl.add(bid);
			suback.addActionListener(this);
			bid.addActionListener(this);
			cardPanel.add(jpl,"zone");
			card.show(cardPanel,"zone");
			if(cp6.gainfine()>0){
				JOptionPane.showMessageDialog(null, "请先交纳罚款");
				return;
			}
		}
		setLocation(250, 70);
		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	public void init() {
	    cardPanel.setLayout(card);
		setTitle("图书管理系统");
		meArray[0].add(item[0]);
		meArray[0].add(item[1]);
		meArray[1].add(item[2]);
		meArray[1].add(item[3]);
		meArray[1].add(item[4]);
		meArray[2].add(item[5]);
		meArray[3].add(item[6]);
		for (int i = 0; i < item.length; i++) {
			item[i].addActionListener(this);
		}

		for (int i = 0; i < meArray.length; i++) {
			menubar.add(meArray[i]);
		}
		setJMenuBar(menubar);
	}

	public void actionPerformed(ActionEvent e) {
		try {
			if (e.getSource() == item[0]) {//浏览图书
				card.show(cardPanel,"browse");
				
			} else if (e.getSource() == item[1]) {//查找图书
				new select_borrow(namep);
			} else if (e.getSource() == item[2]) {//空间
				if(namep==null){
					JOptionPane.showMessageDialog(null, "您还未登录呢");
					return;
				}else{
					card.show(cardPanel,"zone");
				}
			} else if (e.getSource() == item[3]) {//登录
				new login("readers","name","code","用户登录");
				dispose();
			}else if(e.getSource()==item[4]){//注册
				new user_regist();
			}else if(e.getSource()==item[5]){//后台
				new login("admindt","username","password","管理员登录");
				dispose();
			}
			else if(e.getSource()==item[6]){//帮助
				new menu_help();
			}else if(e.getSource()==suback){//还书
				
				String temp=bid.getText();
				if(temp.equals("")){
					JOptionPane.showMessageDialog(null, "输入不能为空");
					return;
				}
				sql="select * from borrow where book='"+temp+"'";
				ResultSet rs = stmt.executeQuery(sql);
				if(!rs.next()){
					JOptionPane.showMessageDialog(null, "输入不存在");
					return;
				}
				sql="delete from borrow where book='"+temp+"'";
				stmt.executeUpdate(sql);
				sql="update books set STOCK=STOCK+1 where TITLE='"+temp+"';";
				stmt.executeUpdate(sql);
				sql="";
				JOptionPane.showMessageDialog(null, "还书成功");
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}