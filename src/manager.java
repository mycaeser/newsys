import javax.swing.*;
import java.awt.event.*;

public class manager extends JFrame implements ActionListener {
	JMenuBar menubar = new JMenuBar();
	JMenu meArray[] = { new JMenu("ͼ�����"), new JMenu("�˻�����"), new JMenu("����") };
	JMenuItem item[] = { new JMenuItem("����ͼ��"), new JMenuItem("ɾ��ͼ��"),new JMenuItem("ע���¹���Ա"),
			new JMenuItem("ɾ������Ա"), new JMenuItem("ɾ���û�"), new JMenuItem("������Ϣ") };
	gaintb jsp;//����࣬�����ݿ��ȡ���
	JScrollPane JSP;//�����������б���������jsp�����ȡ�ı��Ȼ����ӵ�����
	

	public manager() {//���캯��
		init();//��ʼ��
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
		setLayout(null);//��ʽ����Ϊ��
		setTitle("��̨��������");//���ñ���
		meArray[0].add(item[0]);//�˵���Ӳ���
		meArray[0].add(item[1]);
		meArray[1].add(item[2]);
		meArray[1].add(item[3]);
		meArray[1].add(item[4]);
		meArray[2].add(item[5]);
		for (int i = 0; i < meArray.length; i++) {
			menubar.add(meArray[i]);//�����ϲ�������˵���
		}
		setJMenuBar(menubar);//���˵������벼����
		addListener();//��Ӽ����ĺ���
	}

	public void addListener() {
		for (int i = 0; i < item.length; i++) {
			item[i].addActionListener(this);//���θ��˵������Ӽ���
		}
	}

	public void actionPerformed(ActionEvent e) {
		try {//e.getSource()Ϊ��Ӧ�¼����������Ϊ����Ӧ�¼�Ϊ�˵�����һ������
			if (e.getSource() == item[0]) {//����ͼ��
				new addook();
			} else if (e.getSource() == item[1]) {//ɾ��ͼ��
				new delete("books");
			} else if (e.getSource() == item[2]) {//ע���¹���Ա
				new register();
			} else if (e.getSource() == item[3]) {//ɾ������Ա
				new delete("admindt");
			}else if(e.getSource()==item[4]){//ɾ���û�
				new delete("readers");
			}
			else if(e.getSource()==item[5]){//����
				new adm_help();
			}
		} catch (Exception ee) {
			ee.printStackTrace();//�׳��쳣
		}
	}
}