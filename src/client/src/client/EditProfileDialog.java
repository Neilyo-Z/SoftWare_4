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

public class EditProfileDialog extends JDialog {
	public static String id;
	public static String pw;
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
		mailBox = "rongmeme@huanzhugege.com";
		sex = "female";
		
		if(socketRecvFlag < 0) 
			throw new Exception ("无法建立连接！");
	}
	private void SendProfile() throws Exception {
		
		//个人资料发送是否成功的标志
		int socketSendFlag;
		//用户名和密码是否正确的标志
		boolean pwIsRight;
		
		pwIsRight = true;
		socketSendFlag = 1;
		System.out.println("发送用户资料：");
		System.out.println(nickName);
		System.out.println(mailBox);
		System.out.println(sex);
		System.out.println("id: "+id);
		System.out.println("pw: "+pw);
		
		if( socketSendFlag < 0)
			throw new Exception ("无法建立连接！");
		if( pwIsRight == false )
			throw new Exception ("用户名或密码不正确！");
	}
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_nickname;
	private JTextField textField_mailbox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EditProfileDialog dialog = new EditProfileDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
			//令窗体在屏幕中间显示
			dialog.setLocationRelativeTo(null);			
		} catch (Exception e) {			 
				e.printStackTrace();
		}
		
	}

	/**
	 * Create the dialog.
	 */
	public EditProfileDialog() {
		
		try{
			GetProfile();
		}
		//报告无法获取个人资料的错误
		catch (Exception socketRecvException){
			UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
    		JOptionPane.showMessageDialog(null, socketRecvException.getMessage(), "获取个人资料失败", JOptionPane.ERROR_MESSAGE);
		}
		
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
		
		if(sex == "male"){
			rdbtnNewRadioButton.setSelected(true);
			rdbtnNewRadioButton_1.setSelected(false);
		}
		else if (sex == "female"){
			rdbtnNewRadioButton.setSelected(false);
			rdbtnNewRadioButton_1.setSelected(true);
		}
			
		
		textField_nickname = new JTextField();
		textField_nickname.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_nickname.setBounds(112, 51, 226, 21);
		contentPanel.add(textField_nickname);
		textField_nickname.setColumns(10);
		textField_nickname.setText(nickName);
		
		textField_mailbox = new JTextField();
		textField_mailbox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		textField_mailbox.setBounds(112, 165, 226, 21);
		contentPanel.add(textField_mailbox);
		textField_mailbox.setColumns(10);
		textField_mailbox.setText(mailBox);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认修改");
				okButton.setFont(new Font("微软雅黑", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(
						new ActionListener(){
							public void actionPerformed(ActionEvent event_ok) {
								//读取用户输入
								nickName = textField_nickname.getText();
								mailBox = textField_mailbox.getText();
								if (rdbtnNewRadioButton.isSelected() == true && rdbtnNewRadioButton_1.isSelected() == false )
									sex = "male";
								else if (rdbtnNewRadioButton.isSelected() == false && rdbtnNewRadioButton_1.isSelected() == true )
									sex = "female";
								else 
									sex = null;
								//尝试发送用户资料
								try{
									SendProfile();
									dispose();
								}
								catch(Exception sendException){
									UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
						    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
						    		JOptionPane.showMessageDialog(null, sendException.getMessage(), "上传个人资料失败", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
						);
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
