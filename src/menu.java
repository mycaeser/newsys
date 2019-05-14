import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
public class menu extends JFrame implements ActionListener {
	JMenuBar menubar = new JMenuBar();
	JMenu meArray[] = { new JMenu("���ù���"), new JMenu("��������"), new JMenu("ϵͳά��"), new JMenu("����") };
	JMenuItem item[] = { new JMenuItem("���ͼ��",new ImageIcon("image/browse.jpg")), new JMenuItem("����ͼ��",new ImageIcon("image/query.jpg")), new JMenuItem("�ҵĿռ�",new ImageIcon("image/user.jpg")),new JMenuItem("��¼",new ImageIcon("image/login.jpg")), new JMenuItem("ע��",new ImageIcon("image/key.jpg")),
			new JMenuItem("��̨����",new ImageIcon("image/admin.jpg")), new JMenuItem("������Ϣ",new ImageIcon("image/help.jpg")) };
	gaintb jsp;
	JScrollPane JSP;
	JPanel jpl;
	JLabel bgimg=new JLabel(new ImageIcon("image\\MAIN.jpg"));//��ʼ����
	String namep=null;
	 CardLayout card=new CardLayout();
	 JPanel cardPanel=new JPanel();
	 JLabel titback=new JLabel("ѡ��������");
	 JButton suback=new JButton("����");
	 JTextField bid=new JTextField();
	 
	 String sql="";
	 connectdt jdbc = new connectdt();//�������ݿ���
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
				JOptionPane.showMessageDialog(null, "���Ƚ��ɷ���");
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
		setTitle("ͼ�����ϵͳ");
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
			if (e.getSource() == item[0]) {//���ͼ��
				card.show(cardPanel,"browse");
				
			} else if (e.getSource() == item[1]) {//����ͼ��
				new select_borrow(namep);
			} else if (e.getSource() == item[2]) {//�ռ�
				if(namep==null){
					JOptionPane.showMessageDialog(null, "����δ��¼��");
					return;
				}else{
					card.show(cardPanel,"zone");
				}
			} else if (e.getSource() == item[3]) {//��¼
				new login("readers","name","code","�û���¼");
				dispose();
			}else if(e.getSource()==item[4]){//ע��
				new user_regist();
			}else if(e.getSource()==item[5]){//��̨
				new login("admindt","username","password","����Ա��¼");
				dispose();
			}
			else if(e.getSource()==item[6]){//����
				new menu_help();
			}else if(e.getSource()==suback){//����
				
				String temp=bid.getText();
				if(temp.equals("")){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					return;
				}
				sql="select * from borrow where book='"+temp+"'";
				ResultSet rs = stmt.executeQuery(sql);
				if(!rs.next()){
					JOptionPane.showMessageDialog(null, "���벻����");
					return;
				}
				sql="delete from borrow where book='"+temp+"'";
				stmt.executeUpdate(sql);
				sql="update books set STOCK=STOCK+1 where TITLE='"+temp+"';";
				stmt.executeUpdate(sql);
				sql="";
				JOptionPane.showMessageDialog(null, "����ɹ�");
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}
	}
}