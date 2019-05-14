import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class adm_help extends JFrame{
	//顶级
	DefaultMutableTreeNode helpRoot = new DefaultMutableTreeNode(new myNode("菜单","0"));
	//一级
	DefaultMutableTreeNode helpt1 = new DefaultMutableTreeNode("图书管理");
	DefaultMutableTreeNode helpt2 = new DefaultMutableTreeNode("账户中心");
	
	//二级
	DefaultMutableTreeNode helpt1_1 = new DefaultMutableTreeNode(new myNode("增加图书","1"));
	DefaultMutableTreeNode helpt1_2 = new DefaultMutableTreeNode(new myNode("删除图书","2"));
	DefaultMutableTreeNode helpt2_1 = new DefaultMutableTreeNode(new myNode("注册新管理员","3"));
	DefaultMutableTreeNode helpt2_2 = new DefaultMutableTreeNode(new myNode("删除管理员","4"));
	DefaultMutableTreeNode helpt2_3 = new DefaultMutableTreeNode(new myNode("删除用户","5"));
	DefaultMutableTreeNode helpV_1 = new DefaultMutableTreeNode(new myNode("简介","6"));
	//构建树
	JTree helpt=new JTree(helpRoot);
	JSplitPane jspl=new JSplitPane();
	JLabel lbarray[]={new JLabel("欢迎进入后台管理系统"),new JLabel("添加图书功能"),new JLabel("删除图书功能"),new JLabel("注册新管理员"),new JLabel("删除管理员"),
	new JLabel("删除用户"),new JLabel("后台简介")};
	JTextArea jtarray[]={new JTextArea("    后台管理部分是由管理员管理，可以对图书\n进行\"增删改查\"，对读者和管理员账户进行管\n理，请谨慎操作\n\n\n\t\tCaeser开发\n\t\t2015/12/26"),
			new JTextArea("    输入ISBN、书名、出版社、库存，点击添\n加，就可以将新入库的图书添加到数据库内，\n请您多次核对填写内容，以免添加错误，重复\n添加"
			+ "系统会给予提示。"),new JTextArea("    删除图书功能，是一个通用类功能，根据右\n侧显示的表格，查找到需要进行删除操作书籍\n的编号，进行删除操作。"
					+ ""),new JTextArea("    填写必要信息，注册新管理员，注意保管好\n密码。"),new JTextArea("    删除管理员和所有删除操作一样，使用通用\n的类，进行删除。"),new JTextArea("    同上"),
			new JTextArea("    为管理员提供简捷便利的管理环境，让办公\n更得力")};
	CardLayout card=new CardLayout();
	JPanel cardPanel=new JPanel();
	JPanel cpinfo[]={new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
	
	 public adm_help(){
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
		helpt1.add(helpt1_1);
		helpt1.add(helpt1_2);
		helpt2.add(helpt2_1);
		helpt2.add(helpt2_2);
		helpt2.add(helpt2_3);
		helpRoot.add(helpt1);
		helpRoot.add(helpt2);
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
