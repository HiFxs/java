package java_work;

import java.awt.EventQueue;
import java.awt.Graphics;
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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import javax.swing.JPasswordField;

public class new_account {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new_account window = new new_account();
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
	public new_account() {
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		JPanel panel = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon(
						"C:\\Users\\Administrator\\AppData\\LocalLow\\SogouWP\\Net\\WallPaper\\1111588.jpg");
				// 图片随窗体大小而变化
				g.drawImage(icon.getImage(), 0, 0, frame.getSize().width, frame.getSize().height, frame);
			}

		};

		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel label = new JLabel("      \u65B0\u5EFA\u7528\u6237");
		label.setFont(new Font("仿宋", Font.PLAIN, 20));
		label.setBounds(83, 29, 203, 24);
		panel.add(label);

		JLabel label_1 = new JLabel("\u59D3\u540D");
		label_1.setBounds(53, 78, 54, 15);
		panel.add(label_1);

		JLabel lblId = new JLabel("    ID\uFF1A");
		lblId.setFont(new Font("宋体", Font.PLAIN, 14));
		lblId.setBounds(29, 113, 63, 15);
		panel.add(lblId);

		JLabel lblPassword = new JLabel("password:");
		lblPassword.setFont(new Font("宋体", Font.PLAIN, 14));
		lblPassword.setBounds(29, 138, 63, 15);
		panel.add(lblPassword);

		// 姓名
		textField = new JTextField();
		textField.setBounds(144, 75, 66, 21);
		panel.add(textField);
		textField.setColumns(10);

		// ID
		textField_1 = new JTextField();
		textField_1.setBounds(144, 110, 66, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		// 确认
		JButton btnNewButton = new JButton("\u786E\u8BA4");
		btnNewButton.setFont(new Font("仿宋", Font.PLAIN, 16));
		btnNewButton.setBounds(40, 197, 93, 23);
		panel.add(btnNewButton);

		// 撤销
		JButton button = new JButton("\u64A4\u9500");
		button.setFont(new Font("仿宋", Font.PLAIN, 16));
		button.setBounds(239, 197, 93, 23);
		panel.add(button);

		textField_2 = new JTextField();
		textField_2.setBounds(144, 138, 66, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				String idd = textField_1.getText();
				if (isexit(idd)) {
				    JOptionPane.showMessageDialog(null, "该用户已经存在，请重新输入！");
				    textField_1.setText(null);
				}else{
				JDBC j = new JDBC();
				PreparedStatement st = null;
				try {
					st = JDBC.con.prepareStatement(
							"insert into user(name,ID,password,annualInterestRate,balance,notepad) values(?,?,?,'0.025','0',?)");
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}

				try {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
					st.setString(1, textField.getText());
					st.setString(2, textField_1.getText());
					st.setString(3, textField_2.getText());
					Date date = new Date();
					String s = "开户日期：   " + date.toString();
					st.setString(4, s);
					st.executeUpdate();
					JOptionPane.showMessageDialog(null, "新建用户成功！");
					frame.dispose();
					ATM window = new ATM();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				}
			}
		});
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
			}
		});
	}

	public boolean isexit(String i) {
		Boolean is = false;
		JDBC j = new JDBC();
		try {
			if (!JDBC.con.isClosed()) {
				Statement statement = (Statement) JDBC.con.createStatement();
				String sql = "select*from user";
				ResultSet rs = statement.executeQuery(sql);
				String id = textField_1.getText();
				String ID = null;
				while (rs.next()) {
					ID = rs.getString("ID");
					if (id.equals(ID)) {
						is = true;
					}
				}
			}
		} catch (SQLException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}

		return is;
	}

}
