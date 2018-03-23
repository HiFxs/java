package java_work;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mysql.jdbc.Statement;

public class ATM_account {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM_account window = new ATM_account(ATM.iD);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ATM_account(String id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String id) {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 16));
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\workspace\\java test\\1111588.jpg"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("\u8D26\u6237\u6E05\u5355");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 16));
		btnNewButton.setBounds(27, 10, 104, 23);
		frame.getContentPane().add(btnNewButton);

		JButton button = new JButton("\u53D6\u6B3E");
		button.setFont(new Font("����", Font.PLAIN, 16));
		button.setBounds(27, 61, 93, 23);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("\u884C\u4E3A\u8BB0\u5F55");
		button_1.setFont(new Font("����", Font.PLAIN, 16));
		button_1.setBounds(276, 11, 104, 23);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("\u5B58\u6B3E");
		button_2.setFont(new Font("����", Font.PLAIN, 16));
		button_2.setBounds(287, 62, 93, 23);
		frame.getContentPane().add(button_2);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(442, 101, -436, 161);
		frame.getContentPane().add(scrollPane);
		
		TextArea jta = new TextArea();
		jta.setBounds(0, 102, 440, 170);
		frame.getContentPane().add(jta);
		
		JButton button_3 = new JButton("\u8F6C\u8D26");
		button_3.setFont(new Font("����", Font.PLAIN, 19));
		button_3.setBounds(155, 62, 93, 23);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u9000\u51FA");
		button_4.setFont(new Font("����", Font.PLAIN, 19));
		button_4.setBounds(155, 11, 93, 23);
		frame.getContentPane().add(button_4);

		

		// �˻��嵥
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				JDBC j = new JDBC();
				PreparedStatement st = null;
				try {
					st = JDBC.con.prepareStatement("select*from user where ID=?");
				} catch (SQLException e2) {
					// TODO �Զ����ɵ� catch ��
					e2.printStackTrace();
				}
				try {
					st.setString(1, ATM.iD);
					ResultSet rs = st.executeQuery();
					String str = "������ ";
					while (rs.next()) {
						str += rs.getString("name");
						str += "\t" + "ID: " + rs.getString("ID");
						str += "\t" + "������: " + rs.getString("annualInterestRate");
						str += "\t" + "���:  " + rs.getString("balance")+" Ԫ";
					}
					jta.setText(str);

				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}

			}
		});

		// ȡ��
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������

				JDBC j = new JDBC();
				PreparedStatement st = null;
				try {
					st = JDBC.con.prepareStatement("select*from user where ID=?");
				} catch (SQLException e2) {
					// TODO �Զ����ɵ� catch ��
					e2.printStackTrace();
				}
				try {
					st.setString(1, ATM.iD);
					ResultSet rs = st.executeQuery();
					String balance = null;
					String ID = null;
					String note = null;
					while (rs.next()) {
						balance = rs.getString("balance");
						ID = rs.getString("ID");
						if (ID.equals(ATM.iD)) {
							note = rs.getString("notepad");
							rs.close();
							st.close();
							break;
						}
					}
					while (true) {
						String message = withdraw("������ȡ���");
						double number = Double.valueOf(message);
						double num = Double.valueOf(balance);
						if (number > num) {
							JOptionPane.showMessageDialog(null, "�˻����㣬����������!");
							continue;
						} else {
							JOptionPane.showMessageDialog(null, "ȡ��ɹ���");
							num = num - number;
							Double n = Double.valueOf(num);
							String m = n.toString();
							PreparedStatement stt = JDBC.con.prepareStatement("update user set balance=? where ID=?");
							stt.setString(1, m);
							stt.setString(2, ATM.iD);
							stt.executeUpdate();
							SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
							note += "\n" + "ʱ��:  " + (new Date()) + "    ȡ� " + message+" Ԫ";
							PreparedStatement sttt = JDBC.con.prepareStatement("update user set notepad=? where ID=?");
							sttt.setString(1, note);
							sttt.setString(2, ATM.iD);
							sttt.executeUpdate();
							break;
						}
					}
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});

		// ��Ϊ��¼
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				jta.setText(null);
				JDBC j = new JDBC();
				try {
					java.sql.Statement st = JDBC.con.createStatement();
					ResultSet rs = st.executeQuery("select*from user");
					String strr = null;
					String p = null;
					while (rs.next()) {
						p = rs.getString("ID");
						if (p.equals(ATM.iD)) {
							strr = rs.getString("notepad");
							break;
						}
					}
					jta.setText(strr);
					System.out.println(rs.getString("notepad"));
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
			}
		});

		// ���
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				JDBC j = new JDBC();
				PreparedStatement st = null;
				try {
					st = JDBC.con.prepareStatement("select*from user where ID=?");
				} catch (SQLException e2) {
					// TODO �Զ����ɵ� catch ��
					e2.printStackTrace();
				}
				try {
					st.setString(1, ATM.iD);
					ResultSet rs = st.executeQuery();
					String balance = null;
					String ID = null;
					String note = null;
					while (rs.next()) {
						balance = rs.getString("balance");
						ID = rs.getString("ID");
						if (ID.equals(ATM.iD)) {
							note = rs.getString("notepad");
							rs.close();
							st.close();
							break;
						}
					}
					while (true) {
						String message = deposit("���������");
						double numm=Double.valueOf(message);
						if(numm>0){
						JOptionPane.showMessageDialog(null, "���ɹ���");}
						double number = Double.valueOf(message);
						double num = Double.valueOf(balance);
						num = num + number;
						Double n = Double.valueOf(num);
						String m = n.toString();
						PreparedStatement stt = JDBC.con.prepareStatement("update user set balance=? where ID=?");
						stt.setString(1, m);
						stt.setString(2, ATM.iD);
						stt.executeUpdate();
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// �������ڸ�ʽ
						note += "\n" + "ʱ��:  " + (new Date()) + "    �� " + message+" Ԫ";
						PreparedStatement sttt = JDBC.con.prepareStatement("update user set notepad=? where ID=?");
						sttt.setString(1, note);
						sttt.setString(2, ATM.iD);
						sttt.executeUpdate();
						break;
					}
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}

			}
		});
		
		
		//ת��
		button_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				String message=change("�������ת��ID");
				JDBC j=new JDBC();
				try {
					java.sql.Statement  st=JDBC.con.createStatement();
					ResultSet rs=st.executeQuery("select*from user");
					String id=null;
					String b=null;
					String z=null;
					String o=null;
					String k1=null;
					String k2=null;
					while(rs.next()){
						id=rs.getString("ID");
						if(id.equals(message)){
							b=rs.getString("balance");
							z=zj("������ת�˽�");
							k1=rs.getString("notepad");
						}
						if(id.equals(ATM.iD)){
							o=rs.getString("balance");
							k2=rs.getString("notepad");
						}
					}
					
					if(b!=null){
						System.out.println(o+b+z);
						double n5=Double.valueOf(o);
						double n1=Double.valueOf(b);
						double n2=Double.valueOf(z);
						double n3=n1+n2;
						double n6=n5-n2;
						Double n7=Double.valueOf(n6);
						Double n4=Double.valueOf(n3);
						String h=n4.toString();
						String hh=n7.toString();
						if(n5>=n2){
						PreparedStatement s=JDBC.con.prepareStatement("update user set balance=? where ID=?");
						String k3="\n"+"ʱ�䣺 "+(new Date())+"ת�룺 "+z+" Ԫ";
						k1+=k3;
						s.setString(1, h);
						s.setString(2, message);
						s.executeUpdate();
						PreparedStatement s1=JDBC.con.prepareStatement("update user set notepad=? where ID=?");
						s1.setString(1, k1);
						s1.setString(2, message);
						s1.executeUpdate();
						PreparedStatement tt=JDBC.con.prepareStatement("update user set balance=? where ID=?");
						String k4="\n"+"ʱ�䣺"+(new Date())+"ת����"+z+"Ԫ";
						k2+=k4;
						tt.setString(1, hh);
						tt.setString(2, ATM.iD);
						tt.executeUpdate();
						PreparedStatement t1=JDBC.con.prepareStatement("update user set notepad=? where ID=?");
						t1.setString(1, k2);
						t1.setString(2, ATM.iD);
						t1.executeUpdate();
						JOptionPane.showMessageDialog(null, "ת�˳ɹ���");
						}else{
							JOptionPane.showMessageDialog(null, "��ǰ���㣬�����²�����");
						}
					}else{
						
						JOptionPane.showMessageDialog(null,"�Բ���û���ҵ����û������������룡");	
					}
					
					
				} catch (SQLException e1) {
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				}
				
				
				
			}
		});
		
		//�˳�
		button_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO �Զ����ɵķ������
				System.exit(0);
			}
		});
		
		
		

	}

	public static String withdraw(String prompt) {

		return JOptionPane.showInputDialog(prompt);

	}

	public static String deposit(String prompt) {

		return JOptionPane.showInputDialog(prompt);

	}
	public static String  change(String prompt) {

		return JOptionPane.showInputDialog(prompt);

	}
	public static String  zj(String prompt) {

		return JOptionPane.showInputDialog(prompt);

	}
}
