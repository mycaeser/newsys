import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
public class select_borrow extends JFrame implements ActionListener{
	JLabel jlbarr[]={new JLabel("ID"),new JLabel("ISBN"),new JLabel("TITLE"),new JLabel("PUBLISH"),new JLabel("��ѡһ��ѯ")};
	JTextField jtfarr[]={new JTextField(),new JTextField(),new JTextField(),new JTextField()};
	JButton query[]={new JButton("��ѯ"),new JButton("ģ����ѯ")};
	JLabel vague=new JLabel("ģ������");
	String v_str[]={"�����ؼ���","������ؼ���"};
	JTextField v_search[]={new JTextField(v_str[0]),new JTextField(v_str[1])};//����ģ�����Һͳ�����ģ������
	connectdt jdbc = new connectdt();
	Connection conn = jdbc.conn;
	Statement stmt = jdbc.stmt;
	String sql=null,tabstr="";
	String bbnume[]={"����š�:","��ISBN��:","�����ơ�:","�������硿:","����桿:",""};
	JTextArea tabArea=new JTextArea();
	JScrollPane JSP = null;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// �������ڸ�ʽ
	String datestr=df.format(new Date());
	JComboBox comBox = new JComboBox();
	JLabel comb[]={new JLabel("��������"),new JLabel("��ȷ����Ҫ���ͼ������")};
	JTextField btme=new JTextField();
	JButton bbs=new JButton("����");
	String reader;
	
	public select_borrow(String p1){
		reader=p1;
		init();	
		addListener();
		setLocation(300, 150);
		setVisible(true);//���ô��ڿɼ�
		setSize(800, 400);
		setTitle("��ѯ/����");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//���ùرյ�ǰ����
		setResizable(false);//���ɵ������ڳߴ�
	}
	public void init(){
		setLayout(null);
		setText();
		for(int i=0;i<jlbarr.length;i++){
			jlbarr[i].setBounds(20+90*i, 10, 90, 30);
			add(jlbarr[i]);
		}
		for(int i=0;i<jtfarr.length;i++){
			jtfarr[i].setBounds(20+90*i, 40, 70, 30);
			add(jtfarr[i]);
		}
		query[0].setBounds(380, 40, 70, 30);
		add(query[0]);
		vague.setBounds(20, 80, 52, 30);
		add(vague);
		for(int i=0;i<v_search.length;i++){
			v_search[i].setBounds(80+i*150, 80, 120, 30);
			add(v_search[i]);
		}
		query[1].setBounds(380, 80, 90, 30);
		add(query[1]);
		for (int i = 1; i < 11; i++) {
			comBox.addItem(i);
		}
		comBox.setBounds(580, 80, 50, 30);
		add(comBox);
		comb[0].setBounds(520, 80, 52, 30);
		add(comb[0]);
		comb[1].setBounds(490,35,150,30);
		add(comb[1]);
		btme.setBounds(650, 35, 130, 30);
		add(btme);
		bbs.setBounds(650, 80, 60, 30);
		add(bbs);
		tabArea.setEditable(false);
		JSP = new JScrollPane(tabArea);//������ʹ�ù�����������ΪҪʹ�ù�����
		JSP.setBounds(10, 120, 780, 240);//������Ϊ��JTextArea������ʾ����ʱ
		add(JSP);//JTextArea���ܼ�ʱ��ʾ���ݣ�ʹ�ù���������ʵ�֣����ǽ�����ʾ������
	}
	public void setText(){
		    v_search[0].setForeground(Color.LIGHT_GRAY);
		    v_search[1].setForeground(Color.LIGHT_GRAY);
			v_search[0].addFocusListener(new FocusListener() {
				
				public void focusGained(FocusEvent e) {
					JTextField src = (JTextField) e.getSource();
					if (src.getText().equals(v_str[0])) {
						src.setText(null);
					}
				}
				public void focusLost(FocusEvent e) {
					JTextField src = (JTextField) e.getSource();
					if (src.getText().trim().isEmpty()) {
						src.setText(v_str[0]);
					}
				}
			});
			v_search[1].addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) {
					JTextField src = (JTextField) e.getSource();
					if (src.getText().equals(v_str[1])) {
						src.setText(null);
					}//����ʵ���ı���δ������Ϣʱ��ʾ��ʾ��Ϣ
				}
				public void focusLost(FocusEvent e) {
					JTextField src = (JTextField) e.getSource();
					if (src.getText().trim().isEmpty()) {
						src.setText(v_str[1]);
					}//���ı������ݱ����ʱ��ʾ��ʾ��Ϣ
				}
			});
		
	}
	public void addListener(){//��Ӽ�������
		for(int i=0;i<jtfarr.length;i++){
			jtfarr[i].addActionListener(this);
		}
		query[0].addActionListener(this);
		query[1].addActionListener(this);
		bbs.addActionListener(this);
		v_search[0].addActionListener(this);
		v_search[1].addActionListener(this);
		comBox.addActionListener(this);
		btme.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		try {
			
			if(e.getSource()==query[0]){
				String txstr[]=new String[4];
				for(int i=0;i<4;i++){
					txstr[i]=jtfarr[i].getText().trim();
				}
				if(txstr[0].equals("")&&txstr[1].equals("")&&txstr[2].equals("")&&txstr[3].equals("")){
					JOptionPane.showMessageDialog(null, "����ȫΪ��");
					return;
				}
				if(!txstr[0].equals("")){
					sql="select * from books where ID ="+txstr[0];
				    
				}else if(!txstr[1].equals("")){
					sql="select * from books where ISBN ='"+txstr[1]+"'";
				}else if(!txstr[2].equals("")){
					sql="select * from books where TITLE ='"+txstr[2]+"'";
				}else if(!txstr[3].equals("")){
					sql="select * from books where PUBLISH ='"+txstr[3]+"'";
				}
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					
					ResultSetMetaData rsmd = rs.getMetaData();
					int column = rs.getMetaData().getColumnCount();//�������
					do{
						tabstr=tabstr+bbnume[0];
						for (int i = 0; i < column; i++) {//��ȡ����ͷ��ÿһ��
							tabstr=tabstr+rs.getString(i + 1)+bbnume[i+1];
						}
						tabstr=tabstr+"\n";
					}while(rs.next());
					tabArea.setText(tabstr);
					JSP.add(tabArea);
					tabstr="";//�ÿ��ַ�����ʹ��һ���������
					
				}else{
					JOptionPane.showMessageDialog(null, "���벻����");
					return;
				}
			}else if(e.getSource()==query[1]){
				String vastr[]=new String[2];
				for(int i=0;i<2;i++){
					vastr[i]=v_search[i].getText().trim();
				}
				if(v_str[0].equals(vastr[0])&&v_str[1].equals(vastr[1])){
					JOptionPane.showMessageDialog(null, "������ؼ���");
					return;
				}
				if(!v_str[0].equals(vastr[0])){
					sql="select * from books where TITLE like '%"+vastr[0]+"%';";
				}else if(!v_str[1].equals(vastr[1])){
					sql="select * from books where PUBLISH like '%"+vastr[1]+"%';";
				}
				
				ResultSet rs = stmt.executeQuery(sql);
				if(rs.next()){
					
					ResultSetMetaData rsmd = rs.getMetaData();
					int column = rs.getMetaData().getColumnCount();//�������
					do{
						tabstr=tabstr+bbnume[0];
						for (int i = 0; i < column; i++) {//��ȡ����ͷ��ÿһ��
							tabstr=tabstr+rs.getString(i + 1)+bbnume[i+1];
						}
						tabstr=tabstr+"\n";
					}while(rs.next());
					tabArea.setText(tabstr);
					JSP.add(tabArea);
					tabstr="";//�������һ�����ظ����Ҹ�����Ϊ���������ʸ�
				}else{
					JOptionPane.showMessageDialog(null, "���벻����");
					return;
				}
			}else 
				if(e.getSource()==bbs){
				String brid=btme.getText();
				if(reader==null){
					JOptionPane.showMessageDialog(null, "����û��¼��");
					return;
				}
				if(brid.isEmpty()){
					JOptionPane.showMessageDialog(null, "����������");
					return;
				}else{
					sql="select * from books where TITLE='"+brid+"' and STOCK>0";//��ѯ�����Ƿ������ݿ��ﲢ�ҿ�����0
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()){
					int day=(int) comBox.getSelectedItem();
					String prompt="��������"+brid+day+"��,Ը������Ķ�";
					sql="insert into borrow(book,reader,borrow_date,return_date)values('"+brid+"','"+reader+"',NOW(),date_add(NOW(),interval "+day+" day));";
					stmt.executeUpdate(sql);
					sql="update books set STOCK=STOCK-1 where TITLE='"+brid+"';";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, prompt);
					return;
					}else{
						JOptionPane.showMessageDialog(null, "�����ڻ����޿��");
						return;
					}
				}
			}
			
		}catch (Exception ee) {
			ee.printStackTrace();// �׳��쳣
		}
	}
}
