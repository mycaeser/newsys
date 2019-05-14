import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class adm_help extends JFrame{
	//����
	DefaultMutableTreeNode helpRoot = new DefaultMutableTreeNode(new myNode("�˵�","0"));
	//һ��
	DefaultMutableTreeNode helpt1 = new DefaultMutableTreeNode("ͼ�����");
	DefaultMutableTreeNode helpt2 = new DefaultMutableTreeNode("�˻�����");
	
	//����
	DefaultMutableTreeNode helpt1_1 = new DefaultMutableTreeNode(new myNode("����ͼ��","1"));
	DefaultMutableTreeNode helpt1_2 = new DefaultMutableTreeNode(new myNode("ɾ��ͼ��","2"));
	DefaultMutableTreeNode helpt2_1 = new DefaultMutableTreeNode(new myNode("ע���¹���Ա","3"));
	DefaultMutableTreeNode helpt2_2 = new DefaultMutableTreeNode(new myNode("ɾ������Ա","4"));
	DefaultMutableTreeNode helpt2_3 = new DefaultMutableTreeNode(new myNode("ɾ���û�","5"));
	DefaultMutableTreeNode helpV_1 = new DefaultMutableTreeNode(new myNode("���","6"));
	//������
	JTree helpt=new JTree(helpRoot);
	JSplitPane jspl=new JSplitPane();
	JLabel lbarray[]={new JLabel("��ӭ�����̨����ϵͳ"),new JLabel("���ͼ�鹦��"),new JLabel("ɾ��ͼ�鹦��"),new JLabel("ע���¹���Ա"),new JLabel("ɾ������Ա"),
	new JLabel("ɾ���û�"),new JLabel("��̨���")};
	JTextArea jtarray[]={new JTextArea("    ��̨���������ɹ���Ա�������Զ�ͼ��\n����\"��ɾ�Ĳ�\"���Զ��ߺ͹���Ա�˻����й�\n�����������\n\n\n\t\tCaeser����\n\t\t2015/12/26"),
			new JTextArea("    ����ISBN�������������硢��棬�����\n�ӣ��Ϳ��Խ�������ͼ����ӵ����ݿ��ڣ�\n������κ˶���д���ݣ�������Ӵ����ظ�\n���"
			+ "ϵͳ�������ʾ��"),new JTextArea("    ɾ��ͼ�鹦�ܣ���һ��ͨ���๦�ܣ�������\n����ʾ�ı�񣬲��ҵ���Ҫ����ɾ�������鼮\n�ı�ţ�����ɾ��������"
					+ ""),new JTextArea("    ��д��Ҫ��Ϣ��ע���¹���Ա��ע�Ᵽ�ܺ�\n���롣"),new JTextArea("    ɾ������Ա������ɾ������һ����ʹ��ͨ��\n���࣬����ɾ����"),new JTextArea("    ͬ��"),
			new JTextArea("    Ϊ����Ա�ṩ��ݱ����Ĺ��������ð칫\n������")};
	CardLayout card=new CardLayout();
	JPanel cardPanel=new JPanel();
	JPanel cpinfo[]={new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
	
	 public adm_help(){
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
