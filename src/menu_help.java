import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class menu_help extends JFrame{
	//����
	DefaultMutableTreeNode helpRoot = new DefaultMutableTreeNode(new myNode("�˵�","0"));
	//һ��
	DefaultMutableTreeNode helpt1 = new DefaultMutableTreeNode("���ù���");
	DefaultMutableTreeNode helpt2 = new DefaultMutableTreeNode("��������");
	DefaultMutableTreeNode helpt3 = new DefaultMutableTreeNode("ϵͳά��");
	
	//����
	DefaultMutableTreeNode helpt1_1 = new DefaultMutableTreeNode(new myNode("���ͼ��","1"));
	DefaultMutableTreeNode helpt1_2 = new DefaultMutableTreeNode("����ͼ��");
	DefaultMutableTreeNode helpt2_1 = new DefaultMutableTreeNode(new myNode("�ҵĿռ�","4"));
	DefaultMutableTreeNode helpt2_2 = new DefaultMutableTreeNode(new myNode("��¼","5"));
	DefaultMutableTreeNode helpt2_3 = new DefaultMutableTreeNode(new myNode("ע��","6"));
	DefaultMutableTreeNode helpt3_1 = new DefaultMutableTreeNode(new myNode("��̨����","7"));
	DefaultMutableTreeNode helpV_1 = new DefaultMutableTreeNode(new myNode("���","8"));
	//����
	DefaultMutableTreeNode helpt1_2_1 = new DefaultMutableTreeNode(new myNode("����ͼ��","2"));
	DefaultMutableTreeNode helpt1_2_2 = new DefaultMutableTreeNode(new myNode("����","3"));
	
	//������
	JTree helpt=new JTree(helpRoot);
	JSplitPane jspl=new JSplitPane();
	JLabel lbarray[]={new JLabel("��ӭʹ��ͼ�����ϵͳ"),new JLabel("�������"),new JLabel("���ҹ���"),new JLabel("���Ĺ���"),new JLabel("�鿴�ռ�"),
	new JLabel("��¼����"),new JLabel("ע�Ṧ��"),new JLabel("����Աϵͳ"),new JLabel("ϵͳ���")};
	JTextArea jtarray[]={new JTextArea("    ������ϵͳΪ�����ṩ�����Ľ�������黷\n�������߿������в�ѯ���飬�ڽ���֮ǰ����\nע�ᣬֻ��Ҫ�ṩѧ���Ļ�����Ϣ"
			+ "���ɣ�ϵͳ\n���кܶ಻���Ƶĵط��������ʹ�ú������\n��ĸĽ��������Ӫ�Ŷӻἰʱ�Ľ���ף���\nʹ����죡\n\n\n\t\tCaeser����\n\t\t2015/12/16"),new JTextArea("    �����ݿ�����ȡ�����ʾ�����ڹ�ͼ��\n�ı�š�ISBN��������������Ϳ����"
			+ "����\n������������ͼ�顣"),new JTextArea("    ���ҹ��ܷ�Ϊ��ȷ���Һ�ģ�����ң���ȷ\n����ֻ����ͨ����š�ISBN��������������\n����"
					+ "��һ�֣����в�ѯ����ģ������Ҳֻ����\n��ѡ�����еĹؼ��ʻ��߳������еĹؼ��ʣ�\n��ѯ�����������İ�ɫ������ʾ"
					+ "������ʾ��\n�����ƣ�������������������ʾ��Χ������\n��ʾ��"),new JTextArea("    ���Ĺ��ܺͲ��ҹ������໥���ʹ�õ�"
							+ "����\n���ҵ����ǵ�ͼ��ʱ������ͨ������ճ������\n����ӵ����ĵ�ѡ���ѡ���������������\n����ʱ10�죬���"
							+ "���ġ�"),new JTextArea("    ���˿ռ���ʾ���ܣ�����ʾ���ߵ�������ר\nҵ��ѧ�š�������ж����Ѿ����ĵ�ͼ��"
									+ "\n���������Ҳ��������·��ṩ���鹦\n�ܣ����߸��ݱ�����Ϣ�ύ���顣"),new JTextArea(""
											+ "  ���������������ע��ʱ��д�����룬��¼\n�󷽿ɽ�����˿ռ�"),new JTextArea("    ע���¶����û�"
													+ "��д���߻�����Ϣ����Ҫ��\n�ύ���ݼ���"),new JTextArea("    ֻ��ӵ�й���Ա�˻������룬"
															+ "�ſ��Խ����\n̨��ϵͳ���й���"),new JTextArea("    ��java�������ղ��֡���Ƭʽ������ϰ����\nϤ"
																	+ "mysql������java+mysql(jdbc)������ϵͳ")};
	CardLayout card=new CardLayout();
	JPanel cardPanel=new JPanel();
	JPanel cpinfo[]={new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
	
	 public menu_help(){
		init();
		setLocation(700, 100);
		setVisible(true);
		setTitle("����");
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
		helpt.setToggleClickCount(1);//��굥������Ϊ1
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
