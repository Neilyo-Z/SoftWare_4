package client;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Font;

public class MainWindow extends JFrame {

	public static String id;
	public static String pw;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
					//令窗体在屏幕中间显示
					frame.setLocationRelativeTo(null);
					
					//设置关闭窗口时结束程序
					frame.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
					
					//创建窗口关闭监听器 在关闭之前自动进行登出操作
					frame.addWindowListener(new WindowAdapter() {   
					    public void windowClosing(WindowEvent e) {  
						    super.windowClosing(e);  
						    try{
							    Logout.main(id, pw);
						    }
						    catch(Exception event_logout){
								UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
	                    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
	                    		JOptionPane.showMessageDialog(null, event_logout.getMessage(), "登出失败", JOptionPane.ERROR_MESSAGE);
						    }
				     }  
				      
				    });   
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		//固定窗口大小
		setResizable(false);

		setTitle(id+" 您好，欢迎您使用路径查询");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 794, 21);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu(" 用户 ");
		mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("查看/修改个人资料  ");
		mntmNewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						EditProfile.id = id;
						EditProfile.pw = pw;
						EditProfile.main(null);
					}
				}
				);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("切换账号");
		mntmNewMenuItem_2.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e2) {
						try{
							//登出并回到登录界面
							Logout.main(id, pw);
							WelcomeWindow.main(null);
							dispose();	
						}
						catch(Exception event_logout){
							UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		JOptionPane.showMessageDialog(null, event_logout.getMessage(), "登出失败", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("退出");
		mntmNewMenuItem_3.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e3) {
						try{
							//登出并结束程序
							Logout.main(id, pw);
							System.exit(0);
						}
						catch(Exception event_logout){
							UIManager.put("OptionPane.messageFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		UIManager.put("OptionPane.buttonFont",new Font("微软雅黑", Font.PLAIN, 12));
                    		JOptionPane.showMessageDialog(null, event_logout.getMessage(), "登出失败", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
				);
		
		JMenu mnNewMenu_1 = new JMenu(" 帮助 ");
		mnNewMenu_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("关于我们    ");
		mntmNewMenuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		
	}
}
