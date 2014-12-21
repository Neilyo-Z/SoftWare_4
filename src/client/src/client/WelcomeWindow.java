package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
					//令窗体在屏幕中间显示
					frame.setLocationRelativeTo(null);
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
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
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
		TitledBorder panelBorderTitle = 
				new TitledBorder(
						UIManager.getBorder("TitledBorder.border"),
						"\u767B\u5F55", 
						TitledBorder.LEADING, 
						TitledBorder.TOP, 
						null, 
						new Color(0, 0, 0));
		panel.setBorder(panelBorderTitle);
		panelBorderTitle.setTitleFont(new Font("微软雅黑", Font.PLAIN, 12));
		panel.setBounds(271, 10, 153, 242);
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
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 161, 133, 23);
		panel.add(btnNewButton);
		//登录按钮的事件监听
		btnNewButton.addActionListener(
				new ActionListener(){  
                    public void actionPerformed(ActionEvent event_L) {  
                    	if(textField.getText().length() < 1 ||  passwordField.getPassword().length < 1){
                    		UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		JOptionPane.showMessageDialog(null, "用户名或密码不能为空", "你tm在逗我？", JOptionPane.ERROR_MESSAGE);
                    	}
                    	else{
                    		try {
                    			Login login = new Login();
                    			login.main(textField.getText(), passwordField.getPassword());
                    			//登录成功后关闭登录窗口 释放资源
                    			dispose();
                    		}
                    		//对于没发成功的弹窗通知
                    		catch (Exception e1) {
                    			UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
                        		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
                        		JOptionPane.showMessageDialog(null, e1.getMessage(), "登录失败", JOptionPane.ERROR_MESSAGE);
                    		}
                    	}
                    }  
                });
		
		lblDoYouNot = new JLabel("");
		lblDoYouNot.setBounds(10, 169, 133, 15);
		panel.add(lblDoYouNot);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 105, 133, 21);
		panel.add(passwordField);
		
		btnNewButton_1 = new JButton("注册");
		btnNewButton_1.setBounds(10, 194, 133, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		//注册按钮的事件监听
		btnNewButton_1.addActionListener(
				new ActionListener(){  
                    public void actionPerformed(ActionEvent event_R) { 
                    	if(textField.getText().length() < 1 ||  passwordField.getPassword().length < 1){
                    		UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		JOptionPane.showMessageDialog(null, "用户名或密码不能为空", "你tm在逗我？", JOptionPane.ERROR_MESSAGE);
                    	}
                    	else{
                    		try{
	                    		Register register = new Register();
	                    		register.main(textField.getText(), passwordField.getPassword());
	                    		//注册成功后登录 并关闭登录窗口 释放资源
	                    		Login login = new Login();
	                			login.main(textField.getText(), passwordField.getPassword());
	                			dispose();
                    		}
                    		catch(Exception e1) {
                    			UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
                        		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
                        		JOptionPane.showMessageDialog(null, e1.getMessage(), "登录失败", JOptionPane.ERROR_MESSAGE);
                    		}
                    	} 
                    }  
                });
		
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
	}
}
