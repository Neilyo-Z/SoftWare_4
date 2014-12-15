package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class WelcomeWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblId;
	private JLabel lblNewLabel;
	private JLabel lblDoYouNot;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeWindow frame = new WelcomeWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public WelcomeWindow() {
		//固定窗口大小
		setResizable(false);
		
		setTitle("欢迎使用路径查询系统");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		TitledBorder panelBorderTitle = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u767B\u5F55", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0));
		panel.setBorder(panelBorderTitle);
		panelBorderTitle.setTitleFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.setBounds(271, 10, 153, 178);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 49, 133, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		lblId = new JLabel("用户名 ：");
		lblId.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblId.setBounds(10, 24, 133, 15);
		panel.add(lblId);
		
		lblNewLabel = new JLabel("密码 ：");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 80, 133, 15);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Go!");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 136, 133, 23);
		panel.add(btnNewButton);
		
		lblDoYouNot = new JLabel("");
		lblDoYouNot.setBounds(10, 169, 133, 15);
		panel.add(lblDoYouNot);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 105, 133, 21);
		panel.add(passwordField);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 249, 242);
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_1 = new JLabel("路径查询系统");
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_1, BorderLayout.CENTER);
		
		lblNewLabel_2 = new JLabel("作者：朱维希 丁文韬 李昊轩");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblNewLabel_2, BorderLayout.SOUTH);
		
		btnNewButton_1 = new JButton("新用户注册");
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.setBounds(271, 202, 153, 23);
		contentPane.add(btnNewButton_1);
	}
}
