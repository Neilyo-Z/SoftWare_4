package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.ButtonGroup;

public class EditProfile extends JDialog {

	//定义个人资料内容
	private String nickName;
	private String mailBox;
	private String sex;
	
	//定义获取个人资料的方法
	private void GetProfile() throws Exception {
		//个人资料接收是否成功的标志
		int socketRecvFlag;
		
		socketRecvFlag = 1;
		nickName = "容嬷嬷";
		mailBox = "rongmeme@gmail.com";
		sex = "female";
		
		if(socketRecvFlag < 0) 
			throw new Exception ("无法建立连接！");
	}
	
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField textField_nickname;
	private JTextField textField_mailbox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditProfile dialog = new EditProfile();
			dialog.GetProfile();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			//报告无法获取个人资料的错误  一般是由于socket无法recv
			if( e.getMessage() == "无法建立连接！"){
				UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
	    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
	    		JOptionPane.showMessageDialog(null, "无法建立连接！", "获取个人资料失败", JOptionPane.ERROR_MESSAGE);
			}
			else {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * Create the dialog.
	 */
	public EditProfile() {
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setTitle("查看/编辑个人资料");
		
		//设置为模式窗口 其他窗口不被激活
		setModal(true);
		
		//固定窗口大小
		setResizable(false);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("昵称：");
			lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			lblNewLabel.setBounds(59, 29, 54, 15);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("性别：");
			lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(59, 81, 54, 15);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("邮箱：");
			lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(59, 133, 54, 15);
			contentPanel.add(lblNewLabel_2);
		}
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("男");
		rdbtnNewRadioButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		rdbtnNewRadioButton.setBounds(112, 109, 45, 23);
		contentPanel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("女");
		rdbtnNewRadioButton_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		rdbtnNewRadioButton_1.setBounds(217, 109, 121, 23);
		contentPanel.add(rdbtnNewRadioButton_1);
		
		//将RadioButton加入ButtonGroup实现互斥
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		
		textField_nickname = new JTextField();
		textField_nickname.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_nickname.setBounds(112, 51, 226, 21);
		contentPanel.add(textField_nickname);
		textField_nickname.setColumns(10);
		
		textField_mailbox = new JTextField();
		textField_mailbox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_mailbox.setBounds(112, 165, 226, 21);
		contentPanel.add(textField_mailbox);
		textField_mailbox.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JLabel label = new JLabel("请输入密码： ");
			label.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			label.setHorizontalAlignment(SwingConstants.LEFT);
			buttonPane.add(label);
			
			passwordField = new JPasswordField();
			passwordField.setColumns(15);
			passwordField.setFont(new Font("微软雅黑", Font.PLAIN, 12));
			buttonPane.add(passwordField);
			{
				JButton okButton = new JButton("确认修改");
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("我就看看");
				cancelButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(
						new ActionListener(){
							public void actionPerformed(ActionEvent event_cancel) {
								dispose();
							}
						}
						);
			}
		}
	}
}
