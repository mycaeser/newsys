import javax.swing.*;
import java.awt.event.*;

public class manager extends JFrame implements ActionListener {
	JMenuBar menubar = new JMenuBar();
	JMenu meArray[] = { new JMenu("图书管理"), new JMenu("账户中心"), new JMenu("帮助") };
	JMenuItem item[] = { new JMenuItem("增加图书"), new JMenuItem("删除图书"),new JMenuItem("注册新管理员"),
			new JMenuItem("删除管理员"), new JMenuItem("删除用户"), new JMenuItem("帮助信息") };
	gaintb jsp;//表格类，从数据库获取表格
	JScrollPane JSP;//带滚动条的列表框，用来存放jsp类里获取的表格然后添加到容器
	

	public manager() {//构造函数
		init();//初始化
		jsp = new gaintb("books");
		JSP = jsp.rjsp();
		JSP.setBounds(0, 0, 800, 550);
		this.add(JSP);

		setLocation(250, 70);
		setVisible(true);
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	public void init() {
		setLayout(null);//格式布局为空
		setTitle("后台管理中心");//设置标题
		meArray[0].add(item[0]);//菜单添加部件
		meArray[0].add(item[1]);
		meArray[1].add(item[2]);
		meArray[1].add(item[3]);
		meArray[1].add(item[4]);
		meArray[2].add(item[5]);
		for (int i = 0; i < meArray.length; i++) {
			menubar.add(meArray[i]);//将以上部件加入菜单条
		}
		setJMenuBar(menubar);//将菜单条放入布局里
		addListener();//添加监听的函数
	}

	public void addListener() {
		for (int i = 0; i < item.length; i++) {
			item[i].addActionListener(this);//依次给菜单部件加监听
		}
	}

	public void actionPerformed(ActionEvent e) {
		try {//e.getSource()为响应事件，以下语句为当响应事件为菜单的哪一个部件
			if (e.getSource() == item[0]) {//增加图书
				new addook();
			} else if (e.getSource() == item[1]) {//删除图书
				new delete("books");
			} else if (e.getSource() == item[2]) {//注册新管理员
				new register();
			} else if (e.getSource() == item[3]) {//删除管理员
				new delete("admindt");
			}else if(e.getSource()==item[4]){//删除用户
				new delete("readers");
			}
			else if(e.getSource()==item[5]){//帮助
				new adm_help();
			}
		} catch (Exception ee) {
			ee.printStackTrace();//抛出异常
		}
	}
}