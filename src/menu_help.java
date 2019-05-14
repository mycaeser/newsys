import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class menu_help extends JFrame{
	//顶级
	DefaultMutableTreeNode helpRoot = new DefaultMutableTreeNode(new myNode("菜单","0"));
	//一级
	DefaultMutableTreeNode helpt1 = new DefaultMutableTreeNode("常用功能");
	DefaultMutableTreeNode helpt2 = new DefaultMutableTreeNode("个人中心");
	DefaultMutableTreeNode helpt3 = new DefaultMutableTreeNode("系统维护");
	
	//二级
	DefaultMutableTreeNode helpt1_1 = new DefaultMutableTreeNode(new myNode("浏览图书","1"));
	DefaultMutableTreeNode helpt1_2 = new DefaultMutableTreeNode("查找图书");
	DefaultMutableTreeNode helpt2_1 = new DefaultMutableTreeNode(new myNode("我的空间","4"));
	DefaultMutableTreeNode helpt2_2 = new DefaultMutableTreeNode(new myNode("登录","5"));
	DefaultMutableTreeNode helpt2_3 = new DefaultMutableTreeNode(new myNode("注册","6"));
	DefaultMutableTreeNode helpt3_1 = new DefaultMutableTreeNode(new myNode("后台管理","7"));
	DefaultMutableTreeNode helpV_1 = new DefaultMutableTreeNode(new myNode("简介","8"));
	//三级
	DefaultMutableTreeNode helpt1_2_1 = new DefaultMutableTreeNode(new myNode("查找图书","2"));
	DefaultMutableTreeNode helpt1_2_2 = new DefaultMutableTreeNode(new myNode("借书","3"));
	
	//构建树
	JTree helpt=new JTree(helpRoot);
	JSplitPane jspl=new JSplitPane();
	JLabel lbarray[]={new JLabel("欢迎使用图书管理系统"),new JLabel("浏览功能"),new JLabel("查找功能"),new JLabel("借阅功能"),new JLabel("查看空间"),
	new JLabel("登录功能"),new JLabel("注册功能"),new JLabel("管理员系统"),new JLabel("系统简介")};
	JTextArea jtarray[]={new JTextArea("    本管理系统为读者提供便利的借书和找书环\n境，读者可以自行查询借书，在借书之前请先\n注册，只需要提供学生的基本信息"
			+ "即可，系统\n还有很多不完善的地方，望大家使用后提出宝\n贵的改进意见，运营团队会及时改进，祝大家\n使用愉快！\n\n\n\t\tCaeser开发\n\t\t2015/12/16"),new JTextArea("    从数据库中提取表格，显示所有在馆图书\n的编号、ISBN、书名、出版社和库存量"
			+ "方便\n读者总览库内图书。"),new JTextArea("    查找功能分为精确查找和模糊查找，精确\n查找只允许通过编号、ISBN、书名、出版社\n当中"
					+ "的一种，进行查询，而模糊查找也只允许\n挑选书名中的关键词或者出版社中的关键词，\n查询结果将在下面的白色区域显示"
					+ "由于显示区\n域限制，搜索结果如果超出可显示范围将不予\n显示。"),new JTextArea("    借阅功能和查找功能是相互配合使用的"
							+ "当您\n查找到心仪的图书时，可以通过复制粘贴将书\n名添加到借阅的选框里，选择借阅天数，借阅\n上限时10天，点击"
							+ "借阅。"),new JTextArea("    个人空间显示功能，将显示读者的姓名、专\n业、学号、罚款，还有读者已经借阅的图书"
									+ "\n将表格放在右侧表格里，表格下方提供还书功\n能，读者根据表中信息提交还书。"),new JTextArea(""
											+ "  输入读者姓名，和注册时填写的密码，登录\n后方可进入个人空间"),new JTextArea("    注册新读者用户"
													+ "填写读者基本信息，按要求\n提交内容即可"),new JTextArea("    只有拥有管理员账户和密码，"
															+ "才可以进入后\n台对系统进行管理"),new JTextArea("    对java容器、空布局、卡片式布局练习，熟\n悉"
																	+ "mysql操作即java+mysql(jdbc)构建的系统")};
	CardLayout card=new CardLayout();
	JPanel cardPanel=new JPanel();
	JPanel cpinfo[]={new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
	
	 public menu_help(){
		init();
		setLocation(700, 100);
		setVisible(true);
		setTitle("帮助");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	public void init(){
		setLayout(null);
		cardPanel.setLayout(card);
		cardPanel.setBounds(150, -30, 260, 300);
		add(cardPanel);
		initHelp();
		card.show(cardPanel, "0");
		initTree();
		helpt.setToggleClickCount(1);//鼠标单击次数为1
		helpt.setBounds(0, 0, 130, 300);
		add(helpt);
		jspl.setBounds(130, 0, 5, 300);
		add(jspl);
		addListener();
		
	}
	public void initTree(){
		helpt1_2.add(helpt1_2_1);
		helpt1_2.add(helpt1_2_2);
		helpt1.add(helpt1_1);
		helpt1.add(helpt1_2);
		helpt2.add(helpt2_1);
		helpt2.add(helpt2_2);
		helpt2.add(helpt2_3);
		helpt3.add(helpt3_1);
		helpRoot.add(helpt1);
		helpRoot.add(helpt2);
		helpRoot.add(helpt3);
		helpRoot.add(helpV_1);
	}
	public void initHelp(){
		for(int i=0;i<cpinfo.length;i++){
			cpinfo[i].setLayout(null);
			lbarray[i].setBounds(0, 0, 200, 100);
			cpinfo[i].add(lbarray[i]);
			jtarray[i].setBounds(0, 60, 240, 230);
			jtarray[i].setBackground(Color.LIGHT_GRAY);
			jtarray[i].setEditable(false);
			cpinfo[i].add(jtarray[i]);
			cardPanel.add(cpinfo[i],""+i);
		}
	}
	public void addListener(){
		helpt.addMouseListener(
				new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						
						DefaultMutableTreeNode htemp=(DefaultMutableTreeNode)helpt.getLastSelectedPathComponent();
						myNode node=(myNode)htemp.getUserObject();
						String id=node.getId();
						if(id.equals("0")){
							card.show(cardPanel, "0");
						}else if(id.equals("1")){
							card.show(cardPanel, "1");
						}else if(id.equals("2")){
							card.show(cardPanel, "2");
						}else if(id.equals("3")){
							card.show(cardPanel, "3");
						}else if(id.equals("4")){
							card.show(cardPanel, "4");
						}else if(id.equals("5")){
							card.show(cardPanel, "5");
						}else if(id.equals("6")){
							card.show(cardPanel, "6");
						}else if(id.equals("7")){
							card.show(cardPanel, "7");
						}else if(id.equals("8")){
							card.show(cardPanel, "8");
						}
					}
				}
	);}
	
	public class myNode{
		String values,id;
		public myNode(String p1,String p2){
			values=p1;
			id=p2;
		}
		public String toString(){return values;}
		public String getId(){return id;}
	}
}
