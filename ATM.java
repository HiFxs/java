package java_work;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Label;
import javax.swing.JPasswordField;

public class ATM {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	public static String iD;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATM window = new ATM();
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
	public ATM() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(
				Toolkit.getDefaultToolkit().getImage("C:\\Users\\Administrator\\workspace\\java test\\1111588.jpg"));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		JPanel panel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -2748426784349171958L;

			public void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon(
						"C:\\Users\\Administrator\\AppData\\LocalLow\\SogouWP\\Net\\WallPaper\\1111588.jpg");
				// 图片随窗体大小而变化
				g.drawImage(icon.getImage(), 0, 0, frame.getSize().width, frame.getSize().height, frame);
			}

		};
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// 开头标签
		JLabel lblatm = new JLabel("               \u6B22\u8FCE\u4F7F\u7528ATM                ");
		lblatm.setForeground(new Color(255, 0, 0));
		lblatm.setFont(new Font("仿宋", Font.PLAIN, 20));
		lblatm.setBounds(10, 30, 424, 15);
		panel.add(lblatm);

		JLabel lblid = new JLabel("             \u8BF7\u8F93\u5165\u4F60\u7684ID\u548C\u5BC6\u7801");
		lblid.setFont(new Font("仿宋", Font.PLAIN, 16));
		lblid.setBounds(40, 88, 347, 15);
		panel.add(lblid);

		// ID框
		textField = new JTextField();
		textField.setBounds(184, 113, 66, 21);
		panel.add(textField);
		textField.setColumns(10);

		JButton button = new JButton("\u786E\u8BA4");
		button.setFont(new Font("仿宋", Font.PLAIN, 16));
		button.setBounds(61, 202, 93, 23);
		panel.add(button);

		JButton button_1 = new JButton("\u91CD\u65B0\u8F93\u5165");
		button_1.setFont(new Font("仿宋", Font.PLAIN, 16));
		button_1.setBounds(299, 202, 113, 23);
		panel.add(button_1);

		Label label = new Label("            ID:");
		label.setBounds(113, 111, 69, 23);
		panel.add(label);

		Label label_1 = new Label("PASSWORD:\r\n");
		label_1.setBounds(89, 140, 93, 23);
		panel.add(label_1);

		JButton btnNewButton = new JButton("\u65B0\u5EFA\u7528\u6237");
		btnNewButton.setFont(new Font("仿宋", Font.PLAIN, 16));
		btnNewButton.setBounds(174, 203, 115, 23);
		panel.add(btnNewButton);

		textField_1 = new JTextField();
		textField_1.setBounds(184, 144, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		// 新建用户
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				frame.dispose();
				new_account newpeople = new new_account();
			}
		});

		// 确认按钮
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				// 获取当前iD和密码
				String di = textField.getText();
				String pa = textField_1.getText();
				JDBC j = new JDBC();
				try {
					if (!JDBC.con.isClosed()) {
						Statement statement = (Statement) JDBC.con.createStatement();
						String sql = "select*from user";

						ResultSet rs = statement.executeQuery(sql);
						System.out.println("-----------------");
						System.out.println("执行结果如下所示:");
						System.out.println("-----------------");
						System.out.println("姓名" + "\t" + "ID" + "\t" + "balance");
						System.out.println("-----------------");
						String name = null;
						String ID = null;
						String ban = null;
						String ps = null;
						while (rs.next()) {
							name = rs.getString("name");
							ID = rs.getString("ID");
							ps = rs.getString("password");
							ban = rs.getString("balance");
							if (ID.equals(di) && ps.equals(pa)) {
								rs.close();
								JDBC.con.close();
								frame.dispose();
								JOptionPane.showMessageDialog(null, "登陆成功！");
								iD = ID;
								ATM_account people = new ATM_account(iD);
								break;
							}
							System.out.println(name + "\t" + ID + "\t" + ps + "\t" + ban);
							System.out.println(di + "\t" + pa);
						}
						// 登陆失败
						if (!JDBC.con.isClosed()) {
							JOptionPane.showMessageDialog(null, "ID或password错误请重新输入！");
							textField.setText(null);
							textField_1.setText(null);
						}

					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});

		// 清除按钮
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				textField.setText(null);
				textField_1.setText(null);
				panel.updateUI();
			}
		});
	}
}
